package com.example.file.service;

import com.amazonaws.services.s3.AmazonS3;
import com.example.file.domain.utils.AwsS3Utils;
import com.example.file.domain.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ImageFileUploadService implements FileUploadService {

    private final AwsS3Utils awsS3Utils;

    @Override
    public String uploadFile(MultipartFile file, String directory) throws IOException {

        AmazonS3 client = awsS3Utils.getS3Client();

        String filepath = FileUtils.getFilePath(file, directory);

        return awsS3Utils.putObjectToS3Storage(client, filepath, file);
    }
}
