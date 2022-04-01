package client;


import model.Profile;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;


public class ClientApplication {

    public static void main(String[] args) {
        int id = 0;

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                printOutMenu();
                String choice = scanner.nextLine();
                switch (choice) {
                    case "1":
                        showAllProfiles(scanner);
                        break;
                    case "2":
                        showProfileByID(scanner);
                        break;
                    case "3":
                        addProfile(scanner);
                        break;
                    case "4":
                        printOutSearchMenu();
                        String searchChoice = scanner.nextLine();
                        switch (searchChoice) {
                            case "1":
                                searchForFirstName(scanner);
                                break;
                            case "2":
                                searchForLastName(scanner);
                                break;
                            default:
                                break;
                        }
                        break;
                    case "5":
                        updateProfile(scanner);
                        break;
                    case "6":
                        deleteProfile(scanner);
                        break;
                    case "0":
                        System.exit(0);
                        break;

                    default:
                        System.err.println("Invalid input. Try again.");
                        break;
                }
            }
        }
    }

    private static void deleteProfile(Scanner scanner) {
        System.out.print("Enter profile id to delete: ");
        int idToDelete = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\n:---------- Delete Profile By ID = " + idToDelete + " ----------:\n");
        String response = (ClientService.deleteProfile(idToDelete)) ? "Successfully deleted" : "";
        System.out.println(response);
        waitForInput(scanner);
    }

    private static void updateProfile(Scanner scanner) {
        System.out.print("ID of profile to update: ");
        int updateId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter new username: ");
        String newUserName = scanner.nextLine();
        System.out.print("Enter new firstname: ");
        String newFirstName = scanner.nextLine();
        System.out.print("Enter new lastname: ");
        String newLastName = scanner.nextLine();

        Profile profileToUpdate = ClientService.getProfileById(updateId);
        if (!newUserName.equals("")) {
            profileToUpdate.setUserName(newUserName);
        }
        if (!newFirstName.equals("")) {
            profileToUpdate.setFirstName(newFirstName);
        }
        if (!newLastName.equals("")) {
            profileToUpdate.setLastName(newLastName);
        }
        System.out.println("\n:---------- Profile With ID Updated = " + updateId + " ----------:\n");
        Profile updatedProfile = ClientService.updateProfile(updateId, profileToUpdate);
        System.out.println(updatedProfile);
        waitForInput(scanner);
    }

    private static void searchForLastName(Scanner scanner) {
        System.out.println("Search by lastname: ");
        String searchByLastName = scanner.nextLine();
        System.out.println("\n:---------- Search By Lastname  = \"" + searchByLastName + "\" ----------:\n");
        ClientService.searchByLastname(searchByLastName).forEach(System.out::println);
        waitForInput(scanner);
    }

    private static void searchForFirstName(Scanner scanner) {
        System.out.println("Search for: ");
        String searchByFirstName = scanner.nextLine();
        System.out.println("\n:---------- Search By Firstname  = \"" + searchByFirstName + "\"  ----------:\n");
        ClientService.searchByFirstname(searchByFirstName).forEach(System.out::println);
        waitForInput(scanner);
    }

    private static void printOutSearchMenu() {
        System.out.println("Search by:");
        System.out.println("1. Firstname");
        System.out.println("2. Lastname");
        System.out.println("0. Return to main menu");
    }

    private static void addProfile(Scanner scanner) {
        System.out.println("\n:---------- Add Profile ----------:\n");

        System.out.println("Creating new profile.");
        System.out.println("Enter username: ");
        String userName = scanner.nextLine();
        System.out.println("Enter firstname: ");
        String firstName = scanner.nextLine();
        System.out.println("Enter lastname: ");
        String lastName = scanner.nextLine();

        Profile newProfile = new Profile(userName, firstName, lastName);

        if (ClientService.addProfile(newProfile) != null) {
            System.out.println("New profile created!");
        } else {
            System.out.println("Profile not created, try again!");
        }
        waitForInput(scanner);
    }

    private static void showProfileByID(Scanner scanner) {
        int id;
        System.out.print("Enter profile id get: ");
        id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\n:---------- Profile By Id = " + id + " ----------:\n");
        System.out.println(ClientService.getProfileById(id));
        waitForInput(scanner);
    }

    private static void showAllProfiles(Scanner scanner) {
        System.out.println(":---------- Connecting... ----------:");
        List<Profile> allProfiles = ClientService.getAllProfiles();
        System.out.println("\n:---------- All Profiles ----------:\n");
        allProfiles.forEach(System.out::println);
        waitForInput(scanner);
    }

    private static void printOutMenu() {
        System.out.println("Menu options");
        System.out.println("1. Get All Profiles");
        System.out.println("2. Get Profile By Id");
        System.out.println("3. Add Profile");
        System.out.println("4. Search All Profiles");
        System.out.println("5. Update Profile");
        System.out.println("6. Delete Profile");
        System.out.println("0. Exit");
    }

    private static void waitForInput(Scanner scanner) {
        System.out.println();
        System.out.println("Press any button to continue...");
        scanner.nextLine();
    }
}

