/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.panels;

import controllers.Mapper;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.AlertPassword;
import repositories.AlertRepository;
import views.panels.FiltreDatePanel;

/**
 *
 * @author Fr4nk
 */
public class SearchDate implements IFilterPanel<AlertPassword> {

    private FiltreDatePanel panel;

    public SearchDate(FiltreDatePanel panel) {
        this.panel = panel;
    }

    @Override
    public void clear() {

        panel.getTxtDateStart().setText("");
        panel.getTxtDateEnd().setText("");
    }

    @Override
    public Collection<AlertPassword> execute(JTable table, AlertRepository repository) {

        // Obtener valores de los campos de texto
        String dateStr = panel.getTxtDateStart().getText();
        String dateEndx = panel.getTxtDateEnd().getText();

        // Convertir los Strings a LocalDate y LocalTime
        LocalDate dateStart = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate  dateEnd = LocalDate.parse(dateEndx, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        // Obtener alertas en el rango especificado
        List<AlertPassword> alerts  = repository.getPassAlertsDatePeriod(dateStart, dateEnd);

        // Crear el modelo de tabla y llenar con los datos filtrados
        DefaultTableModel model = Mapper.getPassTableModel();
        Mapper.insertDataToModelPass(model, alerts);

        // Actualizar la tabla con el nuevo modelo
        table.setModel(model);
        return null;

    }

    @Override
    public JPanel getPanel() {

        return panel;
    }

}
