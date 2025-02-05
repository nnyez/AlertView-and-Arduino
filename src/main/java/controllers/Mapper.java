/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import models.AlertCard;
import models.AlertPassword;

/**
 *
 * @author geova
 */
public class Mapper {

    //Obtener plantilla para la visualizacion en la tabla sobre AlertCards
    public static DefaultTableModel getTableCardModel() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id");
        model.addColumn("Descripción");
        model.addColumn("Nivel de Alerta");
        model.addColumn("Hora");
        model.addColumn("Fecha");
        model.addColumn("Tarjeta ID");
        model.addColumn("Intento");

        return model;
    }

    //Insertar valores desde una lista de AlertCard al modelo de una tabla
    public static void insertDataToModelCard(DefaultTableModel model, List<AlertCard> l) {
        for (AlertCard alertPassword : l) {
            Vector<String> data = new Vector<>();
            data.add(alertPassword.getAlertId() + "");
            data.add(alertPassword.getDescription() + "");
            data.add(alertPassword.getAlertLevel() + "");
            data.add(alertPassword.getHour() + "");
            data.add(alertPassword.getDate() + "");
            data.add(alertPassword.getCard_code() + "");
            data.add(alertPassword.getAttempt() + "");

            model.addRow(data);
        }
    }

    //Inserto el resultado de una consulta en una lista AlertCard
    public static List<AlertCard> getDataToCard(ResultSet set) throws SQLException {
        List<AlertCard> result = new ArrayList<>();
        while (set.next()) {
            result.add(dataToCard(set));
        }
        return result;
    }

    //Inserta los valores de la consulta en un modelo AlertCard
    private static AlertCard dataToCard(ResultSet set) throws SQLException {
        String description = set.getString("description");
        String alertLevel = set.getString("alert_level");
        String hour = set.getString("hour");
        String date = set.getString("date");
        String code = set.getString("card_code");
        byte attempt = set.getByte("attempt");
        int id = set.getInt("alert_card_id");

        return new AlertCard(id, description, alertLevel, LocalTime.parse(hour), LocalDate.parse(date), code, attempt);
    }

    //Obtener plantilla para la visualizacion en la tabla sobre AlertPass
    public static DefaultTableModel getPassTableModel() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id");
        model.addColumn("Descripción");
        model.addColumn("Nivel de Alerta");
        model.addColumn("Hora");
        model.addColumn("Fecha");
        model.addColumn("Contraseña");
        model.addColumn("Intento");

        return model;
    }

    //Insertar valores desde una lista de AlertPass al modelo de una tabla
    public static void insertDataToModelPass(DefaultTableModel model, List<AlertPassword> l) {
        for (AlertPassword alertPassword : l) {
            Vector<String> data = new Vector<>();
            data.add(alertPassword.getAlertId() + "");
            data.add(alertPassword.getDescription() + "");
            data.add(alertPassword.getAlertLevel() + "");
            data.add(alertPassword.getHour() + "");
            data.add(alertPassword.getDate() + "");
            data.add(alertPassword.getPassword() + "");
            data.add(alertPassword.getAttempt() + "");
            model.addRow(data);
        }
    }

    //Inserto el resultado de una consulta en una lista AlertPass
    public static List<AlertPassword> dataToModelPass(ResultSet set) throws SQLException {
        List<AlertPassword> result = new ArrayList<>();
        while (set.next()) {
            result.add(dataToPass(set));
        }
        return result;
    }

    //Inserta los valores de la consulta en un modelo AlertPass
    private static AlertPassword dataToPass(ResultSet set) throws SQLException {
        String description = set.getString("description");
        String alertLevel = set.getString("alert_level");
        String hour = set.getString("hour");
        String date = set.getString("date");
        String password = set.getString("password");
        byte attempt = set.getByte("attempt");
        int id = set.getInt("alert_pass_id");

        return new AlertPassword(id, description, alertLevel, LocalTime.parse(hour), LocalDate.parse(date), password, attempt);
    }

}
