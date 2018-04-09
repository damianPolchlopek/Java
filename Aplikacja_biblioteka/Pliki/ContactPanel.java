package sample;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import sample.datamodel.Book;

public class ContactPanel {

    @FXML
    private TextField titleField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField authorField;

    @FXML
    private TextField publishingHouseField;

    @FXML
    private TextField publicationDateField;

    @FXML
    private TextField numberOfPagesField;

    @FXML
    private TextField genreField;

    public Book getNewBook(){
        String title = titleField.getText();
        String price = priceField.getText();
        String author = authorField.getText();
        String publishingHouse = publishingHouseField.getText();
        String publicationDate = publicationDateField.getText();
        String numberOfPages = numberOfPagesField.getText();
        String genre = genreField.getText();

        Book newBook = new Book(title, price, author, publishingHouse,
                publicationDate, numberOfPages, genre);
        return newBook;
    }

    public void editBook(Book book){
        titleField.setText(book.getTitle());
        priceField.setText(book.getPrice());
        authorField.setText(book.getAuthor());
        publishingHouseField.setText(book.getPublishingHouse());
        publicationDateField.setText(book.getPublicationDate());
        numberOfPagesField.setText(book.getNumberOfPages());
        genreField.setText(book.getGenre());
    }

    public void updateBook(Book book){
        book.setTitle(titleField.getText());
        book.setPrice(priceField.getText());
        book.setAuthor(authorField.getText());
        book.setPublishingHouse(publishingHouseField.getText());
        book.setPublicationDate(publicationDateField.getText());
        book.setNumberOfPages(numberOfPagesField.getText());
        book.setGenre(genreField.getText());
    }
}
