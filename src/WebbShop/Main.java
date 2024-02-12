package WebbShop;

import java.util.ArrayList;
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

            List<Integer> productIDs = new ArrayList<>();

            if (repository.validateUser(userName, passWord)) {


                boolean continueOrdering = false;
                do {
                    userPrompt.UserFirstMenu();


                    int choice = scan.nextInt();

                    List<CategoryTable> categoryList = repository.getCategories();
                    List<ProductTable> productList = repository.getProduct();
                    List<CustomerTable>  customerTable = repository.getPerson();

                    switch (choice) {

                        case 1, 2, 3, 4, 5:

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
                            productIDs.add(productID);

                            System.out.println("Vill du beställa mer? JA/NEJ");
                            String orderMoreInput = scan.next();

                            if (orderMoreInput.equalsIgnoreCase("JA")) {
                                continueOrdering = true;

                            } else if (orderMoreInput.equalsIgnoreCase("nej")) {
                               continueOrdering = false;

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
                            System.out.println("PersonligInfo");
                            String info = customerTable.stream()
                                    .filter(p -> p.getId() == userID)
                                    .map(p-> String.format("namn: %s, personnummer: %d, telenr: %d",p.getNamn(),p.getSocialSecurityNumber(),p.getPhoneNumber()))
                                    .collect(Collectors.joining("\n"));
                            System.out.println(info);


                        default:
                            System.out.println("ogiltigt svar");
                    }
                    // int choiche2 = scan.nextInt();
                    //  System.out.println("du valde test " + choiche2);


                } while(continueOrdering);

            } else {
                System.out.println("inloggning funkaar ej");
            }
            scan.close();
        } catch (InputMismatchException e) {
        }

    }
}
