### AI Agents Best Practices for Code Generation

This document outlines best practices for generating code within this repository using AI agents.

#### 1. Project Overview
*   **Stack:** Quarkus 3.x with Quarkus MCP Server and Quarkus LangChain4j, built with Maven and Java 21.
*   **Architecture:** Multi-module Maven project demonstrating the Model Context Protocol (MCP) pattern — MCP servers expose data as tools, and an AI client consumes them via SSE transport.
*   **Modules:**
    *   `mcp/person-mcp-server` — MCP server exposing person data (port 8082)
    *   `mcp/account-mcp-server` — MCP server exposing account data (port 8081)
    *   `mcp/sample-client` — Quarkus LangChain4j client that connects to both MCP servers via SSE and exposes REST endpoints
*   **Key patterns used:**
    *   MCP Tools (`@Tool`, `@ToolArg`) — server-side methods exposed to AI clients, in `pl.piomin.services.tools`
    *   MCP Prompts (`@Prompt`, `@PromptArg`) — reusable prompt templates served by MCP servers, in `pl.piomin.services.tools`
    *   AI Services (`@RegisterAiService`) with `@McpToolBox` — client-side declarative LLM integration that uses MCP tools, in `pl.piomin.services.ai`
    *   MCP Client injection (`@McpClientName`) — for direct MCP client access (e.g. loading prompts), in `pl.piomin.services.resource`
    *   REST endpoints via JAX-RS (`@Path`) in `pl.piomin.services.resource`
    *   Hibernate ORM Panache with H2 in-memory database for persistence
    *   Panache repositories in `pl.piomin.services.repository`
    *   Domain classes in `pl.piomin.services.domain`

#### 2. Running the Application
The MCP servers must be started before the client:
1.  Start `person-mcp-server` on port 8082: `mvn quarkus:dev -pl mcp/person-mcp-server`
2.  Start `account-mcp-server` on port 8081: `mvn quarkus:dev -pl mcp/account-mcp-server`
3.  Start `sample-client` on port 8080: `mvn quarkus:dev -pl mcp/sample-client`

The client connects to MCP servers via SSE at `http://localhost:8081/mcp/sse` and `http://localhost:8082/mcp/sse`.

The client requires an OpenAI API key set via the `OPEN_AI_TOKEN` environment variable.

#### 3. Code Generation Guidelines
*   **Be Specific:** Instead of "add a new tool," use "add an MCP tool to `AccountTools` that returns account balance by account ID."
*   **Reference Existing Patterns:** Follow `PersonTools` / `AccountTools` for new MCP server tools, `PersonPrompts` for new MCP prompts, `PersonService` / `AccountService` for new AI service interfaces, and `PersonResource` / `AccountResource` for new REST controllers.
*   **Iterative Development:** For complex features, break down the request into smaller steps (e.g., domain model -> repository -> MCP tool -> AI service -> REST controller).
*   **Request Tests:** Always generate corresponding tests using JUnit 5, `@QuarkusTest`, and `McpAssured` for MCP server modules (see `PersonToolsTest` / `AccountToolsTest` for reference).

#### 4. Code Quality and Style
*   **Maintain Consistency:** Follow existing Quarkus and CDI conventions (constructor injection, `@ApplicationScoped`, JAX-RS annotations).
*   **Java 21:** Use Java 21 features where appropriate (records, text blocks, pattern matching).
*   **Review Generated Code:** Always review AI-generated code for security vulnerabilities, performance issues, and adherence to project-specific rules.

#### 5. Critical Restriction: Target Directory
*   **Do Not Commit `target/`:** Under no circumstances should the `target` directory (or any of its contents) be added or committed to the repository.

#### 6. Build Verification
*   **Compile check:** After applying changes, run `mvn clean compile` to ensure the project builds correctly.
*   **Dev mode:** Use `mvn quarkus:dev -pl mcp/<module>` for live-reload development of a specific module.
*   **Run tests:** Use `mvn test` to verify tests pass. MCP server tests run locally without external dependencies. The `sample-client` module requires the `OPEN_AI_TOKEN` environment variable.
