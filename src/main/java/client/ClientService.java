package client;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.Response;
import model.Profile;

import java.util.List;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * A static service class for the Client Application with methods to perform CRUD operations on the server.
 * @author Magnus Lilja
 * @author Andreas Karlsson
 */
public class ClientService {
    static String uri = "http://164.92.231.95:8080/Grupp1ServerApplication/api";
    static Client client = ClientBuilder.newBuilder().build();

    /**
     * Method to add a profile to the server through a POST request.
     * @param newProfile to add
     * @return the new profile on success, else it prints the error and returns null.
     */
    static Profile addProfile(Profile newProfile) {
        try (Response response = client.target(uri + "/profiles")
                .request(APPLICATION_JSON)
                .buildPost(Entity.entity(newProfile, "application/JSON"))
                .invoke();) {
            if (response.getStatus() >= 400) {
                System.err.println(response.readEntity(String.class));
                return null;
            }
            return response
                    .readEntity(Profile.class);
        }
    }

    /**
     * Method to find a profile on the server by id.
     * @param id of the profile.
     * @return a profile on success, otherwise returns null.
     */
    static Profile getProfileById(int id) {
        try (Response response = client
                .target(uri + "/profiles/" + id)
                .request(APPLICATION_JSON)
                .get();) {

            if (response.getStatus() >= 400) {
                System.err.println(response.readEntity(String.class));
                return null;
            }
            return response.readEntity(Profile.class);
        }
    }

    /**
     * Method to delete a profile on the server through a DELETE request.
     * @param id of the profile to delete
     * @return true on success and false on fail.
     */
    static boolean deleteProfile(int id) {
        try (Response response = client
                .target(uri + "/profiles/" + id)
                .request(APPLICATION_JSON)
                .delete();) {
            if (response.getStatus() != 204) {
                return false;
            }
            return true;
        }
        catch (Exception ex) {
            System.err.println("Error " + ex.getMessage());
        }
        return false;
    }

    /**
     * Method to search for a profile by firstname and lastname through a GET request.
     * @param firstName to search for
     * @param lastName to search for
     * @return a list of profiles matching the search from the server.
     */
    static List<Profile> searchByLastAndFirstName(String lastName, String firstName) {
        return client.target(uri + "/profiles/search?lastname=" + lastName + "&firstname=" + firstName)
                .request(APPLICATION_JSON)
                .get()
                .readEntity(new GenericType<List<Profile>>() {
                });
    }

    /**
     * Method to search for a profile by lastname through a GET request.
     * @param lastName to search for
     * @return a list of profiles matching the search from the server.
     */
    static List<Profile> searchByLastname(String lastName) {
        return client.target(uri + "/profiles/search?lastname=" + lastName)
                .request(APPLICATION_JSON)
                .get()
                .readEntity(new GenericType<List<Profile>>() {
                });
    }

    /**
     * Method to search for a profile by firstname through a GET request.
     * @param firstName to search for
     * @return a list of profiles matching the search from the server.
     */
    static List<Profile> searchByFirstname(String firstName) {
        return client
                .target(uri + "/profiles/search?firstname=" + firstName)
                .request(APPLICATION_JSON)
                .get()
                .readEntity(new GenericType<List<Profile>>() {
                });
    }

    /**
     * Method to get all profiles from the server through a GET request
     * @return a list of profiles.
     */
    static List<Profile> getAllProfiles() {
        return client
                .target(uri + "/profiles")
                .request(APPLICATION_JSON)
                .get()
                .readEntity(new GenericType<List<Profile>>() {
                });
    }

    /**
     * Method to update a profile on the server through a PUT request.
     * @param id of the profile to update
     * @param profile with changes
     * @return on success returns the profile with changes, else returns null and prints the error status.
     */
    static Profile updateProfile(int id, Profile profile) {
        Response response = null;
        try {
            response = client
                    .target(uri + "/profiles/" + id)
                    .request(APPLICATION_JSON)
                    .buildPut(Entity.entity(profile, "application/JSON"))
                    .invoke();
            return response
                    .readEntity(Profile.class);
        } catch (Exception ex) {
            System.out.println("Error " + response.getStatus());
            return null;
        }
    }
}