/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

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
}
