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
public class AlertPassword implements IAlert {

    private int alertId;
    private String description;
    private String alertLevel;
    private LocalTime hour;
    private LocalDate date;
    private String password;
    private byte attempt;

    public AlertPassword(int alertId, String description, String alertLevel, LocalTime hour, LocalDate date,
            String password, byte attempt) {
        this.alertId = alertId;
        this.description = description;
        this.alertLevel = alertLevel;
        this.hour = hour;
        this.date = date;
        this.password = password;
        this.attempt = attempt;
    }

    public AlertPassword(String description, String alertLevel, LocalTime hour, LocalDate date, String password,
            byte attempt) {
        this.description = description;
        this.alertLevel = alertLevel;
        this.hour = hour;
        this.date = date;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public byte getAttempt() {
        return attempt;
    }

    @Override
    public String getHeader() {
        return "Alerta por Contraseña Numerica nivel: " + alertLevel;
    }

    @Override
    public String getMsg() {
        if (attempt >= 3) {
            return "Han Activado el sistema de aleta mediante la contraseña " + password + " por " + attempt + " intentos";

        }
        return "Han intentado ingresar mediante el pin " + password + " " + attempt + " intentos";
    }
}
