package pl.soroczynskadietetyk.tanitabody.person;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;

    public Person save(Person person) {
        return personRepository.save(person);
    }

    public List<Person> selectByLastName(){
        return personRepository.selectByLastName();
    }

    public Person selectById(Long id) {
        return  personRepository.selectById(id);
    }

    public List<Person> selectByRegex(String lastName) {
        return personRepository.selectByRegex(lastName);
    }

    public void delete(long id) {
        personRepository.deleteById(id);
    }
}
