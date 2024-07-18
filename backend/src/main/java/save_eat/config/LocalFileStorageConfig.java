package save_eat.config;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import save_eat.adapters.out.TempFileStorageAdapter;
import save_eat.ports.out.FileStoragePort;

@Configuration
public class LocalFileStorageConfig {

    Path tempPath = Paths.get(UUID.randomUUID().toString());

    @Bean
    FileStoragePort fileStorage() {
        return new TempFileStorageAdapter(tempPath, "/photo/");
    }

    @Bean
    WebMvcConfigurer addStaticResource() {
        return new WebMvcConfigurer() {
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("photo/**")
                    .addResourceLocations(tempPath.toString());
            }

        };
    }

}
