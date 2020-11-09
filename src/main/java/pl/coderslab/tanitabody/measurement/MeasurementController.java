package pl.coderslab.tanitabody.measurement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.tanitabody.person.Person;
import pl.coderslab.tanitabody.person.PersonService;

import javax.validation.Valid;
import javax.validation.Validator;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/measurement")
public class MeasurementController {
    private final MeasurementService measurementService;
    private final PersonService personService;
    private final Validator validator;

    @ModelAttribute("persons")
    public List<Person> getAllUsers() {
        return personService.selectByLastName();
    }

    @RequestMapping("/history/{id}")   // tu jest id osoby
    public String getAll(Model model, @PathVariable long id) {
        Person person = personService.selectById(id);
        Measurement measurement = new Measurement();
        measurement.getPerson();
        model.addAttribute("measurements", measurementService.selectByCreated(person));
        return "measurement/all";
    }

    @GetMapping("/add/{id}")
    public String addMeasure(Model model, @PathVariable long id){
        Person person = personService.selectById(id);
        Measurement measurement = new Measurement();
        measurement.setPerson(person);
        model.addAttribute("measurements", measurement);
        return"measurement/form";
    }

    @PostMapping("/add/{id}")
    public String saveMeasure(@Valid @ModelAttribute("measurements") Measurement measurement,
                              BindingResult result) {
        if(result.hasErrors()) {
            return "measurement/form";
        }
        measurementService.save(measurement);
        return "redirect:/person/all";
    }

    @GetMapping("/edit/{id}")  // tu jest id pomiaru
    public String editMeasure(@PathVariable long id, Model model) {
        Measurement measurement = measurementService.findById(id);
        model.addAttribute("measurements", measurement);
        return "measurement/form";
    }

    @PostMapping("/edit/{id}")   // tu jest id pomiaru
    public String saveEditedMeasure(@PathVariable long id, @Valid @ModelAttribute ("measurements")
            Measurement measurement, BindingResult result) {
//        if(result.hasErrors()) {
//            return "measurement/form";
//        }
        measurementService.save(measurement);
        return "redirect:/measurement/history/" +measurement.getPerson().getId();
    }
}
