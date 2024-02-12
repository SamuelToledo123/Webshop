package WebbShop;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class Main {
    public static void main(String[] args) throws Exception {


        try {
            UserPrompt userPrompt = new UserPrompt();

            Repository repository = new Repository();



            Scanner scan = new Scanner(System.in);

            System.out.print("Ange namn: ");
            String userName = scan.nextLine().trim();

            System.out.print("Ange lösen: ");
            String passWord = scan.nextLine().trim();
            int userID = repository.getUserID(userName, passWord);


            if (repository.validateUser(userName, passWord)) {


               while(true) {
                   boolean continueLoop = false;

                   userPrompt.UserFirstMenu();


                   int choice = scan.nextInt();

                   List<CategoryTable> categoryList = repository.getCategories();
                   List<ProductTable> productList = repository.getProduct();

                   switch (choice) {

                       case 1:
                       case 2:
                       case 3:
                       case 4:


                       case 5:
                           System.out.println("Svara nedan för att välja produkt:");

                           productList.stream()
                                   .filter(p -> p.getCategoryId() == choice)
                                   .forEach(p -> System.out.println(p.printInfo()));


                           //Märke Input
                           System.out.print("Märke: ");
                           String brandChoice = scan.next();
                           //Storlek input
                           System.out.print("Storlek: ");
                           int sizeChoice = scan.nextInt();

                           //Hämtar produktID baserat på svar och sätter i AddToCart
                           int productID = repository.getProductID(sizeChoice, brandChoice);
                           //System.out.println("Product ID: " + productID);
                           int orderID = 0;
                           repository.addToCart(userID, orderID, productID);

                           System.out.println("Vill du beställa mer? JA/NEJ");
                           String orderMoreInput = scan.next();

                           if (orderMoreInput.equalsIgnoreCase("JA")) {
                               userPrompt.UserFirstMenu();

                           } else if (orderMoreInput.equalsIgnoreCase("nej")) {
                               System.out.println("Kvitto");
                               String receipt = productList.stream()
                                       .filter(p -> p.getId() == productID)
                                       .map(p -> String.format("Märke: %s, Storlek: %d, Pris: %d kr", p.getBrand(), p.getSize(), p.getPrice()))
                                       .collect(Collectors.joining("\n"));
                               System.out.println(receipt);

                           }


                           break;
                       case 6:
                           System.out.println("Avslutar");
                           break;

                       case 7:
                           //Här vill vi få User-information namn, tfn-nummer, beställning

                       default:
                           System.out.println("ogiltigt svar");
                   }
                   // int choiche2 = scan.nextInt();
                   //  System.out.println("du valde test " + choiche2);


               }

            } else {
                System.out.println("inloggning funkaar ej");
            }
            scan.close();
        } catch (InputMismatchException e) {
        }

    }
}
