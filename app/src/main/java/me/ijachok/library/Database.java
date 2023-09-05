package me.ijachok.library;

import java.util.ArrayList;
import java.util.List;

public class Database {
    // TODO: change to hashmap
    private static Database instance;
    private static List<Book> allBooks;
    private static List<Book> favouriteBooks;
    private static List<Book> currentlyReadingBooks;
    private static List<Book> readLaterBooks;
    private static List<Book> alreadyReadBooks;

    private Database() {
        if (allBooks == null) {
            allBooks = new ArrayList<>();
            initData();
        }

        if (favouriteBooks == null) {
            favouriteBooks = new ArrayList<>();
        }

        if (currentlyReadingBooks == null) {
            currentlyReadingBooks = new ArrayList<>();
        }

        if (readLaterBooks == null) {
            readLaterBooks = new ArrayList<>();
        }

        if (alreadyReadBooks == null) {
            alreadyReadBooks = new ArrayList<>();
        }
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
            return instance;
        } else {
            return instance;
        }
    }



    private void initData() {
        String description = "A book about programming, improving skill, and avoiding mistakes. The author spent two years researching every bug avoidance technique she could find.";
        String description2 = "Written as a series of self-contained sections and filled with classic and fresh anecdotes, thoughtful examples, and interesting analogies, The Pragmatic Programmer illustrates the best approaches and major pitfalls of many different aspects of software development.";
        String description3 = "Noted software expert Robert C. Martin, presents a revolutionary paradigm with Clean Code: A Handbook of Agile Software Craftsmanship.";
        String description4 = "At any given moment, someone struggles with the same software design problems you have. And, chances are, someone else has already solved your problem.";
        String description5 = "Refactoring is about improving the design of existing code. It is the process of changing a software system in such a way that it does not alter the external behavior of the code, yet improves its internal structure.";
        String description6 = "Introducing The Effective Engineer — the only book designed specifically for today's software engineers, based on extensive interviews with engineering leaders at top tech companies, and packed with hundreds of techniques to accelerate your career.";
        String description7 = "In The Clean Coder: A Code of Conduct for Professional Programmers, legendary software expert Robert C. Martin introduces the disciplines, techniques, tools, and practices of true software craftsmanship.";
        String description8 = "This book provides programmers with the ability to cost effectively handlecommon legacy code problems without having to go through the hugelyexpensive task of rewriting all existing code. ";
        String description9 = "Written by a software developer for software developers, this book is a unique collection of the latest software development methods. The author includes OOD, UML, Design Patterns, Agile and XP methods with a detailed description of a complete software design for reusable programs in C++ and Java.";

        allBooks.add(new Book("Zero Bugs", "Kate Thompson",
                "https://m.media-amazon.com/images/I/61DbOXo7NlL._SX404_BO1,204,203,200_.jpg",
                182, "and Program Faster", description));
        allBooks.add(new Book("The Pragmatic Programmer", "Andrew Hunt",
                "https://m.media-amazon.com/images/I/51IA4hT6jrL._SX380_BO1,204,203,200_.jpg",
                352, "Your Journey To Mastery", description2));
        allBooks.add(new Book("Clean Code", "Robert C. Martin, Dean Wampler",
                "https://m.media-amazon.com/images/I/61lU253WNnL._SX373_BO1,204,203,200_.jpg",
                464, "A Handbook of Agile Software Craftsmanship", description3));
        allBooks.add(new Book("Head First Design Patterns", "Bert Bates, Eric Freeman",
                "https://m.media-amazon.com/images/I/61APhXCksuL._SX430_BO1,204,203,200_.jpg",
                692, "A Brain-Friendly Guide", description4));
        allBooks.add(new Book("Refactoring", "Kent Beck, Martin Fowler",
                "https://m.media-amazon.com/images/I/51k+BvsOl2L._SX392_BO1,204,203,200_.jpg",
                431, "Improving the Design of Existing Code", description5));
        allBooks.add(new Book("The Effective Engineer", "Edmond Lau",
                "https://m.media-amazon.com/images/I/41p7Ty0Hx9L._SX331_BO1,204,203,200_.jpg",
                260, "How to Leverage Your Efforts In Software Engineering to Make a Disproportionate and Meaningful Impact", description6));
        allBooks.add(new Book("The Clean Coder", "Robert C. Martin",
                "https://m.media-amazon.com/images/I/51SxkB3f-qL._SX381_BO1,204,203,200_.jpg",
                256, "A Code of Conduct for Professional Programmers", description7));
        allBooks.add(new Book("Working Effectively with Legacy Code", "Michael C. Feathers",
                "https://m.media-amazon.com/images/I/51yS8PYs03L._SX376_BO1,204,203,200_.jpg",
                464, "", description8));
        allBooks.add(new Book("Agile Software Development", "Robert C. Martin",
                "https://m.media-amazon.com/images/I/51GXv-sMBYL._SX379_BO1,204,203,200_.jpg",
                552, ", Principles, Patterns, and Practices", description9));
    }

    public static List<Book> getAllBooks() {
        return allBooks;
    }

    public static List<Book> getFavouriteBooks() {
        return favouriteBooks;
    }

    public static List<Book> getCurrentlyReadingBooks() {
        return currentlyReadingBooks;
    }

    public static List<Book> getReadLaterBooks() {
        return readLaterBooks;
    }

    public static List<Book> getAlreadyReadBooks() {
        return alreadyReadBooks;
    }

    public Book findBookById(int bookId) {
        for (Book book : allBooks) {
            if (book.getId() == bookId) {
                return book;
            }
        }

        return null;
    }

    public boolean isCurrentlyReading(Book book){
        for (Book b: currentlyReadingBooks){
            if (b.equals(book)){
                return true;
            }
        }
        return false;
    }

    public boolean isInReadLater(Book book){
        for (Book b: readLaterBooks){
            if (b.equals(book)){
                return true;
            }
        }
        return false;
    }

    public boolean isAlreadyRead(Book book){
        for (Book b: alreadyReadBooks){
            if (b.equals(book)){
                return true;
            }
        }
        return false;
    }

    public boolean isFavourite(Book book){
        for (Book b: favouriteBooks){
            if (b.equals(book)){
                return true;
            }
        }
        return false;
    }

    public void removeFavourite(Book book){
        if (isFavourite(book)){
            for (int i =0; i< favouriteBooks.size(); i++){
                if (favouriteBooks.get(i).equals(book)){
                    favouriteBooks.remove(i);
                    break;
                }
            }
        }
    }

    public void removeCurrentlyReading(Book book){
        if (isCurrentlyReading(book)){
            for (int i =0; i< currentlyReadingBooks.size(); i++){
                if (currentlyReadingBooks.get(i).equals(book)){
                    currentlyReadingBooks.remove(i);
                    break;
                }
            }
        }
    }

    public void removeReadLater(Book book){
        if (isInReadLater(book)){
            for (int i =0; i< readLaterBooks.size(); i++){
                if (readLaterBooks.get(i).equals(book)){
                    readLaterBooks.remove(i);
                    break;
                }
            }
        }
    }

    public void removeAlreadyRead(Book book){
        if (isAlreadyRead(book)){
            for (int i =0; i< alreadyReadBooks.size(); i++){
                if (alreadyReadBooks.get(i).equals(book)){
                    alreadyReadBooks.remove(i);
                    break;
                }
            }
        }
    }

    public boolean addToAlreadyRead(Book book) {
        return alreadyReadBooks.add(book);
    }

    public boolean addToFavourites(Book book) {
        return favouriteBooks.add(book);
    }

    public boolean addToCurrentlyReading(Book book) {
        return currentlyReadingBooks.add(book);
    }

    public boolean addToReadLater(Book book) {
        return readLaterBooks.add(book);
    }

}
