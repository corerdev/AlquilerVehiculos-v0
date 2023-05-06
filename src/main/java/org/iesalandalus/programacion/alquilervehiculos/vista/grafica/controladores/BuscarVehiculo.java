package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;


import org.iesalandalus.programacion.alquilervehiculos.controlador.IControlador;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
public class BuscarVehiculo {
	
	private IControlador controladorMVC;

	private ObservableList<Vehiculo> listaVehiculo;

	   @FXML
	    private Button buscar;

	    @FXML
	    private Button cancelar;

	    @FXML
	    private Text dni_text;

	    @FXML
	    private TextField marca_field;

	    @FXML
	    private TextField matricula_field;

	    @FXML
	    private TextField modelo_field;

	    @FXML
	    private Text nombre_text;

	    @FXML
	    private Text telefono_text;

	@FXML
	void buscarVehiculo(ActionEvent event) {

		try {
			Vehiculo vehiculoDummy = new Turismo("Dummy", "Dummy", 10, matricula_field.getText());
			Vehiculo vehiculoABuscar = controladorMVC.buscar(vehiculoDummy);
			buscar.setDisable(true);
			matricula_field.setDisable(true);
			marca_field.setText(vehiculoABuscar.getMarca());
			modelo_field.setText(vehiculoABuscar.getModelo());

			

		} catch (Exception e) {
			String error= e.getMessage();
            Alert alert = new Alert(AlertType.ERROR);
               alert.setTitle("Error");
               alert.setHeaderText("Se produjo un error");
               alert.setContentText(error);

               alert.showAndWait();;
		}

	}


	

	public void setObs(ObservableList<Vehiculo> vehiculo) {
		this.listaVehiculo = vehiculo;
	}

	@FXML
	void cancelar(ActionEvent event) {
		Stage escenario = (Stage) ((Node) event.getSource()).getScene().getWindow();
		escenario.close();

	}

	public void setControladorMVC(IControlador controlador) {
		this.controladorMVC = controlador;
	}

	

}
