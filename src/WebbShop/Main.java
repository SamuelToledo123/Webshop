package WebbShop;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws Exception {

        try {


            Repository repository = new Repository();

            Scanner scan = new Scanner(System.in);

            System.out.println("ange namn");
            String userName = scan.nextLine();

            System.out.println("ange lösen");
            String passWord = scan.nextLine();

            if (repository.validateUser(userName, passWord)) {
                System.out.println("inloggning funka!");
                System.out.println();
                System.out.println();
                System.out.println("välj Kategori");
                System.out.println("1. Boots");
                System.out.println("2. Finskor");
                System.out.println("3. Outdoor");
                System.out.println("4. Sneakers");
                System.out.println("5. Träning");
                System.out.println("6. Avsluta");


                int choice = scan.nextInt();

                List<CategoryTable> categoryList = repository.getCategories();
                List<ProductTable> productList = repository.getProduct();

                switch (choice) {

                    case 1:
                        case 2:
                            case 3:
                                case 4:


                    case 5:
                        productList.stream()
                                .filter(p -> p.getCategoryId() == choice)
                                .forEach(p -> System.out.println(p.printInfo()));
                        break;
                    case 6:
                        System.out.println("Avslutar");
                        break;

                    default:
                        System.out.println("ogiltigt svar");
                }
            } else {
                System.out.println("inloggning funkaar ej");
            }
            scan.close();
        } catch (InputMismatchException e) {
        }
    }
}
