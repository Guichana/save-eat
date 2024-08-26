package save_eat.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import save_eat.adapters.out.AwsS3StorageAdapter;
import save_eat.ports.out.PhotoStoragePort;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
@Profile("prod")
public class AwsS3StorageConfig {

    @Value("${AWS_BUCKET}")
    private String awsBucket;

    @Bean
    PhotoStoragePort s3FileStorage() {

        return AwsS3StorageAdapter.builder()
            .s3Client(S3Client.create())
            .bucket(awsBucket)
            .keyPrefix("photo/")
            .build();
    }

}
