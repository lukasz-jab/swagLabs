package model;

import java.util.List;

public class Pet {
    private String id;
    private Category category;
    private String name;
    private List<String> photoUrls;
    private List<Tag> tags;
    private String status;

    public Pet() {
    }

    public String getId() {
        return id;
    }

    public Pet withId(String id) {
        this.id = id;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public Pet withCategory(Category category) {
        this.category = category;
        return this;
    }

    public String getName() {
        return name;
    }

    public Pet withName(String name) {
        this.name = name;
        return this;
    }

    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    public Pet withPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
        return this;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public Pet withTags(List<Tag> tags) {
        this.tags = tags;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Pet withStatus(String status) {
        this.status = status;
        return this;
    }
}