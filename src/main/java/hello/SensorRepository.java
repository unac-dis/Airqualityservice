package hello;

import org.springframework.data.repository.CrudRepository;

import hello.Sensor;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface SensorRepository extends CrudRepository<Sensor, Integer> {

}
