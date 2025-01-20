/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author geova
 */
public class AlertCard {
    private int alertId;
    private String description;
    private String alertLevel;
    private LocalTime hour;
    private LocalDate date;
    private String password;
    private byte attempt;

    public AlertCard(int alertId, String description, String alertLevel, LocalTime hour, LocalDate date, String password, byte attempt) {
        this.alertId = alertId;
        this.description = description;
        this.alertLevel = alertLevel;
        this.hour = hour;
        this.date = date;
        this.password = password;
        this.attempt = attempt;
    }
    
    
}
