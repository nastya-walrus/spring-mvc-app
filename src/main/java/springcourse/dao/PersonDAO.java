package springcourse.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import springcourse.models.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private List<Person> people;

    {
        people = new ArrayList<Person>();

        people.add(new Person(++PEOPLE_COUNT, "Tom", 20, "tom@mail.ru"));
        people.add(new Person(++PEOPLE_COUNT, "Jack", 23, "jack@mail.ru"));
        people.add(new Person(++PEOPLE_COUNT, "Bob", 33, "bob@mail"));
        people.add(new Person(++PEOPLE_COUNT, "Kate", 22, "kate@mail"));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    public void update(int id, Person updatePerson) {
        Person personToBeUpdated = show(id);

        personToBeUpdated.setName(updatePerson.getName());
        personToBeUpdated.setAge(updatePerson.getAge());
        personToBeUpdated.setEmail(updatePerson.getEmail());
    }

    public void delete(int id) {
        people.removeIf(person -> person.getId() == id);
    }
}
