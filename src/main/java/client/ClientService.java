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

public class ClientService {
    static String uri = "http://164.92.231.95:8080/Grupp1ServerApplication/api";
    static ResteasyClient client = new ResteasyClientBuilder().build();

    static Profile addProfile(Profile newProfile) {

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

    static Profile getProfileById(int id) {

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

    static boolean deleteProfile(int id) {
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

    static List<Profile> searchByLastAndFirstName(String lastName, String firstName) {
        ResteasyWebTarget webTarget = client.target(uri + "/profiles/search?lastname=" + lastName + "&firstname=" + firstName);
        Response response = webTarget.request().get();
        return response.readEntity(new GenericType<List<Profile>>() {
        });
    }

    static List<Profile> searchByLastname(String lastName) {
        ResteasyWebTarget webTarget = client.target(uri + "/profiles/search?lastname=" + lastName);
        Response response = webTarget.request().get();
        return response.readEntity(new GenericType<List<Profile>>() {
        });
    }

    static List<Profile> searchByFirstname(String firstName) {
        ResteasyWebTarget webTarget = client.target(uri + "/profiles/search?firstname=" + firstName);
        Response response = webTarget.request().get();
        return response.readEntity(new GenericType<List<Profile>>() {
        });
    }

    static List<Profile> getAllProfiles() {
        ResteasyWebTarget webTarget = client.target(uri + "/profiles");
        Response response = webTarget.request().get();
        return response.readEntity(new GenericType<List<Profile>>() {
        });
    }
}