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
            System.out.println("\nMain Menu:");
            System.out.println("1. Add, Remove, or Update Flowers");
            System.out.println("2. Add a New Bouquet Sold");
            System.out.println("3. Show All Flowers Planted");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> flowerMenu();
                case 2 -> addBouquet();
                case 3 -> showAllFlowers();
                case 4 -> {
                    System.out.println("Exiting the FlowerBed Application. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void flowerMenu() {
        while (true) {
            clearConsole();
            System.out.println("\nFlower Management:");
            System.out.println("1. Add a Flower");
            System.out.println("2. Remove a Flower");
            System.out.println("3. Update a Flower");
            System.out.println("4. Back to Main Menu");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addFlower();
                case 2 -> removeFlower();
                case 3 -> updateFlower();
                case 4 -> {
                    System.out.println("Returning to Main Menu...");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addFlower() {
        clearConsole();
        System.out.print("Enter flower type: ");
        String type = scanner.nextLine();
        System.out.print("Enter flower quantity: ");
        int quantity = scanner.nextInt();

        flowerService.addFlower(type, quantity);
        System.out.println("Flower added successfully!");
    }

    private void removeFlower() {
        clearConsole();
        System.out.print("Enter flower ID to remove: ");
        Long id = scanner.nextLong();

        flowerService.removeFlower(id);
        System.out.println("Flower removed successfully!");
    }

    private void updateFlower() {
        clearConsole();
        System.out.print("Enter flower ID to update: ");
        Long id = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        Optional<Flower> optionalFlower = flowerService.getFlowerById(id);
        if (optionalFlower.isPresent()) {
            Flower flower = optionalFlower.get();

            System.out.print("Enter new flower type (current: " + flower.getType() + "): ");
            String type = scanner.nextLine();
            System.out.print("Enter new quantity (current: " + flower.getQuantity() + "): ");
            int quantity = scanner.nextInt();

            flower.setType(type);
            flower.setQuantity(quantity);
            flowerService.updateFlower(flower);

            System.out.println("Flower updated successfully!");
        } else {
            System.out.println("No flower found with the provided ID.");
        }
    }

    private void addBouquet() {
        System.out.print("Enter number of bouquets sold: ");
        int quantitySold = scanner.nextInt();
        System.out.print("Enter price per bouquet: ");
        double price = scanner.nextDouble();

        flowerService.addBouquet(quantitySold, price);
        System.out.println("Bouquet sale recorded successfully!");
    }

    private void showAllFlowers() {
        var flowers = flowerService.getAllFlowers();
        if (flowers.isEmpty()) {
            System.out.println("No flowers are currently planted.");
        } else {
            System.out.println("\nAll Flowers Planted:");
            flowers.forEach(flower ->
                    System.out.println("ID: " + flower.getId() + ", Type: " + flower.getType() + ", Quantity: " + flower.getQuantity()));
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


}
