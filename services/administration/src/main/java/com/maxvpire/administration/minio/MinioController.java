package com.maxvpire.administration.minio;

import com.maxvpire.administration.administration.AdministrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class MinioController {
    private final MinioService minioService;
    private final AdministrationService administrationService;

    @PostMapping("/upload")
    public ResponseEntity<String> upload(
            @RequestBody MultipartFile file, String id
    ) {
        try {
            String uuidFilename = minioService.uploadFile(file);
            administrationService.uploadAvatar(id, uuidFilename);
            return ResponseEntity.accepted().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Upload failed: " + e.getMessage());
        }
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<byte[]> download(@PathVariable String filename) {
        try (InputStream stream = minioService.downloadFile(filename)) {
            byte[] content;
            content = stream.readAllBytes();
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(content);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(null);
        }
    }
}
