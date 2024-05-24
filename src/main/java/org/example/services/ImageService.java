package org.example.services;

import org.example.domain.Image;
import org.example.domain.dto.ImageResponse;
import org.example.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public ImageResponse storeFile(MultipartFile file,String webDetecion) throws IOException {
        String fileName = file.getOriginalFilename();
        String contentType = file.getContentType();
        byte[] data = file.getBytes();

        Image image = new Image(fileName, contentType, data,webDetecion);
        imageRepository.save(image);
        return imageResponse(image);
    }

    private ImageResponse imageResponse(Image image){
        return new ImageResponse(image.getId(),image.getFileName(),image.getDetectionResult());
    }

    private ImageResponse imageResponses(Image image){
        return new ImageResponse(image.getId(),image.getFileName(),image.getDetectionResult(),image.getData());
    }


    public List<ImageResponse> getImages() {
        List<Image> images = imageRepository.findAll();
        return images.stream()
                .map(this::imageResponses)
                .toList();
    }



}