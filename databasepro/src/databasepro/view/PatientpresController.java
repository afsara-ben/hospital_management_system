/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databasepro.view;

import databasepro.Databasepro;
import static databasepro.view.PatientadmissionController.temppatient_id;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author user
 */
public class PatientpresController implements Initializable {

    /**
     * Initializes the controller class.
     */
    Connection conn = DBConnection.LoginConnector();
    ObservableList<prescription> data = FXCollections.observableArrayList();
    FilteredList<prescription> filtereddata=new FilteredList<>(data,e->true);

    PreparedStatement prepare = null;
    ResultSet result = null;
    
    ObservableList<presdrug> data1 = FXCollections.observableArrayList();
    FilteredList<presdrug> filtereddata1=new FilteredList<>(data1,e->true);

    PreparedStatement prepare1 = null;
    ResultSet result1 = null;
    
    PreparedStatement prepare2 = null;
    ResultSet result2 = null;
    
    ObservableList<prestest> data2 = FXCollections.observableArrayList();
    FilteredList<prestest> filtereddata2=new FilteredList<>(data2,e->true);

    PreparedStatement prepare3 = null;
    ResultSet result3 = null;
    
    PreparedStatement prepare4 = null;
    ResultSet result4 = null;
    @FXML
    TextField searchbox;
    @FXML
    Button backbutton;
    @FXML
    Button logoutbutton;
    @FXML
    Button drugbutton;
    @FXML
    Button testbutton;
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
    TableView<prescription> tabpres;
    @FXML
    private TableColumn<?, ?> c1;
    @FXML
    private TableColumn<?, ?> c2;
    @FXML
    private TableColumn<?, ?> c3;
 //   @FXML
 //   private TableColumn<?, ?> c4;
    @FXML
    private TableColumn<?, ?> c5;
    @FXML
    TableView<presdrug> tabpresdrug;
  //  @FXML
  //  private TableColumn<?, ?> C1;
    @FXML
    private TableColumn<?, ?> C2;
    @FXML
    private TableColumn<?, ?> C3;
    @FXML
    TableView<prestest> tabprestest;
    @FXML
    TableColumn<?, ?> cl1;
    @FXML
    Label l1;
    @FXML
    Label l2;
    static Integer temppatient_id;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        c1.setCellValueFactory(new PropertyValueFactory<>("pres_id"));
        c2.setCellValueFactory(new PropertyValueFactory<>("pres_date"));
        c3.setCellValueFactory(new PropertyValueFactory<>("pres_comment"));
       // c4.setCellValueFactory(new PropertyValueFactory<>("p_id"));
        c5.setCellValueFactory(new PropertyValueFactory<>("doctor_id"));
        loaddatabase(temppatient_id);
    }    
   // @FXML
    public void loaddatabase(Integer patient_id) {
        temppatient_id=patient_id;
        System.out.println(temppatient_id);
        String query = "SELECT * FROM PRESCRIPTION WHERE P_ID='"+temppatient_id+"'";
        try
        {
            data.clear();
            prepare = conn.prepareStatement(query);
            result = prepare.executeQuery();
            while (result.next()) {
                data.add(
                        new prescription(result.getInt("pres_id"),
                                result.getString("pres_date"),
                                result.getString("pres_comment"),
                                result.getInt("p_id"),
                                result.getInt("doctor_id"))
                );
                tabpres.setItems(data);
            }
            prepare.close();
            result.close(); 
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
     static Integer temp_id;
    
    @FXML
    public void selectonclick()
    {
        try
        {
            prescription pres = (prescription) tabpres.getSelectionModel().getSelectedItem();
            String query = "SELECT * FROM PRESCRIPTION WHERE P_ID='"+temppatient_id+"'";
            prepare = conn.prepareStatement(query);
           // Integer seat_id = Admission.getSeat_id();
            temp_id = pres.getPres_id();
            System.out.println(temp_id);
            prepare.close();
            result.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    @FXML
    public void showdrug()
    {
      //  C1.setCellValueFactory(new PropertyValueFactory<>("drug_id"));
        C2.setCellValueFactory(new PropertyValueFactory<>("drug_id"));
        C3.setCellValueFactory(new PropertyValueFactory<>("time_of_intake"));
        loaddatabasepres(temp_id);
    }
    
    public void loaddatabasepres(Integer temppres_id)
    {
         String query = "SELECT * FROM PRES_DRUG WHERE PRES_ID='"+temp_id+"'";
      //  String query="select drug_name,generic_name,mft_date,exp_date,state,method_of_intake,pharma_name,drug_cost from pres_drug pd, drug d, prescription p where pd.DRUG_ID=d.DRUG_ID and p.PRES_ID='"+temp_id+"' and pd.pres_id='"+temp_id+"'";
      //  String query="select drug.DRUG_NAME,drug.GENERIC_NAME,drug.MFT_DATE,drug.EXP_DATE,drug.STATE,drug.METHOD_OF_INTAKE,drug.PHARMA_NAME,drug.DRUG_COST from drug join pres_drug pd on(drug.DRUG_ID=pd.DRUG_ID) join prescription p on(pd.PRES_ID=p.PRES_ID) where p.PRES_ID='"+temp_id+"'";
        try{
            data1.clear();
            prepare1 = conn.prepareStatement(query);
            result1 = prepare1.executeQuery();
             while (result1.next()) {
                data1.add(
                        new presdrug(result1.getInt("pres_id"),
                        result1.getInt("drug_id"),
                        result1.getString("time_of_intake"))
                );
                tabpresdrug.setItems(data1);
            }
            prepare1.close();
            result1.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    static Integer tempdrug_id;
    @FXML
    public void showonclick() {
        try {
            presdrug Drug = (presdrug) tabpresdrug.getSelectionModel().getSelectedItem();
            Integer drug_id = Drug.getDrug_id();
            tempdrug_id = Drug.getDrug_id();
            String query = "SELECT * FROM DRUG WHERE DRUG_ID='"+tempdrug_id+"'";
            prepare2 = conn.prepareStatement(query);
            result2 = prepare2.executeQuery();
             // while(result1.next())
            result2.next();
            String drug_name=result2.getString("drug_name");
            String generic_name=result2.getString("generic_name");
            String state=result2.getString("state");
            String method_of_intake=result2.getString("method_of_intake");
            String pharma_name=result2.getString("pharma_name");
            Integer drug_cost=result2.getInt("drug_cost");
                        
            t2.setText(drug_name);
            t3.setText(generic_name);
            t4.setText(state);
            t5.setText(method_of_intake);
            t6.setText(pharma_name);
            t7.setText(String.valueOf(drug_cost));
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    @FXML
    public void showtest()
    {
        cl1.setCellValueFactory(new PropertyValueFactory<>("test_id"));
        loaddatabaseprestest(temp_id);
    }
    
    public void loaddatabaseprestest(Integer temppres_id)
    {
         String query = "SELECT * FROM TEST_PRES WHERE PRES_ID="+temp_id;
        try{
            data2.clear();
            prepare3 = conn.prepareStatement(query);
            result3 = prepare3.executeQuery();
             while (result3.next()) {
                data2.add(
                        new prestest(result3.getInt("pres_id"),
                        result3.getInt("test_id"))
                );
                tabprestest.setItems(data2);
            }
            prepare3.close();
            result3.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    static Integer temptest_id;
    @FXML
    public void showonclicktest() {
        try {
            prestest test = (prestest) tabprestest.getSelectionModel().getSelectedItem();
            Integer test_id = test.getTest_id();
            temptest_id = test.getTest_id();
            String query = "SELECT * FROM TEST WHERE TEST_ID='"+temptest_id+"'";
            prepare4 = conn.prepareStatement(query);
            result4 = prepare4.executeQuery();
             // while(result1.next())
            result4.next();
            String test_name=result4.getString("test_name");
            Integer test_cost=result4.getInt("test_cost");
                        
            t8.setText(test_name);
            t9.setText(String.valueOf(test_cost));
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    @FXML
    public void search()
    {
        searchbox.textProperty().addListener((observableValue,oldValue,newValue)->{
			filtereddata.setPredicate((Predicate<? super prescription>)pres->{
				if(newValue==null||newValue.isEmpty()){
					return true;
				}
				String lowerCaseFilter=newValue.toLowerCase();
				if(String.valueOf(pres.getPres_id()).toLowerCase().contains(lowerCaseFilter)){
					return true;
				}
				else if(pres.getPres_date().toLowerCase().contains(lowerCaseFilter)){
					return true;
				}
                                else if(String.valueOf(pres.getDoctor_id()).toLowerCase().contains(lowerCaseFilter)){
					return true;
				}
				return false;
			});
		});
		SortedList<prescription> sortedData=new SortedList<>(filtereddata);
		sortedData.comparatorProperty().bind(tabpres.comparatorProperty());
		tabpres.setItems(sortedData);
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
