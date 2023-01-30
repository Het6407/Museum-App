 package com.museum.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.server.ExportException;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.museum.model.User;
import com.museum.repository.UserRepository;
import com.museum.service.MuseumService;
import com.museum.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
    @Autowired
    private UserRepository userRepository;
	
	@Autowired
	private MuseumService museumService;
	
	
	@GetMapping("/user")
	public String viewUser(Model model) {
		model.addAttribute("listUsers", userService.getAllUsers());

		return "user";
	}
	

	
	@GetMapping("/AddUser")
	public String addUser(User user,Model model) {
		model.addAttribute("listMuseum", museumService.getAllMuseum());
		model.addAttribute("user",user);
		return "add_User";
	}

	public String uploadDirectory=System.getProperty("User.dir")+"/src/main/resources/static/app-assets/images/avatars";
	
	@RequestMapping(value = "/saveUser", consumes = {"multipart/form-data" } )
	public String saveUser( User user,Model model, @RequestPart("file") MultipartFile file) {
		//System.out.println(user);
		try {
			userService.saveUser(user);
		}
		catch(InvalidParameterException e){
			
			model.addAttribute("error", e.getMessage());
			//System.out.println(model.getAttribute("error"));
			model.addAttribute("listMuseum", museumService.getAllMuseum());
			
			model.addAttribute("user",user);
			return "add_User";
		}
		
		try {
			user.setProfileImage(file.getBytes());
		} catch (IOException e1) {

			e1.printStackTrace();
		}

		file.getOriginalFilename();

		String filename = user.getId()
				+ file.getOriginalFilename().substring(file.getOriginalFilename().length());

		// System.out.println(filename);
		java.nio.file.Path fileNameAndPath = Paths.get(uploadDirectory, filename);

		try {
			Files.write(fileNameAndPath, file.getBytes());
		} catch (IOException e) {

			e.printStackTrace();
		}

		
		user.getProfileImage();
		
		
		userService.saveUser(user);
		
		
		
		return "redirect:/user";
	}
	
	@RequestMapping(value = "/editUser", consumes = {"multipart/form-data" } )
	public String editUser( User user1,Model model, @RequestPart("file") MultipartFile file) {
		//System.out.println(user);
		try {
			userService.editUser(user1);
		}
		catch(InvalidParameterException e){
			
			model.addAttribute("error", e.getMessage());
			//System.out.println(model.getAttribute("error"));
			model.addAttribute("listMuseum", museumService.getAllMuseum());
			
			model.addAttribute("user",user1);
			return "add_User";
		}
		
		try {
			user1.setProfileImage(file.getBytes());
		} catch (IOException e1) {

			e1.printStackTrace();
		}

		file.getOriginalFilename();

		String filename = user1.getId()
				+ file.getOriginalFilename().substring(file.getOriginalFilename().length());

		// System.out.println(filename);
		java.nio.file.Path fileNameAndPath = Paths.get(uploadDirectory, filename);

		try {
			Files.write(fileNameAndPath, file.getBytes());
		} catch (IOException e) {

			e.printStackTrace();
		}

		
		user1.getProfileImage();
		
		
		
		userService.editUser(user1);
		
		
		
		return "redirect:/user";
	}
	
	@GetMapping("/ShowImage/{id}")
    public void showimage(@PathVariable Long id, HttpServletResponse resp)throws IOException {
        resp.setContentType("image/jpeg");
        try {
            User user = userService.findById(id);
            InputStream is = new ByteArrayInputStream(user.getProfileImage());
            IOUtils.copy(is, resp.getOutputStream());
        } catch (Exception e) {
            throw new ExportException("Image is not available");
        }
    }	
	
	
		
	
	@GetMapping("/updateUser/{id}")
	public String updateUser(@PathVariable(value = "id") long id, Model model) {
		User user = userService.getUserById(id);
	
		model.addAttribute("listMuseum", museumService.getAllMuseum());
		
		System.out.println(user);
		
		model.addAttribute("user", user);
		

		
		
		return "update_User";
	}
	
	
	

	@RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.GET)
	public String deleteThroughId(@PathVariable(value = "id") long id) {
	
		
		userService.deleteUserById(id);
		
		
		return "redirect:/user";
	}
	
	@RequestMapping(value = "/status/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Boolean saveStatus(@PathVariable(value = "id") long id
			) {
	
		userService.changeStatus(id);
		return true;
	}
	
	
	  @PostMapping
      public ResponseEntity<User> createUser(@RequestBody User user) {
          User newUser = userService.createUser(user);
          sendWelcomeEmail(newUser);
          return ResponseEntity.ok(newUser);
      }
      
      private void sendWelcomeEmail(User newUser) {
          try {
              Properties properties = new Properties();
              properties.put("mail.smtp.auth", "true");
              properties.put("mail.smtp.starttls.enable", "true");
              properties.put("mail.smtp.host", "smtp.gmail.com");
              properties.put("mail.smtp.port", "587");
              properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
              Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
                  protected PasswordAuthentication getPasswordAuthentication() {
                      return new PasswordAuthentication("testproject6409@gmail.com", "hvyemqzmvohcuyqc");
                  }
              });
              MimeMessage message = new MimeMessage(session);
              message.setFrom(new InternetAddress("testproject6409@gmail.com"));
              message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(newUser.getEmail()));
              message.setSubject("Welcome to our app");
              message.setText("Dear " + newUser.getUsername() + ",\n\nWelcome to our application. Your account has been created successfully.");
              Transport.send(message);
          } catch (MessagingException e) {
              throw new RuntimeException(e);
          }
      }
      
      @GetMapping("/users")
      public String getUser(Model model, @PageableDefault(size = 5) Pageable pageable) {
          Page<User> page = userRepository.findAll(pageable);
          model.addAttribute("page", page);
          return "redirect:/user";
      }
	
}
