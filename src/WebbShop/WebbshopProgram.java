package WebbShop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class WebbshopProgram {
    public static void main(String[] args) throws IOException {

       //Hämtar alla behövande klasser
        Repository repository = new Repository();
        UserPrompt user = new UserPrompt();
        List<Integer> productIDs = new ArrayList<>();
        Scanner scan = new Scanner(System.in);

        int userID = user.Login();


        if (userID != -1) {

            boolean continueOrdering = false;
            do {

                user.UserFirstMenu();
                int choice = scan.nextInt();

                List<CategoryTable> categoryList = repository.getCategories();
                List<ProductTable> productList = repository.getProduct();
                List<CustomerTable> customerTable = repository.getPerson();

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
                        //Sparar alla produktID i en Lista
                        productIDs.add(productID);

                        int orderID = 0;
                        repository.addToCart(userID, orderID, productID);


                        System.out.println("Vill du beställa mer? JA/NEJ");
                        String orderMoreInput = scan.next();

                        if (orderMoreInput.equalsIgnoreCase("JA")) {
                            continueOrdering = true;

                        } else if (orderMoreInput.equalsIgnoreCase("nej")) {
                            continueOrdering = false;

                            System.out.println("Beställningar:");
                           /* String receipt = productList.stream()
                                    .filter(p -> p.getId() == productID)
                                    .map(p -> String.format("Märke: %s, Storlek: %d, Pris: %d kr", p.getBrand(), p.getSize(), p.getPrice()))
                                    .collect(Collectors.joining("\n"));
                            System.out.println(receipt);
                            */

                            String receipt = productIDs.stream()  //
                                    .map(productId -> productList.stream() // For each productID, stream over productList
                                             .filter(p -> p.getId() == productId) // Filtrera id
                                            .map(p -> String.format("Märke: %s, Storlek: %d, Pris: %d kr", p.getBrand(), p.getSize(), p.getPrice()))
                                            .collect(Collectors.joining("\n"))) // Rad ner per produkt
                                    .collect(Collectors.joining("\n")); // Join the receipts for all products
                            System.out.println(receipt);

                        }

                        break;
                    case 6:
                        System.out.println("Välkommen åter");
                        break;

                    case 7:
                        System.out.println("PersonligInfo");
                        String info = customerTable.stream()
                                .filter(p -> p.getId() == userID)
                                .map(p -> String.format("namn: %s, personnummer: %d, telenr: %d", p.getNamn(), p.getSocialSecurityNumber(), p.getPhoneNumber()))
                                .collect(Collectors.joining("\n"));
                        System.out.println(info);

                }

            } while (continueOrdering);

        } else {
            System.out.println("inloggning funkaar ej");
        }
        scan.close();
    }
}
