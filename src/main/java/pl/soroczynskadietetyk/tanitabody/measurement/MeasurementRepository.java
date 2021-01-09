package pl.soroczynskadietetyk.tanitabody.measurement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.soroczynskadietetyk.tanitabody.person.Person;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface MeasurementRepository extends JpaRepository<Measurement, Long> {

    @Query("select m from Measurement m where m.person=?1 order by m.created asc")
    List<Measurement> selectByCreated(Person person);

    Measurement findById(long id);

    void deleteById(long id);
}
