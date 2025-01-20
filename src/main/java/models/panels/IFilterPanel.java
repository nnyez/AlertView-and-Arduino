/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package models.panels;

import java.util.Collection;
import javax.swing.JPanel;
import javax.swing.JTable;
import repositories.AlertRepository;

/**
 *
 * @author geova
 */
public interface IFilterPanel {
    //Metodo que limpiara los datos en los campos de los filtros
    void clear();

    //Metodo que obtendra los datos y lo enviara a la tabla
    <T> Collection<T> execute(JTable table, AlertRepository repository);

    JPanel getPanel();

}
