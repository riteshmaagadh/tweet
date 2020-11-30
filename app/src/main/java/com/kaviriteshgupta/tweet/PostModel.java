package com.kaviriteshgupta.tweet;

public class PostModel {

    private String name;
    private String imageUrl;
    private String body;
    private String Uid;

    public PostModel() {
    }

    public PostModel(String name, String imageUrl, String body, String Uid) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.body = body;
        this.Uid = Uid;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String  getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String  imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "PostModel{" +
                "name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", body='" + body + '\'' +
                ", Uid='" + Uid + '\'' +
                '}';
    }
}