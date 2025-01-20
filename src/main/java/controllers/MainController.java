/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package controllers;

import com.panamahitek.ArduinoException;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EventListener;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import jssc.SerialPortException;
import models.AlertCard;
import models.AlertPassword;
import models.panels.IFilterPanel;
import models.panels.SearchFilter;
import repositories.AlertRepository;
import views.MainView;
import views.panels.SearchFilterPanel;

/**
 *
 * @author geova
 */
public class MainController {

    private static final String sURL = "jdbc:mysql://localhost:3306/autalert";

    private MainView view;
    private AlertRepository repository;

    private IFilterPanel actually;
    // Datos para los combobox, intercambiar los datos en vez de crear un CB nuevo
    private List<String> cbData = new ArrayList<>();
    private List<String> cbPass = new ArrayList<>();
    private List<String> cbCard = new ArrayList<>();

    // Mapa donde estan los paneles correspondientes para cada filtro
    private HashMap<String, IFilterPanel> filtersPanels = new HashMap<>();

    public MainController(MainView view, AlertRepository repository) {
        this.view = view;
        this.repository = repository;

        initFilterPanels();
        initData();
        initActions();

        view.setVisible(true);
    }

    public void initData() {
        // Datos posibles a filtrar
        cbData.add("Alertas por Contraseña");
        cbData.add("Alertas por Tarjeta");

        for (String string : cbData) {
            view.getDataOptionsCB().addItem(string);
        }

        // Filtros de tarjeta
        cbCard.add("Ninguno");
        cbCard.add("Nivel de Alerta");
        cbCard.add("Rango de Horas");
        cbCard.add("Busqueda ID");

        // Filtros de contraseña
        cbPass.add("Ninguno");
        cbPass.add("Rango de Fechas");
        cbPass.add("Numero de Intentos");

        view.revalidate();

    }

    // Se agregan todos los paneles correspondientes a los paneles para cada filtro
    public void initFilterPanels() {

        // Tomar en cuenta los registros agregado en cbCard y cbPass
        // Cada item sera la clave del mapa para cada panel
        IFilterPanel searchPanel = new SearchFilter(new SearchFilterPanel());
        filtersPanels.put("Busqueda ID", searchPanel);
    }

    // Inicializacion de ActionsCommads de la interfaz
    public void initActions() {

        view.getDataOptionsCB().addActionListener(e -> dataOptionsCB(e));
        view.getFiltersOptionsCB().addActionListener(e -> filterOptionsCB(e));

        view.getExecuteBtn().addActionListener(e -> execute());

        view.getClearBtn().addActionListener(e -> clear());
    }

    private void clear() {
        if (actually == null) {
            return;
        }
        actually.clear();
    }

    private void execute() {
        view.getDataTable().removeAll();

        if (actually == null) {
            if (view.getDataOptionsCB().getSelectedItem() == "Alertas por Contraseña") {
                executePass();
            } else {
                executeCard();
            }
            return;

        }
        actually.execute(view.getDataTable(), repository);
        refresh();
    }

    //Consulta * Alertas Pass
    private void executePass() {
        List<AlertPassword> l = repository.getPassAlerts();
        DefaultTableModel model = Mapper.getPassTableModel();
        Mapper.insertDataToModelPass(model, l);
        view.getDataTable().setModel(model);
        refresh();
    }

    //Consulta * Alertas Card
    private void executeCard() {
        List<AlertCard> l = repository.getCardAlerts();
        DefaultTableModel model = Mapper.getCardTableModel();

        Mapper.insertDataToModelCard(model, l);

        view.getDataTable().setModel(model);

        refresh();
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
        //actually = filtersPanels.get("Busqueda ID");
        view.getFiltersPanel().removeAll();

        if (actually == null) {
            refresh();

            return;
        }
        view.getFiltersPanel().add(actually.getPanel(), BorderLayout.CENTER);

        refresh();
    }

    private void refresh() {
        view.repaint();
        view.revalidate();
    }

    public static void main(String[] args) throws ArduinoException, SerialPortException, SQLException {

        new MainController(new MainView(), new AlertRepository(sURL));
    }

}
