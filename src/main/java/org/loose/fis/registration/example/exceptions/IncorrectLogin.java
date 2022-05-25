package org.loose.fis.registration.example.exceptions;

public class IncorrectLogin extends Exception {

    private String username;

    public IncorrectLogin(String username) {
        super(String.format("Incorrect credentials for username %s", username));
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
