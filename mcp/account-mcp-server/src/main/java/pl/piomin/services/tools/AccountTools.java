package pl.piomin.services.tools;

import io.quarkiverse.mcp.server.Tool;
import io.quarkiverse.mcp.server.ToolArg;
import jakarta.enterprise.context.ApplicationScoped;
import pl.piomin.services.domain.Account;
import pl.piomin.services.repository.AccountRepository;

import java.util.List;

@ApplicationScoped
public class AccountTools {

    AccountRepository accountRepository;

    public AccountTools(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Tool(description = "Find all accounts by person ID")
    public List<Account> getAccountsByPersonId(
            @ToolArg(description = "Person ID") Long personId) {
        return accountRepository.findByPersonId(personId);
    }

}
