package com.ash1.restaip.bootrest.entity;

public class Books {
    
    private int id;
    private String title;
    private String Author;

    public Books(int id, String title, String author) {
        this.id = id;
        this.title = title;
        Author = author;
    }

    public Books() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return Author;
    } 

    @Override
    public String toString() {
        return "Books [id=" + id + ", title=" + title + ", Author=" + Author + "]";
    }

    


}
