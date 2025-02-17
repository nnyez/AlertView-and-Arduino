/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.Arrays;

/**
 *
 * @author geova
 */
public class User {

    private int userId;
    private String name;
    private String lastName;
    private String mail;
    private String password;
    private String cardCode;

    public User(int userId, String name, String lastName, String mail, String password, String cardCode) {
        this.userId = userId;
        this.name = name;
        this.lastName = lastName;
        this.mail = mail;
        this.password = password;
        this.cardCode = cardCode;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public String getCardCode() {
        return cardCode;
    }

    public boolean verifyCardCode(String[] code) {
        if (cardCode == null) {
            return false;
        }
        String allCode = "";
        for (int i = 1; i < code.length; i++) {
            if (i == code.length - 1) {
                allCode = allCode + code[i];
            } else {
                allCode = allCode + code[i] + " ";
            }

        }
        return cardCode.equals(allCode);
    }

    public boolean verifyPassCode(String[] code) {
        if (password == null) {
            return false;
        }
        String allCode = "";
        for (int i = 1; i < code.length; i++) {
            allCode = allCode + code[i];

        }
        return password.equals(allCode);
    }
}
