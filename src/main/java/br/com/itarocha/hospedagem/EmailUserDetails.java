package br.com.itarocha.hospedagem;

import io.micronaut.security.authentication.UserDetails;

import java.util.Collection;

public class EmailUserDetails extends UserDetails {

    private String email;

    private String name;

    public EmailUserDetails(String username, Collection<String> roles) {
        super(username, roles);
    }


    public EmailUserDetails(String username, Collection<String> roles, String email) {
        super(username, roles);
        this.email = email;
    }

    public EmailUserDetails(String username, String name, Collection<String> roles, String email) {
        this(username, roles, email);
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
