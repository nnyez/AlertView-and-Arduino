/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models.panels;

import controllers.Mapper;
import java.util.Collection;
import java.util.List;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.AlertCard;
import models.AlertPassword;
import repositories.AlertRepository;
import views.panels.SearchFilterPanel;

/**
 *
 * @author geova
 */
public class SearchFilter implements IFilterPanel {
    
    private SearchFilterPanel panel;
    
    public SearchFilter(SearchFilterPanel panel) {
        this.panel = panel;
    }
    //Limpiar los filtros
    @Override
    public void clear() {
        panel.getTxtSearch().setText("");
    }
    //consulta
    @Override
    public Collection<AlertCard> execute(JTable table, AlertRepository repository) {
        String searchSrt = panel.getTxtSearch().getText();
        
        List<AlertCard> l = repository.getCardAlertsSearch(searchSrt);
        
        DefaultTableModel model = Mapper.getCardTableModel();
        Mapper.insertDataToModelCard(model, l);
        
        table.setModel(model);
        return l;
    }
    
    @Override
    public JPanel getPanel() {
        return panel;
    }
    
}
