package fr.insa.mas.LightControl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@RequestMapping("/LightControl/")
public class LightControlApplication {

	private boolean ON;
	
	public LightControlApplication() {
		this.ON = false;
	}

	
	
	@GetMapping("isON/")
	public boolean isON() {
		return ON;
	}

	@GetMapping("setON/")
	public void setON(boolean oN) {
		ON = oN;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(LightControlApplication.class, args);
	}

}
