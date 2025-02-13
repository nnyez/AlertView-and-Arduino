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
import models.AlertPassword;
import repositories.AlertRepository;
import views.panels.AttemptPanel;

/**
 *
 * @author LENOVO
 */
public class AttemptFilter implements IFilterPanel {
    
    private AttemptPanel attempt;

    public AttemptFilter(AttemptPanel attempt) {
        this.attempt = attempt;
    }

    @Override
    public void clear() {
        attempt.getJtxtAttempt().setText("");
    }

    @Override
    public Collection<AlertPassword> execute(JTable table, AlertRepository repository) {
        
        
        String attemptnumber = attempt.getJtxtAttempt().getText();
        
        byte a = Byte.parseByte(attemptnumber);
        
        List<AlertPassword> l = repository.getPassAlertsAttempt(a);
        
        DefaultTableModel model = Mapper.getPassTableModel();
        Mapper.insertDataToModelPass(model, l);
        
        table.setModel(model);
        return l;
    }

    @Override
    public JPanel getPanel() {
        return attempt;
    }
    
    
}
