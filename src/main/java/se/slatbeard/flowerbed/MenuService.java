package se.slatbeard.flowerbed;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;
import java.util.Scanner;

@Service
public class MenuService {

    @Autowired
    private FlowerService flowerService;
    private final Scanner scanner = new Scanner(System.in);

    public void displayMenu() {

        while (true) {
            clearConsole();
            System.out.println("\nHuvud Meny");
            System.out.println("1. Lägg, ta bort, eller uppdatera Blommor");
            System.out.println("2. Lägg till en ny bukett såld");
            System.out.println("3. Visa alla blommor planterade");
            System.out.println();
            System.out.println("0. Avsluta");
            System.out.print("Välj ett alternativ: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> flowerMenu();
                case 2 -> addBouquet();
                case 3 -> showAllFlowers();
                case 0 -> {
                    clearConsole();
                    System.out.println("Avslutar FlowerBed programmet. Hej då!");
                    return;
                }
                default -> System.out.println("Felaktikt val. Pröva igen.");
            }
        }
    }

    private void flowerMenu() {
        while (true) {
            clearConsole();
            System.out.println("\nBlomm Hantering:");
            System.out.println("1. Lägg till en Blomma");
            System.out.println("2. Ta bort en Blomma");
            System.out.println("3. Updatera en Blomma");
            System.out.println();
            System.out.println("0. Tillbaka till huvudmenyn");
            System.out.print("\nVälj ett alternativ: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addFlower();
                case 2 -> removeFlower();
                case 3 -> updateFlower();
                case 0 -> {
                    System.out.println("Går tillbaka till huvudmenyn...");
                    return;
                }
                default -> System.out.println("Felaktikt val. Pröva igen.");
            }
        }
    }

    private void addFlower() {
        clearConsole();
        System.out.print("Ange blomtyp: ");
        String type = scanner.nextLine();
        System.out.print("Ange blommängd: ");
        int quantity = scanner.nextInt();

        flowerService.addFlower(type, quantity);
        System.out.println("Blomma tillagd!");
    }

    private void removeFlower() {
        showAllFlowers();
        clearConsole();
        System.out.print("Skirv in blomID att ta bort: ");
        Long id = scanner.nextLong();

        flowerService.removeFlower(id);
        System.out.println("Blomma har tagits bort!");
    }

    private void updateFlower() {
        clearConsole();
        showAllFlowers();
        System.out.print("Skriv in blomID att uppdatera: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        Optional<Flower> optionalFlower = flowerService.getFlowerById(id);


        if (optionalFlower.isPresent()) {
            Flower flower = optionalFlower.get();

            System.out.print("Skirv in ny blomtyp (nuvarande: " + flower.getType() + "): ");
            String type = scanner.nextLine();
            System.out.print("Skriv in ny mängd (nuvarande: " + flower.getQuantity() + "): ");
            int quantity = scanner.nextInt();

            flower.setType(type);
            flower.setQuantity(quantity);
            flowerService.updateFlower(flower);

            System.out.println("Blomma uppdaterad!");
        } else {
            System.out.println("Ingen blomma fanns med det ID:t.");
        }
    }

    private void addBouquet() {
        System.out.print("Skriv in mängden av buketter sålda: ");
        int quantitySold = scanner.nextInt();
        System.out.print("Skriv in pris per bukett: ");
        double price = scanner.nextDouble();

        flowerService.addBouquet(quantitySold, price);
        System.out.println("Bukett försäljgning tillagd!");
    }

    private void showAllFlowers() {
        boolean running = true;
        while (running) {
            clearConsole();
            var flowers = flowerService.getAllFlowers();
            if (flowers.isEmpty()) {
                System.out.println("Inga blommor är nuvarnde inlagda.");
            } else {
                System.out.println("\nAlla blommor inlagda:");
                flowers.forEach(flower ->
                        System.out.println("ID: " + flower.getId() + ", Typ: " + flower.getType() + ", Mängd: " + flower.getQuantity()));
            }

            System.out.println("\nTryck på enter för att gå tillbaka");
            scanner.nextLine();
            running = false;

        }
    }

    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("oops");
        }


    }

    public static String centerText(String text, int width) {
        String[] lines = text.split("\n");
        StringBuilder centeredText = new StringBuilder();

        for (String line : lines) {
            int padding = (width - line.length()) / 2;
            if (padding > 0) {
                centeredText.append(" ".repeat(padding)).append(line).append("\n");
            } else {
                centeredText.append(line).append("\n");
            }
        }
        return centeredText.toString();
    }


}
