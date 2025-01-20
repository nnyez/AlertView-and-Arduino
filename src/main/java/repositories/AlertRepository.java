/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import models.AlertCard;
import models.AlertPassword;

/**
 *
 * @author geova
 */
public class AlertRepository {

    Connection con = null;
    Statement st;

    public AlertRepository(String conStr) throws SQLException {
        this.con = DriverManager.getConnection(conStr, "root", "admin");
        this.st = con.createStatement();
    }

    //PASSWORD
    public List<AlertPassword> getPassAlerts() {
        return null;
    }

    public List<AlertPassword> getPassAlertsDatePeriod(LocalDate start, LocalDate end) {
        return null;
    }

    public List<AlertPassword> getPassAlertsAttempt(byte attempt) {
        return null;
    }

    //CARDS
    public List<AlertCard> getCardAlerts() {
        return null;
    }

    public List<AlertCard> getCardAlertsTimePeriod(LocalTime start, LocalTime end) {
        return null;
    }

    public List<AlertCard> getCardAlertsAlertLevel(String level) {
        return null;
    }

    public List<AlertCard> getCardAlertsSearch(String chars){
        return null;
    }
    
    
}
