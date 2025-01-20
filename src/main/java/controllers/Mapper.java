/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

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

    public static DefaultTableModel getCardTableModel() {
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
}
