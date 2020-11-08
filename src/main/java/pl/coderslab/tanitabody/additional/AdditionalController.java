package pl.coderslab.tanitabody.additional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/additional")
public class AdditionalController {
    private final AdditionalService additionalService;
}
