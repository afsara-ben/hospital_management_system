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
public class AdminpatientController implements Initializable {

    /**
     * Initializes the controller class.
     */
    Connection conn = DBConnection.LoginConnector();
    ObservableList<patient> data = FXCollections.observableArrayList();
    FilteredList<patient> filtereddata=new FilteredList<>(data,e->true);
    
    PreparedStatement prepare = null;
    ResultSet result = null;
    @FXML
    Button addbutton;
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
    TableView<patient> tab3;
    @FXML
    private TableColumn<?, ?> c1;
    @FXML
    private TableColumn<?, ?> c2;
    @FXML
    private TableColumn<?, ?> c3;
    @FXML
    private TableColumn<?, ?> c4;
  /*  @FXML
    private TableColumn<?, ?> c5;
    @FXML
    private TableColumn<?, ?> c6;
    @FXML
    private TableColumn<?, ?> c7;
    @FXML
    private TableColumn<?, ?> c8;
    @FXML
    private TableColumn<?, ?> c9;*/
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        c1.setCellValueFactory(new PropertyValueFactory<>("p_id"));
        c2.setCellValueFactory(new PropertyValueFactory<>("p_firstname"));
        c3.setCellValueFactory(new PropertyValueFactory<>("p_lastname"));
        c4.setCellValueFactory(new PropertyValueFactory<>("p_gender"));
      /*  c5.setCellValueFactory(new PropertyValueFactory<>("p_dob"));
        c6.setCellValueFactory(new PropertyValueFactory<>("p_bloodgrp"));
        c7.setCellValueFactory(new PropertyValueFactory<>("p_add"));
        c8.setCellValueFactory(new PropertyValueFactory<>("p_contact"));
        c9.setCellValueFactory(new PropertyValueFactory<>("p_email"));*/
        loaddatabase();
    }    
    @FXML    
    public void loaddatabase()
    {
        String query = "SELECT * FROM PATIENT";
        try{
            t1.clear();
            t2.clear();
            t3.clear();
            t4.clear();
            t5.clear();
            t6.clear();
            t7.clear();
            t8.clear();
            t9.clear();
            data.clear();
            prepare = conn.prepareStatement(query);
            result = prepare.executeQuery();
             while (result.next()) {
                data.add(
                        new patient(result.getInt("p_id"),
                        result.getString("p_firstname"),
                        result.getString("p_lastname"),
                        result.getString("p_gender"),
                        result.getString("p_dob"),
                        result.getString("p_bloodgrp"),
                        result.getString("p_add"),
                        result.getInt("p_contact"),
                        result.getString("p_email"))
                );
                tab3.setItems(data);
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
    public void addnew() throws SQLException {
        String p_idstr = t1.getText();
        Integer p_id = Integer.parseInt(p_idstr);
        String p_firstname = t2.getText();
        String p_lastname = t3.getText();
        String p_gender = t4.getText();
        String p_dob = t5.getText();
        String p_bloodgrp = t6.getText();
        String p_add = t7.getText();
        Integer p_contact = Integer.parseInt(t8.getText());
        String p_email=t9.getText();
        String query = "INSERT INTO PATIENT VALUES(?,?,?,?,?,?,?,?,?)";
        prepare = null;
        try {
            prepare = conn.prepareStatement(query);
            prepare.setInt(1, p_id);
            prepare.setString(2, p_firstname);
            prepare.setString(3, p_lastname);
            prepare.setString(4, p_gender);
            prepare.setString(5, p_dob);
            prepare.setString(6, p_bloodgrp);
            prepare.setString(7, p_add);
            prepare.setInt(8, p_contact);
            prepare.setString(9, p_email);

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
        t6.clear();
        t7.clear();
        t8.clear();
        t9.clear();
        data.clear();
        loaddatabase();
        Databasepro.showInformationAlertBox("New patient ID of " + p_firstname + " " + p_lastname + " is added successfully");
    }
    
    static Integer tempp_id;
    @FXML
    public void showonclick() {
        try {
            patient pat = (patient) tab3.getSelectionModel().getSelectedItem();
            String query = "SELECT * FROM PATIENT";
            prepare = conn.prepareStatement(query);
            Integer p_id = pat.getP_id();
            tempp_id = pat.getP_id();
            t1.setText(String.valueOf(p_id));
            t2.setText(pat.getP_firstname());
            t3.setText(pat.getP_lastname());
            t4.setText(pat.getP_gender());
            t5.setText(pat.getP_dob());
            t6.setText(pat.getP_bloodgrp());
            t7.setText(pat.getP_add());
            t8.setText(String.valueOf(pat.getP_contact()));
            t9.setText(pat.getP_email());

            prepare.close();
            result.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
     @FXML
    public void delete() {
        Integer p_id = null;
        try {
            patient pat = (patient) tab3.getSelectionModel().getSelectedItem();
            String query = "DELETE FROM PATIENT WHERE P_ID=?";
            prepare = conn.prepareStatement(query);
            prepare.setInt(1, pat.getP_id());
            p_id = pat.getP_id();
            prepare.executeUpdate();

            prepare.close();
            result.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        loaddatabase();
        Databasepro.showInformationAlertBox("Patient info of " + p_id + " is deleted successfully");
    }

     @FXML
    public void update() {
        String query = "UPDATE PATIENT SET P_ID=?, P_FIRSTNAME=?, P_LASTNAME=?, P_GENDER=?, P_DOB=?, P_BLOODGRP=?, P_ADD=?, P_CONTACT=?, P_EMAIL=? WHERE P_ID ='" + tempp_id+"'";
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
            Databasepro.showInformationAlertBox("patient info of '"+ t1.getText()+"' is updated successfully");
            loaddatabase();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
      @FXML
    public void search()
    {
        searchbox.textProperty().addListener((observableValue,oldValue,newValue)->{
			filtereddata.setPredicate((Predicate<? super patient>)pat->{
				if(newValue==null||newValue.isEmpty()){
					return true;
				}
				String lowerCaseFilter=newValue.toLowerCase();
				if(String.valueOf(pat.getP_id()).toLowerCase().contains(lowerCaseFilter)){
					return true;
				}
				else if(pat.getP_firstname().toLowerCase().contains(lowerCaseFilter)){
					return true;
				}
                                else if(pat.getP_lastname().toLowerCase().contains(lowerCaseFilter)){
					return true;
				}
				return false;
			});
		});
		SortedList<patient> sortedData=new SortedList<>(filtereddata);
		sortedData.comparatorProperty().bind(tab3.comparatorProperty());
		tab3.setItems(sortedData);
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
