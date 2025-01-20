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
    private String card_code;
    private byte attempt;

    public AlertCard(int alertId, String description, String alertLevel, LocalTime hour, LocalDate date, String card_code, byte attempt) {
        this.alertId = alertId;
        this.description = description;
        this.alertLevel = alertLevel;
        this.hour = hour;
        this.date = date;
        this.card_code = card_code;
        this.attempt = attempt;
    }

    public int getAlertId() {
        return alertId;
    }

    public String getDescription() {
        return description;
    }

    public String getAlertLevel() {
        return alertLevel;
    }

    public LocalTime getHour() {
        return hour;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getCard_code() {
        return card_code;
    }

    public byte getAttempt() {
        return attempt;
    }
    
    
}
