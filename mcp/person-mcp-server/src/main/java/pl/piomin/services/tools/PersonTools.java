package pl.piomin.services.tools;

import io.quarkiverse.mcp.server.Tool;
import io.quarkiverse.mcp.server.ToolArg;
import jakarta.enterprise.context.ApplicationScoped;
import pl.piomin.services.domain.Person;
import pl.piomin.services.domain.Persons;
import pl.piomin.services.repository.PersonRepository;

import java.util.List;

@ApplicationScoped
public class PersonTools {

    PersonRepository personRepository;

    public PersonTools(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Tool(description = "Find person by ID")
    public Person getPersonById(
            @ToolArg(description = "Person ID") Long id) {
        return personRepository.findById(id);
    }

    @Tool(description = "Find all persons by nationality")
    public Persons getPersonsByNationality(
            @ToolArg(description = "Nationality") String nationality) {
        return new Persons(personRepository.findByNationality(nationality));
    }
}
