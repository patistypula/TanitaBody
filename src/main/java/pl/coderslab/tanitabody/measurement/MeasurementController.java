package pl.coderslab.tanitabody.measurement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/measurement")
public class MeasurementController {
    private final MeasurementService measurementService;
}
