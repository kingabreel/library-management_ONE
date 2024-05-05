package one.alura.gablibrary.view;

import one.alura.gablibrary.controller.ConsoleAppController;
import one.alura.gablibrary.model.dtos.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class ConsoleApp {
    private final Scanner scanner;
    @Autowired
    private ConsoleAppController controller;
    private List<BookDto> searchList;

    public ConsoleApp(ConsoleAppController controller){
        this.controller = controller;
        this.scanner = new Scanner(System.in);
        this.searchList = new ArrayList<>();

        System.out.println("Welcome to Gab's Library application!");
        showMainOption();
    }

    private void showMainOption(){
        System.out.println("Please select an option: ");
        System.out.println("""
                1- Search book
                2- Search on local database
                3- Save to local database
                4- Save to local txt file
                
                0- Exit
                """);
        int chose = scanner.nextInt();
        scanner.nextLine();

        switch (chose){
            case 1:
                showSearchOptions();
                break;
            case 2:
                showLocalSearchOption();
                break;
            case 3:
                controller.saveLocalDb(searchList);
                showMainOption();
                break;
            case 4:
                controller.saveToLocalTxt(searchList);
                showMainOption();
                break;
            case 0:
                System.exit(0);
        }
    }

    private void showSearchOptions(){
        System.out.println("Please select an option: ");
        System.out.println("""
                1- Search by title or author name
                2- Search by language
                3- Search by topic
                4- Search by copyrighting
                5- Search by id
                
                0- Return
                """);
        int chose = scanner.nextInt();
        scanner.nextLine();
        switch (chose){
            case 1:
                System.out.print("Title: ");
                String title = scanner.nextLine();

                var list = controller.searchByTitleOrName(title);

                list.forEach(b -> {
                    System.out.println(b.toString());
                    searchList.add(b);
                });
                showSearchOptions();
                break;
            case 2:
                System.out.println("Options: \npt, fr, en, de, el, it");
                System.out.print("Language: ");
                String language = scanner.nextLine();

                if (controller.languageAccepted(language)){
                    var byLanguagelist = controller.searchByLanguage(language);
                    if (byLanguagelist.size() >= 10){
                        System.out.println(byLanguagelist.size() + " results.");
                        System.out.println("Show all? y or n");
                        var choseLanguage = scanner.nextLine();
                        if (choseLanguage.equalsIgnoreCase("y")){
                            byLanguagelist.forEach(bookDto -> System.out.println(bookDto.toString()));
                        }
                    } else {
                        System.out.println(byLanguagelist.stream()
                                .findFirst()
                                .get());
                    }
                    searchList.addAll(byLanguagelist);
                } else {
                    System.out.println("Language not accepted");
                }
                showSearchOptions();
                break;
            case 3:
                System.out.print("Topic to search: ");
                String topicSearch = scanner.nextLine();

                var booksByTopic = controller.searchByTopic(topicSearch);

                booksByTopic.forEach(bookDto -> System.out.println(bookDto.toString()));
                searchList.addAll(booksByTopic);
                showSearchOptions();
                break;
            case 4:
                System.out.println("With copyrighting? y or n");
                String copyChoose = scanner.nextLine();

                var booksByCopyrighting = controller.searchByCopyrighting(copyChoose.equalsIgnoreCase("y"));

                booksByCopyrighting.forEach(bookDto -> System.out.println(bookDto.toString()));
                searchList.addAll(booksByCopyrighting);
                showSearchOptions();
                break;
            case 5:
                System.out.print("Id: ");
                int id = scanner.nextInt();
                var byIdSearch = controller.searchById(id);

                if (byIdSearch != null) System.out.println(byIdSearch);
                else System.out.println("Not Found");

                searchList.add(byIdSearch);
                showSearchOptions();
                break;
            case 0:
                showMainOption();
        }
    }

    private void showLocalSearchOption(){
        System.out.println("Please select an option: ");
        System.out.println("""
                1- Search by title
                2- Search by language
                3- Search by id
                4- Search by download count
                5- Search by authorId
                6- Search by author live in a year
                7- Get all authors
                8- Get all books
                
                0- Return
                """);
        int chose = scanner.nextInt();
        scanner.nextLine();
        switch (chose){
            case 1:
                System.out.print("Book title: ");
                var bookName = scanner.nextLine();

                var bookByTitle = controller.searchLocalByTitle(bookName);

                bookByTitle.forEach(bookDto -> System.out.println(bookDto.toString() + "\n"));
                showLocalSearchOption();
                break;
            case 2:
                System.out.println("Options: \npt, fr, en, de, el, it");
                System.out.print("Language: ");

                var bookLang = scanner.nextLine();

                var bookByLang = controller.searchLocalByLanguage(bookLang);

                bookByLang.forEach(bookDto -> System.out.println(bookDto.toString() + "\n"));
                showLocalSearchOption();
                break;
            case 3:
                System.out.print("Book id: ");
                int bookId = scanner.nextInt();

                var bookById = controller.searchLocalById(bookId);

                System.out.println(bookById.toString());
                showLocalSearchOption();
                break;
            case 4:
                System.out.print("Download count minimum: ");

                var downCount = scanner.nextInt();

                var booksGreaterThan = controller.searchLocalByDownloadCount(downCount);

                booksGreaterThan.forEach(bookDto -> System.out.println(bookDto.toString() + "\n"));

                showLocalSearchOption();
                break;
            case 5:
                System.out.print("Author id: ");

                var authorId = scanner.nextInt();

                var bookByAuthor = controller.searchLocalByAuthorId(authorId);

                bookByAuthor.forEach(bookDto -> System.out.println(bookDto.toString() + "\n"));

                showLocalSearchOption();
                break;
            case 6:
                System.out.print("year: ");
                int year = scanner.nextInt();
                scanner.nextLine();
                var authorAlive = controller.findAuthorsAliveInYear(year);
                authorAlive.forEach(authorDto -> System.out.println(authorDto.toString()));

                showLocalSearchOption();
                break;
            case 7:
                var authors = controller.getAllAuthor();
                authors.forEach(System.out::println);
                break;
            case 8:
                var books = controller.getAllLocalBooks();
                books.forEach(System.out::println);
                break;
            case 0:
                showMainOption();
                break;
        }
    }
}
