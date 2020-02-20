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
public class PatientpaymentController implements Initializable {

    /**
     * Initializes the controller class.
     */
    Connection conn = DBConnection.LoginConnector();
    ObservableList<payment> data = FXCollections.observableArrayList();
    FilteredList<payment> filtereddata=new FilteredList<>(data,e->true);
    
    PreparedStatement prepare = null;
    ResultSet result = null;
    @FXML
    Button backbutton;
    @FXML
    Button logoutbutton;
    @FXML
    TableView<payment> tabpay;
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
    @FXML
    private TableColumn<?, ?> c6;
    @FXML
    private TableColumn<?, ?> c7;
 //   @FXML
 //   private TableColumn<?, ?> c8;
    @FXML
    private TableColumn<?, ?> c9;
    @FXML
    private TableColumn<?, ?> c10;
    @FXML
    TextField searchbox;
    static Integer temppatient_id;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        c1.setCellValueFactory(new PropertyValueFactory<>("pay_id"));
        c2.setCellValueFactory(new PropertyValueFactory<>("cost_drug"));
        c3.setCellValueFactory(new PropertyValueFactory<>("cost_test"));
        c4.setCellValueFactory(new PropertyValueFactory<>("cost_seat"));
        c5.setCellValueFactory(new PropertyValueFactory<>("drug_status"));
        c6.setCellValueFactory(new PropertyValueFactory<>("seat_status"));
        c7.setCellValueFactory(new PropertyValueFactory<>("test_status"));
      //  c8.setCellValueFactory(new PropertyValueFactory<>("p_id"));
        c9.setCellValueFactory(new PropertyValueFactory<>("accountant_id"));
        c10.setCellValueFactory(new PropertyValueFactory<>("total"));
        loaddatabase(temppatient_id);
    }    
    
    public void loaddatabase(Integer patient_id)
    {
        temppatient_id=patient_id;
        System.out.println(temppatient_id);
        String query = "SELECT * FROM PAYMENT WHERE P_ID='"+temppatient_id+"'";
        try{
            data.clear();
            prepare = conn.prepareStatement(query);
            result = prepare.executeQuery();
             while (result.next()) {
                data.add(
                        new payment(result.getInt("pay_id"),
                        result.getInt("cost_drug"),
                        result.getInt("cost_test"),
                        result.getInt("cost_seat"),
                        result.getString("drug_status"),
                        result.getString("seat_status"),
                        result.getString("test_status"),
                        result.getInt("p_id"),
                        result.getInt("accountant_id"),
                        result.getInt("total"))
                );
                tabpay.setItems(data);
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
    public void search()
    {
        searchbox.textProperty().addListener((observableValue,oldValue,newValue)->{
			filtereddata.setPredicate((Predicate<? super payment>)pay->{
				if(newValue==null||newValue.isEmpty()){
					return true;
				}
				String lowerCaseFilter=newValue.toLowerCase();
				if(String.valueOf(pay.getPay_id()).toLowerCase().contains(lowerCaseFilter)){
					return true;
				}
                              /*  else if(String.valueOf(pay.getAccountant_id()).toLowerCase().contains(lowerCaseFilter)){
					return true;
				}*/
				return false;
			});
		});
		SortedList<payment> sortedData=new SortedList<>(filtereddata);
		sortedData.comparatorProperty().bind(tabpay.comparatorProperty());
		tabpay.setItems(sortedData);
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
