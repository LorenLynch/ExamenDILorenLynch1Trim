/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.examendilorenlynch;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;
/**
 * FXML Controller class
 *
 * @author loren
 */
public class PrimaryController implements Initializable {

    @FXML
    private TextField tvNombre;
    @FXML
    private ComboBox<String> cbSexo;
    @FXML
    private ComboBox<String> cbActividad;
    @FXML
    private Spinner<Integer> sEdad;
    @FXML
    private Spinner<Double> sPeso;
    @FXML
    private Spinner<Integer> sAltura;
    @FXML
    private TableView<Persona> tabla;
    @FXML
    private TableColumn<Persona, String> cNombre;
    @FXML
    private TableColumn<Persona, String> cSexo;
    @FXML
    private TableColumn<Persona, Integer> cEdad;
    @FXML
    private TableColumn<Persona, Double> cPeso;
    @FXML
    private TableColumn<Persona, Integer> cAltura;
    @FXML
    private TableColumn<Persona, String> cActividad;
    @FXML
    private TableColumn<Persona, Integer> cGER;
    @FXML
    private TableColumn<Persona, Integer> cGET;
    @FXML
    private Label labelInfo;

    List<Persona> listaPersonas= new ArrayList<Persona>();
    
 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarCombos(); 
        llenarSpinners();
        inicializarTabla();
    }    
    
    public void llenarCombos() {
        ObservableList<String> sexo = FXCollections.observableArrayList("Hombre", "Mujer");
        cbSexo.setValue("");
        cbSexo.setItems(sexo);
        
        ObservableList<String> actividad = FXCollections.observableArrayList("Muy ligera", "Ligera", "Moderada", "Intensa");
        cbActividad.setValue("");
        cbActividad.setItems(actividad);
    }
    
    public void llenarSpinners(){
        
        SpinnerValueFactory<Integer> vfEdad = new SpinnerValueFactory.IntegerSpinnerValueFactory(18,100);
        sEdad.setValueFactory(vfEdad);
        
        SpinnerValueFactory<Double> vfPeso = new SpinnerValueFactory.DoubleSpinnerValueFactory(45.0, 150.0, 80, 0.5);
        sPeso.setValueFactory(vfPeso);
        
        SpinnerValueFactory<Integer> vfAltura = new SpinnerValueFactory.IntegerSpinnerValueFactory(145,250);
        sAltura.setValueFactory(vfAltura);
    }
    
    private void inicializarTabla(){
        cNombre.setCellValueFactory(new PropertyValueFactory<Persona,String>("nombre"));
        cSexo.setCellValueFactory(new PropertyValueFactory<Persona,String>("sexo"));
        cEdad.setCellValueFactory(new PropertyValueFactory<Persona,Integer>("edad"));
        cPeso.setCellValueFactory(new PropertyValueFactory<Persona,Double>("peso"));
        cAltura.setCellValueFactory(new PropertyValueFactory<Persona,Integer>("altura"));
        cActividad.setCellValueFactory(new PropertyValueFactory<Persona,String>("actividad"));
        cGER.setCellValueFactory(new PropertyValueFactory<Persona,Integer>("GER"));
        cGET.setCellValueFactory(new PropertyValueFactory<Persona,Integer>("GET"));
    }
    
    @FXML
    private void añadirATabla(ActionEvent event) {
        Persona personaAnadir = crearPersona();
        if(personaAnadir!=null){
            listaPersonas.add(personaAnadir);
        }

        tabla.getItems().clear();
        tabla.getItems().addAll(listaPersonas);
    }
    
    private Persona crearPersona() {
        Persona persona = new Persona();
        
        persona.setNombre(tvNombre.getText());
        if(persona.getNombre().equals("") || persona.getNombre() == null){
            labelInfo.setText("El campo nombre está vacío");
            return null;
        }
        
        persona.setEdad(sEdad.getValue()); 
        
        persona.setSexo(cbSexo.getValue());
        if(persona.getSexo().equals("")){
            labelInfo.setText("El campo sexo está vacío");
            return null;
        }
        
        persona.setPeso(sPeso.getValue());
       
        persona.setAltura(sAltura.getValue());
        
        
        persona.setActividad(cbActividad.getValue());
        if(persona.getActividad().equals("")){
            labelInfo.setText("El campo actividad está vacío");
            return null;
        }
        
        persona.setActividadValor();
        if(persona.getNombre().equals("") || persona.getNombre() == null){
            labelInfo.setText("El campo nombre está vacío");
            return null;
        }
        
        //Creamos el valor de GER en double y se lo asignamos a nuestra persona
        Double GERDouble = Double.valueOf(calcularGER(persona));
        GERDouble = GERDouble/10000;
        persona.setGER(GERDouble);
        
        //Creamos el valor de GET en double y se lo asignamos a nuestra persona
        Double GETDouble = Double.valueOf(calcularGET(persona));
        GETDouble = GETDouble/100000;
        persona.setGET(GETDouble);
        
        return persona;
    }
    
    private Integer calcularGER(Persona persona){
         Integer gerResult;
        //Como sabemos que el peso solo puede venir dado con un decimal:
        Double doubleSinDecimal = persona.getPeso()*10;
        Integer pesoInteger = doubleSinDecimal.intValue();
        if(persona.getSexo().equals("Mujer")){
            gerResult = 6550955+9463*(pesoInteger)+18496*(persona.getAltura())-46756*(persona.getEdad());
        }else if(persona.getSexo().equals("Hombre")){
            gerResult = 664730+13751*(pesoInteger)+50033*(persona.getAltura())-67550*(persona.getEdad());
        }else{
            return null;
        }
        return gerResult;
    }
    
     private Integer calcularGET(Persona persona){
        Integer GERPersona = calcularGER(persona);
        Integer GETPersona = GERPersona*persona.getActividadValor();
        return GETPersona;
    }
}
