/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databasepro;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author user
 */
public class Databasepro extends Application {
    
    public static Stage stage;
    public static BorderPane mainLayout;

    @Override
    public void start(Stage primaryStage) throws IOException {
        Databasepro.stage=primaryStage;    
        Databasepro.stage.setTitle("HospitalManagementProject");
        stage.setMaximized(true);
        Databasepro.showLogin();
    }
    
    public static void showInformationAlertBox(String message)
    {
        Alert alert= new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialogue");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    public static void showLogin() throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Databasepro.class.getResource("view/login.fxml"));
        mainLayout=loader.load();
        Scene scene=new Scene(mainLayout);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
    
    public static void showAdmin() throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Databasepro.class.getResource("view/admin.fxml"));
        mainLayout=loader.load();
        Scene scene=new Scene(mainLayout);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
    
     public static void showPatient() throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Databasepro.class.getResource("view/patient.fxml"));
        mainLayout=loader.load();
        Scene scene=new Scene(mainLayout);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
    
    public static void showEmployee() throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Databasepro.class.getResource("view/employee.fxml"));
        mainLayout=loader.load();
        Scene scene=new Scene(mainLayout);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
    
    public static void showDoctor() throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Databasepro.class.getResource("view/doctor.fxml"));
        mainLayout=loader.load();
        Scene scene=new Scene(mainLayout);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
    
    public static void showNurse() throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Databasepro.class.getResource("view/doctor.fxml"));
        mainLayout=loader.load();
        Scene scene=new Scene(mainLayout);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
    
    public static void showAccountant() throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Databasepro.class.getResource("view/doctor.fxml"));
        mainLayout=loader.load();
        Scene scene=new Scene(mainLayout);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
    
    public static void showPathologist() throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Databasepro.class.getResource("view/doctor.fxml"));
        mainLayout=loader.load();
        Scene scene=new Scene(mainLayout);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
    
    public static void showReceptionist() throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Databasepro.class.getResource("view/doctor.fxml"));
        mainLayout=loader.load();
        Scene scene=new Scene(mainLayout);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
    
    public static void showRegistration() throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Databasepro.class.getResource("view/registration.fxml"));
        mainLayout=loader.load();
        Scene scene=new Scene(mainLayout);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
    
    public static void adminAccount() throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Databasepro.class.getResource("view/adminaccount.fxml"));
        mainLayout=loader.load();
        Scene scene=new Scene(mainLayout);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
    
    public static void adminAdmission() throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Databasepro.class.getResource("view/adminadmission.fxml"));
        mainLayout=loader.load();
        Scene scene=new Scene(mainLayout);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
    
    public static void adminApp() throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Databasepro.class.getResource("view/adminappointment.fxml"));
        mainLayout=loader.load();
        Scene scene=new Scene(mainLayout);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
    
    public static void adminDept() throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Databasepro.class.getResource("view/admindept.fxml"));
        mainLayout=loader.load();
        Scene scene=new Scene(mainLayout);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
    
    public static void adminDoc() throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Databasepro.class.getResource("view/admindoc.fxml"));
        mainLayout=loader.load();
        Scene scene=new Scene(mainLayout);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
    
    public static void adminDrug() throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Databasepro.class.getResource("view/admindrug.fxml"));
        mainLayout=loader.load();
        Scene scene=new Scene(mainLayout);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
    
    public static void adminJob() throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Databasepro.class.getResource("view/adminjob.fxml"));
        mainLayout=loader.load();
        Scene scene=new Scene(mainLayout);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
    
    public static void adminNurse() throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Databasepro.class.getResource("view/adminaccount.fxml"));
        mainLayout=loader.load();
        Scene scene=new Scene(mainLayout);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
    
    public static void adminPat() throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Databasepro.class.getResource("view/adminpatient.fxml"));
        mainLayout=loader.load();
        Scene scene=new Scene(mainLayout);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
    
    public static void adminPath() throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Databasepro.class.getResource("view/adminaccount.fxml"));
        mainLayout=loader.load();
        Scene scene=new Scene(mainLayout);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
    
    public static void adminPharma() throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Databasepro.class.getResource("view/adminpharma.fxml"));
        mainLayout=loader.load();
        Scene scene=new Scene(mainLayout);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
    
    public static void adminPay() throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Databasepro.class.getResource("view/adminpayment.fxml"));
        mainLayout=loader.load();
        Scene scene=new Scene(mainLayout);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
    
    public static void adminRecep() throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Databasepro.class.getResource("view/adminaccount.fxml"));
        mainLayout=loader.load();
        Scene scene=new Scene(mainLayout);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
    
    public static void adminRoom() throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Databasepro.class.getResource("view/adminroom.fxml"));
        mainLayout=loader.load();
        Scene scene=new Scene(mainLayout);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
    
    public static void adminSeat() throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Databasepro.class.getResource("view/adminseat.fxml"));
        mainLayout=loader.load();
        Scene scene=new Scene(mainLayout);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
    
    public static void adminTest() throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(Databasepro.class.getResource("view/admintest.fxml"));
        mainLayout=loader.load();
        Scene scene=new Scene(mainLayout);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
