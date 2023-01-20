package fr.insa.mas.MovementDetection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@SpringBootApplication
@RequestMapping("/MovementDetection/")
public class MovementDetectionApplication {

	private boolean detection;
	private int nbDetection;
	
	
	//Constructor
	public MovementDetectionApplication() {
		this.detection = false;
		this.nbDetection = 0;
	}
	
	//Methods

	@GetMapping("getDetection/")
	public boolean getDetection() {
		return this.detection;
	}
	@GetMapping("setDetection/")
	public void setDetection(boolean detection) {
		this.detection = detection;
	}
	
	@GetMapping("getNbDetection/")
	public int getNbDetection() {
		return this.nbDetection;
	}
	
	@GetMapping("setNbDetection/")
	public void setNbDetection(int detection) {
		this.nbDetection = detection;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(MovementDetectionApplication.class, args);
	}

}
