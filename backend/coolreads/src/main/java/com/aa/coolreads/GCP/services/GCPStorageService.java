package com.aa.coolreads.GCP.services;

import com.google.api.client.util.Value;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class GCPStorageService {

    private final Storage storage;

    public GCPStorageService(){
        this.storage = StorageOptions.getDefaultInstance().getService();
    }

    @Value("${spring.cloud.gcp.storage.bucket}")
    private String bucketName = "coolreads";

    public String uploadFile(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        BlobInfo blobInfo = storage.create(
                BlobInfo.newBuilder(this.bucketName, fileName).build(),
                file.getBytes()
        );
        return blobInfo.getMediaLink();
    }
}
