package org.example.model.entity;
public class BookEntity {

private int isbn;
private String bookName;
private String authorName;
private String genre;
private int releaseYear;
private int pages;
private int rating;


    public int getIsbn() {
        return isbn;
    }

    public BookEntity setIsbn(int isbn) {
        this.isbn = isbn;
        return this;
    }

    public String getBookName() {
        return bookName;
    }

    public BookEntity setBookName(String bookName) {
        this.bookName = bookName;
        return this;
    }

    public String getAuthorName() {
        return authorName;
    }

    public BookEntity setAuthorName(String authorName) {
        this.authorName = authorName;
        return this;
    }

    public String getGenre() {
        return genre;
    }

    public BookEntity setGenre(String genre) {
        this.genre = genre;
        return this;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public BookEntity setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
        return this;
    }

    public int getPages() {
        return pages;
    }

    public BookEntity setPages(int pages) {
        this.pages = pages;
        return this;
    }

    public int getRating() {
        return rating;
    }

    public BookEntity setRating(int rating) {
        this.rating = rating;
        return this;
    }
}
