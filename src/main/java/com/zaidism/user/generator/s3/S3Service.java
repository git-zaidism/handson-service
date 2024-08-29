package com.zaidism.user.generator.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Slf4j
@Service
public class S3Service {

    @Value("${aws.s3.bucket.name}")
    private String bucketName;

    @Value("${aws.s3.object.key}")
    private String objectKey;

    @Autowired
    private AmazonS3 amazonS3Client;


    public String fetchUsersJsonData() {
        try (S3Object object = amazonS3Client.getObject(new GetObjectRequest(bucketName, objectKey));
             InputStream objectData = object.getObjectContent();
             BufferedReader reader = new BufferedReader(new InputStreamReader(objectData))) {

            // Use a StringBuilder to accumulate the JSON content
            StringBuilder jsonStringBuilder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null)
                jsonStringBuilder.append(line).append("\n"); // Ensure proper line breaks

            return jsonStringBuilder.toString().trim(); // Trim trailing newlines
        } catch (IOException e) {
            log.error("Error reading S3 object content", e);
            throw new RuntimeException("Error reading S3 object content", e);
        }
    }
}

