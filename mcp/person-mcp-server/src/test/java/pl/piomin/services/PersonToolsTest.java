package pl.piomin.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkiverse.mcp.server.test.McpAssured;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import pl.piomin.services.domain.Person;
import pl.piomin.services.domain.Persons;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class PersonToolsTest {

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testGetPersonsByNationality() {
        McpAssured.McpSseTestClient client = McpAssured.newConnectedSseClient();
        client.when()
                .toolsCall("getPersonsByNationality", Map.of("nationality", "Denmark"),
                        r -> {
                            try {
                                Persons p = mapper.readValue(r.content().getFirst().asText().text(), Persons.class);
                                assertFalse(p.getPersons().isEmpty());
                            } catch (JsonProcessingException e) {
                                throw new RuntimeException(e);
                            }
                        })
                .thenAssertResults();
    }

    @Test
    public void testGetPersonById() {
        McpAssured.McpSseTestClient client = McpAssured.newConnectedSseClient();
        client.when()
                .toolsCall("getPersonById", Map.of("id", 10),
                        r -> {
                            try {
                                Person p = mapper.readValue(r.content().getFirst().asText().text(), Person.class);
                                assertNotNull(p);
                                assertNotNull(p.id);
                            } catch (JsonProcessingException e) {
                                throw new RuntimeException(e);
                            }
                        })
                .thenAssertResults();
    }
}
