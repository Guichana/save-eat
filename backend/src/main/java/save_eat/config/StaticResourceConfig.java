package save_eat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.time.Duration;

@Configuration
public class StaticResourceConfig {
    @Bean
    WebMvcConfigurer addStaticResourceConfig() {

        return new WebMvcConfigurer() {
            public void addResourceHandlers(ResourceHandlerRegistry registry) {

                // for hash-name files
                registry.addResourceHandler("/**.js", "/**.css", "/**.woff")
                    .addResourceLocations("classpath:/static/")
                    .setCacheControl(CacheControl.maxAge(Duration.ofDays(365)));

                // else
                registry.addResourceHandler("/**")
                    .addResourceLocations("classpath:/static/")
                    .setCacheControl(CacheControl.noCache());

            }
        };
    }

}
