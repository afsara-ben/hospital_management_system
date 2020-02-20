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

/**
 * FXML Controller class
 *
 * @author user
 */

public class AdminController {

    /**
     * Initializes the controller class.
     */
    
@FXML
Button accountbutton;
@FXML
Button admissionbutton;
@FXML
Button appbutton;
@FXML
Button deptbutton;
@FXML
Button drugbutton;
@FXML
Button docbutton;
@FXML
Button jobbutton;
@FXML
Button patientbutton;
@FXML
Button nursebutton;
@FXML
Button pathbutton;
@FXML
Button recepbutton;
@FXML
Button pharmabutton;
@FXML
Button paybutton;
@FXML
Button roombutton;
@FXML
Button testbutton;
@FXML
Button seatbutton;
@FXML
Button backbutton;
@FXML
void showaccount() throws IOException
{
    Databasepro.adminAccount();
}

@FXML
void showAdmission() throws IOException
{
    Databasepro.adminAdmission();
}
@FXML
void showApp() throws IOException
{
    Databasepro.adminApp();
}
@FXML
void showDept() throws IOException
{
    Databasepro.adminDept();
}
@FXML
void showDrug() throws IOException
{
    Databasepro.adminDrug();
}
@FXML
void showdoc() throws IOException
{
    Databasepro.adminDoc();
}
@FXML
void showrecep() throws IOException
{
    Databasepro.adminRecep();
}
@FXML
void showpath() throws IOException
{
    Databasepro.adminPath();
}
@FXML
void shownurse() throws IOException
{
    Databasepro.adminNurse();
}
@FXML
void showJob() throws IOException
{
    Databasepro.adminJob();
}
@FXML
void showPat() throws IOException
{
    Databasepro.adminPat();
}

@FXML
void showPharma() throws IOException
{
    Databasepro.adminPharma();
}
@FXML
void showPay() throws IOException
{
    Databasepro.adminPay();
}
@FXML
void showRoom() throws IOException
{
    Databasepro.adminRoom();
}
@FXML
void showSeat() throws IOException
{
    Databasepro.adminSeat();
}
@FXML
void showTest() throws IOException
{
    Databasepro.adminTest();
}
@FXML
void back() throws IOException
{
    Databasepro.showLogin();
}
}
