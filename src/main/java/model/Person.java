package model;

import java.util.Objects;

/**
 * <h2>Java Implementation of Person Schema</h2>
 */
public class Person implements Comparable<Person>{
    /**
     * Unique identifier for this person
     */
    private String personID;
    /**
     * Username of user to which this person belongs
     */
    private String associatedUsername;
    /**
     * Person's first name
     */
    private String firstName;
    /**
     * Person's last name
     */
    private String lastName;
    /**
     * Person's gender
     */
    private char gender;
    /**
     * Person ID of person's father. May be null
     */
    private String fatherID;
    /**
     * Person ID of person's mother. May be null
     */
    private String motherID;
    /**
     * Person ID of person's spouse. May be null
     */
    private String spouseID;

    /**
     * Generic Constructor. father, mother, or spouse may be null
     * @param personID
     * @param associatedUsername
     * @param firstName
     * @param lastName
     * @param gender
     * @param fatherID
     * @param motherID
     * @param spouseID
     */
    public Person(String personID, String associatedUsername, String firstName, String lastName, char gender, String fatherID, String motherID, String spouseID){
        this.personID = personID;
        this.associatedUsername = associatedUsername;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.fatherID = fatherID;
        this.motherID = motherID;
        this.spouseID = spouseID;
    }

    public Person(String personID, String associatedUsername, String firstName, String lastName, char gender){
        this.personID = personID;
        this.associatedUsername = associatedUsername;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.fatherID = null;
        this.motherID = null;
        this.spouseID = null;
    }

    // Equals method


    @Override
    public boolean equals(Object obj) {
        if (obj==null || getClass() != obj.getClass()){
            return false;
        }
        if (obj == this){
            return true;
        }

        Person prsn = (Person) obj;

        boolean isEqual = Objects.equals(personID, prsn.getPersonID());
        isEqual = isEqual && Objects.equals(associatedUsername, prsn.associatedUsername);
        isEqual = isEqual && Objects.equals(firstName, prsn.getFirstName());
        isEqual = isEqual && Objects.equals(lastName, prsn.getLastName());
        isEqual = isEqual && gender == prsn.getGender();
        isEqual = isEqual && Objects.equals(fatherID, prsn.getFatherID());
        isEqual = isEqual && Objects.equals(motherID, prsn.getMotherID());
        isEqual = isEqual && Objects.equals(spouseID, prsn.getSpouseID());

        return isEqual;
    }



    // getters and setters
    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getAssociatedUsername() {
        return associatedUsername;
    }

    public void setAssociatedUsername(String associatedUsername) {
        this.associatedUsername = associatedUsername;
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

    public String getFatherID() {
        if (fatherID != null && fatherID.equalsIgnoreCase("null")){fatherID = null;}
        return fatherID;
    }

    public void setFatherID(String fatherID) {
        this.fatherID = fatherID;
    }

    public String getMotherID() {
        if (motherID != null && motherID.equalsIgnoreCase("null")){motherID = null;}
        return motherID;
    }

    public void setMotherID(String motherID) {
        this.motherID = motherID;
    }

    public String getSpouseID() {
        if (spouseID != null && spouseID.equalsIgnoreCase("null")){spouseID = null;}
        return spouseID;
    }

    public void setSpouseID(String spouseID) {
        this.spouseID = spouseID;
    }

    @Override
    public int compareTo(Person person) {
        return personID.compareTo(person.getPersonID());
    }
}
