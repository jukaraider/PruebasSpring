package com.example.restAndSecurity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	/**
	 * Si no ponemos ningun user/password en applicaction.properties entonces cuando
	 * inicia el server vamos a ver que dice: Using generated security password: ...
	 * Este password va a ser requerido en cada Filter e Interceptor que se generan
	 * por tener Spring Security. Le podemos pegar directamente a localhost:8080 y
	 * vamos a tener una pagina de login que nos la provee Thymeleef, usamos "user"
	 * y "el password que aparece en la consola". Entonces nos redirecciona aca, al
	 * raiz: "/"
	 */
	@GetMapping("/")
	public String getHomePage() {
		return "home";
	}
}