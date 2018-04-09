package sample.datamodel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.xml.stream.*;
import javax.xml.stream.events.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

public class BookData {

    private static final String BOOKS_FILE = "database.xml";

    private static final String AllBOOK = "books";
    private static final String BOOK = "book";
    private static final String TITLE = "title";
    private static final String PRICE = "price";
    private static final String AUTHOR = "author";
    private static final String PUBLISHING_HOUSE = "publishing_house";
    private static final String PUBLICATION_DATE = "publication_date";
    private static final String NUMBER_OF_PAGES = "number_of_pages";
    private static final String GENRE = "genre";

    private ObservableList<Book> books;

    public BookData(){
        books = FXCollections.observableArrayList();
    }

    public ObservableList<Book> getBooks(){
        return books;
    }

    public void addBook(Book book){
        this.books.add(book);
    }

    public void deleteBook(Book book){
        this.books.remove(book);
    }

    public void loadBooks(){
        try {
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            InputStream input = new FileInputStream(BOOKS_FILE);
            XMLEventReader ER = inputFactory.createXMLEventReader(input);
            Book book = null;

            while (ER.hasNext()) {
                XMLEvent e = ER.nextEvent();

                if (e.isStartElement()) {
                    StartElement startElement = e.asStartElement();
                    if (startElement.getName().getLocalPart().equals(BOOK)) {
                        book = new Book();
                    }

                    if (e.isStartElement()) {
                        if (e.asStartElement().getName().getLocalPart().equals(TITLE)) {
                            e = ER.nextEvent();
                            book.setTitle(e.asCharacters().getData());
                        }
                    }

                    if (e.isStartElement()) {
                        if (e.asStartElement().getName().getLocalPart().equals(PRICE)) {
                            e = ER.nextEvent();
                            book.setPrice(e.asCharacters().getData());
                        }
                    }

                    if (e.isStartElement()) {
                        if (e.asStartElement().getName().getLocalPart().equals(AUTHOR)) {
                            e = ER.nextEvent();
                            book.setAuthor(e.asCharacters().getData());
                        }
                    }

                    if (e.isStartElement()) {
                        if (e.asStartElement().getName().getLocalPart().equals(PUBLICATION_DATE)) {
                            e = ER.nextEvent();
                            book.setPublicationDate(e.asCharacters().getData());
                        }
                    }

                    if (e.isStartElement()) {
                        if (e.asStartElement().getName().getLocalPart().equals(PUBLISHING_HOUSE)) {
                            e = ER.nextEvent();
                            book.setPublishingHouse(e.asCharacters().getData());
                        }
                    }

                    if (e.isStartElement()) {
                        if (e.asStartElement().getName().getLocalPart().equals(NUMBER_OF_PAGES)) {
                            e = ER.nextEvent();
                            book.setNumberOfPages(e.asCharacters().getData());
                        }
                    }

                    if (e.isStartElement()) {
                        if (e.asStartElement().getName().getLocalPart().equals(GENRE)) {
                            e = ER.nextEvent();
                            book.setGenre(e.asCharacters().getData());
                        }
                    }
                }

                if (e.isEndElement()) {
                    EndElement endElement = e.asEndElement();
                    if (endElement.getName().getLocalPart().equals(BOOK)) {
                        books.add(book);
                    }
                }
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    public void saveBooks(){
        try {
            XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
            XMLEventWriter eventWriter = outputFactory.createXMLEventWriter(new FileOutputStream(BOOKS_FILE));
            XMLEventFactory eventFactory = XMLEventFactory.newInstance();
            XMLEvent end = eventFactory.createDTD("\n");


            StartDocument startDocument = eventFactory.createStartDocument();
            eventWriter.add(startDocument);
            eventWriter.add(end);

            // wstawienie znacznika poczatku
            StartElement booksStartElement = eventFactory.createStartElement("",
                    "", AllBOOK);
            eventWriter.add(booksStartElement);
            eventWriter.add(end);

            // zapis wszystkich ksiazek
            for (Book book: books) {
                saveBook(eventWriter, eventFactory, book);
            }

            // wstawienie zancznika konca
            eventWriter.add(eventFactory.createEndElement("", "", AllBOOK));
            eventWriter.add(end);
            eventWriter.add(eventFactory.createEndDocument());
            eventWriter.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    public void saveBook(XMLEventWriter EW, XMLEventFactory eventFactory, Book book)
            throws FileNotFoundException, XMLStreamException {

        XMLEvent end = eventFactory.createDTD("\n");
        XMLEvent tab = eventFactory.createDTD("\t");

        EW.add(tab);
        StartElement startElement = eventFactory.createStartElement("",
                "", BOOK);
        EW.add(startElement);
        EW.add(end);

        // tworzenie zawartosci
        createNode(EW, TITLE, book.getTitle());
        createNode(EW, PRICE, book.getPrice());
        createNode(EW, AUTHOR, book.getAuthor());
        createNode(EW, PUBLISHING_HOUSE, book.getPublishingHouse());
        createNode(EW, PUBLICATION_DATE, book.getPublicationDate());
        createNode(EW, NUMBER_OF_PAGES, book.getNumberOfPages());
        createNode(EW, GENRE, book.getGenre());

        EW.add(tab);
        EW.add(eventFactory.createEndElement("", "", BOOK));
        EW.add(end);
    }

    public void createNode(XMLEventWriter EW, String node,
                           String value) throws XMLStreamException {

        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        XMLEvent end = eventFactory.createDTD("\n");
        XMLEvent tab = eventFactory.createDTD("\t");

        // wstawienie znacznika poczatkowego
        StartElement sElement = eventFactory.createStartElement("", "", node);
        EW.add(tab);
        EW.add(tab);
        EW.add(sElement);

        // wstawienie zawartosci
        Characters characters = eventFactory.createCharacters(value);
        EW.add(characters);

        //wstawienie znacznika konca
        EndElement eElement = eventFactory.createEndElement("", "", node);
        EW.add(eElement);
        EW.add(end);
    }

}
