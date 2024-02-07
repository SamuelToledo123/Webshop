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
                System.out.println("inloggning funka");
                System.out.println("välj ett alternativ");
                System.out.println("1. visa skor");
                System.out.println("2.avsluta program");



                int choice = scan.nextInt();

                List<ProductTable> productList = repository.getProduct();

                switch (choice) {

                    case 1:
                        productList.forEach(p -> System.out.println(p.printInfo()));
                        break;
                    case 2:
                        System.out.println("avslutar program");
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
