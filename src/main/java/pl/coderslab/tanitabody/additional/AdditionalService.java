package pl.coderslab.tanitabody.additional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AdditionalService {
    private final AdditionalRepository additionalRepository;
}
