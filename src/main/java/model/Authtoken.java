package model;

/**
 * <h2>Java Implementation of Authtoken Schema</h2>
 */
public class Authtoken {
    /**
     * Proves that the current user has verified their identity
     */
    private String authtoken;
    /**
     * The Current User's username
     */
    private String username;

    public Authtoken(String iauthtoken, String iusername){
        authtoken = iauthtoken;
        username  = iusername;
    }

    public String getAuthtoken() {
        return authtoken;
    }

    public void setAuthtoken(String authtoken) {
        this.authtoken = authtoken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
