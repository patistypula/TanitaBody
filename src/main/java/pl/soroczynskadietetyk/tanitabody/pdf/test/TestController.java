package pl.soroczynskadietetyk.tanitabody.pdf.test;

import java.io.*;
import java.time.LocalDateTime;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class TestController {
    private static final String DIRECTORY = "D:/PLIKI";
    private static final String DEFAULT_FILE_NAME = "java-tutorial.pdf";

    @Autowired
    private ServletContext servletContext;

    @RequestMapping("/download1")
    @ResponseBody
    public String downloadFile1(
            @RequestParam(defaultValue = DEFAULT_FILE_NAME) String fileName) throws IOException {

        MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, fileName);
        System.out.println("fileName: " + fileName);
        System.out.println("mediaType: " + mediaType);
        return fileName;
    }

    @RequestMapping(value = "/pdfreport", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> savePDFfile(){
        LocalDateTime data = LocalDateTime.now();
        String fileName = "inline; filename=measurement_"+data.toString()+".pdf";
        ByteArrayInputStream pdf = Test.createByteStream();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", fileName);
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdf));
    }

    @GetMapping("/download3")
    public void downloadFile3(HttpServletResponse resonse,
                              @RequestParam(defaultValue = DEFAULT_FILE_NAME) String fileName) throws IOException {
        System.out.println(resonse.getHeaderNames());
    }

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String uploadViev(){
        return "test";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String test(@RequestParam("name") String name, @RequestParam("file") MultipartFile file){
        return file.toString();
    }
}
