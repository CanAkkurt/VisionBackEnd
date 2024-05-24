package org.example.domain.dto;


import jakarta.persistence.Lob;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class ImageResponse {

    public ImageResponse(){}
    private Long id;

    private String fileName;

    private String contentType;
    private String detectionResult;
    private byte[] data;// URL where the actual image is stored


    public ImageResponse(Long id,String fileName,String detectionResult){
        this.id = id;
        this.fileName = fileName;
        this.detectionResult = detectionResult;

    }

    public ImageResponse(Long id,String fileName,String detectionResult, byte[] data){
        this.id = id;
        this.fileName = fileName;
        this.detectionResult = detectionResult;
        this.data = data;

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
}
