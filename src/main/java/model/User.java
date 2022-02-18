package model;

import java.util.UUID;

/**
 * <h2>Java Implementation of User Schema</h2>
 */
public class User {
    /**
     * Unique username for user
     */
    private String username;
    /**
     * User's password
     */
    private String password;
    /**
     * User's email address
     */
    private String email;
    /**
     * User's first name
     */
    private String firstName;
    /**
     * User's last name
     */
    private String lastName;
    /**
     * User's gender
     */
    private char gender;

    /**
     * No parameters may be null
     * @param username
     * @param password
     * @param email
     * @param firstName
     * @param lastName
     * @param gender
     */
    public User(String username, String password, String email, String firstName, String lastName, char gender) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    // Overridden Equals method
    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()){
            return false;
        }
        if (this == obj){
            return true;
        }

        User usr = (User) obj;

        return (
            username.equals(usr.username) &&
            password.equals(usr.password) &&
            email.equals(usr.email) &&
            firstName.equals(usr.firstName) &&
            lastName.equals(usr.lastName) &&
            gender == usr.gender
        );
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }
}
