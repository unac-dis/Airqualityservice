package hello;

import org.springframework.data.repository.PagingAndSortingRepository;

import hello.Sensor;

public interface SensorRepository extends PagingAndSortingRepository<Sensor, Integer> {
	

}
