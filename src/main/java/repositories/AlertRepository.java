/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import com.mysql.cj.jdbc.PreparedStatementWrapper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.AlertPassword;
import models.AlertCard;

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
        ResultSet resultSet = null;
        List<AlertPassword> result = new ArrayList<>();

        try {
            resultSet = st.executeQuery("SELECT * FROM alerts_pass;");

            if (resultSet == null) {
                return result;
            }
            while (resultSet.next()) {
                String description = resultSet.getString("description");
                String alertLevel = resultSet.getString("alert_level");
                String hour = resultSet.getString("hour");
                String date = resultSet.getString("date");
                String password = resultSet.getString("password");
                byte attempt = resultSet.getByte("attempt");
                int id = resultSet.getInt("alert_pass_id");

                result.add(new AlertPassword(id, description, alertLevel, LocalTime.parse(hour), LocalDate.parse(date), password, attempt));

            }
        } catch (SQLException ex) {
            Logger.getLogger(AlertRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public List<AlertPassword> getPassAlertsDatePeriod(LocalDate start, LocalDate end) {
        return null;
    }

    public List<AlertPassword> getPassAlertsAttempt(byte attempt) {
        return null;
    }

    //CARDS
    public List<AlertCard> getCardAlerts() {
        ResultSet resultSet = null;
        List<AlertCard> result = new ArrayList<>();

        try {
            resultSet = st.executeQuery("SELECT * FROM alerts_card;");

            if (resultSet == null) {
                return result;
            }
            while (resultSet.next()) {
                String description = resultSet.getString("description");
                String alertLevel = resultSet.getString("alert_level");
                String hour = resultSet.getString("hour");
                String date = resultSet.getString("date");
                String code = resultSet.getString("card_code");
                byte attempt = resultSet.getByte("attempt");
                int id = resultSet.getInt("alert_card_id");

                result.add(new AlertCard(id, description, alertLevel, LocalTime.parse(hour), LocalDate.parse(date), code, attempt));

            }
        } catch (SQLException ex) {
            Logger.getLogger(AlertRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public List<AlertCard> getCardAlertsTimePeriod(LocalTime start, LocalTime end) {
        return null;
    }

    public List<AlertCard> getCardAlertsAlertLevel(String level) {
        return null;
    }

    public List<AlertCard> getCardAlertsSearch(String chars) {
        ResultSet resultSet = null;
        List<AlertCard> result = new ArrayList<>();

        try {
            PreparedStatement prepare = con.prepareStatement("SELECT * FROM alerts_card WHERE card_code like ?;");
            prepare.setString(1, "" + chars + "%");

            resultSet = prepare.executeQuery();

            if (resultSet == null) {
                return result;
            }
            while (resultSet.next()) {
                String description = resultSet.getString("description");
                String alertLevel = resultSet.getString("alert_level");
                String hour = resultSet.getString("hour");
                String date = resultSet.getString("date");
                String code = resultSet.getString("card_code");
                byte attempt = resultSet.getByte("attempt");
                int id = resultSet.getInt("alert_card_id");

                result.add(new AlertCard(id, description, alertLevel, LocalTime.parse(hour), LocalDate.parse(date), code, attempt));

            }
        } catch (SQLException ex) {
            Logger.getLogger(AlertRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

}
