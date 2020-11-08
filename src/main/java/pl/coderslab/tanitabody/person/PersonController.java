package pl.coderslab.tanitabody.person;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.Validator;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
@RequiredArgsConstructor
@RequestMapping("/person")
public class PersonController {
    private final PersonService personService;
    private final Validator validator;

    @RequestMapping("/all")
    public String getAllPerson(Model model) {
        List<Person> persons = personService.selectByLastName();
        model.addAttribute("persons", persons);
        LocalDate now = LocalDate.now();
        List<Integer> ages = new ArrayList<>();
        for (int i = 0; i < persons.size(); i++) {
            ages.add(now.getYear() - persons.get(i).getYearOfBirth().getYear());
            model.addAttribute("ages", ages);
        }
        return "person/all";
    }

    @GetMapping("/add")
    public String addPerson(Model model){
        model.addAttribute("persons", new Person());
        return"person/form";
    }

    @PostMapping("/add")
    public String savePerson(@Valid @ModelAttribute("persons") Person person, BindingResult result) {
        if(result.hasErrors()) {
            return "person/form";
        }
        personService.save(person);
        return "redirect:/person/all";
    }

    @GetMapping("/edit/{id}")
    public String editPerson (@PathVariable long id, Model model) {
        Person person = personService.selectById(id);
        if (person == null) {
            return "person/all";
        }
        model.addAttribute("persons", person);
        return "person/form";
    }

    @PostMapping("/edit/{id}")
    public String saveEditPerson (@PathVariable long id, @Valid @ModelAttribute("persons")
            Person person, BindingResult result) {
        if(id != person.getId()) {
            return "person/form";
        }
        if(result.hasErrors()) {
            return "person/form";
        }
        personService.save(person);
        return "redirect:/person/all";
    }
}
