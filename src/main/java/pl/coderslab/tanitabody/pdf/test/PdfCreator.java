package pl.coderslab.tanitabody.pdf.test;

import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.tanitabody.measurement.Measurement;
import pl.coderslab.tanitabody.measurement.MeasurementService;
import pl.coderslab.tanitabody.person.Person;
import pl.coderslab.tanitabody.person.PersonService;

import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@AllArgsConstructor
public class PdfCreator {
    private final MeasurementService measurementService;
    private final PersonService      personService;

    @RequestMapping(value = "/creating-measurement-PDF-raport", method = RequestMethod.GET)
    public String filingPdfDocument(@RequestParam long id, Model model){
        Person person = personService.selectById(id);
        List<Measurement> measurements = measurementService.selectByCreated(person);
        model.addAttribute("measurements", measurements);
        model.addAttribute("person", person);
        model.addAttribute("pdfData", new PdfData());
        return "pdf/PDF-measurement";
    }

    @RequestMapping(value = "/creating-measurement-PDF-raport", params = "createPDF", method = RequestMethod.POST,
                    produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> generetPDFfile(@ModelAttribute("pdfData") PdfData pdfData){
        if(pdfData.getMeasurements().size()<11) {
            int sizeList = pdfData.getMeasurements().size();
            for (int i = 0; i < 11 - sizeList; i++) {
                pdfData.getMeasurements().add(new Measurement());
            }
        }
        //System.out.println("Pacjent: "+pdfData.getFirstName()+" "+pdfData.getLastName());
        LocalDateTime data = LocalDateTime.now();
        String fileName = "inline; filename=measurement_"+pdfData.getFirstName()+"_"
                +pdfData.getLastName()+".pdf";
        ByteArrayInputStream pdf = PDF_FileCreator.createByteStream(pdfData);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", fileName);
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdf));
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
