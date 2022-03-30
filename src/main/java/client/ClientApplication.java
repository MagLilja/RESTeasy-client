package client;


import model.Profile;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Scanner;


public class ClientApplication {
    private static String uri = "http://164.92.231.95:8080/Grupp1ServerApplication/api";

    public static void main(String[] args) {
        int id = 1;
        Scanner scanner = new Scanner(System.in);

        System.out.println(":---------- Connecting... ----------:");
        getAllProfiles();


        System.out.println("\n:---------- getAllProfiles ----------:\n");
        getAllProfiles().forEach(System.out::println);
        System.out.println();
        System.out.print("Enter profile id get: ");

        id = scanner.nextInt();
        System.out.println("\n:---------- getProfileById = " + id + " ----------:\n");
        System.out.println(getProfileById(id));

        scanner.nextLine();
        System.out.println("\n:---------- AddProfile ----------:\n");

        System.out.println("Creating new profile.");
        System.out.println("Enter username: ");
        String userName = scanner.nextLine();
        System.out.println("Enter firstname: ");
        String firstName = scanner.nextLine();
        System.out.println("Enter lastname: ");
        String lastName = scanner.nextLine();

        Profile newProfile = new Profile(userName, firstName, lastName);

        System.out.println(addProfile(newProfile));
        System.out.println("New profile created!");


        System.out.println("\n:---------- getAllProfiles ----------:\n");
        getAllProfiles().forEach(System.out::println);

        System.out.println();
        System.out.println("Search by lastname: ");
        lastName = scanner.nextLine();
        System.out.println("\n:---------- searchByLastname  = \"" + lastName + "\" ----------:\n");
        searchByLastname(lastName).forEach(System.out::println);

        System.out.println();
        System.out.println("Search by firstname: ");
        firstName = scanner.nextLine();
        System.out.println("\n:---------- searchByFirstname  = \"" + firstName + "\"  ----------:\n");
        System.out.println(searchByFirstname(firstName));

        scanner.nextLine();
        System.out.println("\n:---------- searchByLastAndFirstName ----------:\n");
        System.out.println(searchByLastAndFirstName(lastName, firstName));

        scanner.nextLine();
        System.out.print("Enter profile id to delete: ");
        id = scanner.nextInt();
        System.out.println("\n:---------- DeleteProfileByID = " + id + " ----------:\n");
        String response = (deleteProfile(id)) ? "Successfully deleted" : "";
        System.out.println(response);


    }

    private static Profile addProfile(Profile newProfile) {
        ResteasyClient client = new ResteasyClientBuilder().build();
        try {
            ResteasyWebTarget webTarget = client.target(uri + "/profiles");
            Entity newProfileEntity = Entity.entity(newProfile, "application/JSON");
            Response response = webTarget.request().buildPost(newProfileEntity).invoke();
            return response.readEntity(Profile.class);
        } catch (ProcessingException ex) {
            System.err.println("This resource does not exist");
            return null;
        }
    }

    private static Profile getProfileById(int id) {
        ResteasyClient client = new ResteasyClientBuilder().build();
        try {
            ResteasyWebTarget webTarget = client.target(uri + "/profiles/" + id);
            Response response = webTarget.request().get();
            Profile profile = response.readEntity(Profile.class);
            return profile;
        } catch (ProcessingException ex) {
            System.err.println("This resource does not exist");
            return null;
        }
    }

    private static boolean deleteProfile(int id) {
        ResteasyClient client = new ResteasyClientBuilder().build();
        try {
            ResteasyWebTarget webTarget = client.target(uri + "/profiles/" + id);
            Response response = webTarget.request().delete();
            Profile profile = response.readEntity(Profile.class);
            if (response.getStatus() == 204) {
                return true;
            }
            return false;
        } catch (ProcessingException ex) {
            System.err.println("This resource does not exist");
            return false;
        }
    }

    private static List<Profile> searchByLastAndFirstName(String lastName, String firstName) {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget webTarget = client.target(uri + "/profiles/search?lastname=" + lastName + "&firstname=" + firstName);
        Response response = webTarget.request().get();
        return response.readEntity(new GenericType<List<Profile>>() {
        });
    }


    private static List<Profile> searchByLastname(String lastName) {
        // http://164.92.231.95:8080/Grupp1ServerApplication/api/profiles/search?lastname=and
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget webTarget = client.target(uri + "/profiles/search?lastname=" + lastName);
        Response response = webTarget.request().get();
        return response.readEntity(new GenericType<List<Profile>>() {
        });
    }

    private static List<Profile> searchByFirstname(String firstName) {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget webTarget = client.target(uri + "/profiles/search?firstname=" + firstName);
        Response response = webTarget.request().get();
        return response.readEntity(new GenericType<List<Profile>>() {
        });
    }

    private static List<Profile> getAllProfiles() {

        //http://164.92.231.95:8080/Grupp1ServerApplication/api/profiles

        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget webTarget = client.target(uri + "/profiles");
        Response response = webTarget.request().get();
        return response.readEntity(new GenericType<List<Profile>>() {
        });
    }

    // UPDATE en profile


}
