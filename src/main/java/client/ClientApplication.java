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

            System.out.println(":---------- Connecting... ----------:");
            List<Profile> allProfiles = ClientService.getAllProfiles();

            System.out.println("Menu options");
            System.out.println("1. Get All Profiles");
            System.out.println("2. Get Profile By Id");
            System.out.println("3. Add Profile");
            System.out.println("4. Search All Profiles");
            System.out.println("5. Update Profile");
            System.out.println("6. Delete Profile");
            System.out.println("0. Exit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\n:---------- Get All Profiles ----------:\n");
                    allProfiles.forEach(System.out::println);
                    break;
                case 2:
                    System.out.print("Enter profile id get: ");
                    id = scanner.nextInt();
                    System.out.println("\n:---------- Get Profile By Id = " + id + " ----------:\n");
                    System.out.println(ClientService.getProfileById(id));
                    break;
                case 3:
                    System.out.println("\n:---------- Add Profile ----------:\n");

                    System.out.println("Creating new profile.");
                    System.out.println("Enter username: ");
                    String userName = scanner.nextLine();
                    System.out.println("Enter firstname: ");
                    String firstName = scanner.nextLine();
                    System.out.println("Enter lastname: ");
                    String lastName = scanner.nextLine();

                    Profile newProfile = new Profile(userName, firstName, lastName);

                    System.out.println(ClientService.addProfile(newProfile));
                    System.out.println("New profile created!");
                    break;
                case 4:
                    System.out.println("Search by:");
                    System.out.println("1. First name");
                    System.out.println("2. Last name");
                    System.out.println("0. Return to main menu");
                    int searchChoice = scanner.nextInt();
                    switch (searchChoice) {
                        case 1:
                            System.out.println("Search for: ");
                            String searchByFirstName = scanner.nextLine();
                            System.out.println("\n:---------- Search By Firstname  = \"" + searchByFirstName + "\"  ----------:\n");
                            ClientService.searchByFirstname(searchByFirstName).forEach(System.out::println);
                            break;
                        case 2:
                            System.out.println("Search by lastname: ");
                            String searchByLastName = scanner.nextLine();
                            System.out.println("\n:---------- Search By Lastname  = \"" + searchByLastName + "\" ----------:\n");
                            ClientService.searchByLastname(searchByLastName).forEach(System.out::println);
                            break;
                        case 0:
                            return;
                    }

                    break;
                case 5:
                    System.out.print("ID of profile to update: ");
                    int updateId = scanner.nextInt();
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
                    break;
                case 6:
                    System.out.print("Enter profile id to delete: ");
                    int idToDelete = scanner.nextInt();
                    System.out.println("\n:---------- Delete Profile By ID = " + idToDelete + " ----------:\n");
                    String response = (ClientService.deleteProfile(idToDelete)) ? "Successfully deleted" : "";
                    System.out.println(response);

                    break;
                case 0:
                    System.exit(0);
                    break;
            }


//            scanner.nextLine();
//            System.out.println("\n:---------- Search ByLast And FirstName ----------:\n");
//            ClientService.searchByLastAndFirstName(lastName, firstName).forEach(System.out::println);

            scanner.nextLine();


            scanner.nextLine();


        }
    }


}


// UPDATE a profile



