package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import org.iesalandalus.programacion.alquilervehiculos.controlador.IControlador;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
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

public class BorrarVehiculos {

	private IControlador controladorMVC;

	private ObservableList<Vehiculo> listaVehiculo;

	@FXML
	private RadioButton autobus_button;

	@FXML
	private Button cancelar;

	@FXML
	private Button buscar;

	@FXML
	private Text cilindrada;

	@FXML
	private TextField cilindradafield;

	@FXML
	private Text dni_text;

	@FXML
	private RadioButton furgoneta_button;

	@FXML
	private Button borrar;

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
	void borrarVehiculo(ActionEvent event) {

		try {
			Vehiculo vehiculoDummy = new Turismo("Dummy", "Dummy", 10, matricula_field.getText());
			controladorMVC.borrar(controladorMVC.buscar(vehiculoDummy));
			listaVehiculo.remove(vehiculoDummy);
			Stage escenario = (Stage) ((Node) event.getSource()).getScene().getWindow();
			escenario.close();
			String error= "El vehiculo se ha borrado correctamente";
            Alert alert = new Alert(AlertType.CONFIRMATION);
               alert.setTitle("Ha ocurrido.");
               alert.setHeaderText("Mensaje de confirmación.");
               alert.setContentText(error);

               alert.showAndWait();

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
	void buscarVehiculo(ActionEvent event) {

		try {
			Vehiculo vehiculoDummy = new Turismo("Dummy", "Dummy", 10, matricula_field.getText());
			Vehiculo vehiculoABuscar = controladorMVC.buscar(vehiculoDummy);
			buscar.setDisable(true);
			borrar.setDisable(false);
			matricula_field.setDisable(true);
			marca_field.setText(vehiculoABuscar.getMarca());
			modelo_field.setText(vehiculoABuscar.getModelo());
			

			/*if (vehiculoDummy instanceof Turismo) {
				System.out.println("Urismo");

				autobusInvisible();
				furgonetaInvisible();
				turismoVisible();
				//cilindradafield.setText(Integer.toString(((Turismo) vehiculoABuscar).getCilindrada()));

			}
			
			if (vehiculoDummy instanceof Autobus) {
				
				System.out.println("Utobus");

				autobusVisible();
				furgonetaInvisible();
				turismoInvisible();
				//plazasfield.setText(Integer.toString(((Autobus) vehiculoABuscar).getPlazas()));

			}
			
			if (vehiculoDummy instanceof Furgoneta) {
				System.out.println("Furgoneta");
				autobusInvisible();
				furgonetaVisible();
				turismoInvisible();
				//cilindradafield.setText(Integer.toString(((Turismo) vehiculoABuscar).getCilindrada()));
				//pmafield.setText(Integer.toString(((Furgoneta) vehiculoABuscar).getPma()));

			}*/



		} catch (Exception e) {
			String error= e.getMessage();
            Alert alert = new Alert(AlertType.ERROR);
               alert.setTitle("Error");
               alert.setHeaderText("Se produjo un error");
               alert.setContentText(error);

               alert.showAndWait();;
		}

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
