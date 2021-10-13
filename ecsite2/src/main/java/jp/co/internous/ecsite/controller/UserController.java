package jp.co.internous.ecsite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import jp.co.internous.ecsite.model.dao.UserRepository;
import jp.co.internous.ecsite.model.entity.User;
import jp.co.internous.ecsite.model.form.UserForm;

@Controller
@RequestMapping("/ecsite/admin")

public class UserController {
	@Autowired
	private UserRepository userRepos;
	
	private Gson gson = new Gson();
	
	
	@RequestMapping("/user")
	public String user() {
		
		return "user";
	}
	
	@RequestMapping("/user/add")
	public String UserRegister(UserForm userForm) {
		
		User user = new User();
		user.setUserName(userForm.getUserName());
		user.setPassword(userForm.getPassword());
		user.setFullName(userForm.getFullName());
		userRepos.saveAndFlush(user);
		
		return "forward:/ecsite/admin/";
		@ResponseBody
		@PostMapping("/check/userName")
		public String checkUserName(@RequestBody UserForm form) {
			String userName = form.getUserName();
			List<User> count = userRepos.countDup(userName);
			
		return gson.toJson();
		}
	}
}
