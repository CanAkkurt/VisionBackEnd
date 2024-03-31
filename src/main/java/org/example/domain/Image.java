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
    private byte[] data;// URL where the actual image is stored

    // Constructors, getters, and setters
    public Image() {
    }

    public Image(String fileName, String contentType, byte[] data) {
        this.fileName = fileName;
        this.contentType = contentType;
        this.data = data;
    }

    // getters and setters
}