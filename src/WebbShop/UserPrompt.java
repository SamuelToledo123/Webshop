package WebbShop;

import java.io.IOException;
import java.util.Scanner;

public class UserPrompt {
    Repository repository = new Repository();

    public UserPrompt() {

    }


    public void orderMoreInput() {


    }

    public int Login() {

        Scanner scan = new Scanner(System.in);

        while(true) {

            try {

                System.out.print("Ange namn: ");
                String userName = scan.nextLine().trim();

                System.out.print("Ange lösen: ");
                String passWord = scan.nextLine().trim();
                int userID = repository.getUserID(userName, passWord);


                if (repository.validateUser(userName, passWord)) {
                    System.out.println("Välkommen! " + userName);
                    System.out.println("---------------------");
                    return userID;
                } else {
                    System.out.println("Försök igen");
                }
            } catch (IOException e) {
                throw new RuntimeException("Personen finns inte med i databasen");

            }
        }
    }
    public void UserFirstMenu() {

        System.out.println("VÄLJ KATEGORI");
        System.out.println("1. Boots");
        System.out.println("2. Finskor");
        System.out.println("3. Outdoor");
        System.out.println("4. Sneakers");
        System.out.println("5. Träning");
        System.out.println("6. Avsluta");
        System.out.println("7. Personlig Information");
    }
}




