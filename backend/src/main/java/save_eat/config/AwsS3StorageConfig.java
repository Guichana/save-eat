package save_eat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import save_eat.adapters.out.AwsS3StorageAdapter;
import save_eat.ports.out.FileStoragePort;

@Configuration
@Profile("prod")
public class AwsS3StorageConfig {

    @Bean
    FileStoragePort s3FileStorage() {
        return AwsS3StorageAdapter.builder()
            // TODO:
            .build();
    }

}
