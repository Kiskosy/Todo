package kosy.todo.todo.controller;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kosy.todo.todo.domain.Issue;
import kosy.todo.todo.domain.Item;
import kosy.todo.todo.domain.User;
import kosy.todo.todo.repository.UserRepository;
import kosy.todo.todo.service.UserService;
import kosy.todo.todo.validator.UserValidator;
import kosy.todo.todo.repository.IssueRepository;
import kosy.todo.todo.repository.ItemRepository;

@Controller
public class MainController {


	@Autowired
	private UserRepository userRepository;
	@Autowired
	private IssueRepository issueRepository;
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private UserValidator userValidator;

	@GetMapping(path="/add") 
	public @ResponseBody String addNewUser (@RequestParam String name, @RequestParam String email) {

		User n = new User();
		n.setName(name);
		n.setEmail(email);
		userRepository.save(n);
		return "Saved";
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<Issue> getAllUsers() {

		return issueRepository.findAll();
	}

	@GetMapping("/login")
	public String loginForm(Model model){
		model.addAttribute("user", new User());
		return "login";
	}

	@PostMapping(path="/login", params="Login")
	public String loginSubmitLoginBTN(@ModelAttribute User user) {
		System.out.println(user.getName());
		User u = userRepository.findByName(user.getName());
		System.out.println(u.toString());

		return "redirect:todo";
	}

	@PostMapping(path="/login", params="Register")
	public String loginSubmitRegisterBTN() {
		return "redirect:register";
	}

	@GetMapping("/register")
	public String registerForm(Model model){
		model.addAttribute("user", new User());
		return "register";
	}

	@PostMapping("/register")
	public String registerSubmit(@ModelAttribute User user, BindingResult bindingResult) {
		userValidator.validate(user, bindingResult);
		if(bindingResult.hasErrors()){
			return "register";
		}

		//userRepository.save(user);
		userService.save(user);

		return "redirect:login";

	}

	@GetMapping("/newissue")
	public String newissueForm(Model model){
		model.addAttribute("issue", new Issue());
		return "newissue";
	}

	@PostMapping(path="/newissue", params="Create")
	public String newissueSubmit(@ModelAttribute Issue issue) {
		issueRepository.save(issue);
		return "redirect:todo";
	}

	@GetMapping(path="/newitem")
	public String newitemForm(Model model){
		model.addAttribute("item", new Item());
		return "newitem";
	}

	@PostMapping(path="/newitem", params="Create")
	public String newitemSubmit(@ModelAttribute Item item){
		itemRepository.save(item);
		return "redirect:todo";
	}

	@GetMapping("/todo")
	public String todoForm(Model model){
		List<Issue> issues = issueRepository.findAll();
		List<Item> items = itemRepository.findAll();
		model.addAttribute("issues",issues);
		model.addAttribute("items", items);
		return "todo";
	}

	@RequestMapping("/")
	public String viewHome(Model model) {
		
		return "redirect:login";
	}
}