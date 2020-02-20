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
public class AdminpaymentController implements Initializable {

    /**
     * Initializes the controller class.
     */
    Connection conn = DBConnection.LoginConnector();
    ObservableList<payment> data = FXCollections.observableArrayList();
    FilteredList<payment> filtereddata=new FilteredList<>(data,e->true);
    
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
    @FXML
    private TableColumn<?, ?> c8;
    @FXML
    private TableColumn<?, ?> c9;
    @FXML
    private TableColumn<?, ?> c10;
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
        c8.setCellValueFactory(new PropertyValueFactory<>("p_id"));
        c9.setCellValueFactory(new PropertyValueFactory<>("accountant_id"));
        c10.setCellValueFactory(new PropertyValueFactory<>("total"));
        loaddatabase();
    }    
    
    public void loaddatabase()
    {
        String query = "SELECT * FROM PAYMENT";
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
            t10.clear();
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
    public void addnew() throws SQLException {
        String pay_idstr = t1.getText();
        Integer pay_id = Integer.parseInt(pay_idstr);
        Integer cost_drug = Integer.parseInt(t2.getText());
        Integer cost_test = Integer.parseInt(t3.getText());
        Integer cost_seat = Integer.parseInt(t4.getText());
        String drug_status = t5.getText();
        String seat_status = t6.getText();
        String test_status = t7.getText();
        Integer p_id = Integer.parseInt(t8.getText());
        Integer accountant_id= Integer.parseInt(t9.getText());
        Integer total= Integer.parseInt(t10.getText());
        
        String query = "INSERT INTO PAYMENT VALUES(?,?,?,?,?,?,?,?,?,?)";
        prepare = null;
        try {
            prepare = conn.prepareStatement(query);
            prepare.setInt(1, pay_id);
            prepare.setInt(2, cost_drug);
            prepare.setInt(3, cost_test);
            prepare.setInt(4, cost_seat);
            prepare.setString(5, drug_status);
            prepare.setString(6, seat_status);
            prepare.setString(7, test_status);
            prepare.setInt(8, p_id);
            prepare.setInt(9, accountant_id);
            prepare.setInt(10, total);

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
        t10.clear();
        data.clear();
        loaddatabase();
        Databasepro.showInformationAlertBox("New payment info of " + p_id +" is added successfully");
    }
    
    static Integer temppay_id;
    @FXML
    public void showonclick() {
        try {
            payment pay = (payment) tabpay.getSelectionModel().getSelectedItem();
            String query = "SELECT * FROM PAYMENT";
            prepare = conn.prepareStatement(query);
            Integer pay_id = pay.getPay_id();
            temppay_id = pay.getPay_id();
            t1.setText(String.valueOf(pay_id));
            t2.setText(String.valueOf(pay.getCost_drug()));
            t3.setText(String.valueOf(pay.getCost_test()));
            t4.setText(String.valueOf(pay.getCost_seat()));
            t5.setText(pay.getDrug_status());
            t6.setText(pay.getSeat_status());
            t7.setText(pay.getTest_status());
            t8.setText(String.valueOf(pay.getP_id()));
            t9.setText(String.valueOf(pay.getAccountant_id()));
            t10.setText(String.valueOf(pay.getTotal()));
            prepare.close();
            result.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
     @FXML
    public void delete() {
        Integer pay_id = null;
        try {
            payment pay = (payment) tabpay.getSelectionModel().getSelectedItem();
            String query = "DELETE FROM PAYMENT WHERE PAY_ID=?";
            prepare = conn.prepareStatement(query);
            prepare.setInt(1, pay.getPay_id());
            pay_id = pay.getPay_id();
            prepare.executeUpdate();

            prepare.close();
            result.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        loaddatabase();
        Databasepro.showInformationAlertBox("Payment info of " + pay_id + " is deleted successfully");
    }
    
     @FXML
    public void update() {
        String query = "UPDATE PAYMENT SET PAY_ID=?, COST_DRUG=?, COST_TEST=?, COST_SEAT=?, DRUG_STATUS=?, SEAT_STATUS=?, TEST_STATUS=?, P_ID=?, ACCOUNTANT_ID=?, TOTAL=? WHERE PAY_ID ='" + temppay_id+"'";
        try {
            prepare = conn.prepareStatement(query);
            prepare.setInt(1, Integer.valueOf(t1.getText()));
            prepare.setInt(2, Integer.valueOf(t2.getText()));
            prepare.setInt(3, Integer.valueOf(t3.getText()));
            prepare.setInt(4, Integer.valueOf(t4.getText()));
            prepare.setString(5, t5.getText());
            prepare.setString(6, t6.getText());
            prepare.setString(7, t7.getText());
            prepare.setInt(8, Integer.valueOf(t8.getText()));
            prepare.setInt(9, Integer.valueOf(t9.getText()));
            prepare.setInt(10, Integer.valueOf(t10.getText()));
            
            prepare.execute();
            prepare.close();
            Databasepro.showInformationAlertBox("payment info of patient'"+ t8.getText()+"' is updated successfully");
            loaddatabase();
        } catch (Exception e) {
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
				else if(String.valueOf(pay.getP_id()).toLowerCase().contains(lowerCaseFilter)){
					return true;
				}
                                else if(String.valueOf(pay.getAccountant_id()).toLowerCase().contains(lowerCaseFilter)){
					return true;
				}
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
        Databasepro.showAdmin();
    }
    @FXML
    void logout()
    {
        exit(1);
    }
}
