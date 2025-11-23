package pl.piomin.services.domain;

import java.util.List;

public class Accounts {

    private List<Account> accounts;

    public Accounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
