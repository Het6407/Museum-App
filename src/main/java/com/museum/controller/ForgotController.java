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
				+"<a href='javascript:void(0);' class='brand-logo'>"
				+"    <svg viewbox='0 0 139 95' version='1.1' xmlns='http://www.w3.org/2000/svg'"
				+"        xmlns:xlink='http://www.w3.org/1999/xlink' height='28'>"
				+"        <defs>"
				+"            <lineargradient id='linearGradient-1' x1='100%' y1='10.5120544%' x2='50%'"
				+"                y2='89.4879456%'>"
				+"                <stop stop-color='#000000' offset='0%'></stop>"
				+"                <stop stop-color='#FFFFFF' offset='100%'></stop>"
				+"            </lineargradient>"
				+"            <lineargradient id='linearGradient-2' x1='64.0437835%' y1='46.3276743%'"
				+"                x2='37.373316%' y2='100%'>"
				+"                <stop stop-color='#EEEEEE' stop-opacity='0' offset='0%'></stop>"
				+"                <stop stop-color='#FFFFFF' offset='100%'></stop>"
				+"            </lineargradient>"
				+"        </defs>"
				+"        <g id='Page-1' stroke='none' stroke-width='1' fill='none' fill-rule='evenodd'>"
				+"            <g id='Artboard' transform='translate(-400.000000, -178.000000)'>"
				+"                <g id='Group' transform='translate(400.000000, 178.000000)'>"
				+"                    <path class='text-primary' id='Path'"
				+"                        d='M-5.68434189e-14,2.84217094e-14 L39.1816085,2.84217094e-14 L69.3453773,32.2519224 L101.428699,2.84217094e-14 L138.784583,2.84217094e-14 L138.784199,29.8015838 C137.958931,37.3510206 135.784352,42.5567762 132.260463,45.4188507 C128.736573,48.2809251 112.33867,64.5239941 83.0667527,94.1480575 L56.2750821,94.1480575 L6.71554594,44.4188507 C2.46876683,39.9813776 0.345377275,35.1089553 0.345377275,29.8015838 C0.345377275,24.4942122 0.230251516,14.560351 -5.68434189e-14,2.84217094e-14 Z'"
				+"                        style='fill: currentColor'></path>"
				+"                    <path id='Path1'"
				+"                        d='M69.3453773,32.2519224 L101.428699,1.42108547e-14 L138.784583,1.42108547e-14 L138.784199,29.8015838 C137.958931,37.3510206 135.784352,42.5567762 132.260463,45.4188507 C128.736573,48.2809251 112.33867,64.5239941 83.0667527,94.1480575 L56.2750821,94.1480575 L32.8435758,70.5039241 L69.3453773,32.2519224 Z'"
				+"                        fill='url(#linearGradient-1)' opacity='0.2'></path>"
				+"                    <polygon id='Path-2' fill='#000000' opacity='0.049999997'"
				+"                        points='69.3922914 32.4202615 32.8435758 70.5039241 54.0490008 16.1851325'>"
				+"                    </polygon>"
				+"                    <polygon id='Path-21' fill='#000000' opacity='0.099999994'"
				+"                        points='69.3922914 32.4202615 32.8435758 70.5039241 58.3683556 20.7402338'>"
				+"                    </polygon>"
				+"                    <polygon id='Path-3' fill='url(#linearGradient-2)'"
				+"                        opacity='0.099999994'"
				+"                        points='101.428699 0 83.0667527 94.1480575 130.378721 47.0740288'>"
				+"                    </polygon>"
				+"                </g>"
				+"            </g>"
				+"        </g>"
				+"    </svg>"
				+"    <h2 class='brand-text text-primary ml-1'>Museum App</h2>"
				+"</a>"
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
			User user = this.userRepository.findByEmail(email);
			
			if(user == null) {
				
				session.setAttribute("message", "User does not exists with this email !!");
				return "forgot_email_form";
			}else {
				
			}
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
		//String email=(String)session.getAttribute("email");
		if(myotp==otp) {
			
			
			
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
