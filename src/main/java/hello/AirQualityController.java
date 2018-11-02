package hello;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hello.Sensor;
import hello.SensorRepository;

@Controller
@RequestMapping(path = "/info")
public class AirQualityController {
	
	@Autowired 			
	private SensorRepository sensorRepository;

	@GetMapping(path = "/save/{idSensor}/{humedad}/{temperatura}/{co2}") 
	public ResponseEntity<String> addNewSensor(@PathVariable("idSensor") Integer idSensor, @PathVariable("humedad") Double humedad, @PathVariable("temperatura") Double temperatura, @PathVariable("co2") Double co2) {
		Sensor s = new Sensor();
		s.setFecha(LocalDateTime.now());
		s.setCo2(co2);
		s.setHumedad(humedad);
		s.setIdSensor(idSensor);
		s.setTemperatura(temperatura);
		sensorRepository.save(s);
		return ResponseEntity.ok("Saved");
	}

	@GetMapping(path = "/save2") 
	public ResponseEntity<String> addNewSensor2(@RequestParam Integer idSensor, @RequestParam Double humedad, @RequestParam Double temperatura, @RequestParam Double co2) {
		Sensor s = new Sensor();
		s.setFecha(LocalDateTime.now());
		s.setCo2(co2);
		s.setHumedad(humedad);
		s.setIdSensor(idSensor);
		s.setTemperatura(temperatura);
		sensorRepository.save(s);
		return ResponseEntity.ok("Saved2");
	}

	@GetMapping(path = "/see")
	public @ResponseBody Iterable<Sensor> getAllSensors() {
		return sensorRepository.findAll();
	}
	
	@GetMapping(path = "/status")
	public @ResponseBody ResponseEntity<AirStatus> getAirStatus() {
		Pageable pageable = new PageRequest(0, 10, Sort.Direction.DESC, "id");
		Page<Sensor> bottomPage = sensorRepository.findAll(pageable);
		List<Sensor> list = bottomPage.getContent();	
		Double humedad = list.stream().mapToDouble(Sensor::getHumedad).average().orElse(Double.NaN);
		Double temperatura = list.stream().mapToDouble(Sensor::getTemperatura).average().orElse(Double.NaN);
		Double co2 = list.stream().mapToDouble(Sensor::getCo2).average().orElse(Double.NaN);
		return ResponseEntity.ok(new AirStatus(clasifyAirStatus(co2), humedad, temperatura, co2));
	}

	private String clasifyAirStatus(Double co2) {
		if (co2 < 50)
			return "Excelente";
		else if (co2 >= 50 && co2 < 100)
			return "Bueno";
		else if (co2 >= 100 && co2 < 120)
			return "Malo";
		else 
			return "Pesimo";
		
	}
	
}
