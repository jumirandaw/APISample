package br.atitus.edu.apisample.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.atitus.edu.apisample.dtos.SignupDTO;
import br.atitus.edu.apisample.entities.TypeUser;
import br.atitus.edu.apisample.entities.UserEntity;
import br.atitus.edu.apisample.services.UserService;






@RestController
@RequestMapping("/auth")
public class AuthController {
	
	private final UserService userService;
	
	
	public AuthController(UserService userService) {
		super();
		this.userService = userService;
	}


	@PostMapping("/signup")
	public ResponseEntity<UserEntity> postSignup(@Validated @RequestBody SignupDTO signup, BindingResult result) throws Exception{
		// Ajuste de erros
		if(result.hasErrors()) {
			StringBuilder errorMessage = new StringBuilder();
			result.getAllErrors().forEach(error -> errorMessage.append(error.getDefaultMessage()).append("\n"));
			throw new Exception(errorMessage.toString());
		}
		
		// - Converter o DTO em Entity (UserEntity)
		UserEntity newUser = new UserEntity();
		BeanUtils.copyProperties(signup, newUser);
		newUser.setType(TypeUser.Comum);
		
		// - Invocar a camada Service para salvar o novo User
		userService.save(newUser);
		
		
		// - Retornar o novo usuário salvo
		return ResponseEntity.ok(newUser);
		
	}
		
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handlerMethod(Exception ex) {
		String msg = ex.getMessage().replaceAll("\r\n", "");
		return ResponseEntity.badRequest().body(msg);
	
	}

}
