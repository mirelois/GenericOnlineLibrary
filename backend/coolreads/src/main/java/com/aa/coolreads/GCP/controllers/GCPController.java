package com.aa.coolreads.GCP.controllers;

import com.aa.coolreads.GCP.services.GCPStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/gcp")
public class GCPController {

    private final GCPStorageService gcpStorageService;

    @Autowired
    public GCPController(GCPStorageService gcpStorageService){
        this.gcpStorageService = gcpStorageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(MultipartFile file) {
        try {
            String imageUrl = gcpStorageService.uploadFile(file);
            return ResponseEntity.ok(imageUrl);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed while uploading image");
        }
    }
}
