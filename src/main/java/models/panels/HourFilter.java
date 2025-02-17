/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.panels;

import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JTable;
import repositories.AlertRepository;
import views.panels.HourFilterpPanel;
import models.AlertCard;
import controllers.Mapper;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LENOVO
 */
public class HourFilter implements IFilterPanel<AlertCard> {

    private HourFilterpPanel panel;

    public HourFilter(HourFilterpPanel panel) {
        this.panel = panel;
    }

    @Override
    public void clear() {
        LocalDateTime localDate = LocalDateTime.now();
        int hours = localDate.getHour();

        JComboBox<String> comboDesde = panel.getComboDesde();
        JComboBox<String> comboHasta = panel.getComboHasta();
        String horaActual = String.format("%02d:00", hours);
        comboDesde.setSelectedItem(horaActual);
        comboHasta.setSelectedItem(horaActual);
    }

    @Override
    public Collection<AlertCard> execute(JTable table, AlertRepository repository) {
        String hour1 = (String) panel.getComboDesde().getSelectedItem();
        String hour2 = (String) panel.getComboHasta().getSelectedItem();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime hourDesde = LocalTime.parse(hour1, formatter);
            LocalTime hourHasta = LocalTime.parse(hour2, formatter);
            List<AlertCard> m = repository.getCardAlertsTimePeriod(hourDesde, hourHasta);
            if (m.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No existen intentos registrados a esa hora.");
            } else {
                DefaultTableModel model = Mapper.getPassTableModel();
                Mapper.insertDataToModelCard(model, m);
                table.setModel(model);
            }
            return m;
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "Formato de hora ingresado incorrecto"
                    + " Formato esperado HH:mm");

        }
        return new ArrayList<>();
    }

    @Override
    public JPanel getPanel() {
        return panel;
    }
}
