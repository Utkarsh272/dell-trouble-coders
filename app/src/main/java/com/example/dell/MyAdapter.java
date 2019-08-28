package com.example.dell;

public class MyAdapter {

    public String imageName;

    public String imageURL;



    public void ImageUploadInfo(String name, String url) {

        this.imageName = name;
        this.imageURL= url;
    }

    public String getImageName() {
        return imageName;
    }

    public String getImageURL() {
        return imageURL;
    }
}