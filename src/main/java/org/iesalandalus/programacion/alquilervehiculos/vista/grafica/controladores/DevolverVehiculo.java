package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.controlador.IControlador;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DevolverVehiculo {
	
	private IControlador controladorMVC;

	private ObservableList<Alquiler> listaAlquiler;
	
	private static String PATRON_FECHA = "dd/MM/yyyy";
	
	private static DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern(PATRON_FECHA);

	 @FXML
	    private Button añadir_botón;

	    @FXML
	    private Button añadir_botón1;


	    @FXML
	    private Button cancelar;

	    @FXML
	    private TextField dev_field;

	    @FXML
	    private Text fecha_text1;

	    @FXML
	    private TextField matricula_field;

	    @FXML
	    private Text matricula_text;

	    @FXML
	    private Button modificar;
  

    @FXML
    void devolverAlquiler(ActionEvent event) {
    	
    	try {
			controladorMVC.devolver(Vehiculo.getVehiculoConMatricula(matricula_field.getText()), LocalDate.parse(dev_field.getText(), FORMATO_FECHA));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			String error= e.getMessage();
            Alert alert = new Alert(AlertType.ERROR);
               alert.setTitle("Error");
               alert.setHeaderText("Se produjo un error");
               alert.setContentText(error);

               alert.showAndWait();;
		}
    	String error= "El alquiler se ha devuelto correctamente";
        Alert alert = new Alert(AlertType.CONFIRMATION);
           alert.setTitle("Ha ocurrido.");
           alert.setHeaderText("Mensaje de confirmación.");
           alert.setContentText(error);

           alert.showAndWait();
    	Stage escenario = (Stage) ((Node) event.getSource()).getScene().getWindow();
		escenario.close();

    }
    public void setObs(ObservableList<Alquiler> alquiler) {
 		this.listaAlquiler = alquiler;
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
