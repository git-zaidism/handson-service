package com.zaidism.user.generator.s3;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private S3Service s3Service;

    @GetMapping("/fetch-users")
    public String generateAndFetchUsersFromS3() {
        return s3Service.fetchUsersJsonData();
    }
}
