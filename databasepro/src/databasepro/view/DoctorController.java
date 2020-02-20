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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author user
 */
public class DoctorController implements Initializable {

    /**
     * Initializes the controller class.
     *
     */
    Connection conn = DBConnection.LoginConnector();
    PreparedStatement prepare = null;
    ResultSet result = null;
     PreparedStatement prepare1 = null;
    ResultSet result1 = null;
    @FXML
    Button backbutton;
    @FXML
    Button applicationbutton;
    @FXML
    TextField n1;
    @FXML
    TextField text1;
    @FXML
    TextField text2;
    @FXML
    TextField text3;
    @FXML
    TextField text4;
    @FXML
    TextField text5;
    @FXML
    TextField text6;
     @FXML
    TextField text7;
    @FXML
    TextField text8;
    @FXML
    Button patbutton;
    @FXML
    Button deptbutton;
    @FXML
    Label label1;
    @FXML
    Label label2;
    @FXML
    Label label3;
    @FXML
    Label label4;
    @FXML
    Label label5;
    @FXML
    Label label6;
     @FXML
    Label label7;
    @FXML
    Label label8;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    @FXML
    void back() throws IOException {
        Databasepro.showEmployee();
    }

    @FXML
    void application() throws IOException {
        Databasepro.showEmployee();
    }

    @FXML
    void patient() {
        String query = "select p.p_id, p.p_firstname ,n.employee_id, a.AD_ID,s.SEAT_ID,r.ROOM_ID from patient p, nurse n, admission a, seat s, room r where p.P_ID=(select a.p_id from admission a where a.SEAT_ID=(select s.seat_id from seat s where s.ROOM_ID=(select r.room_id from room r, nurse n where r.ROOM_ID=n.ROOM_ID)))";
        try {
            prepare = conn.prepareStatement(query);
            result = prepare.executeQuery();
            result.next();
            {
                String p_id = result.getString("p_id");
                String p_firstname = result.getString("p_firstname");
                String employee_id = result.getString("employee_id");
                String ad_id = result.getString("ad_id");
                String seat_id = result.getString("seat_id");
                String room_id = result.getString("room_id");

                text1.setText(p_id);
                text2.setText(p_firstname);
                text3.setText(employee_id);
                text4.setText(ad_id);
                text5.setText(seat_id);
                text6.setText(room_id);
                label1.setText("patient_id");
                label2.setText("patient_first name");
                label3.setText("nurse_id");
                label4.setText("admission_id");
                label5.setText("seat_id");
                label6.setText("room_id");
                    }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    @FXML
    void department()       
    {
            String query1="select r.dept_id,(select dept_title from department d where d.DEPT_ID=r.DEPT_ID)\"dept_title\" from room r join nurse s on(r.ROOM_ID=s.ROOM_ID)";
            try{
                prepare1 = conn.prepareStatement(query1);
                result1 = prepare1.executeQuery();
             // while(result1.next())
                result1.next();
               String dept_id=result1.getString("dept_id");
               String dept_title=result1.getString("dept_title");
                        
                text7.setText(dept_id);
                text8.setText(dept_title);
                label7.setText("dept_id");
                label8.setText("dept_title");
                
            }catch(Exception e){
                System.out.println(e);
            }
    }
    
    @FXML
    void logout()
    {
        exit(1);
    }
}
