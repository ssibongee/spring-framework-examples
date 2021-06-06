package com.example.file.api;

import com.example.file.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class FileUploadApiController {

    private final FileUploadService fileUploadService;

    @PostMapping("/uploads/images")
    public ResponseEntity<String> uploadImageFile(@RequestParam("file")MultipartFile file, String directory) throws IOException {
        return ResponseEntity.ok(fileUploadService.uploadFile(file, directory));
    }
}
