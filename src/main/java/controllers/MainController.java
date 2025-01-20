/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package controllers;

import com.panamahitek.ArduinoException;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EventListener;
import java.util.HashMap;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import jssc.SerialPortException;
import models.panels.IFilterPanel;
import views.MainView;
import views.panels.SearchFilterPanel;

/**
 *
 * @author geova
 */
public class MainController {

    private final String sURL = "jdbc:mysql://localhost:3306/autalert";

    private MainView view;
    
    private IFilterPanel actually;
    //Datos para los combobox, intercambiar los datos en vez de crear un CB nuevo
    private List<String> cbData = new ArrayList<>();
    private List<String> cbPass = new ArrayList<>();
    private List<String> cbCard = new ArrayList<>();

    //Mapa donde estan los paneles correspondientes para cada filtro
    private HashMap<String, IFilterPanel> filtersPanels = new HashMap<>();

    public MainController(MainView view) {
        this.view = view;

        initData();
        initActions();

        view.setVisible(true);
    }

    public void initData() {
        //Datos posibles a filtrar
        cbData.add("Alertas por Contraseña");
        cbData.add("Alertas por Tarjeta");

        for (String string : cbData) {
            view.getDataOptionsCB().addItem(string);
        }

        //Filtros de tarjeta
        cbCard.add("Nivel de Alerta");
        cbCard.add("Rango de Horas");
        cbCard.add("Busqueda ID");

        //Filtros de contraseña
        cbPass.add("Rango de Fechas");
        cbPass.add("Numero de Intentos");
        view.revalidate();
        
    }
    //Se agregan todos los paneles correspondientes a los paneles para cada filtro
    public void initFilterPanels(){
        
        //Tomar en cuenta los registros agregado en cbCard y cbPass
        //Cada item sera la clave del mapa para cada panel
        
        
    }

    //Inicializacion de ActionsCommads de la interfaz
    public void initActions() {

        view.getDataOptionsCB().addActionListener(e -> dataOptionsCB(e));
        view.getFiltersOptionsCB().addActionListener(e -> filterOptionsCB(e));
        
        view.getExecuteBtn().addActionListener(e->execute());
    }

    private void execute(){
        view.getDataTable().removeAll();
        actually.execute(view.getDataTable());
    }
    
    private void dataOptionsCB(ActionEvent e) {

        if (view.getDataOptionsCB().getSelectedItem() == "Alertas por Contraseña") {
            view.getFiltersOptionsCB().removeAllItems();

            for (String item : cbPass) {
                view.getFiltersOptionsCB().addItem(item);
            }

        } else {
            view.getFiltersOptionsCB().removeAllItems();

            for (String item : cbCard) {
                view.getFiltersOptionsCB().addItem(item);
            }
        }
        view.revalidate();
    }

    private void filterOptionsCB(ActionEvent e) {
        actually = filtersPanels.get(view.getFiltersOptionsCB().getSelectedItem());
        if(actually==null){
            return;
        }
       view.getFiltersPanel().add(actually.getPanel(), BorderLayout.CENTER);
        
       view.repaint();
       view.revalidate();
    }

    public static void main(String[] args) throws ArduinoException, SerialPortException {

        new MainController(new MainView());
    }

}
