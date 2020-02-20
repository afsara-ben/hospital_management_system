/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databasepro.view;

import databasepro.Databasepro;
import static databasepro.Databasepro.mainLayout;
import static databasepro.Databasepro.stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class LoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
    loginModel loginmodel = new loginModel();
    ObservableList<String> combo = FXCollections.observableArrayList("Admin", "Accountant", "Doctor", "Nurse", "Pathologist", "Receptionist", "Patient");
    @FXML
    Button signInbutton;
    @FXML
    Button registrationbutton;
    @FXML
    TextField u1;
    @FXML
    PasswordField p1;
    @FXML
    Text errorid;
    @FXML
    ComboBox type;
    @FXML
    RadioButton rb1;
    @FXML
    RadioButton rb2;
    @FXML
    RadioButton rb3;
    @FXML
    RadioButton rb4;
    @FXML
    RadioButton rb5;
    @FXML
    RadioButton rb6;
    @FXML
    RadioButton rb7;
    /*  @FXML
     CheckBox cb1;
     @FXML
     CheckBox cb2;
     @FXML
     CheckBox cb3;*/

    @FXML
    void signIn() throws IOException, SQLException {
        try {
            if (loginmodel.isLoginValid(u1.getText(), p1.getText()) && /*type.getValue().equals("Admin")*/ rb1.isSelected()) {
                Databasepro.showAdmin();
            } else if (loginmodel.isLoginValid(u1.getText(), p1.getText()) && /*type.getValue().equals("Accountant")*/rb2.isSelected()) {
                Databasepro.showEmployee();
            } else if (loginmodel.isLoginValid(u1.getText(), p1.getText()) && /*type.getValue().equals("Doctor")*/rb3.isSelected()) {
                Databasepro.showEmployee();
            } else if (loginmodel.isLoginValid(u1.getText(), p1.getText()) && /*type.getValue().equals("Nurse")*/rb4.isSelected()) {
                Databasepro.showEmployee();
            } else if (loginmodel.isLoginValid(u1.getText(), p1.getText()) && /*type.getValue().equals("Pathologist")*/rb5.isSelected()) {
                Databasepro.showEmployee();
            } else if (loginmodel.isLoginValid(u1.getText(), p1.getText()) && /*type.getValue().equals("Receptionist")*/ rb6.isSelected()) {
                Databasepro.showEmployee();
            } else if (loginmodel.isLoginValid(u1.getText(), p1.getText()) &&  rb7.isSelected()/*type.getValue().equals("Patient")*/) {
                // Databasepro.showPatient();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Databasepro.class.getResource("view/patient.fxml"));
                mainLayout = loader.load();
                PatientController patientcontroller = (PatientController) loader.getController();
                patientcontroller.getPatient(Integer.parseInt(u1.getText()));
                Scene scene = new Scene(mainLayout);
                stage.setScene(scene);
                stage.setMaximized(true);
                stage.show();
                /*  Stage stage=new Stage();
                 FXMLLoader loader=new FXMLLoader();
                 Pane mainLayout=loader.load(getClass().getResource("view/patient.fxml").openStream());
                 PatientController patientcontroller=(PatientController)loader.getController();
                 patientcontroller.getPatient(u1.getText());
                 Scene scene=new Scene(mainLayout);
                 stage.setScene(scene);
                 stage.show();*/
            } else {
                errorid.setText("Invalid Login");
            }
        } catch (Exception e) {
            System.out.println(e);
            //  errorid.setText("invalid login");
        }
    }

    @FXML
    void registration() throws IOException {
        Databasepro.showRegistration();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        type.setItems(combo);
    }
}
