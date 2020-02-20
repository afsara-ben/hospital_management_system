/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databasepro.view;

import databasepro.Databasepro;
import static databasepro.Databasepro.mainLayout;
import static databasepro.Databasepro.stage;
import static databasepro.view.PatientController.temppatient_id;
import java.io.IOException;
import static java.lang.System.exit;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author user
 */
public class PatientController implements Initializable {

    /**
     * Initializes the controller class.
     */
    Connection conn = DBConnection.LoginConnector();
    ObservableList<patient> data = FXCollections.observableArrayList();
    FilteredList<patient> filtereddata=new FilteredList<>(data,e->true);

    PreparedStatement prepare = null;
    ResultSet result = null;
    @FXML
    Button backbutton;
    @FXML
    Button logoutbutton;
    @FXML
    Button editbutton;
    @FXML
    Button billbutton;
    @FXML
    Button appbutton;
    @FXML
    Button presbutton;
    @FXML
    Button admissionbutton;
    @FXML
    Button showprofilebutton;
    @FXML
    Button showdocbutton;
    @FXML
    TextField t1;
    @FXML
    TextField t2;
    @FXML
    TextField t3;
    @FXML
    TextField t4;
    @FXML
    TextField t5;
    @FXML
    TextField t6;
    @FXML
    TextField t7;
    @FXML
    TextField t8;
    @FXML
    TextField t9;
    static Integer temppatient_id;

    public void getPatient(Integer patient)
    {
        temppatient_id=patient;
       // System.out.println("patient id is "+temppatient_id);
         String query="SELECT * FROM PATIENT WHERE P_ID='"+temppatient_id+"'";
        System.out.println(temppatient_id);
        try {
            prepare = conn.prepareStatement(query);
            result = prepare.executeQuery();
            result.next();
            {
                Integer p_id=result.getInt("p_id");
                String p_firstname=result.getString("p_firstname");
                String p_lastname=result.getString("p_lastname");
                String p_gender=result.getString("p_gender");
                String p_dob=result.getString("p_dob");
                String p_bloodgrp=result.getString("p_bloodgrp");
                String p_add=result.getString("p_add");
                Integer p_contact=result.getInt("p_contact");
                String p_email=result.getString("p_email");
                
                t1.setText(String.valueOf(p_id));
                t2.setText(p_firstname);
                t3.setText(p_lastname);
                t4.setText(p_gender);
                t5.setText(p_dob);
                t6.setText(p_bloodgrp);
                t7.setText(p_add);
                t8.setText(String.valueOf(p_contact));
                t9.setText(p_email);
            }
            prepare.close();
            result.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    @FXML
    public void showprofile()
    {
        getPatient(temppatient_id);        
    }

    @FXML
    public void edit()
    {
        System.out.println(temppatient_id);
         String query = "UPDATE PATIENT SET P_ID=?, P_FIRSTNAME=?, P_LASTNAME=?, P_GENDER=?, P_DOB=?, P_BLOODGRP=?, P_ADD=?, P_CONTACT=?, P_EMAIL=? WHERE P_ID ='" + temppatient_id+"'";
        try {
            prepare = conn.prepareStatement(query);
            prepare.setInt(1, Integer.valueOf(t1.getText()));
            prepare.setString(2, t2.getText());
            prepare.setString(3, t3.getText());
            prepare.setString(4, t4.getText());
            prepare.setString(5, t5.getText());
            prepare.setString(6, t6.getText());
            prepare.setString(7, t7.getText());
            prepare.setInt(8, Integer.valueOf(t8.getText()));
            prepare.setString(9, t9.getText());
            prepare.execute();
            prepare.close();
            Databasepro.showInformationAlertBox("patient info of '"+ t2.getText()+" "+t3.getText()+"' is updated successfully");
           // loaddatabase();
            getPatient(temppatient_id);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    @FXML
    public void bill() throws IOException
    {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Databasepro.class.getResource("view/patientpayment.fxml"));
        mainLayout=loader.load();
        PatientpaymentController patientpaymentcontroller = (PatientpaymentController) loader.getController();
        patientpaymentcontroller.loaddatabase(temppatient_id);
        Scene scene=new Scene(mainLayout);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
    
    @FXML
    public void appointment() throws IOException
    {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Databasepro.class.getResource("view/patientapp.fxml"));
        mainLayout=loader.load();
        PatientappController patientappcontroller = (PatientappController) loader.getController();
        patientappcontroller.loaddatabase(temppatient_id);
        Scene scene=new Scene(mainLayout);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
    
    @FXML
    public void admission() throws IOException
    {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Databasepro.class.getResource("view/patientadmission.fxml"));
        mainLayout=loader.load();
        PatientadmissionController patientadmissioncontroller = (PatientadmissionController) loader.getController();
        patientadmissioncontroller.loaddatabase(temppatient_id);
        Scene scene=new Scene(mainLayout);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
    
    @FXML
    public void prescription() throws IOException
    {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Databasepro.class.getResource("view/patientpres.fxml"));
        mainLayout=loader.load();
        PatientpresController patientprescontroller = (PatientpresController) loader.getController();
        patientprescontroller.loaddatabase(temppatient_id);
        Scene scene=new Scene(mainLayout);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
    
    @FXML
    public void doctor() throws IOException
    {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Databasepro.class.getResource("view/patientdoctor.fxml"));
        mainLayout=loader.load();
        Scene scene=new Scene(mainLayout);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
    
    @FXML
    void back() throws IOException
    {
        Databasepro.showLogin();
    }
    
    @FXML
    void logout()
    {
        exit(1);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
