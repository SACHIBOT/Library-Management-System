/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.library.management.system.dto;

/**
 *
 * @author Lenovo
 */
public class SessionDto {
    private boolean isLoggedIn = false;
    private String loggedUserId;
    private String loggedUsername;
    private String loggedPassword;

    private String loggedRole;

    public SessionDto(String loggedUserId, String loggedPassword) {
        this.loggedUserId = loggedUserId;
        this.loggedPassword = loggedPassword;
    }

    public void setIsLoggedIn(boolean value) {
        this.isLoggedIn = value;
    }

    public boolean getIsLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedUserName(String loggedUsername) {
        this.loggedUsername = loggedUsername;
    }

    public void setLoggedUserId(String id) {
        this.loggedUserId = id;
    }

    public String getLoggedUserId() {
        return loggedUserId;
    }

    public String getLoggedUsername() {
        return loggedUsername;
    }

    public void setLoggedPassword(String loggedPassword) {
        this.loggedPassword = loggedPassword;
    }

    public String getLoggedPassword() {
        return loggedPassword;
    }

    public void setLoggedRole(String role) {
        this.loggedRole = role;
    }

    public String getLoggedRole() {
        return loggedRole;
    }

}
