package com.payex.vas.config;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

import static com.payex.vas.util.Constants.SESSION_ID;
import static com.payex.vas.util.Constants.SPRING_PROFILE_SWAGGER;

@Configuration
@EnableSwagger2
@Profile(SPRING_PROFILE_SWAGGER)
public class SwaggerConfig {

    @Value("${spring.application.name}")
    private String appName;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.payex.vas.rest"))
            .paths(PathSelectors.any())
            .build()
            .apiInfo(metaData())
            .globalOperationParameters(operationParameters());
    }

    private List<Parameter> operationParameters() {
        List<Parameter> parameters = Lists.newArrayList();
        parameters.add(createHeader(SESSION_ID, "Unique id for each request. Use GUID", "string", false));
        return parameters;
    }

    private Parameter createHeader(String name, String description, String type, boolean required) {
        return new ParameterBuilder()
            .name(name)
            .description(description)
            .modelRef(new ModelRef(type))
            .parameterType("header")
            .required(required)
            .build();
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
            .title(appName)
            .description(null)
            .version(SwaggerConfig.class.getPackage().getImplementationVersion())
            .license(null)
            .licenseUrl(null)
            .contact(new Contact("VAS support", null, "support.vas@payex.com"))
            .build();
    }
}
