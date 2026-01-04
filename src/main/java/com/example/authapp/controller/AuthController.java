package com.example.authapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpSession;
import com.example.authapp.model.User;
import com.example.authapp.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Controller
@RequestMapping("/api")
public class AuthController {
	@Autowired
	UserService userService;
	
	private String encryptPassword(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}
	
	@GetMapping("/login")
	public String showLogin(Model model) {
	    model.addAttribute("user", new User());
	    return "screen/login";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam String email, @RequestParam String pass, Model model, HttpSession session) {
		// Validate input
		if (email == null || email.trim().isEmpty() || pass == null || pass.trim().isEmpty()) {
			model.addAttribute("error", "メールアドレスとパスワードは必須です");
			return "screen/login";
		}
		
		// Check user credentials
		User user = userService.findByEmail(email);
		if (user != null) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			if (encoder.matches(pass, user.getPass())) {
				session.setAttribute("user", user);
				return "redirect:/api/home";
			}
		}
		
		model.addAttribute("error", "メールアドレスまたはパスワードが正しくありません");
		return "screen/login";
	}
	 
	@GetMapping("/register")
	public String registerForm(Model model) {
	    model.addAttribute("user", new User());
	    return "screen/register";
	}
	
	@PostMapping("/register")
	public String register(@ModelAttribute User user, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "screen/register";
		}
		
		// Encrypt password before saving
		user.setPass(encryptPassword(user.getPass()));
		User reg = userService.register(user);
		if (reg != null) {
			redirectAttributes.addFlashAttribute("success", "登録が完了しました！ログインしてください。");
			return "redirect:/api/login";
		} else {
			model.addAttribute("error", "登録に失敗しました。メールアドレスが既に存在する可能性があります。");
			return "screen/register";
		}
	}
	
	@GetMapping("/home")
	public String home(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/api/login";
		}
		model.addAttribute("user", user);
		return "screen/home";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/api/login";
	}
}
