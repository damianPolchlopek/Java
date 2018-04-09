package sample.datamodel;

import javafx.beans.property.SimpleStringProperty;

public class Book {

    private SimpleStringProperty title = new SimpleStringProperty("");
    private SimpleStringProperty price = new SimpleStringProperty("");
    private SimpleStringProperty author = new SimpleStringProperty("");
    private SimpleStringProperty publishingHouse = new SimpleStringProperty("");
    private SimpleStringProperty publicationDate = new SimpleStringProperty("");
    private SimpleStringProperty numberOfPages = new SimpleStringProperty("");
    private SimpleStringProperty genre = new SimpleStringProperty("");

    public Book() {
    }

    public Book(String title, String price, String author, String publishingHouse,
                String publicationDate, String numberOfPages, String genre){
        this.title.set(title);
        this.price.set(price);
        this.author.set(author);
        this.publishingHouse.set(publishingHouse);
        this.publicationDate.set(publicationDate);
        this.numberOfPages.set(numberOfPages);
        this.genre.set(genre);
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getPrice() {
        return price.get();
    }

    public void setPrice(String price) {
        this.price.set(price);
    }

    public String getAuthor() {
        return author.get();
    }

    public void setAuthor(String author) {
        this.author.set(author);
    }

    public String getPublishingHouse() {
        return publishingHouse.get();
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse.set(publishingHouse);
    }

    public String getPublicationDate() {
        return publicationDate.get();
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate.set(publicationDate);
    }

    public String getNumberOfPages() {
        return numberOfPages.get();
    }

    public void setNumberOfPages(String numberOfPages) {
        this.numberOfPages.set(numberOfPages);
    }

    public String getGenre() {
        return genre.get();
    }

    public void setGenre(String genre) {
        this.genre.set(genre);
    }

}
