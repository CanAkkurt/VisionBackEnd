package org.example.domain;

import jakarta.persistence.*;

@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    private String contentType;
    @Lob
    private String detectionResult;
    @Lob
    private byte[] data;// URL where the actual image is stored

    // Constructors, getters, and setters
    public Image() {
    }

    public Image(String fileName, String contentType, byte[] data,String detectionResult) {
        this.fileName = fileName;
        this.contentType = contentType;
        this.data = data;
        this.detectionResult = detectionResult;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getDetectionResult() {
        return detectionResult;
    }

    public void setDetectionResult(String detectionResult) {
        this.detectionResult = detectionResult;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    // getters and setters
}