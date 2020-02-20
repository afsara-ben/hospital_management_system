/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databasepro.view;

import databasepro.Databasepro;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author user
 */
public class EmployeeController {

    /**
     * Initializes the controller class.
     */
    @FXML
    Button doctorbutton;
    @FXML
    Button nursebutton;
    @FXML
    Button accountantbutton;
    @FXML
    Button reciptionistbutton;
    @FXML
    Button pathologistbutton;
    
    @FXML
    void godoctor() throws IOException
    {
        Databasepro.showDoctor();
    }
    @FXML
    void gonurse() throws IOException
    {
        Databasepro.showNurse();
    }
    @FXML
    void goreciptionist() throws IOException
    {
        Databasepro.showReceptionist();
    }
    @FXML
    void goaccountant() throws IOException
    {
        Databasepro.showAccountant();
    }
    @FXML
    void gopathologist() throws IOException
    {
        Databasepro.showPathologist();
    }
    @FXML
    void back() throws IOException
    {
        Databasepro.showLogin();
    }
    
}
