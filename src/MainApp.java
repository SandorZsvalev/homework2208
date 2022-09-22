import java.util.*;

public class MainApp {
    public static void main(String[] args) {

        Book bookOne = new Book("Prestupenie i nakazanie", "Dostoevsky", 820);
        Book bookTwo = new Book("Voina i mir", "Tolstoy", 2500);
        Book bookThree = new Book("Ulysses", "Joyce", 900);
        Book bookThree_1 = new Book("Ulysses", "Joyce", 900);
        Book bookThree_2 = new Book("Ulysses", "Joyce", 1000);
        Book bookThree_3 = new Book("Ulysses", "Joyce", 1100);
        Book bookFour = new Book("On the road", "Kerouac", 500);


        Map<Box, List<Book>> catalog = new HashMap<>();
        MainApp mainApp = new MainApp();
        mainApp.putBooksToCatalog(catalog, bookOne, bookTwo, bookThree, bookThree_1, bookThree_2, bookThree_3, bookFour);

        // не стал запихивать общий поиск и поиск с ограничением по количеству выдачи в один цикл
        // для экономии времени и кода - просто надо было бы добавить switch case и убрать туда два разных поиска

        //общий поиск
        //      mainApp.search(catalog);

        // поиск с ограничением выдачи
        mainApp.searchWithLimitOfBooks(catalog);

    }

    private void putBooksToCatalog(Map<Box, List<Book>> catalog, Book... books) {
        for (Book book : books) {
            char alphabetIndex = book.getAlphabetIndex();
            Box box = createBoxForCheck(alphabetIndex);
            if (catalog.containsKey(box)) {
                List<Book> booksLetter = catalog.get(box);
                booksLetter.add(book);
            } else {
                List<Book> booksList = new ArrayList<>();
                booksList.add(book);
                catalog.put(box, booksList);
            }
        }
    }

    private Box createBoxForCheck(char alphabetIndex) {
        return new Box(alphabetIndex);
    }

    private char menu() {
        Scanner scn = new Scanner(System.in);
        System.out.println("\n <--- Поиск по каталогу --->");
        System.out.println("Для поиска введите букву, " +
                "Для выхода - 0");
        return scn.nextLine().toLowerCase().charAt(0);
    }

    private void search(Map<Box, List<Book>> catalog) {
        char choice = menu();
        while (choice != '0') {
            System.out.println("буква " + choice);
            System.out.println(catalog.get(createBoxForCheck(choice)));
            choice = menu();
        }
        System.exit(0);
    }

    private void searchWithLimitOfBooks(Map<Box, List<Book>> catalog) {
        char choice = menu();
        while (choice != '0') {
            System.out.println("Книги на букву " + choice);
            System.out.println("Введите цифру ограничения вывода");
            Scanner scanner = new Scanner(System.in);
            int limit = scanner.nextLine().toLowerCase().charAt(0) - '1';
            List<Book> books = catalog.get(createBoxForCheck(choice));
            books.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
            for (int i = 0; i <= limit & i < books.size(); i++) {
                System.out.println(books.get(i));
            }
            choice = menu();
        }
        System.exit(0);
    }

}
