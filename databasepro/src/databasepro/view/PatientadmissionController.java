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
public class PatientadmissionController implements Initializable {

    /**
     * Initializes the controller class.
     */
    Connection conn = DBConnection.LoginConnector();
    ObservableList<admission> data = FXCollections.observableArrayList();
    FilteredList<admission> filtereddata=new FilteredList<>(data,e->true);

    PreparedStatement prepare = null;
    ResultSet result = null;

    @FXML
    TextField searchbox;
    @FXML
    Button backbutton;
    @FXML
    Button logoutbutton;
    @FXML
    Button seatbutton;
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
 //   @FXML
 //   TableColumn<?, ?> c6;
    @FXML
    TableColumn<?, ?> c7;
    static Integer temppatient_id;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        c1.setCellValueFactory(new PropertyValueFactory<>("ad_id"));
        c2.setCellValueFactory(new PropertyValueFactory<>("ad_date"));
        c3.setCellValueFactory(new PropertyValueFactory<>("ad_time"));
        c4.setCellValueFactory(new PropertyValueFactory<>("date_discharge"));
        c5.setCellValueFactory(new PropertyValueFactory<>("seat_id"));
      //  c6.setCellValueFactory(new PropertyValueFactory<>("p_id"));
        c7.setCellValueFactory(new PropertyValueFactory<>("doctor_id"));
        loaddatabase(temppatient_id);
    }    

    void loaddatabase(Integer patient_id) {
        temppatient_id=patient_id;
        System.out.println(temppatient_id);
        String query = "SELECT * FROM ADMISSION WHERE P_ID='"+temppatient_id+"'";
        try {
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
    
    static Integer temp_id;
    
    @FXML
    public void selectonclick()
    {
        try
        {
            admission Admission = (admission) tabad.getSelectionModel().getSelectedItem();
            String query = "SELECT * FROM ADMISSION WHERE P_ID='"+temppatient_id+"'";
            prepare = conn.prepareStatement(query);
           // Integer seat_id = Admission.getSeat_id();
            temp_id = Admission.getSeat_id();
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
    public void seatinfo()
    {
        String query="select s.seat_id, s.SEAT_COST, r.ROOM_ID,r.FLOOR, d.DEPT_TITLE from seat s  join room r on( s.ROOM_ID=r.ROOM_ID ) join department d on (r.DEPT_ID=d.DEPT_ID) join admission a on(a.SEAT_ID=s.SEAT_ID) where a.seat_id='"+temp_id+"'";
        try
        {
           prepare = conn.prepareStatement(query);
           result = prepare.executeQuery(); 
           result.next();
           {
               Integer seat_id=result.getInt("seat_id");
               Integer seat_cost=result.getInt("seat_cost");
               Integer room_id=result.getInt("room_id");
               Integer floor=result.getInt("floor");
               String dept_title=result.getString("dept_title");
               
               t1.setText(String.valueOf(seat_id));
               t2.setText(String.valueOf(seat_cost));
               t3.setText(String.valueOf(room_id));
               t4.setText(String.valueOf(floor));
               t5.setText(dept_title);
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
			filtereddata.setPredicate((Predicate<? super admission>)Admission->{
				if(newValue==null||newValue.isEmpty()){
					return true;
				}
				String lowerCaseFilter=newValue.toLowerCase();
				if(String.valueOf(Admission.getAd_id()).toLowerCase().contains(lowerCaseFilter)){
					return true;
				}
				else if(Admission.getAd_date().toLowerCase().contains(lowerCaseFilter)){
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
        Databasepro.showPatient();
    }
    
    @FXML
    void logout()
    {
        exit(1);
    }
    
}
