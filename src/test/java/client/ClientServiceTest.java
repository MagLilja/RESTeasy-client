package client;


import model.Profile;
import org.junit.jupiter.api.Test;

import java.util.List;

class ClientServiceTest {

    @Test
    void updateProfileTest() {
        // given
        int updateId = 2;
        Profile profileToUpdate = ClientService.getProfileById(updateId);
        String newUserName = "cooool";
        String newFirstName = "hej";
        String newLastName = "";

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
        // when

        // then
    }

    @Test
    void addProfileTest() {
        // given
        Profile newProfile1 = new Profile("mag", "mag", "mag");

        if (ClientService.addProfile(newProfile1) != null) {
            System.out.println("New profile created!");
        } else {
            System.out.println("Profile not created, try again!");
        }
    }
}