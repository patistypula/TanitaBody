package pl.soroczynskadietetyk.tanitabody.person;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonConverter implements Converter<String, Person> {
    private PersonService personService;

    @Override
    public Person convert(String source) {
        Person group = personService.selectById(Long.parseLong(source));
        return group;
    }
}
