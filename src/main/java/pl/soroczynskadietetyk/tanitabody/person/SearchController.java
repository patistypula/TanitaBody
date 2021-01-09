package pl.soroczynskadietetyk.tanitabody.person;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/person")
public class SearchController {
    private final PersonService personService;

    @GetMapping("/search")
    public String searchPerson(Model model, @RequestParam String lastName){
        List<Person> list = personService.selectByRegex(lastName);
        model.addAttribute("persons", list);
        LocalDate now = LocalDate.now();
        List<Integer> ages = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            ages.add(now.getYear() - list.get(i).getYearOfBirth().getYear());
            model.addAttribute("ages", ages);
        }
        return"person/all";
    }
}
