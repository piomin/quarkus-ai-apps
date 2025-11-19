package pl.piomin.services.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import pl.piomin.services.domain.Account;

import java.util.List;

@ApplicationScoped
public class AccountRepository implements PanacheRepository<Account> {

    public List<Account> findByPersonId(Long personId) {
        return find("personId", personId).list();
    }

}
