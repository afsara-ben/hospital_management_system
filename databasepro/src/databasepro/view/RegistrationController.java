/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databasepro.view;

import databasepro.Databasepro;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author user
 */
public class RegistrationController implements Initializable {

    /**
     * Initializes the controller class.
     */
    Connection conn = DBConnection.LoginConnector();
    PreparedStatement prepare = null;
    ResultSet result = null;
    @FXML
    TextField u1;
    @FXML
    PasswordField p1;
    @FXML
    Button signUpbutton;
    @FXML
    Button backbutton;
    @FXML
    ComboBox<String> type1;
    ObservableList<String> combo = FXCollections.observableArrayList("Admin", "Accountant", "Doctor", "Nurse", "Pathologist", "Receptionist", "Patient");
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        type1.setItems(combo);
    }
    static String typerb;
    @FXML
    public void radiobutton(ActionEvent event) {
        if (rb1.isSelected()) {
            typerb = rb1.getText();
        }
        if (rb2.isSelected()) {
            typerb = rb2.getText();
        }
        if (rb3.isSelected()) {
            typerb = rb3.getText();
        }
        if (rb4.isSelected()) {
            typerb = rb4.getText();
        }
        if (rb5.isSelected()) {
            typerb = rb5.getText();
        }
        if (rb6.isSelected()) {
            typerb = rb6.getText();
        }
        if (rb7.isSelected()) {
            typerb = rb7.getText();
        }

    }

    @FXML
    void signUp() throws SQLException {
        String user = u1.getText();
        String pass = p1.getText();
        String type = type1.getValue();

        String query = "insert into login(user_id,password,status) values(?,?,?)";
        prepare = null;
        try {
            System.out.println(typerb);
            prepare = conn.prepareStatement(query);
            prepare.setString(1, user);
            prepare.setString(2, pass);
            prepare.setString(3, typerb);
            prepare.execute();
            prepare.close();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            conn.close();
        }
    }

    @FXML
    void back() throws IOException {
        Databasepro.showLogin();
    }
}
