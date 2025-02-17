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
import views.panels.SearchFilterPanel;

/**
 *
 * @author geova
 */
// Modelo filtro de busqueda en AlertCard
public class SearchFilter implements IFilterPanel<AlertCard> {

    private SearchFilterPanel panel;

    public SearchFilter(SearchFilterPanel panel) {
        this.panel = panel;
    }

    // Limpiar los filtros
    @Override
    public void clear() {
        panel.getTxtSearch().setText("");
    }

    // consulta
    @Override
    public Collection<AlertCard> execute(JTable table, AlertRepository repository) {
        String searchSrt = panel.getTxtSearch().getText();// Obtengo los caracteres a buscar

        List<AlertCard> l = repository.getCardAlertsSearch(searchSrt);//

        DefaultTableModel model = Mapper.getTableCardModel();
        Mapper.insertDataToModelCard(model, l);

        table.setModel(model);
        return l;
    }

    @Override
    public JPanel getPanel() {
        return panel;
    }

}
