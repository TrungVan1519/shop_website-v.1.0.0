package com.dauXanh.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dauXanh.entity.Employee;
import com.dauXanh.service.EmployeeService;

@Controller
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	EmployeeService employeeService;

	/**
	 * login sử dụng Ajax nên hàm PostMapping của login nằm ở AjaxApiController
	 * 
	 * @param req
	 * @param session
	 * @return
	 */
	@GetMapping("/login")
	String login(HttpServletRequest req, HttpSession session) {
		if (session.getAttribute("sessionEmail") != null) {
			String sessionEmail = session.getAttribute("sessionEmail").toString();
			req.setAttribute("sessionEmail", sessionEmail);
		}
		return "auth/login";
	}

	/**
	 * signup không thích sử dụng Ajax mà sử dụng POST của <form> nên hàm
	 * PostMapping của signup sẽ nằm ở đây Do signup co field re_password mà
	 * re_password lại ko nằm trong Entity Employee nên ko sử dụng
	 * được @ModelAttribute mà phải sử dụng @RequestParam thủ công
	 * 
	 * @param email
	 * @param password
	 * @param rePassword
	 * @return
	 */
	@PostMapping("/signup")
	String signup(HttpServletRequest req, @RequestParam(name = "email") String email,
			@RequestParam(name = "password") String password, @RequestParam(name = "re_password") String rePassword) {

		if (password.equals(rePassword)) {
			Employee emp = new Employee();
			emp.setName("unknown");
			emp.setEmail(email);
			emp.setPassword(rePassword);

			boolean accountCreated = employeeService.addEmployee(emp);
			req.setAttribute("accountCreated", accountCreated);
		} else {
			req.setAttribute("signupMessage", "Password and re-password are not matched");
		}

		return "auth/login";
	}
}
