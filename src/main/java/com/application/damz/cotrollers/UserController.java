package com.application.damz.cotrollers;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Comparator;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import com.application.damz.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.application.damz.dao.UserRepository;
import com.application.damz.entities.User;
import com.application.damz.utils.Role;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EmailService emailService;
	
	@RequestMapping(value="/users",method=RequestMethod.GET)
	public List<User> getUsers(){
		return userRepository.findAll();
	}
	
	@RequestMapping(value="/addUser",method=RequestMethod.POST)
	public User save(@RequestBody User user) {
		return userRepository.save(user);
	}
	
	@RequestMapping(value="/user",method=RequestMethod.GET)
	public User getUser(@RequestParam String email) {
		return userRepository.findByEmail(email);
	}
	
	@RequestMapping(value="/user/{id}",method=RequestMethod.GET)
	public User getUserById(@PathVariable("id") long id) {	
		return userRepository.findById(id).get();
	}
	
	@RequestMapping(value="/role/{id}",method=RequestMethod.GET)
	public Role getUserRole(@PathVariable("id") long id) {
		return userRepository.findById(id).get().getRole();
	}
	
	@RequestMapping(value="/user/upload/{id}/quesiton/{qId}/{score}",method=RequestMethod.PUT)
	public User updateUser(@PathVariable("id") long id, @PathVariable("qId") long qId,@PathVariable("score") long score) {
		User usr =  userRepository.findById(id).get();
		usr.getQuestions().add(qId);
		usr.setScore(usr.getScore()+score);
		return userRepository.save(usr);
		
	}
	
	@RequestMapping(value="/user/upload/{id}/role/{role}",method=RequestMethod.PUT)
	public User updateRoleUser(@PathVariable("id") long id, @PathVariable("role") Role role) {
		User usr = userRepository.findById(id).get();
		usr.setRole(role);
		return userRepository.save(usr);
		
	}
	
	@RequestMapping(value="/user/delete/histo/{id}",method=RequestMethod.PUT)
	public User deleteHisto(@PathVariable("id")long id) {
		User usr = userRepository.findById(id).get();
		usr.setScore((long) 0);
		usr.getQuestions().clear();
		return userRepository.save(usr);
	}
	
	@RequestMapping(value="/user/ranking",method=RequestMethod.GET)
	public List<User> getUsersRanking() {
		List<User> users = userRepository.findAll();
		users.sort(Comparator.comparingLong(User :: getScore).reversed());
		return users;
	}
	

	@RequestMapping(value="/user/upload/{id}/score/{score}",method=RequestMethod.PUT)
	public User updateUser(@PathVariable("id") long id, @PathVariable("score") long score) {
		User usr =  userRepository.findById(id).get();
		usr.setScore(usr.getScore()-score);
		return userRepository.save(usr);
		
	}
	
	@RequestMapping(value="/user/update/{id}/image",method=RequestMethod.PUT,headers = "content-type=multipart/form-data")
	public User updateUser(@PathVariable("id") long id,  @RequestParam("file") MultipartFile file) {
		System.out.println(file.getContentType());
		System.out.println(StringUtils.cleanPath(file.getOriginalFilename()));
		try {
			System.out.println(file.getBytes().toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		User usr = userRepository.findById(id).get();
		usr.setFileName(StringUtils.cleanPath(file.getOriginalFilename()));
		usr.setFileType(file.getContentType());
		try {
			usr.setImageBlob(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return userRepository.save(usr);
	}
	
	@RequestMapping(value="/user/reset/{email}",method=RequestMethod.GET)
	public ResponseEntity<String> resetPassword(@PathVariable("email") String email) {
		System.out.println(email);
		User usr = userRepository.findByEmail(email);
		String to = email;
		String subject = "[NePasRépondre] TQG - Renitialisation Mot de Passe";
		String encode = usr.getId()+usr.getFirstName();
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(encode.getBytes());
		    byte[] digest = md.digest();
		    encode = DatatypeConverter
		      .printHexBinary(digest);
		} catch (NoSuchAlgorithmException e) {
			encode = "ERROR";
			e.printStackTrace();
		}
		System.out.println(encode);
		String text = "Bonjour "+usr.getFirstName()+"\nVeuillez cliquez sur ce lien pour rénitialiser votre mot de passe : \nhttp://localhost:4200/Reset/"+usr.getId()+"/"+encode;		
		emailService.sendSimpleMessage(to, subject, text);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	@RequestMapping(value="/user/reset/{id}/{pass}",method=RequestMethod.PUT)
	public User resetPassword(@PathVariable("id") long id, @PathVariable("pass") String pass){
		HttpHeaders headers = new HttpHeaders();
	    headers.add("Content-Type", "application/json;charset=UTF-8");
		User usr = userRepository.findById(id).get();
		usr.setPassword(pass);
		return userRepository.save(usr);
	}
}
