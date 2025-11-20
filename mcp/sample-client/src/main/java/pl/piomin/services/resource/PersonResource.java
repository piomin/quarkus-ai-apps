package pl.piomin.services.resource;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import pl.piomin.services.ai.PersonService;
import pl.piomin.services.domain.Person;
import pl.piomin.services.domain.Persons;

import java.util.List;

@Path("/persons")
public class PersonResource {

    private final PersonService personService;

    public PersonResource(PersonService personService) {
        this.personService = personService;
    }

    @POST
    @Path("/nationality/{nationality}")
    public List<Person> findByNationality(String nationality) {
        Persons p = personService.findByNationality(nationality);
        return p.getPersons();
    }

    @POST
    @Path("/count-by-nationality/{nationality}")
    public int countByNationality(String nationality) {
        return personService.countByNationality(nationality);
    }
}
