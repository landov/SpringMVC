package course.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import course.dto.UserDTO;

@Controller
@SessionAttributes("user")
public class UserController {
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		
		UserDTO user = new UserDTO();
		user.setUserName("admin");
		model.addAttribute("user", user);
		return "redirect:courses";
	/*	UserDTO user = new UserDTO();
		model.addAttribute("user", user);
		return "login"; */
	}
	
	@RequestMapping (value="/login", method=RequestMethod.POST)
	public String doLogin (@ModelAttribute ("user") UserDTO user, Model model) {
		return "redirect:courses";
	//Hard-coded validation of user name and
	//password to keep this example simple
	//But validation could be done against database or
	//any other means here.
	/*if (user.getUserName().equals("admin") && user.getPassword().equals("admin"))
		return "redirect:courses";
	user.setMessage("Invalid user name or password. Please try again");
	return "login";*/
	}
}
