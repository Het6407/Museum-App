package com.museum.controller;

import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.museum.model.User;
import com.museum.repository.UserRepository;
import com.museum.service.EmailServiceImpl;

@Controller
public class ForgotController {
	Random random = new Random(1000);

	@Autowired
	private EmailServiceImpl emailServiceImpl;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	

	
	@RequestMapping("/forgot")
	public String openEmailForm() {
		return "forgot_email_form";
	}
	 
	
	
	@PostMapping("/sendOtp")
	public String sendOtp(@RequestParam("email")String email, RedirectAttributes redirectAttributes,HttpSession session) {
		
		System.out.println("EMAIL"+ email);
		
		
		
		int otp = random.nextInt(999999);
		
		
		System.out.println("OTP"+ otp);
		
		
		
		String subject="OTP from Museum App";
		String message=""
				+"<!DOCTYPE html PUBLIC ‘-//W3C//DTD XHTML 1.0 Strict//EN’ ‘http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd’>"
				+"<html xmlns=‘http://www.w3.org/1999/xhtml’>"
				+""
				+"<head>"
				+"  <meta http-equiv=‘Content-Type’ content=‘text/html; charset=utf-8’>"
				+"  <meta name=‘viewport’ content=‘width=device-width, initial-scale=1.0’>"
				+"  <title>Verify your login</title>"
				+"  <!--[if mso]><style type=‘text/css’>body, table, td, a { font-family: Arial, Helvetica, sans-serif !important; }</style><![endif]-->"
				+"</head>"
				+""
				+"<body style='font-family: Helvetica, Arial, sans-serif; margin: 0px; padding: 0px; background-color: #ffffff;'>"
				+"  <table role=‘presentation’"
				+"    style='width: 100%; border-collapse: collapse; border: 0px; border-spacing: 0px; font-family: Arial, Helvetica, sans-serif; background-color: rgb(132, 238, 245);'>"
				+"    <tbody>"
				+"      <tr>"
				+"        <td align='center' style='padding: 1rem 2rem; vertical-align: top; width: 100%;'>"
				+"          <table role=‘presentation’ style='max-width: 600px; border-collapse: collapse; border: 0px; border-spacing: 0px; text-align: left;'"
				+"            <tbody>"
				+"              <tr>"
				+"                <td style='padding: 40px 0px 0px;'>"
				+"                  <div style='text-align: center;'>"
				+"                    <!---->"
				+"                  </div>"
				+"                  <div style='padding: 20px; background-color: rgb(245, 245, 245);'>"
				+"                    <div style='color: rgb(0, 0, 0); text-align: left;'>"
				+"                      <h1 style='margin: 1rem 0'>Verification code</h1>"
				+"                      <p style='padding-bottom: 16px'>Please use the verification code below for forgot password.</p>"
				+"                      <p style='padding-bottom: 16px'><strong style='font-size: 130%'>"+otp
				+"                        </strong></p>"
				+"                      <p style='padding-bottom: 16px'>If you didn't request this, you can ignore this email.</p>"
				+"                      <p style='padding-bottom: 16px'>Thanks,<br>The Museum App</p>"
				+"                    </div>"
				+"                  </div>"
				+"                  <div style='padding-top: 20px; color: rgb(0, 0, 0); text-align: center;'>"
				+"                    <p style='padding-bottom: 16px'>Museum App Support</p>"
				+"                  </div>"
				+"                </td>"
				+"              </tr>"
				+"            </tbody>"
				+"          </table>"
				+"        </td>"
				+"      </tr>"
				+"    </tbody>"
				+"  </table>"
				+"</body>"
				+""
				+"</html>";

		String to=email;
		
		boolean flag = this.emailServiceImpl.sendEmail(subject, message, to);
		
		if(flag) {
			
			session.setAttribute("myotp", otp);
			session.setAttribute("email",email );
			 return "verify_otp";
		}else {
			redirectAttributes.addFlashAttribute("message", "Check your email id..");
			 redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
			
			return "redirect:/forgot";
		}
		
	}
	

	
	@PostMapping("/verify_Otp")
	public String verifyOtp(@RequestParam("otp") int otp,HttpSession session) {
		
		int myotp=(int)session.getAttribute("myotp");
		String email=(String)session.getAttribute("email");
		if(myotp==otp) {
			
			User user = this.userRepository.findByEmail(email);
			
			if(user == null) {
				
				session.setAttribute("message", "User does not exists with this email !!");
				return "forgot_email_form";
			}else {
				
			}
			
			return "reset_password_form";
		}else {
			session.setAttribute("message", "You have entered wrong otp!!"); 
			return "verify_otp";
		}
	}
	
	@PostMapping("/change_password")
	public String ChangePassword(@RequestParam("newPassword") String newPassword,HttpSession session) {
		
		String email = (String)session.getAttribute("email");
		User user = this.userRepository.findByEmail(email);
		user.setPassword(this.bCryptPasswordEncoder.encode(newPassword));
		this.userRepository.save(user);
		
		return "redirect:/?change=password change successfully..";
	}
}
