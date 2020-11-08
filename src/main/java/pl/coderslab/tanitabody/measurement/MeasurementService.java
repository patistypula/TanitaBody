package pl.coderslab.tanitabody.measurement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
}
