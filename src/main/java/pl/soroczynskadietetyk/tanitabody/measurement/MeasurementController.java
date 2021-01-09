package pl.soroczynskadietetyk.tanitabody.measurement;

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
        model.addAttribute("person", person);
        return "measurement/all";
    }

    @GetMapping("/add/{id}")
    public String addMeasure(Model model, @PathVariable long id){
        Person person = personService.selectById(id);
        Measurement measurement = new Measurement();
        measurement.setPerson(person);
        LocalDate now = LocalDate.now();
        model.addAttribute("sex", person.getGender());
        model.addAttribute("age", now.getYear()-person.getYearOfBirth().getYear());
        model.addAttribute("height", person.getHeight());
        model.addAttribute("measurements", measurement);
        return"measurement/form";
    }

    @PostMapping("/add/{id}")
    public String saveMeasure(@Valid @ModelAttribute("measurements") Measurement measurement,
                              BindingResult result) {
        if(result.hasErrors()) {
            return "measurement/form";
        }
        LocalDate now = LocalDate.now();
        if(measurement.getCreated() == null){
            measurement.setCreated(now);
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
        if(result.hasErrors()) {
            return "measurement/form";
        }
        measurementService.save(measurement);
        return "redirect:/measurement/history/" +measurement.getPerson().getId();
    }

    @GetMapping("/delete/{id}")
    public String deleteMeasurement (@PathVariable long id, Model model){
        Measurement measurement = measurementService.findById(id);
        if (measurement != null) {
            model.addAttribute("measurements", measurement);
            return "measurement/confirm";
        }
        else {
            return "redirect:/measurement/history/" +measurement.getPerson().getId();
        }
    }

    @PostMapping("/delete")
    public String delete (@RequestParam long id){
        Measurement measurement = measurementService.findById(id);
        if(measurement != null){
            measurementService.delete(id);
            return "redirect:/measurement/history/" +measurement.getPerson().getId();
        }
        else{
            return "redirect:/measurement/history/" +measurement.getPerson().getId();
        }
    }
}
