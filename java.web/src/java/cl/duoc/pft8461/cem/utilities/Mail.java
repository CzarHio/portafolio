/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.duoc.pft8461.cem.utilities;

import java.io.IOException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Joe-Xidu
 */
public class Mail {
    
    private Properties props = new Properties();
    private PropertiesManager cem_props = new PropertiesManager();
    private Session session;
    private String u_email;
    private String p_email;
    private String from;
    private String to;
    private String subject;
    private String content;
    
    public Mail() throws IOException {
        this.props.put("mail.smtp.auth", this.cem_props.get("mail_smtp_auth"));
        this.props.put("mail.smtp.starttls.enable", this.cem_props.get("mail_smtp_starttls_enable"));
        this.props.put("mail.smtp.host", this.cem_props.get("mail_smtp_host"));
        this.props.put("mail.smtp.port", this.cem_props.get("mail_smtp_port"));
        this.props.put("mail.smtp.ssl.trust", this.cem_props.get("mail_smtp_host"));
        this.from = this.cem_props.get("u_email");
        this.u_email = this.cem_props.get("u_email");
        this.p_email = this.cem_props.get("p_email");
    }
    
    public void init() throws IOException {
        
        this.session = Session.getInstance(this.props,
            new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(u_email, p_email);
                }
            });
    }
    
    public void from(String from) {
        this.from = from;
    }
    
    public void to(String to) {
        this.to = to;
    }
    
    public void subject(String subject) {
        this.subject = subject;
    }
    
    public void content(String content) {
        this.content = content;
    }
    
    public boolean send() throws Exception {
        boolean send = false;
        try {

            Message message = new MimeMessage(this.session);
            message.setFrom(new InternetAddress(this.validate(this.from)));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(this.validate(this.to)));
            message.setSubject(this.validate(this.subject));
            message.setText(this.validate(this.content));

            Transport.send(message);

            send = true;

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return send;
    }
    
    private String validate(String variable) throws Exception {
        
        if (variable.isEmpty())
            throw new Exception("Propiedad no encontrada: Algunos de los "
                    + "atributos necesarios para el envío de correos no fue seteado.");
        
        return variable;
    }
}
