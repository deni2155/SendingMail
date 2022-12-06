package com.dreamer.sendingmail;

import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.Properties;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


@Named(value = "mainClass")
@SessionScoped
public class MainClass implements Serializable {

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public MainClass() {
    }
    
    public void Send() throws IOException, InterruptedException{

        
        Properties props = new Properties();
        props.put("mail.smtp.host", "localhost");
        
        Session session = Session.getInstance(props);
  
        try {
            // Instantiate a message
            Message msg = new MimeMessage(session);

            // Set the FROM message
            msg.setFrom(new InternetAddress("dreamer@ubuntu"));

            // The recipients can be more than one so we use an array but you can
            // use 'new InternetAddress(to)' for only one address.
            //массовая отправка
            //InternetAddress[] address = {new InternetAddress(email)};
            //msg.setRecipients(Message.RecipientType.TO, address);
            //единичная отправка
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));

            // Set the message subject and date we sent it.
            msg.setSubject("Email from JavaMail test");
            msg.setSentDate(new Date());

            // Set message content
            msg.setText("This is the text for this simple demo using JavaMail.");

            // Send the message
            Transport.send(msg);
            FacesContext.getCurrentInstance().getExternalContext().redirect("result.xhtml");
        }
        catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
