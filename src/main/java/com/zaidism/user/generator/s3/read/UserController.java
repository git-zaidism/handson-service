package com.zaidism.user.generator.s3.read;


import com.zaidism.user.generator.s3.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> generateAndFetchUsersFromS3() {
       return ResponseEntity.ok(s3Service.fetchUsersJsonData());
    }

    @GetMapping("/ecs")
    public ResponseEntity<String> dockerTestForEcs() {
        return ResponseEntity.ok("Hello Docker for ECS!!");
    }
}
