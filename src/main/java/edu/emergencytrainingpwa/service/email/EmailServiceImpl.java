package edu.emergencytrainingpwa.service.email;

import edu.emergencytrainingpwa.constant.AppConstant;
import edu.emergencytrainingpwa.dao.repository.UserRepo;
import edu.emergencytrainingpwa.exception.WrongEmailException;
import jakarta.mail.MessagingException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import jakarta.mail.internet.MimeMessage;
import org.springframework.stereotype.Service;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.Context;


@Slf4j
@Service
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender javaMailSender;
    private final ITemplateEngine templateEngine;
    private final Executor executor;
    private final String senderEmailAddress;
    private final MessageSource messageSource;
    private final String clientLink;

    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender,
                            ITemplateEngine templateEngine,
                            @Qualifier("sendEmailExecutor") Executor executor,
                            @Value("${client.address}") String clientLink,
                            @Value("${sender.email.address}") String senderEmailAddress,
                            MessageSource messageSource) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
        this.executor = executor;
        this.clientLink = clientLink;
        this.senderEmailAddress = senderEmailAddress;
        this.messageSource = messageSource;
    }

    @Override
    public void sendRestoreEmail(Long userId, String username, String userEmail, String token) {
        Map<String, Object> model = buildModelMapForPasswordRestore(userId, username, token);
        String template = createEmailTemplate(model, AppConstant.RESTORE_EMAIL_PAGE);
        sendEmail(userEmail, AppConstant.CONFIRM_RESTORING_PASS, template);
    }

    @Override
    public void sendSuccessRestorePasswordByEmail(String email, String userName) {
        Map<String, Object> model = new HashMap<>();
        model.put(AppConstant.CLIENT_LINK, clientLink + "/signin");
        model.put(AppConstant.USER_NAME, userName);
        String template = createEmailTemplate(model, AppConstant.SUCCESS_RESTORED_PASSWORD_PAGE);
        sendEmail(email, AppConstant.RESTORE_PASSWORD, template);
    }

    private String createEmailTemplate(Map<String, Object> vars, String templateName) {
        log.info(AppConstant.IN_CREATE_TEMPLATE_NAME, null, templateName);
        Context context = new Context();
        context.setVariables(vars);
        return templateEngine.process("email/" + templateName, context);
    }

    private void sendEmail(String receiverEmail, String subject, String content) {
        validate(receiverEmail);
        log.info(AppConstant.IN_SEND_EMAIL, receiverEmail, subject);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom(senderEmailAddress);
            mimeMessageHelper.setTo(receiverEmail);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(content, true);
        } catch (MessagingException e) {
            log.error(e.getMessage());
        }
        executor.execute(() -> javaMailSender.send(mimeMessage));
    }

    private static void validate(String emailAddress) {
        Pattern emailRegex = Pattern.compile(AppConstant.EMAIL_REGEXP);
        if (emailAddress == null || !emailRegex.matcher(emailAddress).matches()) {
            throw new WrongEmailException("Invalid email address: " + emailAddress);
        }
    }

    private Map<String, Object> buildModelMapForPasswordRestore(Long userId, String name, String token) {
        Map<String, Object> model = new HashMap<>();
        model.put(AppConstant.CLIENT_LINK, "/sfl");
        model.put(AppConstant.USER_NAME, name);
        model.put(AppConstant.RESTORE_PASS, clientLink  + "/auth/restore?"
            + "token=" + token + AppConstant.PARAM_USER_ID + userId);
        return model;
    }
}
