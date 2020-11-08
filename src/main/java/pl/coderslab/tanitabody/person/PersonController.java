package pl.coderslab.tanitabody.person;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/person")
public class PersonController {
    private final PersonService personService;
}
