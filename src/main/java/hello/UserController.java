package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import hello.User;
import hello.UserRepository;

@Controller
@RequestMapping(path = "/home")
public class UserController {
	
	@Autowired 
	private UserRepository userRepository;

	public String index() {
		return "Airquality Version 5.6 Ultimate";
	}

	@GetMapping(path = "/add") // Map ONLY GET Requests
	public @ResponseBody String addNewUser(@RequestParam Double peso, @RequestParam String email,
			@RequestParam int numerodehoras, @RequestParam int numerodepasos, @RequestParam double imc,
			@RequestParam Double estatura, @RequestParam int edad) {
		User n = new User();
		n.setEmail(email);
		n.setEstatura(estatura);
		n.setPeso(peso);
		n.setNumerodehoras(numerodehoras);
		n.setNumerodepasos(numerodepasos);
		n.setImc(imc);
		n.setEdad(edad);
		userRepository.save(n);
		return "Saved";
	}

	@GetMapping(path = "/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}
}
