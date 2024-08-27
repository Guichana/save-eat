package save_eat.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.annotation.PostConstruct;

import save_eat.adapters.out.LocalStorageAdapter;
import save_eat.ports.out.PhotoStoragePort;

@Configuration
@Profile("dev")
public class TempFileStorageConfig {

    private static final Logger logger = LoggerFactory.getLogger(TempFileStorageConfig.class);

    Path path;
    String urlPrefix = "/photo/";

    @PostConstruct
    void initDir() throws IOException {
        path = Files.createTempDirectory("save-eat");
        path.toFile().deleteOnExit();
        logger.info(String.format("temp storage %s created", path.toString()));
    }

    @Bean
    PhotoStoragePort fileStorage() {
        return new LocalStorageAdapter(path);
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
