/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugas.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.apache.commons.mail.HtmlEmail;
/**
 * FXML Controller class
 *
 * @author Fadillah
 */
public class V_emailController implements Initializable {

    @FXML
    private TextField emailTujuan;
    @FXML
    private TextField subject;
    @FXML
    private TextArea isi;
    @FXML
    private Button btnKirim;
    
    String reciever;
    String subj;
    String con;
    String myAccount = "schoolmanagementassets@gmail.com";
    String password = "Sma12345_";
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void kirimEmail(ActionEvent event) throws Exception{
        HtmlEmail email = new HtmlEmail();
        
        reciever= emailTujuan.getText();
        subj= subject.getText();
        con= isi.getText();
        
        try{
            /*** konfigurasi ***/
            email.setHostName("smtp.gmail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator(myAccount, password));
            email.setSSLOnConnect(true);

            // Email pengirim
            email.setFrom("schoolmanagementassets@gmail.com");

            // Email Tujuan
            email.addTo(reciever);

            // Subjek Email
            email.setSubject(subj);
            email.setHtmlMsg("<html><h2>Test HTML Format</h2></html>");
            
            // Isi Email
            email.setTextMsg(con);

            // Kirim Email
            //email.send();
            
            System.out.println("Message sent successfully");
            JOptionPane.showMessageDialog(null, "Message sent successfully");
        }catch(Exception ex){
            System.out.println("Unable to send email");
            System.out.println(ex);
        }
        
    }
    
}
