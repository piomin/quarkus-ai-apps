package pl.piomin.services.tools;

import io.quarkiverse.mcp.server.Prompt;
import io.quarkiverse.mcp.server.PromptArg;
import io.quarkiverse.mcp.server.PromptMessage;
import io.quarkiverse.mcp.server.TextContent;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PersonPrompts {

    @Prompt(description = "Find by nationality.")
    PromptMessage findByNationalityPrompt(@PromptArg(description = "The nationality") String nationality) {
        String text = """
            Find persons with %s nationality.
            Output **only valid JSON**, no explanations, no markdown, no ```json blocks.
            """.formatted(nationality);
        return PromptMessage.withUserRole(new TextContent(text));
    }

}
