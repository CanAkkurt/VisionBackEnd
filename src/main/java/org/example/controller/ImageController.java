package org.example.controller;

import org.example.ChatGpt;
import org.example.DetectWebDetectionsImage;
import org.example.domain.Image;
import org.example.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/apiV1/")
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/images")
    public ResponseEntity<Image> uploadImage(@RequestParam("file") MultipartFile file) {




        try {
            String output = DetectWebDetectionsImage.detectWebDetections(file);
            System.out.println(output);
            String information = ChatGpt.chatGPT(output+"based on this data give information about Name of place/object,Location and Cultural Context,Community Importance,Historical Significance,Conclusion");
            return new ResponseEntity(imageService.storeFile(file,information), HttpStatus.CREATED);


        } catch (IOException e) {
            System.out.println("error");
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/images")
    public ResponseEntity GetImage() {
        return new ResponseEntity(imageService.getImages(),HttpStatus.OK);
    }





    @GetMapping("/test")
    public int test() {
        return 200;
    }

}