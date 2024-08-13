package org.softuin.mobilele.util;


import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class CurrentUser {

    private String firstName;
    private String lastName;
    private boolean isLogged;


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

    public boolean isLogged() {
        return isLogged;
    }

    public void setLogged(boolean logged) {
        isLogged = logged;
    }

    public void logout(){
        setLogged(false);
        setLastName(null);
        setLastName(null);
    }

    public String fullName(){
        StringBuilder sb = new StringBuilder();
        if(firstName != null){
            sb.append(firstName);
        }
        if (!sb.isEmpty()){
            sb.append(" ");
        }

        sb.append(lastName);

        return sb.toString();
    }
    @Override
    public String toString() {
        return "CurrentUser{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", isLogged=" + isLogged +
                '}';
    }
}
