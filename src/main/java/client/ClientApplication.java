package client;


import model.Profile;

import java.util.Scanner;


public class ClientApplication {

    public static void main(String[] args) {
        int id = 0;

        try (Scanner scanner = new Scanner(System.in)) {

            System.out.println(":---------- Connecting... ----------:");
            ClientService.getAllProfiles();


            System.out.println("\n:---------- Get All Profiles ----------:\n");
            ClientService.getAllProfiles().forEach(System.out::println);
            System.out.println();
            System.out.print("Enter profile id get: ");

            id = scanner.nextInt();
            System.out.println("\n:---------- Get Profile By Id = " + id + " ----------:\n");
            System.out.println(ClientService.getProfileById(id));

            scanner.nextLine();
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


            System.out.println("\n:---------- Get All Profiles ----------:\n");
            ClientService.getAllProfiles().forEach(System.out::println);

            System.out.println();
            System.out.println("Search by lastname: ");
            lastName = scanner.nextLine();
            System.out.println("\n:---------- Search By Lastname  = \"" + lastName + "\" ----------:\n");
            ClientService.searchByLastname(lastName).forEach(System.out::println);

            System.out.println();
            System.out.println("Search by firstname: ");
            firstName = scanner.nextLine();
            System.out.println("\n:---------- Search By Firstname  = \"" + firstName + "\"  ----------:\n");
            ClientService.searchByFirstname(firstName).forEach(System.out::println);

            scanner.nextLine();
            System.out.println("\n:---------- Search ByLast And FirstName ----------:\n");
            ClientService.searchByLastAndFirstName(lastName, firstName).forEach(System.out::println);

            scanner.nextLine();
            System.out.print("Enter profile id to delete: ");
            id = scanner.nextInt();
            System.out.println("\n:---------- Delete Profile By ID = " + id + " ----------:\n");
            String response = (ClientService.deleteProfile(id)) ? "Successfully deleted" : "";
            System.out.println(response);
        }


    }


    // UPDATE a profile


}
