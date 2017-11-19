package com.wy.estore.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailUtils {
    public static void sendMail(String to,String code){
        Properties props=new Properties();
        Session session=Session.getInstance(props,new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("service@estore.com","111");
            }
        });
        Message message=new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress("service@estore.com"));
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setSubject("Estore Activation Mail");
            message.setContent("<h1>Estore Activation Mail: Please click link below to active your account</h1><h3><a href='http://192.168.1.60:8080/bookStore/userServlet?method=active&code="+code+"'>http://192.168.1.60:8080/bookStore/userServlet?method=active&code="+code+"</a></h3>","text/html;charset=UTF-8");
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
