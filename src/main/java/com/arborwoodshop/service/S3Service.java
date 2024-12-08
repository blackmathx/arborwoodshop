package com.arborwoodshop.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.time.Duration;
import java.util.List;

@Service
public class S3Service {
    Logger logger = LoggerFactory.getLogger(this.getClass());


    public String generatePresignedUrl(String statePath, String cityPath, String fileType){
        String type = fileType.substring(fileType.indexOf("/") + 1);
        String filename = statePath + "/" + cityPath + "/" + "image" + System.currentTimeMillis() + "." + type;

        try (S3Presigner presigner = S3Presigner.create()) {
            PutObjectRequest objectRequest = PutObjectRequest.builder()
                    .bucket("arborwoodshop-listings")
                    .key(filename)
                    .contentType(fileType)
                    .build();

            PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
                    .signatureDuration(Duration.ofMinutes(10))  // The URL expires in 3 minutes.
                    .putObjectRequest(objectRequest)
                    .build();

            PresignedPutObjectRequest presignedRequest = presigner.presignPutObject(presignRequest);

            return presignedRequest.url().toExternalForm();
        }

    }

    public void deleteImageForListing(String imageName){

        try (S3Client s3Client = S3Client.builder()
                .region(Region.US_EAST_2)
                .build()) {

            String bucketName = "arborwoodshop-listings";
            try {
                // Build the DeleteObjectRequest
                DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                        .bucket(bucketName)
                        .key(imageName)
                        .build();

                // Execute the delete operation
                s3Client.deleteObject(deleteObjectRequest);

                logger.debug("Successfully deleted object: {} from bucket: {}", imageName, bucketName);

            } catch (S3Exception e) {
                logger.error("Failed to delete image: {}; Error details: {}", imageName, e.awsErrorDetails().errorMessage());
            }
        }

    }


    public static void listBuckets() {
        Region region = Region.US_EAST_2;
        S3Client s3 = S3Client.builder()
                .region(region)
                .build();

        try {
            ListBucketsResponse response = s3.listBuckets();
            List<Bucket> bucketList = response.buckets();
            bucketList.forEach(bucket -> {
                System.out.println("Bucket Name: " + bucket.name());
            });
        } catch (S3Exception e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }


}
