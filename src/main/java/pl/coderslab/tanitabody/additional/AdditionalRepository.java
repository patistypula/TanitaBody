package pl.coderslab.tanitabody.additional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface AdditionalRepository extends JpaRepository<Additional, Long> {
}
