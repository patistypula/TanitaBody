package pl.coderslab.tanitabody.measurement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface MeasurementRepository extends JpaRepository<Measurement, Long> {
}
