package pl.piomin.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkiverse.mcp.server.test.McpAssured;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import pl.piomin.services.domain.Accounts;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;

@QuarkusTest
public class AccountToolsTest {

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testGetAccountsByPersonId() {
        McpAssured.McpSseTestClient client = McpAssured.newConnectedSseClient();
        client.when()
                .toolsCall("getAccountsByPersonId", Map.of("personId", 1),
                        r -> {
                            try {
                                Accounts a = mapper.readValue(r.content().getFirst().asText().text(), Accounts.class);
                                assertFalse(a.getAccounts().isEmpty());
                            } catch (JsonProcessingException e) {
                                throw new RuntimeException(e);
                            }
                        })
                .thenAssertResults();
    }
}
