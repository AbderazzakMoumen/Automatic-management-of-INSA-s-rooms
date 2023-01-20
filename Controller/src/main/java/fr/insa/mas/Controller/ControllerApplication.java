package fr.insa.mas.Controller;


import java.time.LocalTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@SpringBootApplication
@RequestMapping("/Controller/")
public class ControllerApplication {
	
	private final String MovDetectUrl = "http://localhost:8082/MovementDetection/";
	private final String LightControlUrl = "http://localhost:8081/LightControl/";
	private final String AlarmControlUrl = "http://localhost:8083/AlarmControl/";
	
	boolean AutoLightControl = false;
	boolean AutoAlarmControl = false;
	
	//Automatic Light control with movement detection
	
	@GetMapping("isAutoLightControlActivated/")
	private final boolean isAutoLightControl() {
		return AutoLightControl;
	}
	@GetMapping("setAutoLightControl/")
	private final void setAutoLightControl(boolean autoLightControl) {
		AutoLightControl = autoLightControl;
	}

	
	public void AutoLightControl() {
		String url_getDetection = MovDetectUrl + "getDetection/";
		String url_setLight = LightControlUrl + "setON/?oN=true";
		String url_unsetLight = LightControlUrl + "setON/?oN=false";
		RestTemplate restTemplate = new RestTemplate();
		
		
		//return restTemplate.getForObject(test, String.class);
		
		
		if (restTemplate.getForObject(url_getDetection,boolean.class)) {
			restTemplate.getForObject(url_setLight,boolean.class);			
		}
		else {
			restTemplate.getForObject(url_unsetLight,boolean.class);
		}
	}
	
	
	//Automatic alarm control with movement detection
	
	@GetMapping("isAutoAlarmControlActivated/")
	private final boolean isAutoAlarmControl() {
		return AutoAlarmControl;
	}
	@GetMapping("setAutoAlarmControl/")
	private final void setAutoAlarmControl(boolean autoAlarmControl) {
		AutoAlarmControl = autoAlarmControl;
	}
	
	// Know if a room is used or not with movement detection
	
	@GetMapping("getRoomUsed/")
	public String RoomUsed() {
		String url_getDetection = MovDetectUrl + "getDetection/";
		RestTemplate restTemplate = new RestTemplate();
		
		boolean room_used = restTemplate.getForObject(url_getDetection,boolean.class);
		if (room_used) {
			return ("Room is used");
		}else {	
			return ("Room is not used");
		}
	}
	
    public void AutoAlarmControl() {
		
	  String url_getDetection = MovDetectUrl + "getDetection/";
	  String url_setAlarm = AlarmControlUrl + "/setStatus/?oN=true";	
	  RestTemplate restTemplate = new RestTemplate();
      LocalTime currentTime = LocalTime.now();
      
      if ( currentTime.isAfter(LocalTime.of(22, 0)) || currentTime.isBefore(LocalTime.of(6, 0))) {
    	  if (restTemplate.getForObject(url_getDetection,boolean.class)) {
    		  restTemplate.getForObject(url_setAlarm,boolean.class);	
    	  }
      }
    }
	
	public boolean getMovementDetection() {
		String url_getDetection = MovDetectUrl + "getDetection/";
		RestTemplate restTemplate = new RestTemplate();
		
		return (restTemplate.getForObject(url_getDetection, boolean.class));
	}
	
	@GetMapping("runAuto")
	public int run() throws Exception {
		
		if (AutoLightControl) {
			AutoLightControl();
		}
		
		if (AutoAlarmControl) {
			AutoAlarmControl();
		}

		// Number of times movement was detected
		if (getMovementDetection()) {
			RestTemplate restTemplate = new RestTemplate();
			int nbDetection;
			nbDetection = restTemplate.getForObject(MovDetectUrl +"getNbDetection/",int.class);
			restTemplate.getForObject(MovDetectUrl +"setNbDetection/?detection="+String.valueOf(nbDetection+1),void.class);
		}
			
		return 0;
			
	}
	
	

	 
	public static void main(String[] args) {
		SpringApplication.run(ControllerApplication.class, args);
	}

}
