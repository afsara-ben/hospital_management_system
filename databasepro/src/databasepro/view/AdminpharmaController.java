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
public class AdminpharmaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    Connection conn = DBConnection.LoginConnector();
    ObservableList<pharmacompany> data = FXCollections.observableArrayList();
    FilteredList<pharmacompany> filtereddata=new FilteredList<>(data,e->true);

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
    TableView<pharmacompany> tabpharma;
    @FXML
    TableColumn<?, ?> c1;
    @FXML
    TableColumn<?, ?> c2;
    @FXML
    TableColumn<?, ?> c3;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        c1.setCellValueFactory(new PropertyValueFactory<>("pharma_id"));
        c2.setCellValueFactory(new PropertyValueFactory<>("pharma_name"));
        c3.setCellValueFactory(new PropertyValueFactory<>("pharma_contact"));
        loaddatabase();
    }    
    
    public void loaddatabase()
    {
         String query = "SELECT * FROM PHARMACOMPANY";
        try {
            t1.clear();
            t2.clear();
            t3.clear();
            data.clear();
            prepare = conn.prepareStatement(query);
            result = prepare.executeQuery();
            while (result.next()) {
                data.add(
                        new pharmacompany(result.getInt("pharma_id"),
                                result.getString("pharma_name"),
                                result.getInt("pharma_contact"))
                );
                tabpharma.setItems(data);
            }
            prepare.close();
            result.close();
        } catch (Exception e1) {
            System.err.println(e1);
        }
    }
    
    @FXML
    public void addnew() throws SQLException {
        String pharma_idstr = t1.getText();
        Integer pharma_id = Integer.parseInt(pharma_idstr);
        String pharma_name = t2.getText();
        Integer pharma_contact = Integer.parseInt(t3.getText());
        String query = "INSERT INTO PHARMACOMPANY VALUES(?,?,?)";
        prepare = null;
        try {
            prepare = conn.prepareStatement(query);
            prepare.setInt(1, pharma_id);
            prepare.setString(2, pharma_name);
            prepare.setInt(3, pharma_contact);

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
        Databasepro.showInformationAlertBox("New pharma company info of " + pharma_id + " is added successfully");
    }
    
     static Integer temppharma_id;
    
    @FXML
    public void showonclick() {
        try {
            pharmacompany pharma = (pharmacompany) tabpharma.getSelectionModel().getSelectedItem();
            String query = "SELECT * FROM PHARMACOMPANY";
            prepare = conn.prepareStatement(query);
            Integer pharma_id = pharma.getPharma_id();
            temppharma_id = pharma.getPharma_id();
            t1.setText(String.valueOf(pharma_id));
            t2.setText(pharma.getPharma_name());
            t3.setText(String.valueOf(pharma.getPharma_contact()));

            prepare.close();
            result.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
     @FXML
    public void delete() {
        Integer pharma_id = null;
        try {
            pharmacompany pharma = (pharmacompany) tabpharma.getSelectionModel().getSelectedItem();
            String query = "DELETE FROM PHARMACOMPANY WHERE PHARMA_ID=?";
            prepare = conn.prepareStatement(query);
            prepare.setInt(1, pharma.getPharma_id());
            pharma_id = pharma.getPharma_id();
            prepare.executeUpdate();

            prepare.close();
            result.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        loaddatabase();
        Databasepro.showInformationAlertBox("Appointment info of " + pharma_id + " is deleted successfully");
    }
    
     @FXML
    public void update() {
        String query = "UPDATE PHARMACOMPANY SET PHARMA_ID=?, PHARMA_NAME=?, PHARMA_CONTACT=? WHERE PHARMA_ID ='" + temppharma_id+"'";
        try {
            prepare = conn.prepareStatement(query);
            prepare.setInt(1, Integer.valueOf(t1.getText()));
            prepare.setString(2, t2.getText());
            prepare.setInt(3, Integer.valueOf(t3.getText()));
            prepare.execute();
            prepare.close();
            Databasepro.showInformationAlertBox("Pharma company info of '"+ t2.getText()+"' is updated successfully");
            loaddatabase();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
     @FXML
    public void search()
    {
        searchbox.textProperty().addListener((observableValue,oldValue,newValue)->{
			filtereddata.setPredicate((Predicate<? super pharmacompany>)pharma->{
				if(newValue==null||newValue.isEmpty()){
					return true;
				}
				String lowerCaseFilter=newValue.toLowerCase();
				if(String.valueOf(pharma.getPharma_id()).toLowerCase().contains(lowerCaseFilter)){
					return true;
				}
				else if(pharma.getPharma_name().toLowerCase().contains(lowerCaseFilter)){
					return true;
				}
				return false;
			});
		});
		SortedList<pharmacompany> sortedData=new SortedList<>(filtereddata);
		sortedData.comparatorProperty().bind(tabpharma.comparatorProperty());
		tabpharma.setItems(sortedData);
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
