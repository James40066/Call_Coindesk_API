package com.coindesk.call_coindesk_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    //swagger2配置
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.coindesk.call_coindesk_api.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("coindesk API")
                .description("實作內容：\n" +
                        "1. 幣別 DB 維護功能。\n" +
                        "2. 呼叫 coindesk 的 API。\n" +
                        "3. 呼叫 coindesk 的 API，並進行資料轉換，組成新 API。\n" +
                        "此新 API 提供：\n" +
                        "A. 更新時間（時間格式範例：1990/01/01 00:00:00）。\n" +
                        "B. 幣別相關資訊（幣別，幣別中文名稱，以及匯率）。")
                .version("1.0")
                .build();

    }

}
