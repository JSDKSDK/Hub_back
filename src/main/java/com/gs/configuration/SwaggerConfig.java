package com.gs.configuration;


import com.gs.constants.ApiConfigConsts;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;

@Configuration
@EnableSwagger2

public class SwaggerConfig {


    public static final Contact DEFAULT_CONTACT =
            new Contact("QA CENTRAL",
                    "https://confluence.colaboracionsimba.net/display/QATEST/Gremio+Calidad",
                    "pruebascentrales@elektra.com.mx");

    public static final ApiInfo DEFAULT_API_INFO =
            new ApiInfo("Switch Persistence Boot - Documentation",
                    "Switch Persistence Boot Documentation",
                    "1.0",
                    "PREMIUM",
                    DEFAULT_CONTACT,
                    "Apache 2.0",
                    "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList<VendorExtension>());

    @Bean
    public Docket swaggerSpringfoxDocket() {

        return new Docket(DocumentationType.SWAGGER_2)
                .enableUrlTemplating(true)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(DEFAULT_API_INFO)
                .securitySchemes(Arrays.asList(apiKey()));
    }

    private ApiKey apiKey() {
        return new ApiKey(ApiConfigConsts.JWT_TOKEN, "Authorization", "header");
    }
}