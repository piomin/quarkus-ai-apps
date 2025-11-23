package pl.piomin.services;

import io.quarkiverse.mcp.server.test.McpAssured;
import org.junit.jupiter.api.Test;
import pl.piomin.services.domain.Person;
import pl.piomin.services.domain.Persons;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PersonToolsTest {

    @Test
    public void testGetPersonsByNationality() {
        McpAssured.McpSseTestClient client = McpAssured.newConnectedSseClient();
        client.when()
                .toolsCall("getPersonsByNationality", Map.of("nationality", "Denmark"),
                        r -> assertFalse(((Persons) r.structuredContent()).getPersons().isEmpty()))
                .thenAssertResults();
    }

    @Test
    public void testGetPersonById() {
        McpAssured.McpSseTestClient client = McpAssured.newConnectedSseClient();
        client.when()
                .toolsCall("getPersonById", Map.of("id", 10),
                        r -> assertNotNull(((Person) r.structuredContent()).id))
                .thenAssertResults();
    }
}
