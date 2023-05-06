package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import org.iesalandalus.programacion.alquilervehiculos.controlador.IControlador;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;

public class VentanaVehiculos {
	private IControlador controladorMVC;

	private ObservableList<Vehiculo> listaVehiculo;

	@FXML
	private RadioButton autobus_button;

	@FXML
	private Button cancelar;

	@FXML
	private Text cilindrada;

	@FXML
	private TextField cilindradafield;

	@FXML
	private Text dni_text;

	@FXML
	private RadioButton furgoneta_button;

	@FXML
	private Button insertar;

	@FXML
	private TextField marca_field;

	@FXML
	private TextField matricula_field;

	@FXML
	private TextField modelo_field;

	@FXML
	private Text nombre_text;

	@FXML
	private Text plazas;

	@FXML
	private TextField plazasfield;

	@FXML
	private Text pma;

	@FXML
	private TextField pmafield;

	@FXML
	private Text telefono_text;

	@FXML
	private ToggleGroup tipoVehiculo;

	@FXML
	private RadioButton turismo_button;

	@FXML
	void autobusShow(ActionEvent event) {

		turismoInvisible();
		furgonetaInvisible();
		autobusVisible();
		insertar.setDisable(false);

	}

	@FXML
	void furgonetaShow(ActionEvent event) {

		turismoInvisible();

		autobusInvisible();
		furgonetaVisible();
		insertar.setDisable(false);

	}

	@FXML
	void insertar(ActionEvent event) {

		Vehiculo vehiculo = null;

		if (furgoneta_button.isSelected()) {

			vehiculo = new Furgoneta(marca_field.getText(), modelo_field.getText(),
					Integer.parseInt(plazasfield.getText()), Integer.parseInt(pmafield.getText()),
					matricula_field.getText());

		}
		if (autobus_button.isSelected()) {

			vehiculo = new Autobus(marca_field.getText(), modelo_field.getText(),
					Integer.parseInt(plazasfield.getText()), matricula_field.getText());
		}

		if (turismo_button.isSelected()) {

			vehiculo = new Turismo(marca_field.getText(), modelo_field.getText(),
					Integer.parseInt(cilindradafield.getText()), matricula_field.getText());
		}
		
		try {
			
			controladorMVC.insertar(vehiculo);
			listaVehiculo.add(vehiculo);
			String error= "El vehiculo se ha insertado correctamente";
            Alert alert = new Alert(AlertType.CONFIRMATION);
               alert.setTitle("Ha ocurrido.");
               alert.setHeaderText("Mensaje de confirmación.");
               alert.setContentText(error);

               alert.showAndWait();
			Stage escenario = (Stage) ((Node) event.getSource()).getScene().getWindow();
			escenario.close();

		} catch (Exception e) {
			String error= e.getMessage();
            Alert alert = new Alert(AlertType.ERROR);
               alert.setTitle("Error");
               alert.setHeaderText("Se produjo un error");
               alert.setContentText(error);

               alert.showAndWait();;
		}

	}

	@FXML
	void turismoShow(ActionEvent event) {

		autobusInvisible();
		furgonetaInvisible();
		turismoVisible();
		insertar.setDisable(false);

	}

	void turismoVisible() {

		cilindrada.setVisible(true);
		cilindrada.setDisable(false);
		cilindradafield.setVisible(true);
		cilindradafield.setDisable(false);

	}

	void turismoInvisible() {

		cilindrada.setVisible(false);
		cilindrada.setDisable(true);
		cilindradafield.setVisible(false);
		cilindradafield.setDisable(true);

	}

	void autobusVisible() {

		plazas.setVisible(true);
		plazas.setDisable(false);
		plazasfield.setVisible(true);
		plazasfield.setDisable(false);

	}

	void autobusInvisible() {

		plazas.setVisible(false);
		plazas.setDisable(true);
		plazasfield.setVisible(false);
		plazasfield.setDisable(true);

	}

	void furgonetaVisible() {

		pma.setVisible(true);
		pma.setDisable(false);
		plazas.setVisible(true);
		plazas.setDisable(false);
		pmafield.setVisible(true);
		pmafield.setDisable(false);
		plazasfield.setVisible(true);
		plazasfield.setDisable(false);

	}

	void furgonetaInvisible() {

		pma.setVisible(false);
		pma.setDisable(true);
		plazas.setVisible(false);
		plazas.setDisable(true);
		pmafield.setVisible(false);
		pmafield.setDisable(true);
		plazasfield.setVisible(false);
		plazasfield.setDisable(true);

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
