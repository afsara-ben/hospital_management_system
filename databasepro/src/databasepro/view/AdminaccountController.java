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
public class AdminaccountController implements Initializable {

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
    TableView<accountant> tab1;
    @FXML
    private TableColumn<?, ?> c1;
    @FXML
    private TableColumn<?, ?> c2;
    @FXML
    private TableColumn<?, ?> c3;
 /*   @FXML
    private TableColumn<?, ?> c4;
    @FXML
    private TableColumn<?, ?> c5;
    @FXML
    private TableColumn<?, ?> c6;
    @FXML
    private TableColumn<?, ?> c7;
    @FXML
    private TableColumn<?, ?> c8;
    @FXML
    private TableColumn<?, ?> c9;
    @FXML
    private TableColumn<?, ?> c11;
    @FXML
    private TableColumn<?, ?> c12;
    @FXML
    private TableColumn<?, ?> c13;*/
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
    TextField t11;
    @FXML
    TextField t12;
    @FXML
    TextField t13;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        c1.setCellValueFactory(new PropertyValueFactory<>("em_id"));
        c2.setCellValueFactory(new PropertyValueFactory<>("em_firstname"));
        c3.setCellValueFactory(new PropertyValueFactory<>("em_lastname"));
    /*    c4.setCellValueFactory(new PropertyValueFactory<>("em_desig"));
        c5.setCellValueFactory(new PropertyValueFactory<>("em_dob"));
        c6.setCellValueFactory(new PropertyValueFactory<>("joining_date"));
        c7.setCellValueFactory(new PropertyValueFactory<>("salary"));
        c8.setCellValueFactory(new PropertyValueFactory<>("em_add"));
        c9.setCellValueFactory(new PropertyValueFactory<>("em_contact"));
        // c10.setCellValueFactory(new PropertyValueFactory<>("em_mail"));
        c11.setCellValueFactory(new PropertyValueFactory<>("job_id"));
        c12.setCellValueFactory(new PropertyValueFactory<>("dept_id"));
        c13.setCellValueFactory(new PropertyValueFactory<>("qualification"));*/
        loaddatabase();
    }

    public void loaddatabase() {
        String query = "SELECT * FROM EMPLOYEE WHERE OCCUPATION ='accountant'";
        try {
            t1.clear();
            t2.clear();
            t3.clear();
            t4.clear();
            t5.clear();
            t6.clear();
            t7.clear();
            t8.clear();
            t9.clear();
            //  t10.clear();
            t11.clear();
            t12.clear();
            t13.clear();
            data.clear();
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
                tab1.setItems(data);
            }
            prepare.close();
            result.close();
        } catch (Exception e1) {
            System.err.println(e1);
        }
    }

    @FXML
    public void addnew() throws SQLException {
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
        Integer job_id = Integer.parseInt(t11.getText());
        Integer dept_id = Integer.parseInt(t12.getText());
        String qualification = t13.getText();
        String query = "INSERT INTO EMPLOYEE VALUES(?,?,?,?,?,?,?,?,?,?,?,?,'accountant')";
        String query1="INSERT INTO ACCOUNTANT VALUES(?)";
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
        //  t10.clear();
        t11.clear();
        t12.clear();
        t13.clear();
        data.clear();
        loaddatabase();
        Databasepro.showInformationAlertBox("New job ID of " + em_firstname + " " + em_lastname + " is added successfully");
    }
   static Integer tempem_id;
    @FXML
    public void showonclick() {
        try {
            accountant account = (accountant) tab1.getSelectionModel().getSelectedItem();
            String query = "SELECT * FROM EMPLOYEE";
            prepare = conn.prepareStatement(query);
            Integer em_id = account.getEm_id();
            tempem_id = account.getEm_id();
            t1.setText(String.valueOf(em_id));
            t2.setText(account.getEm_firstname());
            t3.setText(account.getEm_lastname());
            t4.setText(account.getEm_desig());
            t5.setText(account.getEm_dob());
            t6.setText(account.getJoining_date());
            t7.setText(String.valueOf(account.getSalary()));
            t8.setText(account.getEm_add());
            t9.setText(String.valueOf(account.getEm_contact()));
            t11.setText(String.valueOf(account.getJob_id()));
            t12.setText(String.valueOf(account.getDept_id()));
            t13.setText(String.valueOf(account.getQualification()));

            prepare.close();
            result.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    public void delete() {
        Integer em_id = null;
        try {
            accountant account = (accountant) tab1.getSelectionModel().getSelectedItem();
            String query = "DELETE FROM EMPLOYEE WHERE EM_ID=?";
            prepare = conn.prepareStatement(query);
            prepare.setInt(1, account.getEm_id());
            em_id = account.getEm_id();
            prepare.executeUpdate();

            prepare.close();
            result.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        loaddatabase();
        Databasepro.showInformationAlertBox("Job ID of " + em_id + " is deleted successfully");
    }

    @FXML
    void back() throws IOException {
        Databasepro.showAdmin();
    }
    
      @FXML
    public void update() {
        String query = "UPDATE EMPLOYEE SET EM_ID=?, EM_FIRSTNAME=?, EM_LASTNAME=?, EM_DESIG=?,EM_DOB=?, JOINING_DATE=?, SALARY=?, EM_ADD=?, EM_CONTACT=?, JOB_ID=?, DEPT_ID=?, QUALIFICATION=?, OCCUPATION='accountant' WHERE EM_ID ='" + tempem_id+"'";
        String query1="UPDATE ACCOUNTANT SET EMPLOYEE_ID=? WHERE EMPLOYEE_ID='"+tempem_id+"'";
        try {
            prepare = conn.prepareStatement(query);
            prepare1 = conn.prepareStatement(query1);
            prepare.setInt(1, Integer.valueOf(t1.getText()));
            prepare1.setInt(1, Integer.valueOf(t1.getText()));
            prepare.setString(2, t2.getText());
            prepare.setString(3, t3.getText());
            prepare.setString(4, t4.getText());
            prepare.setString(5, t5.getText());
            prepare.setString(6, t6.getText());
            prepare.setInt(7, Integer.valueOf(t7.getText()));
            prepare.setString(8, t8.getText());
            prepare.setInt(9,Integer.valueOf( t9.getText()));
            prepare.setInt(10, Integer.valueOf(t11.getText()));
            prepare.setInt(11, Integer.valueOf(t12.getText()));
            prepare.setString(12, t13.getText());
            prepare.execute();
            prepare1.execute();
            prepare.close();
            prepare1.close();
            Databasepro.showInformationAlertBox("Job ID of '"+ t1.getText()+"' is updated successfully");
            loaddatabase();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
     @FXML
    public void search()
    {
        searchbox.textProperty().addListener((observableValue,oldValue,newValue)->{
			filtereddata.setPredicate((Predicate<? super accountant>)account->{
				if(newValue==null||newValue.isEmpty()){
					return true;
				}
				String lowerCaseFilter=newValue.toLowerCase();
				if(String.valueOf(account.getEm_id()).toLowerCase().contains(lowerCaseFilter)){
					return true;
				}
				else if(account.getEm_firstname().toLowerCase().contains(lowerCaseFilter)){
					return true;
				}
                                else if(account.getEm_lastname().toLowerCase().contains(lowerCaseFilter)){
					return true;
				}
				return false;
			});
		});
		SortedList<accountant> sortedData=new SortedList<>(filtereddata);
		sortedData.comparatorProperty().bind(tab1.comparatorProperty());
		tab1.setItems(sortedData);
    }
    
    @FXML
    void logout() {
        exit(1);
    }
}
