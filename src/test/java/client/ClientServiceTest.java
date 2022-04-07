package client;


import jakarta.ws.rs.core.Response;
import model.Profile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ClientServiceTest {

    // The tests needs mocking to be correct tests!

    @Test
    void updateProfileMethodShouldReturnStatus202Test() {
        // given
        int updateId = 2;
        Profile profileToUpdate = new Profile();
        profileToUpdate.setUserName("cooool");
        profileToUpdate.setFirstName("firstname");
        profileToUpdate.setLastName("lastname");
        // when
        Profile response = ClientService.updateProfile(updateId, profileToUpdate);
        // then
        assertNotNull(response);
    }

}