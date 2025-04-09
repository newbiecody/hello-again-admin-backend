package com.example.hello_again_admin.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Set;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private long price;
    private Set<String> previewImageFilePaths;

    public Product(String name, long price, Set<String> previewImageFilePaths) {
        this.name = name;
        this.price = price;
        this.previewImageFilePaths = previewImageFilePaths;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return this.price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public Set<String> getPreviewImageFilePaths() {
        return this.previewImageFilePaths;
    }

    public void setPreviewImageFilePaths(Set<String> filepaths) {
        this.previewImageFilePaths = filepaths;
    }

    public void addPreviewImages(Set<String> filepaths) {
        this.previewImageFilePaths.addAll(filepaths);
    }

    public void removePreviewImages(Set<String> filepaths) {
        this.previewImageFilePaths.removeAll(filepaths);
    }
}
