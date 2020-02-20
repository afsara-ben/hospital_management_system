/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databasepro.view;

import databasepro.Databasepro;
import java.io.IOException;
import static java.lang.System.exit;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AdminappointmentController implements Initializable {

    /**
     * Initializes the controller class.
     */
    Connection conn = DBConnection.LoginConnector();
    ObservableList<appointment> data = FXCollections.observableArrayList();
    FilteredList<appointment> filtereddata=new FilteredList<>(data,e->true);

    PreparedStatement prepare = null;
    ResultSet result = null;
    @FXML
    Button addnewbutton;
    @FXML
    Button deletebutton;
    @FXML
    Button updatebutton;
    @FXML
    TextField searchbox;
    @FXML
    Button backbutton;
    @FXML
    Button logoutbutton;
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
    TableView<appointment> tabapp;
    @FXML
    TableColumn<?, ?> c1;
    @FXML
    TableColumn<?, ?> c2;
    @FXML
    TableColumn<?, ?> c3;
    @FXML
    TableColumn<?, ?> c4;
    @FXML
    TableColumn<?, ?> c5;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        c1.setCellValueFactory(new PropertyValueFactory<>("apt_id"));
        c2.setCellValueFactory(new PropertyValueFactory<>("apt_date"));
        c3.setCellValueFactory(new PropertyValueFactory<>("apt_time"));
        c4.setCellValueFactory(new PropertyValueFactory<>("p_id"));
        c5.setCellValueFactory(new PropertyValueFactory<>("doctor_id"));
        loaddatabase();
    }    
    
    public void loaddatabase()
    {
         String query = "SELECT * FROM APPOINTMENT";
        try {
            t1.clear();
            t2.clear();
            t3.clear();
            t4.clear();
            t5.clear();
            data.clear();
            prepare = conn.prepareStatement(query);
            result = prepare.executeQuery();
            while (result.next()) {
                data.add(
                        new appointment(result.getInt("apt_id"),
                                result.getString("apt_date"),
                                result.getString("apt_time"),
                                result.getInt("p_id"),
                                result.getInt("doctor_id"))
                );
                tabapp.setItems(data);
            }
            prepare.close();
            result.close();
        } catch (Exception e1) {
            System.err.println(e1);
        }
    }
    
    @FXML
    public void addnew() throws SQLException {
        String apt_idstr = t1.getText();
        Integer apt_id = Integer.parseInt(apt_idstr);
        String apt_date = t2.getText();
        String apt_time = t3.getText();
        Integer p_id = Integer.parseInt(t4.getText());
        Integer doctor_id = Integer.parseInt(t5.getText());
        String query = "INSERT INTO APPOINTMENT VALUES(?,?,?,?,?)";
        prepare = null;
        try {
            prepare = conn.prepareStatement(query);
            prepare.setInt(1, apt_id);
            prepare.setString(2, apt_date);
            prepare.setString(3, apt_time);
            prepare.setInt(4, p_id);
            prepare.setInt(5, doctor_id);

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            prepare.execute();
            prepare.close();
        }
        t1.clear();
        t2.clear();
        t3.clear();
        t4.clear();
        t5.clear();
        data.clear();
        loaddatabase();
        Databasepro.showInformationAlertBox("New appointment ID of " + p_id + " is added successfully");
    }
    
    static Integer tempapt_id;
    
    @FXML
    public void showonclick() {
        try {
            appointment app = (appointment) tabapp.getSelectionModel().getSelectedItem();
            String query = "SELECT * FROM APPOINTMENT";
            prepare = conn.prepareStatement(query);
            Integer apt_id = app.getApt_id();
            tempapt_id = app.getApt_id();
            t1.setText(String.valueOf(apt_id));
            t2.setText(app.getApt_date());
            t3.setText(app.getApt_time());
            t4.setText(String.valueOf(app.getP_id()));
            t5.setText(String.valueOf(app.getDoctor_id()));

            prepare.close();
            result.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    @FXML
    public void delete() {
        Integer apt_id = null;
        try {
            appointment app = (appointment) tabapp.getSelectionModel().getSelectedItem();
            String query = "DELETE FROM APPOINTMENT WHERE APT_ID=?";
            prepare = conn.prepareStatement(query);
            prepare.setInt(1, app.getApt_id());
            apt_id = app.getApt_id();
            prepare.executeUpdate();

            prepare.close();
            result.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        loaddatabase();
        Databasepro.showInformationAlertBox("Appointment info of " + apt_id + " is deleted successfully");
    }
    
     @FXML
    public void update() {
        String query = "UPDATE APPOINTMENT SET APT_ID=?, APT_DATE=?, APT_TIME=?, P_ID=?, DOCTOR_ID=? WHERE APT_ID ='" + tempapt_id+"'";
        try {
            prepare = conn.prepareStatement(query);
            prepare.setInt(1, Integer.valueOf(t1.getText()));
            prepare.setString(2, t2.getText());
            prepare.setString(3, t3.getText());
            prepare.setInt(4, Integer.valueOf(t4.getText()));
            prepare.setInt(5, Integer.valueOf(t5.getText()));
            prepare.execute();
            prepare.close();
            Databasepro.showInformationAlertBox("Appointment info of '"+ t1.getText()+"' is updated successfully");
            loaddatabase();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    @FXML
    public void search()
    {
        searchbox.textProperty().addListener((observableValue,oldValue,newValue)->{
			filtereddata.setPredicate((Predicate<? super appointment>)app->{
				if(newValue==null||newValue.isEmpty()){
					return true;
				}
				String lowerCaseFilter=newValue.toLowerCase();
				if(String.valueOf(app.getApt_id()).toLowerCase().contains(lowerCaseFilter)){
					return true;
				}
				else if(String.valueOf(app.getP_id()).toLowerCase().contains(lowerCaseFilter)){
					return true;
				}
                                else if(String.valueOf(app.getDoctor_id()).toLowerCase().contains(lowerCaseFilter)){
					return true;
				}
				return false;
			});
		});
		SortedList<appointment> sortedData=new SortedList<>(filtereddata);
		sortedData.comparatorProperty().bind(tabapp.comparatorProperty());
		tabapp.setItems(sortedData);
    }
    
    @FXML
    void back() throws IOException
    {
        Databasepro.showAdmin();
    }
    
    @FXML
    void logout()
    {
        exit(1);
    }
}
