package pl.coderslab.tanitabody.measurement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.coderslab.tanitabody.person.Person;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MeasurementService {
    private final MeasurementRepository measurementRepository;

    public Measurement save(Measurement measurement) {
        return measurementRepository.save(measurement);
    }

    public List<Measurement> selectByCreated(Person person) {
        return measurementRepository.selectByCreated(person);
    }

    public Measurement findById(long id) {
        return measurementRepository.findById(id);
    }
}











