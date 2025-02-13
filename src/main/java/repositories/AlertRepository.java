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
import java.time.format.DateTimeFormatter;
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

    private final Connection conn;
    private final Statement st;

    public AlertRepository() throws SQLException {
        this.conn = DriverManager.getConnection(strConn, "root", "admin");
        this.st = conn.createStatement();
    }

    // PASSWORD----------------------
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

        ResultSet resultSet;
        List<AlertPassword> result = new ArrayList<>();

        String sql = "SELECT * FROM alerts_pass WHERE date BETWEEN ? AND ?;";

        try (PreparedStatement prepare = conn.prepareStatement(sql)) {
            prepare.setDate(1, java.sql.Date.valueOf(start));
            prepare.setDate(2, java.sql.Date.valueOf(end));

            resultSet = prepare.executeQuery();
            if (resultSet == null) {
                return result;
            }

            result = Mapper.dataToModelPass(resultSet);
        } catch (SQLException ex) {
            Logger.getLogger(AlertRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public List<AlertPassword> getPassAlertsAttempt(byte attempt) {
        ResultSet resulset;
        List<AlertPassword> result = new ArrayList<>();
        try {

            PreparedStatement selection = conn.prepareStatement("SELECT * FROM alerts_pass WHERE attempt = ?;");
            selection.setByte(1, attempt);
            resulset = selection.executeQuery();

            if (resulset == null) {
                return result;
            }
            result = Mapper.dataToModelPass(resulset);
            System.out.println(attempt);
        } catch (SQLException ex) {
            Logger.getLogger(AlertRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public boolean insertAlertPass(AlertPassword pass) {
        return false;
    }

    // CARDS--------------------
    public List<AlertCard> getCardAlerts() {
        ResultSet resultSet;
        List<AlertCard> result = new ArrayList<>();

        try {
            resultSet = st.executeQuery("SELECT * FROM alerts_card;");

            if (resultSet == null) {
                return result;
            }
            result = Mapper.dataToModelCard(resultSet);
        } catch (SQLException ex) {
            Logger.getLogger(AlertRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public List<AlertCard> getCardAlertsTimePeriod(LocalTime start, LocalTime end) {
        ResultSet resulSet;
        List<AlertCard> result = new ArrayList<>();
        try {
            PreparedStatement prepa = conn.prepareStatement("SELECT * FROM alerts_card WHERE hour BETWEEN ? AND ?");
            prepa.setString(1, start.format(DateTimeFormatter.ofPattern("HH:mm")));
            prepa.setString(2, end.format(DateTimeFormatter.ofPattern("HH:mm")));

            resulSet = prepa.executeQuery();
            if (resulSet == null) {
                return result;
            }
            result = Mapper.dataToModelCard(resulSet);
        } catch (SQLException ex) {
            Logger.getLogger(AlertRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public List<AlertCard> getCardAlertsAlertLevel(String level) {
        ResultSet resultSet;
        List<AlertCard> result = new ArrayList<>();

        try {
            PreparedStatement pas = conn.prepareStatement("SELECT * FROM alerts_card where alert_level = ?; ");
            pas.setString(1, level);
            resultSet = pas.executeQuery();

            if (resultSet == null) {
                return result;
            }
            result = Mapper.dataToModelCard(resultSet);
        } catch (SQLException ex) {
            Logger.getLogger(AlertRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public List<AlertCard> getCardAlertsSearch(String chars) {
        ResultSet resultSet;
        List<AlertCard> result = new ArrayList<>();

        try {
            PreparedStatement prepare = conn
                    .prepareStatement("SELECT * FROM alerts_card  WHERE BINARY card_code like ?;");
            prepare.setString(1, "" + chars + "%");

            resultSet = prepare.executeQuery();

            if (resultSet == null) {
                return result;
            }
            result = Mapper.dataToModelCard(resultSet);
        } catch (SQLException ex) {
            Logger.getLogger(AlertRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public boolean insertAlertCard(AlertCard pass) {
        return false;
    }
}
