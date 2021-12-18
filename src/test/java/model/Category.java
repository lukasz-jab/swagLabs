package model;

import java.util.Objects;

public class Category {
    public int id;
    public String name;

    public Category() {
    }

    public int getId() {
        return id;
    }

    public Category withId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Category withName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return id == category.id && Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
