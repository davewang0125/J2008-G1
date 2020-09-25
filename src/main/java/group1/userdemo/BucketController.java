package group1.userdemo;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/storage/")
public class BucketController {
    private AmazonClient amazonClient;

    @Autowired
    BucketController(AmazonClient amazonClient){
        this.amazonClient = amazonClient;
    }

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestPart(value = "file")MultipartFile file){
        /*
        API to upload a file.
        For testing with Postman
        Choose POST method, in the Body we should select ‘form-data’.
        As a key we should enter ‘file’ and choose value type ‘File’.
        Then choose any file from your PC as a value.
        The endpoint url is: http://localhost:8080/storage/uploadFile
         */
        return this.amazonClient.uploadFile(file);
    }

    @DeleteMapping("/deleteFile")
    public String deleteFile(@RequestPart(value = "url") String fileUrl){
        /*
        API to download a file.
        Now let’s test our delete method. Choose DELETE method with
        endpoint url: http://localhost:8080/storage/deleteFile.
        Body type is the same: ‘form-data’, key: ‘url’,
        and into value field enter the fileUrl created by S3 bucket.
         */
        return this.amazonClient.deleteFileFormatS3Bucket(fileUrl);
    }

}
