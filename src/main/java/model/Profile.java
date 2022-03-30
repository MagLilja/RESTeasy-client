package model;

public class Profile {

    int id;
    private String userName;
    private String firstName;
    private String lastName;

    public Profile(String userName, String firstName, String lastName) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public Profile() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Profile (" +
                "id" + id +
                ") = Username: '" + userName + '\'' +
                ", Firstname: '" + firstName + '\'' +
                ", Lastname:'" + lastName + '\'';
    }
}
