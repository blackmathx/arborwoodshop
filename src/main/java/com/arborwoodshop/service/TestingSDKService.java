package com.arborwoodshop.service;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.Bucket;
import software.amazon.awssdk.services.s3.model.ListBucketsResponse;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.util.List;

@Service
public class TestingSDKService {

    public TestingSDKService(){

    }

    public static void runner(){
        //awsS3Access();
    }


    private static void awsS3Access() {

        Region region = Region.US_EAST_2;
        S3Client s3 = S3Client.builder()
                .region(region)
                .build();
        listBuckets(s3);

    }

    private static void listBuckets(S3Client s3) {

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
