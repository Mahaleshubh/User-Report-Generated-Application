package com.example.email;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtils {
	
	@Autowired(required=true)
	private JavaMailSenderImpl mailSender;
	
	private boolean status=false;
	
	public void fileSender(File file) {
		try {
		
		MimeMessage msg = mailSender.createMimeMessage();
		MimeMessageHelper helper =new MimeMessageHelper(msg, true);
		
		helper.setTo("shubhammahale080900@gmail.com");
		helper.setText("<h2>Download your Report</h2>",true);
		helper.addAttachment(file.getName(), file);
		
		mailSender.send(msg);
		
		status=true;
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
