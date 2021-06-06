package com.example.file.domain.utils;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.IOUtils;
import com.example.file.config.properties.AwsS3Properties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AwsS3Utils {

    private final AwsS3Properties properties;

    public AmazonS3 getS3Client() {

        AWSCredentials credentials = new BasicAWSCredentials(properties.getAccessKey(), properties.getSecretKey());

        return AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(properties.getEndPoint(), properties.getRegion()))
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(properties.getAccessKey(), properties.getSecretKey())))
                .build();
    }

    public String putObjectToS3Storage(AmazonS3 client, String filepath, MultipartFile file) throws IOException {
        String bucket = properties.getBucket();

        ObjectMetadata metadata = new ObjectMetadata();
        ByteArrayInputStream stream = getByteArrayInputStream(file, metadata);

        client.putObject(new PutObjectRequest(bucket, filepath, stream, metadata)
                .withCannedAcl(CannedAccessControlList.PublicRead));

        return client.getUrl(bucket, filepath).toString();
    }

    private ByteArrayInputStream getByteArrayInputStream(MultipartFile file, ObjectMetadata metadata) throws IOException {
        byte[] bytes = IOUtils.toByteArray(file.getInputStream());
        metadata.setContentLength(bytes.length);
        return new ByteArrayInputStream(bytes);
    }
}
