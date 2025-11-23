package pl.piomin.services.tools;

import io.quarkiverse.mcp.server.Tool;
import io.quarkiverse.mcp.server.ToolArg;
import jakarta.enterprise.context.ApplicationScoped;
import pl.piomin.services.domain.Accounts;
import pl.piomin.services.repository.AccountRepository;

@ApplicationScoped
public class AccountTools {

    AccountRepository accountRepository;

    public AccountTools(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Tool(description = "Find all accounts by person ID")
    public Accounts getAccountsByPersonId(
            @ToolArg(name = "personId", description = "Person ID") Long personId) {
        return new Accounts(accountRepository.findByPersonId(personId));
    }

}
