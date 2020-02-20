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
public class AdminroomController implements Initializable {

    /**
     * Initializes the controller class.
     */
    Connection conn = DBConnection.LoginConnector();
    ObservableList<room> data = FXCollections.observableArrayList();
    FilteredList<room> filtereddata=new FilteredList<>(data,e->true);

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
    TableView<room> tabroom;
    @FXML
    TableColumn<?, ?> c1;
    @FXML
    TableColumn<?, ?> c2;
    @FXML
    TableColumn<?, ?> c3;
    @FXML
    TableColumn<?, ?> c4;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        c1.setCellValueFactory(new PropertyValueFactory<>("room_id"));
        c2.setCellValueFactory(new PropertyValueFactory<>("room_cap"));
        c3.setCellValueFactory(new PropertyValueFactory<>("floor"));
        c4.setCellValueFactory(new PropertyValueFactory<>("dept_id"));
        loaddatabase();
    }    
    
    public void loaddatabase()
    {
        String query = "SELECT * FROM ROOM";
        try {
            t1.clear();
            t2.clear();
            t3.clear();
            t4.clear();
            data.clear();
            prepare = conn.prepareStatement(query);
            result = prepare.executeQuery();
            while (result.next()) {
                data.add(
                        new room(result.getInt("room_id"),
                                result.getInt("room_cap"),
                                result.getInt("floor"),
                                result.getInt("dept_id"))
                );
                tabroom.setItems(data);
            }
            prepare.close();
            result.close();
        } catch (Exception e1) {
            System.err.println(e1);
        }
    }
    
    @FXML
    public void addnew() throws SQLException {
        String room_idstr = t1.getText();
        Integer room_id = Integer.parseInt(room_idstr);
        Integer room_cap = Integer.parseInt(t2.getText());
        Integer floor = Integer.parseInt(t3.getText());
        Integer dept_id = Integer.parseInt(t4.getText());
        String query = "INSERT INTO ROOM VALUES(?,?,?,?)";
        prepare = null;
        try {
            prepare = conn.prepareStatement(query);
            prepare.setInt(1, room_id);
            prepare.setInt(2, room_cap);
            prepare.setInt(3, floor);
            prepare.setInt(4, dept_id);

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
        data.clear();
        loaddatabase();
        Databasepro.showInformationAlertBox("New room info of " + room_id + " is added successfully");
    }
    
     static Integer temproom_id;
    
    @FXML
    public void showonclick() {
        try {
            room Room = (room) tabroom.getSelectionModel().getSelectedItem();
            String query = "SELECT * FROM ROOM";
            prepare = conn.prepareStatement(query);
            Integer room_id = Room.getRoom_id();
            temproom_id = Room.getRoom_id();
            t1.setText(String.valueOf(room_id));
            t2.setText(String.valueOf(Room.getRoom_cap()));
            t3.setText(String.valueOf(Room.getFloor()));
            t4.setText(String.valueOf(Room.getDept_id()));

            prepare.close();
            result.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    @FXML
    public void delete() {
        Integer room_id = null;
        try {
            room Room = (room) tabroom.getSelectionModel().getSelectedItem();
            String query = "DELETE FROM ROOM WHERE ROOM_ID=?";
            prepare = conn.prepareStatement(query);
            prepare.setInt(1, Room.getRoom_id());
            room_id = Room.getRoom_id();
            prepare.executeUpdate();

            prepare.close();
            result.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        loaddatabase();
        Databasepro.showInformationAlertBox("Room info of " + room_id + " is deleted successfully");
    }
    
     @FXML
    public void update() {
        String query = "UPDATE ROOM SET ROOM_ID=?, ROOM_CAP=?, FLOOR=?, DEPT_ID=? WHERE ROOM_ID ='" + temproom_id+"'";
        try {
            prepare = conn.prepareStatement(query);
            prepare.setInt(1, Integer.valueOf(t1.getText()));
            prepare.setInt(2, Integer.valueOf(t2.getText()));
            prepare.setInt(3, Integer.valueOf(t3.getText()));
            prepare.setInt(4, Integer.valueOf(t4.getText()));
            prepare.execute();
            prepare.close();
            Databasepro.showInformationAlertBox("Room info of '"+ t1.getText()+"' is updated successfully");
            loaddatabase();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    @FXML
    public void search()
    {
        searchbox.textProperty().addListener((observableValue,oldValue,newValue)->{
			filtereddata.setPredicate((Predicate<? super room>)Room->{
				if(newValue==null||newValue.isEmpty()){
					return true;
				}
				String lowerCaseFilter=newValue.toLowerCase();
				if(String.valueOf(Room.getRoom_id()).toLowerCase().contains(lowerCaseFilter)){
					return true;
				}
                                else if(String.valueOf(Room.getRoom_cap()).toLowerCase().contains(lowerCaseFilter)){
					return true;
				}
                                else if(String.valueOf(Room.getFloor()).toLowerCase().contains(lowerCaseFilter)){
					return true;
				}
                                else if(String.valueOf(Room.getDept_id()).toLowerCase().contains(lowerCaseFilter)){
					return true;
				}
				return false;
			});
		});
		SortedList<room> sortedData=new SortedList<>(filtereddata);
		sortedData.comparatorProperty().bind(tabroom.comparatorProperty());
		tabroom.setItems(sortedData);
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
