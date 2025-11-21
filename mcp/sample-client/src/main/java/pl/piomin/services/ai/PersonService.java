package pl.piomin.services.ai;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;
import io.quarkiverse.langchain4j.mcp.runtime.McpToolBox;
import jakarta.enterprise.context.ApplicationScoped;
import pl.piomin.services.domain.Person;
import pl.piomin.services.domain.Persons;

import java.util.List;

@ApplicationScoped
@RegisterAiService
public interface PersonService {

    @SystemMessage("""
        You are a helpful assistant that generates realistic person data.
        Always respond with valid JSON format.
        """)
    @UserMessage("""
        Find persons with {nationality} nationality.
        Output **only valid JSON**, no explanations, no markdown, no ```json blocks.
        """)
    @McpToolBox("person-service")
    Persons findByNationality(String nationality);

    @SystemMessage("""
        You are a helpful assistant that generates realistic person data.
        Always respond with valid JSON format.
        """)
    @UserMessage("How many persons come from {nationality} ?")
    @McpToolBox("person-service")
    int countByNationality(String nationality);

    @SystemMessage("""
        You are a helpful assistant that generates realistic person data.
        Always respond with valid JSON format.
        """)
    @McpToolBox("person-service")
    Persons findByNationalityWithPrompt(@UserMessage String userMessage, String nationality);

}
