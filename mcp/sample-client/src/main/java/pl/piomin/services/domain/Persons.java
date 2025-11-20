package pl.piomin.services.domain;

import java.util.List;

public class Persons {

    private List<Person> persons;

    public Persons(List<Person> persons) {
        this.persons = persons;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
}
