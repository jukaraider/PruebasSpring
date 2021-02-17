package com.loginAndRoles.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;

	public void sendEmail(String to, String subject, String message) {
		SimpleMailMessage registrationEmail = new SimpleMailMessage();
		registrationEmail.setTo(to);
		registrationEmail.setSubject(subject);
		registrationEmail.setText(message);
		
		//mailSender.send(registrationEmail);
	}
}