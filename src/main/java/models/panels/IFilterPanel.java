/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package models.panels;

import java.util.Collection;
import javax.swing.JPanel;
import javax.swing.JTable;

/**
 *
 * @author geova
 */
public interface IFilterPanel {

    void clear();

    //Metodo que obtendra los datos y lo enviara a la tabla
    <T> Collection<T> execute(JTable table);

    JPanel getPanel();

}
