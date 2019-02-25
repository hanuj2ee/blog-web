package com.blog.config;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * SwaggerConfig class
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     *
     * @return
     */
    @Bean
    public Docket produceApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.blog.rest.controller"))
                .paths(paths())
                .build();
    }

    /**
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Blog web Rest APIs")
                .description("This page lists all the rest apis for Blog App.")
                .version("0.0.1-SNAPSHOT")
                .build();
    }

    /**
     * Selects only the apis that matches the given Predicates.
     * @return
     */
    private Predicate<String> paths() {
        // Match all paths except /h2-console used for H2 DB
        return Predicates.and(
                PathSelectors.regex("/blog-web/*"),
                Predicates.not(PathSelectors.regex("/h2-console/")));
    }
}
