package co.com.crudanimal.jpa;
import co.com.crudanimal.model.animal.Animal;
import co.com.crudanimal.model.animal.gateways.AnimalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@AllArgsConstructor
public class JPAAnimalImp implements AnimalRepository {

    private JPARepositoryAdapter jpaRepositoryAdapter;
    @Override
    public List<Animal> getAll() {
        return jpaRepositoryAdapter.findAll();
    }
    @Override
    public void create(Animal animal) {
        jpaRepositoryAdapter.save(animal);
    }
    @Override
    public Animal read(String name) {return jpaRepositoryAdapter.findById(name);}

    @Override
    public Animal update(String name, Animal animal) throws Exception {
        Animal animalId = jpaRepositoryAdapter.findById(name);

        if (animalId == null) throw new Exception("Animal Not Found : " + name);

        animalId.setGenero(animal.getGenero());
        animalId.setPatas(animal.getPatas());
        animalId.setEspecie(animal.getEspecie());
        animalId.setHabitat(animal.getHabitat());
        animalId.setDomestico(animal.getDomestico());

        jpaRepositoryAdapter.save(animalId);
        return animalId;
    }

    @Override
    public void delete(String name) {
        jpaRepositoryAdapter.deleteById(name);
    }


}
