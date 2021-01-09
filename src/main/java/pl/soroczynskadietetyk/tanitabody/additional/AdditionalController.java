package pl.soroczynskadietetyk.tanitabody.additional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.soroczynskadietetyk.tanitabody.person.Person;
import pl.soroczynskadietetyk.tanitabody.person.PersonService;

import javax.validation.Valid;
import javax.validation.Validator;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/additional")
public class AdditionalController {
    private final AdditionalService additionalService;
    private final PersonService personService;
    private final Validator validator;

    @ModelAttribute("persons")
    public List<Person> getAllUsers() {
        return personService.selectByLastName();
    }

    @RequestMapping("/history/{id}")  // tu jest od uzytkownika
    public String getAll(Model model, @PathVariable long id) {
        Person person = personService.selectById(id);
        Additional additional = new Additional();
        additional.getPerson();
        model.addAttribute("additionals", additionalService.selectByCreated(person));
        model.addAttribute("person", person);
        return "additional/all";
    }

    @GetMapping("/add/{id}")
    public String addAdditional(Model model, @PathVariable long id) {
        Person person = personService.selectById(id);
        Additional additional = new Additional();
        additional.setPerson(person);
        model.addAttribute("additionals", additional);
        return "additional/form";
    }

    @PostMapping("/add/{id}")
    public String saveAdditional(@Valid @ModelAttribute ("additionals") Additional additional,
                                 BindingResult result) {
        if(result.hasErrors()) {
            return "additional/form";
        }
        LocalDate now = LocalDate.now();
        if(additional.getCreated() == null){
            additional.setCreated(now);
        }
        additionalService.save(additional);
        return "redirect:/person/all";
    }

    @GetMapping("/edit/{id}")  // tu jest id pomiaru
    public String editAdditio(@PathVariable long id, Model model) {
        Additional additional = additionalService.findById(id);
        model.addAttribute("additionals", additional);
        return "additional/form";
    }

    @PostMapping("/edit/{id}")   // tu jest id pomiaru
    public String saveEditedAddition(@PathVariable long id, @Valid @ModelAttribute ("additionals")
            Additional additional, BindingResult result) {
        if(result.hasErrors()) {
            return "additional/form";
        }
        additionalService.save(additional);
        return "redirect:/additional/history/" + additional.getPerson().getId();
    }

    @GetMapping("/delete/{id}")
    public String deleteMeasurement (@PathVariable long id, Model model){
        Additional additional = additionalService.findById(id);
        if (additional != null) {
            model.addAttribute("additionals", additional);
            return "additional/confirm";
        }
        else {
            return "redirect:/additional/history/" +additional.getPerson().getId();
        }
    }

    @PostMapping("/delete")
    public String delete (@RequestParam long id){
        Additional additional = additionalService.findById(id);
        if(additional != null){
            additionalService.delete(id);
            return "redirect:/additional/history/" +additional.getPerson().getId();
        }
        else{
            return "redirect:/additional/history/" +additional.getPerson().getId();
        }
    }
}
