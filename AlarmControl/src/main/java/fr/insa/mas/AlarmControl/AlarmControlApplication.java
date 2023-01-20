package fr.insa.mas.AlarmControl;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/AlarmControl/")
public class AlarmControlApplication {

  private boolean alarmTriggered = false;

  public static void main(String[] args) {
    SpringApplication.run(AlarmControlApplication.class, args);
  }

  @GetMapping("/status")
  public boolean getAlarmStatus() {
    return alarmTriggered;
  }
  
  @GetMapping("/setStatus/")
  public void setAlarmStatus(boolean oN) {
	  alarmTriggered = oN;
  }
	
}
