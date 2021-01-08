package pl.coderslab.tanitabody.pdf.test;

import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.tanitabody.additional.Additional;
import pl.coderslab.tanitabody.additional.AdditionalService;
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
    private final AdditionalService  additionalService;
    private final PersonService      personService;

    @RequestMapping(value = "/creating-measurement-PDF-raport/{id}", method = RequestMethod.GET)
    public String filingPdfDocument(@PathVariable long id, Model model){
        Person person = personService.selectById(id);
        List<Measurement> measurements = measurementService.selectByCreated(person);
        model.addAttribute("measurements", measurements);
        model.addAttribute("person", person);
        model.addAttribute("pdfData", new PdfData());
        return "pdf/PDF-measurement";
    }

    @RequestMapping(value = "/creating-measurement-PDF-raport/{id}", params = "createPDF", method = RequestMethod.POST,
                    produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> generetPDFfile(@ModelAttribute("pdfData") PdfData pdfData,
                                                              @PathVariable long id){
        if(pdfData.getMeasurements().size()<11) {
            int sizeList = pdfData.getMeasurements().size();
            for (int i = 0; i < 11 - sizeList; i++) {
                pdfData.getMeasurements().add(new Measurement());
            }
        }
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

    @RequestMapping(value = "/creating-measurement-PDF-raport/{id}", params = "cancel", method = RequestMethod.POST)
    public String cancelCreatePDF(@PathVariable long id){
        String adress = "redirect:/measurement/history/"+id;
        return adress;
    }


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(){
        return "test";
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public String cretLink(@RequestParam long id, Model model){

        String adress = "redirect:/creating-measurement-PDF-raport?id="+id;
        return adress;
    }

    @RequestMapping(value = "/creating-additional-measurement-PDF-raport/{id}", method = RequestMethod.GET)
    public String filingAdditionalPdfDocument(@PathVariable long id, Model model){
        Person person = personService.selectById(id);
        List<Additional> additionals = additionalService.selectByCreated(person);
        model.addAttribute("measurements", additionals);
        model.addAttribute("person", person);
        model.addAttribute("pdfData", new AdditionalPDFData());
        return "pdf/PDF-additional-measurement";
    }

    @RequestMapping(value = "/creating-additional-measurement-PDF-raport/{id}", params = "createPDF", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> generetAdditionalPDFfile(@ModelAttribute("pdfData") AdditionalPDFData pdfData,
                                                              @PathVariable long id){
        if(pdfData.getMeasurements().size()<11) {
            int sizeList = pdfData.getMeasurements().size();
            for (int i = 0; i < 11 - sizeList; i++) {
                pdfData.getMeasurements().add(new Additional());
            }
        }
        //LocalDateTime data = LocalDateTime.now();
        String fileName = "inline; filename=measurement_"+pdfData.getFirstName()+"_"
                +pdfData.getLastName()+".pdf";
        ByteArrayInputStream pdf = PDF_Additional_FileCreator.createByteStream(pdfData);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", fileName);
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdf));
    }

    @RequestMapping(value = "/creating-additional-measurement-PDF-raport/{id}", params = "cancel", method = RequestMethod.POST)
    public String cancelCreateAdditionalPDF(@PathVariable long id){
        String adress = "redirect:/additional/history/"+id;
        return adress;
    }
}
