package one.alura.gablibrary.view;

import one.alura.gablibrary.controller.ConsoleAppController;
import one.alura.gablibrary.model.dtos.BookDto;
import one.alura.gablibrary.service.LibraryService;

import java.util.List;
import java.util.Scanner;

public class ConsoleApp {
    private Scanner scanner;
    private ConsoleAppController controller;
    private List<BookDto> searchList;
    public ConsoleApp(){
        scanner =  new Scanner(System.in);
        controller = new ConsoleAppController(new LibraryService());
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
                break;
            case 4:

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
                            byLanguagelist.forEach(bookDto -> {
                                System.out.println(bookDto.toString());
                            });
                        }
                    } else {
                        byLanguagelist.forEach(bookDto -> {
                            System.out.println(bookDto.toString());
                        });
                    }
                    searchList.addAll(byLanguagelist);
                } else {
                    System.out.println("Language not accepted");
                }

                break;
            case 3:
                controller.saveLocalDb(searchList);
                System.out.println("Saved to local database");
                break;
            case 4:

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
                
                0- Exit
                """);
    }
}
