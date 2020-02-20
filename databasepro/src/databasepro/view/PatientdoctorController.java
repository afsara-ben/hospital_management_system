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
public class PatientdoctorController implements Initializable {

    /**
     * Initializes the controller class.
     */
    Connection conn = DBConnection.LoginConnector();
    ObservableList<department> data = FXCollections.observableArrayList();
    FilteredList<department> filtereddata=new FilteredList<>(data,e->true);

    PreparedStatement prepare = null;
    ResultSet result = null;
    
    ObservableList<accountant> data1 = FXCollections.observableArrayList();
    FilteredList<accountant> filtereddata1=new FilteredList<>(data1,e->true);

    PreparedStatement prepare1 = null;
    ResultSet result1 = null;
    
    PreparedStatement prepare2 = null;
    ResultSet result2 = null;
    
    PreparedStatement prepare3 = null;
    ResultSet result3 = null;
    
    @FXML
    Button backbutton;
    @FXML
    Button logoutbutton;
    @FXML
    Button docinfobutton;
    @FXML
    TextField searchbox;
    @FXML
    TextField searchbox1;
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
    TableView<department> tabpdept;
    @FXML
    private TableColumn<?, ?> c1;
    @FXML
    private TableColumn<?, ?> c2;
    @FXML
    TableView<accountant> tabpdoc;
    @FXML
    private TableColumn<?, ?> C1;
    @FXML
    private TableColumn<?, ?> C2;
    @FXML
    private TableColumn<?, ?> C3;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        c1.setCellValueFactory(new PropertyValueFactory<>("dept_id"));
        c2.setCellValueFactory(new PropertyValueFactory<>("dept_title"));
        loaddatabase();
    }    
    
    public void loaddatabase() {
        String query = "SELECT * FROM DEPARTMENT";
        try {
            data.clear();
            prepare = conn.prepareStatement(query);
            result = prepare.executeQuery();
            while (result.next()) {
                data.add(
                        new department(result.getInt("dept_id"),
                                result.getString("dept_title"),
                                result.getInt("total_patient"))
                );
                tabpdept.setItems(data);
            }
            prepare.close();
            result.close();
        } catch (Exception e1) {
            System.err.println(e1);
        }
    }
    
    static Integer tempdept_id;

    @FXML
    public void selectonclick() {
        try {
            department dept = (department) tabpdept.getSelectionModel().getSelectedItem();
            String query = "SELECT * FROM DEPARTMENT";
            prepare = conn.prepareStatement(query);
            Integer dept_id = dept.getDept_id();
            String dept_title=dept.getDept_title();
            Integer total_pat=dept.getTotal_patient();
            tempdept_id = dept.getDept_id();
            prepare.close();
            result.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    @FXML
    public void showdoc()
    {
        C1.setCellValueFactory(new PropertyValueFactory<>("em_id"));
        C2.setCellValueFactory(new PropertyValueFactory<>("em_firstname"));
        C3.setCellValueFactory(new PropertyValueFactory<>("em_lastname"));
        loaddatabasedoc(tempdept_id);
    }
    
    public void loaddatabasedoc(Integer temp_id)
    {
         String query = "SELECT * FROM EMPLOYEE WHERE DEPT_ID='"+tempdept_id+"'";
         System.out.println(tempdept_id);
        try {
            data1.clear();
            prepare1 = conn.prepareStatement(query);
            result1 = prepare1.executeQuery();
            while (result1.next()) {
                data1.add(
                        new accountant(result1.getInt("em_id"),
                                result1.getString("em_firstname"),
                                result1.getString("em_lastname"),
                                result1.getString("em_desig"),
                                result1.getString("em_dob"),
                                result1.getString("joining_date"),
                                result1.getInt("salary"),
                                result1.getString("em_add"),
                                result1.getInt("em_contact"),
                                //  result.getString("em_email"),
                                result1.getInt("job_id"),
                                result1.getInt("dept_id"),
                                result1.getString("qualification"))
                );
                tabpdoc.setItems(data1);
            }
            prepare1.close();
            result1.close();
        } catch (Exception e1) {
            System.err.println(e1);
        }
    }
    
    static Integer tempem_id;
    @FXML
    public void showonclick() {
        try {
            accountant doc = (accountant) tabpdoc.getSelectionModel().getSelectedItem();
            Integer em_id = doc.getEm_id();
            tempem_id = doc.getEm_id();
            String query = "SELECT * FROM EMPLOYEE WHERE EM_ID='"+tempem_id+"'";
            String query1 = "SELECT * FROM DOCTOR WHERE EMPLOYEE_ID='"+tempem_id+"'";
            prepare2 = conn.prepareStatement(query);
            prepare3=conn.prepareStatement(query1);
            result2 = prepare2.executeQuery();
            result3=prepare3.executeQuery();
             // while(result1.next())
            result2.next();
            String em_firstname=result2.getString("em_firstname");
            String em_lastname=result2.getString("em_lastname");
            String em_desig=result2.getString("em_desig");
            Integer em_contact=result2.getInt("em_contact");
            String qualification=result2.getString("qualification");
            result3.next();
            String opd_day=result3.getString("opd_day");
            String opd_time=result3.getString("opd_time");
            
            t1.setText(em_firstname);
            t2.setText(em_lastname);
            t3.setText(em_desig);
            t4.setText(String.valueOf(em_contact));
            t5.setText(qualification);
            t6.setText(opd_day);
            t7.setText(opd_time);
            
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
		sortedData.comparatorProperty().bind(tabpdept.comparatorProperty());
		tabpdept.setItems(sortedData);
    }
    
      @FXML
    public void searchdoc()
    {
        searchbox1.textProperty().addListener((observableValue,oldValue,newValue)->{
			filtereddata1.setPredicate((Predicate<? super accountant>)doc->{
				if(newValue==null||newValue.isEmpty()){
					return true;
				}
				String lowerCaseFilter=newValue.toLowerCase();
				if(String.valueOf(doc.getEm_id()).toLowerCase().contains(lowerCaseFilter)){
					return true;
				}
				else if(doc.getEm_firstname().toLowerCase().contains(lowerCaseFilter)){
					return true;
				}
                                else if(doc.getEm_lastname().toLowerCase().contains(lowerCaseFilter)){
					return true;
				}
				return false;
			});
		});
		SortedList<accountant> sortedData=new SortedList<>(filtereddata1);
		sortedData.comparatorProperty().bind(tabpdoc.comparatorProperty());
		tabpdoc.setItems(sortedData);
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
