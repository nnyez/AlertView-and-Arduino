/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositories;

import controllers.Mapper;
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

    private final String strConn = "jdbc:mysql://localhost:3306/autalert";

    private Connection conn = null;
    private Statement st;

    public AlertRepository() throws SQLException {
        this.conn = DriverManager.getConnection(strConn, "root", "admin");
        this.st = conn.createStatement();
    }

    //PASSWORD
    public List<AlertPassword> getPassAlerts() {
        ResultSet resultSet;
        List<AlertPassword> result = new ArrayList<>();

        try {
            resultSet = st.executeQuery("SELECT * FROM alerts_pass;");

            if (resultSet == null) {
                return result;
            }

            result = Mapper.dataToModelPass(resultSet);
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
        ResultSet resultSet;
        List<AlertCard> result = new ArrayList<>();

        try {
            resultSet = st.executeQuery("SELECT * FROM alerts_card;");

            if (resultSet == null) {
                return result;
            }
            result = Mapper.getDataToCard(resultSet);
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
        ResultSet resultSet;
        List<AlertCard> result = new ArrayList<>();

        try {
            PreparedStatement prepare = conn.prepareStatement("SELECT * FROM alerts_card  WHERE BINARY card_code like ?;");
            prepare.setString(1, "" + chars + "%");

            resultSet = prepare.executeQuery();

            if (resultSet == null) {
                return result;
            }
            result = Mapper.getDataToCard(resultSet);
        } catch (SQLException ex) {
            Logger.getLogger(AlertRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

}
