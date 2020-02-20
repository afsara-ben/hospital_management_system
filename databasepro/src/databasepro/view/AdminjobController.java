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
public class AdminjobController implements Initializable {

    /**
     * Initializes the controller class.
     */
    Connection conn = DBConnection.LoginConnector();
    ObservableList<job> data = FXCollections.observableArrayList();
    FilteredList<job> filtereddata=new FilteredList<>(data,e->true);

    PreparedStatement prepare = null;
    ResultSet result = null;
    @FXML
    Button backbutton;
    @FXML
    Button updatebutton;
    @FXML
    Button deletebutton;
    @FXML
    Button addbutton;
    @FXML
    Button logoutbutton;
    @FXML
    TextField searchbox;
    @FXML
    TextField t1;
    @FXML
    TextField t2;
    @FXML
    TableView<job> tab2;
    @FXML
    TableColumn<?, ?> c1;
    @FXML
    TableColumn<?, ?> c2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        c1.setCellValueFactory(new PropertyValueFactory<>("job_id"));
        c2.setCellValueFactory(new PropertyValueFactory<>("job_title"));
        loaddatabase();
    }

    public void loaddatabase() {
        String query = "SELECT * FROM JOB";
        try {
            t1.clear();
            t2.clear();
            data.clear();
            prepare = conn.prepareStatement(query);
            result = prepare.executeQuery();
            while (result.next()) {
                data.add(
                        new job(result.getInt("job_id"),
                                result.getString("job_title"))
                );
                tab2.setItems(data);
            }
            prepare.close();
            result.close();
        } catch (Exception e1) {
            System.err.println(e1);
        }
    }

    @FXML
    public void addnewuser() throws SQLException {
        String job_idstr = t1.getText();
        Integer job_id = Integer.parseInt(job_idstr);
        String job_title = t2.getText();
        String query = "INSERT INTO JOB VALUES(?,?)";
        prepare = null;
        try {
            prepare = conn.prepareStatement(query);
            prepare.setInt(1, job_id);
            prepare.setString(2, job_title);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            prepare.execute();
            prepare.close();
        }
        t1.clear();
        t2.clear();
        data.clear();
        loaddatabase();
        Databasepro.showInformationAlertBox("New job ID of " + job_title + " is added successfully");
    }

    @FXML
    void back() throws IOException {
        Databasepro.showAdmin();
    }

    static String tempjobtitle;

    @FXML
    public void showonclick() {
        try {
            job Job = (job) tab2.getSelectionModel().getSelectedItem();
            String query = "SELECT * FROM JOB";
            prepare = conn.prepareStatement(query);
            Integer job_id = Job.getJob_id();
            tempjobtitle = Job.getJob_title();
            t1.setText(String.valueOf(job_id));
            t2.setText(Job.getJob_title());
            prepare.close();
            result.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    public void delete() {
        String job_title = null;
        try {
            job Job = (job) tab2.getSelectionModel().getSelectedItem();
            String query = "DELETE FROM JOB WHERE JOB_TITLE=?";
            prepare = conn.prepareStatement(query);
            prepare.setString(1, Job.getJob_title());
            job_title = Job.getJob_title();
            prepare.executeUpdate();

            prepare.close();
            result.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        loaddatabase();
        Databasepro.showInformationAlertBox("Job ID of " + job_title + " is deleted successfully");
    }

    @FXML
    public void update() {
        String query = "UPDATE JOB SET JOB_ID=?, JOB_TITLE=? WHERE JOB_TITLE ='" + tempjobtitle+"'";
        try {
            prepare = conn.prepareStatement(query);
            prepare.setInt(1, Integer.valueOf(t1.getText()));
            prepare.setString(2, t2.getText());
            prepare.execute();
            prepare.close();
            Databasepro.showInformationAlertBox("Job ID of '"+ t2.getText()+"' is updated successfully");
            loaddatabase();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    @FXML
    public void search()
    {
        searchbox.textProperty().addListener((observableValue,oldValue,newValue)->{
			filtereddata.setPredicate((Predicate<? super job>)Job->{
				if(newValue==null||newValue.isEmpty()){
					return true;
				}
				String lowerCaseFilter=newValue.toLowerCase();
				if(String.valueOf(Job.getJob_id()).toLowerCase().contains(lowerCaseFilter)){
					return true;
				}
				else if(Job.getJob_title().toLowerCase().contains(lowerCaseFilter)){
					return true;
				}
				return false;
			});
		});
		SortedList<job> sortedData=new SortedList<>(filtereddata);
		sortedData.comparatorProperty().bind(tab2.comparatorProperty());
		tab2.setItems(sortedData);
    }
    
    @FXML
    void logout() {
        exit(1);
    }
}
