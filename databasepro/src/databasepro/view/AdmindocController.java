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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
public class AdmindocController implements Initializable {

    /**
     * Initializes the controller class.
     */
    Connection conn = DBConnection.LoginConnector();
    ObservableList<accountant> data = FXCollections.observableArrayList();
    FilteredList<accountant> filtereddata=new FilteredList<>(data,e->true);

    PreparedStatement prepare = null;
    ResultSet result = null;
    PreparedStatement prepare1 = null;
    ResultSet result1 = null;
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
    TableView<accountant> tabdoc;
    @FXML
    TableColumn<?, ?> c1;
    @FXML
    TableColumn<?, ?> c2;
    @FXML
    TableColumn<?, ?> c3;
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
    @FXML
    TextField t10;
    @FXML
    TextField t11;
    @FXML
    TextField t12;
    @FXML
    TextField t13;
    @FXML
    TextField t14;
    @FXML
    TextField t15;
    @FXML
    TextField t16;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        c1.setCellValueFactory(new PropertyValueFactory<>("em_id"));
        c2.setCellValueFactory(new PropertyValueFactory<>("em_firstname"));
        c3.setCellValueFactory(new PropertyValueFactory<>("em_lastname"));
        loaddatabase();
    }    
    
     public void loaddatabase() {
        String query = "SELECT * FROM EMPLOYEE WHERE OCCUPATION ='doctor'";
        try {
            prepare = conn.prepareStatement(query);
            result = prepare.executeQuery();
            while (result.next()) {
                data.add(
                        new accountant(result.getInt("em_id"),
                                result.getString("em_firstname"),
                                result.getString("em_lastname"),
                                result.getString("em_desig"),
                                result.getString("em_dob"),
                                result.getString("joining_date"),
                                result.getInt("salary"),
                                result.getString("em_add"),
                                result.getInt("em_contact"),
                                //  result.getString("em_email"),
                                result.getInt("job_id"),
                                result.getInt("dept_id"),
                                result.getString("qualification"))
                );
                tabdoc.setItems(data);
            }
            prepare.close();
            result.close();
        } catch (Exception e1) {
            System.err.println(e1);
        }
    }
    
    
    
    @FXML
    public void addnew() throws SQLException
    {
        String em_idstr = t1.getText();
        Integer em_id = Integer.parseInt(em_idstr);
        String em_firstname = t2.getText();
        String em_lastname = t3.getText();
        String em_desig = t4.getText();
        String em_dob = t5.getText();
        String joining_date = t6.getText();
        Integer salary = Integer.parseInt(t7.getText());
        String em_add = t8.getText();
        Integer em_contact = Integer.parseInt(t9.getText());
        //   String em_email = t10.getText();
        Integer job_id = Integer.parseInt(t10.getText());
        Integer dept_id = Integer.parseInt(t11.getText());
        String qualification = t12.getText();
        Integer d_status=Integer.parseInt(t13.getText());
        Integer d_experience=Integer.parseInt(t14.getText());
        String opd_day=t15.getText();
        String opd_time=t16.getText();
        String query = "INSERT INTO EMPLOYEE VALUES(?,?,?,?,?,?,?,?,?,?,?,?,'doctor')";
        String query1="INSERT INTO DOCTOR VALUES(?,?,?,?,?)";
        prepare = null;
        prepare1=null;
        try {
            prepare = conn.prepareStatement(query);
            prepare1=conn.prepareStatement(query1);
            prepare.setInt(1, em_id);
            prepare.setString(2, em_firstname);
            prepare.setString(3, em_lastname);
            prepare.setString(4, em_desig);
            prepare.setString(5, em_dob);
            prepare.setString(6, joining_date);
            prepare.setInt(7, salary);
            prepare.setString(8, em_add);
            prepare.setInt(9, em_contact);
            //  prepare.setString(10, em_email);
            prepare.setInt(10, job_id);
            prepare.setInt(11, dept_id);
            prepare.setString(12, qualification);
            prepare1.setInt(1, em_id);
            prepare1.setInt(2,d_status);
            prepare1.setInt(3,d_experience);
            prepare1.setString(4, opd_time);
            prepare1.setString(5, opd_day);

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            prepare.execute();
            prepare1.execute();
            prepare.close();
            prepare1.close();
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
        t10.clear();
        t11.clear();
        t12.clear();
        t13.clear();
        t14.clear();
        t15.clear();
        t16.clear();
        data.clear();
        loaddatabase();
        Databasepro.showInformationAlertBox("New job ID of " + em_firstname + " " + em_lastname + " is added successfully");
    }
    
    @FXML
    void delete()
    {
        
    }
    
    @FXML
     void update()
    {
        
    }
    
    @FXML
     void search()
    {
        
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
