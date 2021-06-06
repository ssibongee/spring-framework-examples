package com.example.file.domain.utils;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;
import java.util.UUID;

public class FileUtils {

    public static String getFilePath(MultipartFile file, String directory) {
        String suffix = UUID.randomUUID().toString().replace("-", "");

        String extension = StringUtils.getFilenameExtension(Objects.requireNonNull(file.getOriginalFilename()));

        return directory + "/" + suffix + "." + extension;
    }

}
