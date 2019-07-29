package com.wearable.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;


import javax.mail.Message;

import javax.mail.MessagingException;

import javax.mail.PasswordAuthentication;

import javax.mail.Session;

import javax.mail.Transport;

import javax.mail.internet.InternetAddress;

import javax.mail.internet.MimeMessage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NotificationService
 */
@WebServlet("/NotificationService")
public class NotificationService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public String googleMapLocationOfSajjadHouse="https://goo.gl/maps/UifwyeuW8Ms";

    /**
     * Default constructor. 
     */
    public NotificationService() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//**Get the Heart Beat
		String heartBeat = (String) request.getParameter("heartBeat");
		//** If Heartbeat is more that 100 than notify (This needs ot be updated based on a study of heart beat as per the age kevels)
		if(heartBeat!=null && (Integer.valueOf(heartBeat))>100) {
			
			response.getWriter().append("===>>>>> Should send an email to Medicle facility notifying madicle emergency");
			//**Get the source sending this message i.e companionApp or URL 
			String source=(String) request.getParameter("source");
			if(source!=null && source.equals("companionApp") ) {
				System.out.println("Request received from Companion App");
			}else
				System.out.println("Request received from URL");
			
			//Get the Location of user's mobile
			String locationOfUsersMobile=(String) request.getParameter("locationOfUsersMobile");
			
			
			response.getWriter().append("\n===> Served at: ").append(request.getContextPath());
			
			System.out.println("HeartBeat "+heartBeat+" locationOfUsersMobile "+locationOfUsersMobile+" source"+source);
			//**Send email to notify about heart beat
			sendMail(Integer.valueOf(heartBeat),locationOfUsersMobile);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
    public void sendMail(int heartBeat,String locationOfUsersMobile) {

        //Setting up configurations for the email connection to the Google SMTP server using TLS

        Properties props = new Properties();

        props.put("mail.smtp.host", "true");

        props.put("mail.smtp.starttls.enable", "true");

        props.put("mail.smtp.host", "smtp.gmail.com");

        props.put("mail.smtp.port", "587");

        props.put("mail.smtp.auth", "true");

        //Establishing a session with required user details

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
            	/* https://support.google.com/accounts/answer/185833 
            	your normal google password will not work because of the dual authentication enabled in gmail account 
            	 you will have to go to above link to create a one time app password in order to use google service like mail e.t.c.
            	 */
                return new PasswordAuthentication("sajadparacha", "rijestvemvnbchtd");

            }

        });

        try {

            //Creating a Message object to set the email content

            MimeMessage msg = new MimeMessage(session);

            //Storing the comma seperated values to email addresses

            String to = "sajadparacha@gmail.com,sajjad.paracha@outlook.com";

            /*Parsing the String with defualt delimiter as a comma by marking the boolean as true and storing the email

            addresses in an array of InternetAddress objects*/

            InternetAddress[] address = InternetAddress.parse(to, true);

            //Setting the recepients from the address variable

            msg.setRecipients(Message.RecipientType.TO, address);

            String timeStamp = new SimpleDateFormat("yyyymmdd_hh-mm-ss").format(new Date());

            //msg.setSubject("Sample Mail : " + timeStamp);
            msg.setSubject("Heart Beat Alert, current heart beat is = "+heartBeat);
            
            msg.setSentDate(new Date());

            msg.setText("Sampel System Generated mail");

            msg.setText("Heart Beat Alert, current heart beat is = "+heartBeat);

            msg.setText("The current location of this person is at  = "+locationOfUsersMobile);
            msg.setHeader("XPriority", "1");

            Transport.send(msg);

            System.out.println("Mail has been sent successfully");

        } catch (MessagingException mex) {

            System.out.println("Unable to send an email" + mex);

        }

    }

}
