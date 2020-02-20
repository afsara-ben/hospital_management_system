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
public class AdmintestController implements Initializable {

    /**
     * Initializes the controller class.
     */
    Connection conn = DBConnection.LoginConnector();
    ObservableList<test> data = FXCollections.observableArrayList();
    FilteredList<test> filtereddata=new FilteredList<>(data,e->true);

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
    TableView<test> tabtest;
    @FXML
    TableColumn<?, ?> c1;
    @FXML
    TableColumn<?, ?> c2;
    @FXML
    TableColumn<?, ?> c3;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        c1.setCellValueFactory(new PropertyValueFactory<>("test_id"));
        c2.setCellValueFactory(new PropertyValueFactory<>("test_name"));
        c3.setCellValueFactory(new PropertyValueFactory<>("test_cost"));
        loaddatabase();
    }    
    
    public void loaddatabase()
    {
        String query = "SELECT * FROM TEST";
        try {
            t1.clear();
            t2.clear();
            t3.clear();
            data.clear();
            prepare = conn.prepareStatement(query);
            result = prepare.executeQuery();
            while (result.next()) {
                data.add(
                        new test(result.getInt("test_id"),
                                result.getString("test_name"),
                                result.getInt("test_cost"))
                );
                tabtest.setItems(data);
            }
            prepare.close();
            result.close();
        } catch (Exception e1) {
            System.err.println(e1);
        }
    }
    
    @FXML
    public void addnew() throws SQLException {
        String test_idstr = t1.getText();
        Integer test_id = Integer.parseInt(test_idstr);
        String test_name = t2.getText();
        Integer test_cost = Integer.parseInt(t3.getText());
        String query = "INSERT INTO TEST VALUES(?,?,?)";
        prepare = null;
        try {
            prepare = conn.prepareStatement(query);
            prepare.setInt(1, test_id);
            prepare.setString(2, test_name);
            prepare.setInt(3, test_cost);

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
        Databasepro.showInformationAlertBox("New test info of " + test_name + " is added successfully");
    }
    
     static Integer temptest_id;
    
    @FXML
    public void showonclick() {
        try {
            test Test = (test) tabtest.getSelectionModel().getSelectedItem();
            String query = "SELECT * FROM TEST";
            prepare = conn.prepareStatement(query);
            Integer test_id = Test.getTest_id();
            temptest_id = Test.getTest_id();
            t1.setText(String.valueOf(test_id));
            t2.setText(Test.getTest_name());
            t3.setText(String.valueOf(Test.getTest_cost()));

            prepare.close();
            result.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
     @FXML
    public void delete() {
        String test_name = null;
        try {
            test Test = (test) tabtest.getSelectionModel().getSelectedItem();
            String query = "DELETE FROM TEST WHERE TEST_NAME=?";
            prepare = conn.prepareStatement(query);
            prepare.setString(1, Test.getTest_name());
            test_name = Test.getTest_name();
            prepare.executeUpdate();

            prepare.close();
            result.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        loaddatabase();
        Databasepro.showInformationAlertBox("Test info of " + test_name + " is deleted successfully");
    }
    
    @FXML
    public void update() {
        String query = "UPDATE TEST SET TEST_ID=?, TEST_NAME=?, TEST_COST=? WHERE TEST_ID ='" + temptest_id+"'";
        try {
            prepare = conn.prepareStatement(query);
            prepare.setInt(1, Integer.valueOf(t1.getText()));
            prepare.setString(2, t2.getText());
            prepare.setInt(3, Integer.valueOf(t3.getText()));
            prepare.execute();
            prepare.close();
            Databasepro.showInformationAlertBox("Test info of '"+ t2.getText()+"' is updated successfully");
            loaddatabase();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
     @FXML
    public void search()
    {
        searchbox.textProperty().addListener((observableValue,oldValue,newValue)->{
			filtereddata.setPredicate((Predicate<? super test>)Test->{
				if(newValue==null||newValue.isEmpty()){
					return true;
				}
				String lowerCaseFilter=newValue.toLowerCase();
				if(String.valueOf(Test.getTest_id()).toLowerCase().contains(lowerCaseFilter)){
					return true;
				}
                                else if(Test.getTest_name().toLowerCase().contains(lowerCaseFilter)){
					return true;
				}
                                else if(String.valueOf(Test.getTest_cost()).toLowerCase().contains(lowerCaseFilter)){
					return true;
				}
				return false;
			});
		});
		SortedList<test> sortedData=new SortedList<>(filtereddata);
		sortedData.comparatorProperty().bind(tabtest.comparatorProperty());
		tabtest.setItems(sortedData);
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
