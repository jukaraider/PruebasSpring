package com.loginAndRoles.controllers;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.loginAndRoles.model.User;
import com.loginAndRoles.services.EmailService;
import com.loginAndRoles.services.UserService;

@Controller
public class RegistrationController {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private EmailService emailService;

	@Autowired
	private UserService userService;

	// Esto es muy comun con Thymeleaf, cuando cargamos una vista devolvemos
	// ModelAndView y en este caso le pasamos el objecto User para que luego lo
	// podamos recuperar en el POST
	@GetMapping("/register")
	public ModelAndView showRegistrationPage(ModelAndView mav, User user) {
		mav.addObject("user", user);
		mav.setViewName("register");

		return mav;
	}

	// El @Valid esta validando con lo que pusimos en los atributos del objeto User
	@PostMapping("/register")
	public ModelAndView processRegistrationForm(ModelAndView mav, @Valid User user, BindingResult result,
			HttpServletRequest request) {

		// El @Valid nos dice si tuvo errors a traves del bindingResult
		if (result.hasErrors()) {
			mav.setViewName("register");
		} else if (userService.findByEmail(user.getEmail()) != null) {
			mav.addObject("alreadyRegisteredMessage", "Ya existe este email");
			mav.setViewName("register");

			result.reject("email");
		} else {
			user.setEnabled(false);
			user.setConfirmationToken(UUID.randomUUID().toString());

			userService.saveUser(user);

			String appURL = request.getScheme() + "://" + request.getServerName() + ":8080";
			String message = "Activar el password, Click en " + appURL + "/confirm?token="
					+ user.getConfirmationToken();
			emailService.sendEmail(user.getEmail(), "Set Password", message);
			
			System.out.println("Se envio el email con:" + message);

			mav.addObject("confirmationMessage", "Se envio un email para setear el password a: " + user.getEmail());
			mav.setViewName("register");
		}

		return mav;
	}

	@GetMapping("/confirm")
	public ModelAndView confirmRegistration(ModelAndView mav, @RequestParam String token) {
		User user = userService.findByConfirmationToken(token);

		if (user == null) {
			mav.addObject("invalidToken", "El token es invalido");
		} else {
			mav.addObject("confirmationToken", user.getConfirmationToken());
		}
		mav.setViewName("confirm");

		return mav;
	}

	@PostMapping("/confirm")
	public ModelAndView confirmRegistration(ModelAndView mav, BindingResult result,
			@RequestParam Map<String, String> request, RedirectAttributes redirect) {
		
		User user = userService.findByConfirmationToken(request.get("token"));
		
		user.setPassword(passwordEncoder.encode(request.get("password")));
		user.setEnabled(true);
		
		userService.saveUser(user);
		
		mav.addObject("successMessage", "El Password se guardo correctamente");
		mav.setViewName("confirm");
		
		return mav;
	}
}