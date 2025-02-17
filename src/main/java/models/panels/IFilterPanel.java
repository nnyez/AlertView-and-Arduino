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
//Interfaz que implementa el filtro para mantener la abstraccion
public interface IFilterPanel <T>{
    //Metodo que limpiara los datos en los campos de los filtros
    void clear();

    //Metodo que obtendra los datos y lo enviara a la tabla
    Collection<T> execute(JTable table, AlertRepository repository);

    //Obterngo el panel
    JPanel getPanel();

}
