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
public class PatientappController implements Initializable {

    /**
     * Initializes the controller class.
     */
    Connection conn = DBConnection.LoginConnector();
    ObservableList<appointment> data = FXCollections.observableArrayList();
    FilteredList<appointment> filtereddata=new FilteredList<>(data,e->true);

    PreparedStatement prepare = null;
    ResultSet result = null;
    @FXML
    TextField searchbox;
    @FXML
    Button backbutton;
    @FXML
    Button logoutbutton;
    @FXML
    TableView<appointment> tabapp;
    @FXML
    TableColumn<?, ?> c1;
    @FXML
    TableColumn<?, ?> c2;
    @FXML
    TableColumn<?, ?> c3;
 //   @FXML
 //   TableColumn<?, ?> c4;
    @FXML
    TableColumn<?, ?> c5;
    static Integer temppatient_id;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        c1.setCellValueFactory(new PropertyValueFactory<>("apt_id"));
        c2.setCellValueFactory(new PropertyValueFactory<>("apt_date"));
        c3.setCellValueFactory(new PropertyValueFactory<>("apt_time"));
      //  c4.setCellValueFactory(new PropertyValueFactory<>("p_id"));
        c5.setCellValueFactory(new PropertyValueFactory<>("doctor_id"));
        loaddatabase(temppatient_id);
    }    
    
    public void loaddatabase(Integer patient_id)
    {
        temppatient_id=patient_id;
        System.out.println(temppatient_id);
        String query = "SELECT * FROM APPOINTMENT WHERE P_ID='"+temppatient_id+"'";
        try {
            
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
				else if(app.getApt_date().toLowerCase().contains(lowerCaseFilter)){
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
        Databasepro.showPatient();
    }
    
    @FXML
    void logout()
    {
        exit(1);
    }
    
}
