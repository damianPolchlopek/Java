package sample;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import sample.datamodel.Book;
import sample.datamodel.BookData;
import java.io.IOException;
import java.util.Optional;


public class Controller {

    //ogolne zmienne
    @FXML
    private BorderPane mainPanel;

    @FXML
    private TableView<Book> booksTable;

    private BookData data;

    // wyszukiwanie
    @FXML
    private TextField searchingBook;

    @FXML
    private ChoiceBox whatWeSearchBox;

    ObservableList<String> whatWeSearchList = FXCollections.observableArrayList("Title",
            "Author", "Genre");

    // inicjowanie interfejsu
    public void initialize(){
        data = new BookData();
        data.loadBooks();
        booksTable.setItems(data.getBooks());
        whatWeSearchBox.setItems(whatWeSearchList);
        whatWeSearchBox.setValue("Title");
    }

    @FXML
    public void searchBook() {
        FilteredList<Book> filteredData = new FilteredList<>(data.getBooks(), p -> true);

        filteredData.setPredicate(book -> {

            String lowerCaseFilter = searchingBook.getText().toLowerCase();

            if(whatWeSearchBox.getValue() == "Title"){
                if (book.getTitle().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                }
            } else if(whatWeSearchBox.getValue() == "Author"){
                if (book.getAuthor().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                }
            } else if (whatWeSearchBox.getValue() == "Genre"){
                if (book.getGenre().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                }
            }

            return false; // Does not match.
        });

        booksTable.setItems(filteredData);
    }

    // funkcje menu - edytowanie, usuwanie i dodawanie
    @FXML
    public void showAddBookDialog(){
        Dialog<ButtonType> dialog = new Dialog<ButtonType>();
        dialog.initOwner(mainPanel.getScene().getWindow());
        dialog.setTitle("Add new book");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("book.fxml"));

        try{
            dialog.getDialogPane().setContent(fxmlLoader.load());
        }catch (IOException e){
            e.printStackTrace();
            return ;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            ContactPanel contactController = fxmlLoader.getController();
            Book newBook = contactController.getNewBook();
            data.addBook(newBook);
            data.saveBooks();
        }
    }

    @FXML
    public void showEditBookDialog(){
        Book selectedBook = booksTable.getSelectionModel().getSelectedItem();
        if(selectedBook == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Book Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select the book you want to edit");
            alert.showAndWait();
            return;
        }

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainPanel.getScene().getWindow());
        dialog.setTitle("Edit Book");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("book.fxml"));
        try{
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch(IOException e){
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        ContactPanel contactController = fxmlLoader.getController();
        contactController.editBook(selectedBook);
        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            contactController.updateBook(selectedBook);
            data.saveBooks();
        }
    }

    @FXML
    public void deleteBook(){
        Book selectedBook = booksTable.getSelectionModel().getSelectedItem();
        if(selectedBook == null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Book Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select the book you want to delete.");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Book");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete the book: " + selectedBook.getTitle());

        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            data.deleteBook(selectedBook);
            data.saveBooks();
        }
    }
}
