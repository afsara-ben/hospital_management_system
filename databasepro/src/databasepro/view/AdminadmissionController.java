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
public class AdminadmissionController implements Initializable {

    /**
     * Initializes the controller class.
     */
    Connection conn = DBConnection.LoginConnector();
    ObservableList<admission> data = FXCollections.observableArrayList();
    FilteredList<admission> filtereddata=new FilteredList<>(data,e->true);

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
    TextField t4;
    @FXML
    TextField t5;
    @FXML
    TextField t6;
    @FXML
    TextField t7;
    @FXML
    TableView<admission> tabad;
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
    TableColumn<?, ?> c6;
    @FXML
    TableColumn<?, ?> c7;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        c1.setCellValueFactory(new PropertyValueFactory<>("ad_id"));
        c2.setCellValueFactory(new PropertyValueFactory<>("ad_date"));
        c3.setCellValueFactory(new PropertyValueFactory<>("ad_time"));
        c4.setCellValueFactory(new PropertyValueFactory<>("date_discharge"));
        c5.setCellValueFactory(new PropertyValueFactory<>("seat_id"));
        c6.setCellValueFactory(new PropertyValueFactory<>("p_id"));
        c7.setCellValueFactory(new PropertyValueFactory<>("doctor_id"));
        loaddatabase();
    }    
    @FXML
    public void loaddatabase() {
        String query = "SELECT * FROM ADMISSION";
        try {
            t1.clear();
            t2.clear();
            t3.clear();
            t4.clear();
            t5.clear();
            t6.clear();
            t7.clear();
            data.clear();
            prepare = conn.prepareStatement(query);
            result = prepare.executeQuery();
            while (result.next()) {
                data.add(
                        new admission(result.getInt("ad_id"),
                                result.getString("ad_date"),
                                result.getString("ad_time"),
                                result.getString("date_discharge"),
                                result.getInt("seat_id"),
                                result.getInt("p_id"),
                                result.getInt("doctor_id"))
                );
                tabad.setItems(data);
            }
            prepare.close();
            result.close();
        } catch (Exception e1) {
            System.err.println(e1);
        }
    }
    
    @FXML
    public void addnew() throws SQLException {
        String ad_idstr = t1.getText();
        Integer ad_id = Integer.parseInt(ad_idstr);
        String ad_date = t2.getText();
        String ad_time = t3.getText();
        String date_discharge = t4.getText();
        Integer seat_id =Integer.parseInt(t5.getText());
        Integer p_id = Integer.parseInt(t6.getText());
        Integer doctor_id = Integer.parseInt(t7.getText());
        String query = "INSERT INTO ADMISSION VALUES(?,?,?,?,?,?,?)";
        prepare = null;
        try {
            prepare = conn.prepareStatement(query);
            prepare.setInt(1, ad_id);
            prepare.setString(2, ad_date);
            prepare.setString(3, ad_time);
            prepare.setString(4, date_discharge);
            prepare.setInt(5, seat_id);
            prepare.setInt(6, p_id);
            prepare.setInt(7, doctor_id);

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
        data.clear();
        loaddatabase();
        Databasepro.showInformationAlertBox("New admission ID of " + p_id + " is added successfully");
    }
    static Integer tempad_id;
    @FXML
    public void showonclick() {
        try {
            admission Admission = (admission) tabad.getSelectionModel().getSelectedItem();
            String query = "SELECT * FROM ADMISSION";
            prepare = conn.prepareStatement(query);
            Integer ad_id = Admission.getAd_id();
            tempad_id = Admission.getAd_id();
            t1.setText(String.valueOf(ad_id));
            t2.setText(Admission.getAd_date());
            t3.setText(Admission.getAd_time());
            t4.setText(Admission.getDate_discharge());
            t5.setText(String.valueOf(Admission.getSeat_id()));
            t6.setText(String.valueOf(Admission.getP_id()));
            t7.setText(String.valueOf(Admission.getDoctor_id()));

            prepare.close();
            result.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    @FXML
    public void delete() {
        Integer ad_id = null;
        try {
            admission Admission = (admission) tabad.getSelectionModel().getSelectedItem();
            String query = "DELETE FROM ADMISSION WHERE AD_ID=?";
            prepare = conn.prepareStatement(query);
            prepare.setInt(1, Admission.getAd_id());
            ad_id = Admission.getAd_id();
            prepare.executeUpdate();

            prepare.close();
            result.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        loaddatabase();
        Databasepro.showInformationAlertBox("Admission info of " + ad_id + " is deleted successfully");
    }
    
      @FXML
    public void update() {
        String query = "UPDATE ADMISSION SET AD_ID=?, AD_DATE=?, AD_TIME=?, DATE_DISCHARGE=?, SEAT_ID=?, P_ID=?, DOCTOR_ID=? WHERE AD_ID ='" + tempad_id+"'";
        try {
            prepare = conn.prepareStatement(query);
            prepare.setInt(1, Integer.valueOf(t1.getText()));
            prepare.setString(2, t2.getText());
            prepare.setString(3, t3.getText());
            prepare.setString(4, t4.getText());
            prepare.setInt(5, Integer.valueOf(t5.getText()));
            prepare.setInt(6, Integer.valueOf(t6.getText()));
            prepare.setInt(7, Integer.valueOf(t7.getText()));
            prepare.execute();
            prepare.close();
            Databasepro.showInformationAlertBox("Admission info of '"+ t1.getText()+"' is updated successfully");
            loaddatabase();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
     @FXML
    public void search()
    {
        searchbox.textProperty().addListener((observableValue,oldValue,newValue)->{
			filtereddata.setPredicate((Predicate<? super admission>)Admission->{
				if(newValue==null||newValue.isEmpty()){
					return true;
				}
				String lowerCaseFilter=newValue.toLowerCase();
				if(String.valueOf(Admission.getAd_id()).toLowerCase().contains(lowerCaseFilter)){
					return true;
				}
				else if(String.valueOf(Admission.getP_id()).toLowerCase().contains(lowerCaseFilter)){
					return true;
				}
                                else if(String.valueOf(Admission.getDoctor_id()).toLowerCase().contains(lowerCaseFilter)){
					return true;
				}
				return false;
			});
		});
		SortedList<admission> sortedData=new SortedList<>(filtereddata);
		sortedData.comparatorProperty().bind(tabad.comparatorProperty());
		tabad.setItems(sortedData);
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
