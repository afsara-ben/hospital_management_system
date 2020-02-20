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
public class AdmindrugController implements Initializable {

    /**
     * Initializes the controller class.
     */
    Connection conn = DBConnection.LoginConnector();
    ObservableList<drug> data = FXCollections.observableArrayList();
    FilteredList<drug> filtereddata=new FilteredList<>(data,e->true);

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
    TableView<drug> tabdrug;
    @FXML
    private TableColumn<?, ?> c1;
  //  @FXML
  //  private TableColumn<?, ?> c2;
    @FXML
    private TableColumn<?, ?> c3;
 /*   @FXML
    private TableColumn<?, ?> c4;
    @FXML
    private TableColumn<?, ?> c5;
    @FXML
    private TableColumn<?, ?> c6;
    @FXML
    private TableColumn<?, ?> c7;*/
    @FXML
    private TableColumn<?, ?> c8;
 //   @FXML
 //   private TableColumn<?, ?> c9;
 //   @FXML
 //   private TableColumn<?, ?> c10;
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
        c1.setCellValueFactory(new PropertyValueFactory<>("drug_id"));
      //  c2.setCellValueFactory(new PropertyValueFactory<>("drug_name"));
        c3.setCellValueFactory(new PropertyValueFactory<>("generic_name"));
      /*  c4.setCellValueFactory(new PropertyValueFactory<>("mft_date"));
        c5.setCellValueFactory(new PropertyValueFactory<>("exp_date"));
        c6.setCellValueFactory(new PropertyValueFactory<>("state"));
        c7.setCellValueFactory(new PropertyValueFactory<>("method_of_intake"));*/
        c8.setCellValueFactory(new PropertyValueFactory<>("pharma_name"));
      //  c9.setCellValueFactory(new PropertyValueFactory<>("max_bought"));
       // c10.setCellValueFactory(new PropertyValueFactory<>("drug_cost"));
        loaddatabase();
    }    
    @FXML
    public void loaddatabase()
    {
         String query = "SELECT * FROM DRUG";
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
                        new drug(result.getInt("drug_id"),
                        result.getString("drug_name"),
                        result.getString("generic_name"),
                        result.getString("mft_date"),
                        result.getString("exp_date"),
                        result.getString("state"),
                        result.getString("method_of_intake"),
                        result.getInt("max_bought"),
                        result.getString("pharma_name"),
                        result.getInt("drug_cost"))
                );
                tabdrug.setItems(data);
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
        String drug_idstr = t1.getText();
        Integer drug_id = Integer.parseInt(drug_idstr);
        String drug_name = t2.getText();
        String generic_name = t3.getText();
        String mft_date = t4.getText();
        String exp_date = t5.getText();
        String state = t6.getText();
        String method_of_intake = t7.getText();
        Integer max_bought = Integer.parseInt(t9.getText());
        String pharma_name=t8.getText();
        Integer drug_cost = Integer.parseInt(t10.getText());
        String query = "INSERT INTO DRUG VALUES(?,?,?,?,?,?,?,?,?,?)";
        prepare = null;
        try {
            prepare = conn.prepareStatement(query);
            prepare.setInt(1, drug_id);
            prepare.setString(2, drug_name);
            prepare.setString(3, generic_name);
            prepare.setString(4, mft_date);
            prepare.setString(5, exp_date);
            prepare.setString(6, state);
            prepare.setString(7, method_of_intake);
            prepare.setInt(8, max_bought);
            prepare.setString(9, pharma_name);
            prepare.setInt(10, drug_cost);

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
        Databasepro.showInformationAlertBox("New drug ID of " + generic_name + " is added successfully");
    }
    
    static Integer tempdrug_id;
    @FXML
    public void showonclick() {
        try {
            drug Drug = (drug) tabdrug.getSelectionModel().getSelectedItem();
            String query = "SELECT * FROM DRUG";
            prepare = conn.prepareStatement(query);
            Integer drug_id = Drug.getDrug_id();
            tempdrug_id = Drug.getDrug_id();
            t1.setText(String.valueOf(drug_id));
            t2.setText(Drug.getDrug_name());
            t3.setText(Drug.getGeneric_name());
            t4.setText(Drug.getMft_date());
            t5.setText(Drug.getExp_date());
            t6.setText(Drug.getState());
            t7.setText(Drug.getMethod_of_intake());
            t8.setText(Drug.getPharma_name());
            t9.setText(String.valueOf(Drug.getMax_brought()));
            t10.setText(String.valueOf(Drug.getDrug_cost()));
            
            prepare.close();
            result.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
     @FXML
    public void delete() {
        Integer drug_id = null;
        try {
            drug Drug = (drug) tabdrug.getSelectionModel().getSelectedItem();
            String query = "DELETE FROM DRUG WHERE DRUG_ID=?";
            prepare = conn.prepareStatement(query);
            prepare.setInt(1, Drug.getDrug_id());
            drug_id = Drug.getDrug_id();
            prepare.executeUpdate();

            prepare.close();
            result.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        loaddatabase();
        Databasepro.showInformationAlertBox("Drug info of " + drug_id + " is deleted successfully");
    }
    
     @FXML
    public void update() {
        String query = "UPDATE DRUG SET DRUG_ID=?, DRUG_NAME=?, GENERIC_NAME=?, MFT_DATE=?, EXP_DATE=?, STATE=?, METHOD_OF_INTAKE=?, MAX_BOUGHT=?, PHARMA_NAME=?, DRUG_COST=? WHERE DRUG_ID ='" + tempdrug_id+"'";
        try {
            prepare = conn.prepareStatement(query);
            prepare.setInt(1, Integer.valueOf(t1.getText()));
            prepare.setString(2, t2.getText());
            prepare.setString(3, t3.getText());
            prepare.setString(4, t4.getText());
            prepare.setString(5, t5.getText());
            prepare.setString(6, t6.getText());
            prepare.setString(7, t7.getText());
            prepare.setInt(8, Integer.valueOf(t9.getText()));
            prepare.setString(9, t8.getText());
            prepare.setInt(10, Integer.valueOf(t10.getText()));
            prepare.execute();
            prepare.close();
            Databasepro.showInformationAlertBox("Drug info of '"+ t2.getText()+"' is updated successfully");
            loaddatabase();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
      @FXML
    public void search()
    {
        searchbox.textProperty().addListener((observableValue,oldValue,newValue)->{
			filtereddata.setPredicate((Predicate<? super drug>)Drug->{
				if(newValue==null||newValue.isEmpty()){
					return true;
				}
				String lowerCaseFilter=newValue.toLowerCase();
				if(String.valueOf(Drug.getDrug_id()).toLowerCase().contains(lowerCaseFilter)){
					return true;
				}
				else if(Drug.getDrug_name().toLowerCase().contains(lowerCaseFilter)){
					return true;
				}
                                else if(Drug.getGeneric_name().toLowerCase().contains(lowerCaseFilter)){
					return true;
				}
                                else if(Drug.getPharma_name().toLowerCase().contains(lowerCaseFilter)){
					return true;
				}
				return false;
			});
		});
		SortedList<drug> sortedData=new SortedList<>(filtereddata);
		sortedData.comparatorProperty().bind(tabdrug.comparatorProperty());
		tabdrug.setItems(sortedData);
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
