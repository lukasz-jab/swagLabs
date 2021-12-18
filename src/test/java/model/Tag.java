package model;

public class Tag {
    public int id;
    public String name;

    public Tag() {
    }

    public int getId() {
        return id;
    }

    public Tag withId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Tag withName(String name) {
        this.name = name;
        return this;
    }
}
