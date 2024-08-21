package save_eat.adapters.out;

import lombok.Builder;
import save_eat.dto.storage.PhotoFileDto;
import save_eat.ports.out.FileStoragePort;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

public class AwsS3StorageAdapter implements FileStoragePort {

    final private S3Client s3Client;
    final private String bucket;

    @Builder
    AwsS3StorageAdapter(S3Client s3Client, String bucket) {
        this.s3Client = s3Client;
        this.bucket = bucket;
    }

    public void save(PhotoFileDto saveDto) {
        PutObjectRequest putRequest = PutObjectRequest.builder()
            .bucket(bucket)
            .key(saveDto.getFileName())
            .build();

        RequestBody body = RequestBody.fromInputStream(saveDto.getInputStream(), saveDto.getSize());

        s3Client.putObject(putRequest, body);
    }

}
