package pl.piomin.services.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import pl.piomin.services.domain.Person;

import java.util.List;

@ApplicationScoped
public class PersonRepository implements PanacheRepository<Person> {

    public List<Person> findByName(String name) {
        return find("name", name).list();
    }

    public List<Person> findByAgeGreaterThan(int age) {
        return find("age > ?1", age).list();
    }

    public List<Person> findByNationality(String nationality) {
        return find("nationality", nationality).list();
    }

}
