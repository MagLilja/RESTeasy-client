package client;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.Profile;
//import org.jboss.resteasy.client.jaxrs.ResteasyClient;
//import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
//import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
//
//import javax.ws.rs.ProcessingException;
//import javax.ws.rs.client.Entity;
//import javax.ws.rs.core.GenericType;
//import javax.ws.rs.core.Response;
import java.util.List;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

public class ClientService {
    static String uri = "http://164.92.231.95:8080/Grupp1ServerApplication/api";
    static Client client = ClientBuilder.newBuilder().build();

    static Profile addProfile(Profile newProfile) {
        try {
            return client.target(uri + "/profiles")
                    .request()
                    .buildPost(Entity.entity(newProfile, "application/JSON"))
                    .invoke()
                    .readEntity(Profile.class);
        } catch (Exception ex) {
            System.err.println("This resource does not exist");
            return null;
        }
    }

    static Profile getProfileById(int id) {

        try {
            WebTarget webTarget = client.target(uri + "/profiles/" + id);
            Response response = webTarget.request().get();
            Profile profile = response.readEntity(Profile.class);
            return profile;
        } catch (Exception ex) {
            System.err.println("This resource does not exist");
            return null;
        }
    }

    static boolean deleteProfile(int id) {
        try {
            WebTarget webTarget = client.target(uri + "/profiles/" + id);
            Response response = webTarget.request().delete();
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
                .request()
                .get()
                .readEntity(new GenericType<List<Profile>>() {
        });
    }

    static List<Profile> searchByLastname(String lastName) {
        return client.target(uri + "/profiles/search?lastname=" + lastName)
                .request()
                .get()
                .readEntity(new GenericType<List<Profile>>() {
        });
    }

    static List<Profile> searchByFirstname(String firstName) {
        return client
                .target(uri + "/profiles/search?firstname=" + firstName)
                .request()
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
}