/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.panels;

import controllers.Mapper;
import java.util.Collection;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.AlertCard;
import repositories.AlertRepository;
import views.panels.AlertLevelPanel;


/**
 *
 * @author joser
 */
public class AlertNivel implements IFilterPanel<AlertCard>{
  private AlertLevelPanel level;
    
    public AlertNivel(AlertLevelPanel level) {
        this.level = level;
    }
    //Limpiar los filtros
    @Override
    public void clear() {
        level.getTxtLevel().setText("");
    }
    //consulta
    @Override
    public Collection<AlertCard> execute(JTable table, AlertRepository repository) {
        String alertLevel = level.getTxtLevel().getText();
        
        List<AlertCard> l = repository.getCardAlertsAlertLevel(alertLevel);
        
        DefaultTableModel model = Mapper.getTableCardModel();
        Mapper.insertDataToModelCard(model, l);
        
        table.setModel(model);
        return l;
    }
    @Override
    public JPanel getPanel() {
        return level;
    }
}
