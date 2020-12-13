package pl.coderslab.tanitabody.additional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.tanitabody.person.Person;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AdditionalService {
    private final AdditionalRepository additionalRepository;

    public List<Additional> selectByCreated(Person person) {
        return additionalRepository.selectByCreated(person);
    }

    public Additional findById(long id)  {
        return additionalRepository.findById(id);
    }

    public Additional save(Additional additional){
        return additionalRepository.save(additional);
    }
}
