package pl.piomin.services;

import io.quarkiverse.mcp.server.test.McpAssured;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import pl.piomin.services.domain.Accounts;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;

@QuarkusTest
public class AccountToolsTest {

    @Test
    public void testGetAccountsByPersonId() {
        McpAssured.McpSseTestClient client = McpAssured.newConnectedSseClient();
        client.when()
                .toolsCall("getAccountsByPersonId", Map.of("personId", 1),
                        r -> assertFalse(((Accounts) r.structuredContent()).getAccounts().isEmpty()))
                .thenAssertResults();
    }
}
