package pl.piomin.services.resource;

import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.mcp.client.McpClient;
import dev.langchain4j.mcp.client.McpGetPromptResult;
import io.quarkiverse.langchain4j.mcp.runtime.McpClientName;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import pl.piomin.services.ai.PersonService;
import pl.piomin.services.domain.Person;
import pl.piomin.services.domain.Persons;

import java.util.List;
import java.util.Map;

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

    @POST
    @Path("/nationality-with-prompt/{nationality}")
    public List<Person> findByNationalityWithPrompt(String nationality) {
        Persons p = personService.findByNationalityWithPrompt(loadPrompt(nationality), nationality);
        return p.getPersons();
    }

    @McpClientName("person-service")
    McpClient mcpClient;

    private String loadPrompt(String nationality) {
        McpGetPromptResult prompt = mcpClient.getPrompt("findByNationalityPrompt", Map.of("nationality", nationality));
        return ((TextContent) prompt.messages().getFirst().content().toContent()).text();
    }
}
