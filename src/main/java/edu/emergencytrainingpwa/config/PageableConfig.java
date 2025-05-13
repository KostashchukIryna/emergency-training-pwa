package edu.emergencytrainingpwa.config;

import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.SortHandlerMethodArgumentResolver;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableSpringDataWebSupport
public class PageableConfig implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        // Sort resolver (as before)
        SortHandlerMethodArgumentResolver sortResolver =
            new SortHandlerMethodArgumentResolver();
        sortResolver.setSortParameter("sort");
        resolvers.add(sortResolver);

        // Pageable resolver
        PageableHandlerMethodArgumentResolver pageResolver =
            new PageableHandlerMethodArgumentResolver(sortResolver);
        pageResolver.setPageParameterName("page");
        pageResolver.setSizeParameterName("size");

        // DEFAULT if client omits page/size:
        pageResolver.setFallbackPageable(
            PageRequest.of(0, 10, Sort.by("title").ascending())
        );

        // MAXIMUM size (any ?size>50 → size=50)
        pageResolver.setMaxPageSize(50);

        resolvers.add(pageResolver);
    }
}