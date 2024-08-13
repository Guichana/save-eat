package save_eat.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.annotation.PostConstruct;

import save_eat.adapters.out.LocalStorageAdapter;
import save_eat.ports.out.FileStoragePort;

@Configuration
public class TempFileStorageConfig {

    Path path;
    String urlPrefix = "/photo/";

    @PostConstruct
    void initDir() throws IOException {
        path = Files.createTempDirectory("save-eat");
        path.toFile().deleteOnExit();
    }

    @Bean
    FileStoragePort fileStorage() {
        return new LocalStorageAdapter(path, urlPrefix);
    }

    @Bean
    WebMvcConfigurer addStaticResource() {
        return new WebMvcConfigurer() {
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler(urlPrefix + "**")
                    .addResourceLocations("file:" + path.toString() + "/");
            }
        };
    }

}
