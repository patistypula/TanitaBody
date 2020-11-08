package pl.coderslab.tanitabody.additional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.tanitabody.person.Person;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface AdditionalRepository extends JpaRepository<Additional, Long> {
    @Query("select a from Additional a where a.person=?1 order by a.created asc")
    List<Additional> selectByCreated(Person person);

    Additional findById(long id);
}
