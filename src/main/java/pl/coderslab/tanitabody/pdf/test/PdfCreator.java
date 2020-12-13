package pl.coderslab.tanitabody.pdf.test;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.tanitabody.measurement.Measurement;
import pl.coderslab.tanitabody.measurement.MeasurementService;
import pl.coderslab.tanitabody.person.Person;
import pl.coderslab.tanitabody.person.PersonService;

import java.util.List;

@Controller
@AllArgsConstructor
public class PdfCreator {
    private final MeasurementService measurementService;
    private final PersonService      personService;

    @RequestMapping(value = "/creating-measurement-PDF-raport", method = RequestMethod.GET)
    public String filingPdfDocument(@RequestParam long id, Model model){
        Person person = personService.selectById(id);
        System.out.println("Person: "+person.getFirstName()+" "+person.getLastName());
        List<Measurement> measurements = measurementService.selectByCreated(person);
        System.out.println("Liczba pomiarów: "+measurements.size());
        model.addAttribute("measurements", measurements);
        model.addAttribute("person", person);
        return "pdf/PDF-measurement";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(){
        return "test";
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public String cretLink(@RequestParam long id, Model model){

        String adress = "redirect:/creating-measurement-PDF-raport?id="+id;
        return adress;
//        model.addAttribute("adress", adress);
//        return "test2";
    }
}
