/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package controllers;

import com.panamahitek.ArduinoException;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import jssc.SerialPortException;
import models.AlertCard;
import models.AlertPassword;
import models.panels.AlertNivel;
import models.panels.AttemptFilter;
import models.panels.HourFilter;
import models.panels.IFilterPanel;
import models.panels.SearchDate;
import models.panels.SearchFilter;
import repositories.AlertRepository;
import repositories.MailAddressRepository;
import views.MainView;
import views.panels.AlertLevelPanel;
import views.panels.AttemptPanel;
import views.panels.FiltreDatePanel;
import views.panels.HourFilterpPanel;
import views.panels.SearchFilterPanel;

/**
 *
 * @author geova
 */
public class MainController {

    private MainView view;

    private AlertRepository repository;

    // Modelo de filtro actual
    private IFilterPanel actually;

    // Datos para los combobox, intercambiar los datos en vez de crear un CB nuevo
    private List<String> cbData = new ArrayList<>();
    private List<String> cbPass = new ArrayList<>();
    private List<String> cbCard = new ArrayList<>();

    // Mapa donde esta los modelos con cada panel correspondientes para cada filtro
    private HashMap<String, IFilterPanel> filtersPanels = new HashMap<>();

    // Inicializo todos las clases necesarias y llamo a los metodos para iniciar la
    // APP
    public MainController(MainView view, AlertRepository repository) {
        this.view = view;
        this.repository = repository;

        initFilterPanels();
        initData();
        initActions();

        view.setVisible(true);
    }

    // Agrega las opciones del combobox, cada lista corresponde a las opciones
    // A su vez son las keys para seleccionar el panel del filtro
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

        refresh();

    }

    // Se agregan todos los paneles correspondientes a los paneles para cada filtro
    public void initFilterPanels() {

        // Tomar en cuenta los registros agregado en cbCard y cbPass
        // Cada item sera la clave del mapa para cada panel
        IFilterPanel searchPanel = new SearchFilter(new SearchFilterPanel());
        filtersPanels.put("Busqueda ID", searchPanel);

        IFilterPanel searchDate = new SearchDate(new FiltreDatePanel());
        filtersPanels.put("Rango de Fechas", searchDate);

        IFilterPanel alertLevel = new AlertNivel(new AlertLevelPanel());
        filtersPanels.put("Nivel de Alerta", alertLevel);

        IFilterPanel attemptpanel = new AttemptFilter(new AttemptPanel());
        filtersPanels.put("Numero de Intentos", attemptpanel);

        IFilterPanel hourPanel = new HourFilter(new HourFilterpPanel());
        filtersPanels.put("Rango de Horas", hourPanel);

    }

    // Inicializacion de ActionsCommads de la interfaz
    // Union Interfaz Controlador
    public void initActions() {

        view.getDataOptionsCB().addActionListener(e -> dataOptionsCB(e));
        view.getFiltersOptionsCB().addActionListener(e -> filterOptionsCB(e));

        view.getExecuteBtn().addActionListener(e -> execute());

        view.getClearBtn().addActionListener(e -> clear());
    }

    // Llamo al metodo limpiar de cada panel de filtro
    private void clear() {
        if (actually == null) {
            return;
        }
        actually.clear();
    }

    // Ejecuta la consulta del filtro seleccionado
    private void execute() {
        // Limpia los datos de la tabla
        view.getDataTable().removeAll();
        // Si no existe un filtro seleccionado ejecuta una consulta general
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

    // Consulta * Alertas Pass
    private void executePass() {
        List<AlertPassword> l = repository.getPassAlerts();

        DefaultTableModel model = Mapper.getPassTableModel();
        Mapper.insertDataToModelPass(model, l);
        view.getDataTable().setModel(model);
        refresh();
    }

    // Consulta * Alertas Card
    private void executeCard() {
        List<AlertCard> l = repository.getCardAlerts();
        DefaultTableModel model = Mapper.getTableCardModel();

        Mapper.insertDataToModelCard(model, l);

        view.getDataTable().setModel(model);

        refresh();
    }

    // Ingresa las opciones en el cb de filtros
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
        refresh();
    }

    // Actualiza la vista con el panel segun el filtro seleccionado
    private void filterOptionsCB(ActionEvent e) {
        actually = filtersPanels.get(view.getFiltersOptionsCB().getSelectedItem());

        view.getFiltersPanel().removeAll();

        if (actually == null) {
            refresh();

            return;
        }
        view.getFiltersPanel().add(actually.getPanel(), BorderLayout.CENTER);

        refresh();
    }

    // Actualizo la vista
    private void refresh() {
        view.repaint();
        view.revalidate();
    }

    public static void main(String[] args) throws ArduinoException, SerialPortException, SQLException {
        AlertRepository repository = new AlertRepository();
        MailAddressRepository mailRepository = new MailAddressRepository();
        new ConRXTX(repository, mailRepository);
        new MainController(new MainView(),repository);
    }

}
