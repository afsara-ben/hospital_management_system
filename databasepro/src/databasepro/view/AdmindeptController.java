/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databasepro.view;

import databasepro.Databasepro;
import static databasepro.view.AdminjobController.tempjobtitle;
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
public class AdmindeptController implements Initializable {

    /**
     * Initializes the controller class.
     * 
     */
    Connection conn=DBConnection.LoginConnector();
    ObservableList<department> data = FXCollections.observableArrayList();
    FilteredList<department> filtereddata=new FilteredList<>(data,e->true);

    PreparedStatement prepare = null;
    ResultSet result = null;
    @FXML
    Button backbutton;
    @FXML
    Button logoutbutton;
    @FXML
    Button addnewbutton;
    @FXML
    Button deletebutton;
    @FXML
    TextField t1;
    @FXML
    TextField t2;
    @FXML
    TextField t3;
    @FXML
    TextField searchbox;
    @FXML
    TableView<department> tabdept;
    @FXML
    private TableColumn<?, ?> c1;
    @FXML
    private TableColumn<?, ?> c2;
    @FXML
    private TableColumn<?, ?> c3;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        c1.setCellValueFactory(new PropertyValueFactory<>("dept_id"));
        c2.setCellValueFactory(new PropertyValueFactory<>("dept_title"));
        c3.setCellValueFactory(new PropertyValueFactory<>("total_patient"));
        loaddatabase();
    }
     public void loaddatabase() {
        String query = "SELECT * FROM DEPARTMENT";
        try {
            t1.clear();
            t2.clear();
            t3.clear();
            data.clear();
            prepare = conn.prepareStatement(query);
            result = prepare.executeQuery();
            while (result.next()) {
                data.add(
                        new department(result.getInt("dept_id"),
                                result.getString("dept_title"),
                                result.getInt("total_patient"))
                );
                tabdept.setItems(data);
            }
            prepare.close();
            result.close();
        } catch (Exception e1) {
            System.err.println(e1);
        }
    }
     
     @FXML
    public void addnew() throws SQLException {
        String dept_idstr = t1.getText();
//        Integer dept_id = Integer.parseInt(dept_idstr);
        String dept_title = t2.getText();
        String total_pat=t3.getText();
      //  Integer total_patient=Integer.parseInt(total_pat);
        String query = "INSERT INTO DEPARTMENT VALUES(?,?,?)";
        prepare = null;
        try {
            prepare = conn.prepareStatement(query);
            prepare.setInt(1, Integer.valueOf(dept_idstr));
            prepare.setString(2, dept_title);
            prepare.setInt(3, Integer.valueOf(total_pat));
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            prepare.execute();
            prepare.close();
        }
        t1.clear();
        t2.clear();
        t3.clear();
        data.clear();
        loaddatabase();
        Databasepro.showInformationAlertBox("New dept ID of " + dept_title + " is added successfully");
    } 
    
     static String tempdepttitle;

    @FXML
    public void showonclick() {
        try {
            department dept = (department) tabdept.getSelectionModel().getSelectedItem();
            String query = "SELECT * FROM DEPARTMENT";
            prepare = conn.prepareStatement(query);
            Integer dept_id = dept.getDept_id();
            Integer total_pat=dept.getTotal_patient();
            tempdepttitle = dept.getDept_title();
            t1.setText(String.valueOf(dept_id));
            t2.setText(dept.getDept_title());
            t3.setText(String.valueOf(total_pat));
            prepare.close();
            result.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    @FXML
    public void delete() {
        String dept_title = null;
        try {
            department dept = (department) tabdept.getSelectionModel().getSelectedItem();
            String query = "DELETE FROM DEPARTMENT WHERE DEPT_TITLE=?";
            prepare = conn.prepareStatement(query);
            prepare.setString(1, dept.getDept_title());
            dept_title = dept.getDept_title();
            prepare.executeUpdate();

            prepare.close();
            result.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        loaddatabase();
        Databasepro.showInformationAlertBox("Dept ID of " + dept_title + " is deleted successfully");
    }

    
    @FXML
    public void update() {
        String query = "UPDATE DEPARTMENT SET DEPT_ID=?, DEPT_TITLE=?, TOTAL_PATIENT=? WHERE DEPT_TITLE ='" + tempdepttitle+"'";
        try {
            prepare = conn.prepareStatement(query);
            prepare.setInt(1, Integer.valueOf(t1.getText()));
            prepare.setString(2, t2.getText());
            prepare.setInt(3, Integer.valueOf(t3.getText()));
            prepare.execute();
            prepare.close();
            Databasepro.showInformationAlertBox("Dept ID of '"+ t2.getText()+"' is updated successfully");
            loaddatabase();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
     @FXML
    public void search()
    {
        searchbox.textProperty().addListener((observableValue,oldValue,newValue)->{
			filtereddata.setPredicate((Predicate<? super department>)dept->{
				if(newValue==null||newValue.isEmpty()){
					return true;
				}
				String lowerCaseFilter=newValue.toLowerCase();
				if(String.valueOf(dept.getDept_id()).toLowerCase().contains(lowerCaseFilter)){
					return true;
				}
				else if(dept.getDept_title().toLowerCase().contains(lowerCaseFilter)){
					return true;
				}
				return false;
			});
		});
		SortedList<department> sortedData=new SortedList<>(filtereddata);
		sortedData.comparatorProperty().bind(tabdept.comparatorProperty());
		tabdept.setItems(sortedData);
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
