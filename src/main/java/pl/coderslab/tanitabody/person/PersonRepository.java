package pl.coderslab.tanitabody.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface PersonRepository extends JpaRepository<Person, Long> {
    @Query(value = "select p from Person p order by p.lastName asc")
    List<Person> selectByLastName();

    @Query(value = "Select p from Person p where p.lastName like %?1%")
    List<Person> selectByRegex(String lastName);

    @Query("SELECT p FROM Person p WHERE p.id=?1")
    Person selectById(long id);

    void deleteById(long id);
}









