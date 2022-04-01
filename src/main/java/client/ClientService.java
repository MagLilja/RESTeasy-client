package client;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.Response;
import model.Profile;

import java.util.List;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

public class ClientService {
    static String uri = "http://164.92.231.95:8080/Grupp1ServerApplication/api";
    static Client client = ClientBuilder.newBuilder().build();

    static Profile addProfile(Profile newProfile) {
        Response response = null;
        try {
            response = client.target(uri + "/profiles")
                    .request(APPLICATION_JSON)
                    .buildPost(Entity.entity(newProfile, "application/JSON"))
                    .invoke()
            ;
            return response
                    .readEntity(Profile.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println("Client error " + response.getStatus());
            return null;
        }
    }

    static Profile getProfileById(int id) {
        try {
            return client
                    .target(uri + "/profiles/" + id)
                    .request(APPLICATION_JSON)
                    .get()
                    .readEntity(Profile.class);
        } catch (Exception ex) {
            System.err.println("This resource does not exist");
            return null;
        }
    }

    static boolean deleteProfile(int id) {
        try {
            Response response = client
                    .target(uri + "/profiles/" + id)
                    .request(APPLICATION_JSON)
                    .delete();
            if (response.getStatus() == 204) {
                return true;
            }
            return false;
        } catch (Exception ex) {
            System.err.println("This resource does not exist");
            return false;
        }
    }

    static List<Profile> searchByLastAndFirstName(String lastName, String firstName) {
        return client.target(uri + "/profiles/search?lastname=" + lastName + "&firstname=" + firstName)
                .request(APPLICATION_JSON)
                .get()
                .readEntity(new GenericType<List<Profile>>() {
        });
    }

    static List<Profile> searchByLastname(String lastName) {
        return client.target(uri + "/profiles/search?lastname=" + lastName)
                .request(APPLICATION_JSON)
                .get()
                .readEntity(new GenericType<List<Profile>>() {
        });
    }

    static List<Profile> searchByFirstname(String firstName) {
        return client
                .target(uri + "/profiles/search?firstname=" + firstName)
                .request(APPLICATION_JSON)
                .get()
                .readEntity(new GenericType<List<Profile>>() {
                });
    }

    static List<Profile> getAllProfiles() {
        return client
                .target(uri + "/profiles")
                .request(APPLICATION_JSON)
                .get()
                .readEntity(new GenericType<List<Profile>>() {
                });
    }

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