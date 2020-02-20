package ca.mcgill.ecse321.petshelter.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ca.mcgill.ecse321.petshelter.model.Pet;

/**
 * @author louis
 *
 */
//REST endpoint specification
@RepositoryRestResource(collectionResourceRel = "pet_data", path = "pet_data")
public interface PetRepository extends CrudRepository<Pet, Long>{

	Pet findPetByName(String name);
	Pet findPetById(long id);
}
