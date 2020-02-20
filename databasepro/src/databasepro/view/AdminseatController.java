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
public class AdminseatController implements Initializable {

    /**
     * Initializes the controller class.
     */
    Connection conn = DBConnection.LoginConnector();
    ObservableList<seat> data = FXCollections.observableArrayList();
    FilteredList<seat> filtereddata=new FilteredList<>(data,e->true);

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
    TableView<seat> tabseat;
    @FXML
    TableColumn<?, ?> c1;
    @FXML
    TableColumn<?, ?> c2;
    @FXML
    TableColumn<?, ?> c3;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        c1.setCellValueFactory(new PropertyValueFactory<>("seat_id"));
        c2.setCellValueFactory(new PropertyValueFactory<>("seat_cost"));
        c3.setCellValueFactory(new PropertyValueFactory<>("room_id"));
        loaddatabase();
    }    
    
    public void loaddatabase()
    {
         String query = "SELECT * FROM SEAT";
        try {
            t1.clear();
            t2.clear();
            t3.clear();
            data.clear();
            prepare = conn.prepareStatement(query);
            result = prepare.executeQuery();
            while (result.next()) {
                data.add(
                        new seat(result.getInt("seat_id"),
                                result.getInt("seat_cost"),
                                result.getInt("room_id"))
                );
                tabseat.setItems(data);
            }
            prepare.close();
            result.close();
        } catch (Exception e1) {
            System.err.println(e1);
        }
    }
    
     @FXML
    public void addnew() throws SQLException {
        String seat_idstr = t1.getText();
        Integer seat_id = Integer.parseInt(seat_idstr);
        Integer seat_cost = Integer.parseInt(t2.getText());
        Integer room_id = Integer.parseInt(t3.getText());
        String query = "INSERT INTO SEAT VALUES(?,?,?)";
        prepare = null;
        try {
            prepare = conn.prepareStatement(query);
            prepare.setInt(1, seat_id);
            prepare.setInt(2, seat_cost);
            prepare.setInt(3, room_id);

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            prepare.execute();
            prepare.close();
        }
        t1.clear();
        t2.clear();
        t3.clear();
        data.clear();
        loaddatabase();
        Databasepro.showInformationAlertBox("New seat info of " + seat_id + " is added successfully");
    }
    
     static Integer tempseat_id;
    
    @FXML
    public void showonclick() {
        try {
            seat Seat = (seat) tabseat.getSelectionModel().getSelectedItem();
            String query = "SELECT * FROM SEAT";
            prepare = conn.prepareStatement(query);
            Integer seat_id = Seat.getSeat_id();
            tempseat_id = Seat.getSeat_id();
            t1.setText(String.valueOf(seat_id));
            t2.setText(String.valueOf(Seat.getSeat_cost()));
            t3.setText(String.valueOf(Seat.getRoom_id()));

            prepare.close();
            result.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
     @FXML
    public void delete() {
        Integer seat_id = null;
        try {
            seat Seat = (seat) tabseat.getSelectionModel().getSelectedItem();
            String query = "DELETE FROM SEAT WHERE SEAT_ID=?";
            prepare = conn.prepareStatement(query);
            prepare.setInt(1, Seat.getSeat_id());
            seat_id = Seat.getSeat_id();
            prepare.executeUpdate();

            prepare.close();
            result.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        loaddatabase();
        Databasepro.showInformationAlertBox("Seat info of " + seat_id + " is deleted successfully");
    }
    
     @FXML
    public void update() {
        String query = "UPDATE SEAT SET SEAT_ID=?, SEAT_COST=?, ROOM_ID=? WHERE SEAT_ID ='" + tempseat_id+"'";
        try {
            prepare = conn.prepareStatement(query);
            prepare.setInt(1, Integer.valueOf(t1.getText()));
            prepare.setInt(2, Integer.valueOf(t2.getText()));
            prepare.setInt(3, Integer.valueOf(t3.getText()));
            prepare.execute();
            prepare.close();
            Databasepro.showInformationAlertBox("Seat info of '"+ t1.getText()+"' is updated successfully");
            loaddatabase();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    @FXML
    public void search()
    {
        searchbox.textProperty().addListener((observableValue,oldValue,newValue)->{
			filtereddata.setPredicate((Predicate<? super seat>)Seat->{
				if(newValue==null||newValue.isEmpty()){
					return true;
				}
				String lowerCaseFilter=newValue.toLowerCase();
				if(String.valueOf(Seat.getSeat_id()).toLowerCase().contains(lowerCaseFilter)){
					return true;
				}
                                else if(String.valueOf(Seat.getSeat_cost()).toLowerCase().contains(lowerCaseFilter)){
					return true;
				}
                                else if(String.valueOf(Seat.getRoom_id()).toLowerCase().contains(lowerCaseFilter)){
					return true;
				}
				return false;
			});
		});
		SortedList<seat> sortedData=new SortedList<>(filtereddata);
		sortedData.comparatorProperty().bind(tabseat.comparatorProperty());
		tabseat.setItems(sortedData);
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
