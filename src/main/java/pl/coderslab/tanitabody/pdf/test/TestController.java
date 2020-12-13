package pl.coderslab.tanitabody.pdf.test;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;


import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;
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

//    public ResponseEntity<InputStreamResource> downloadFile1(
//            @RequestParam(defaultValue = DEFAULT_FILE_NAME) String fileName) throws IOException {
    public String downloadFile1(
            @RequestParam(defaultValue = DEFAULT_FILE_NAME) String fileName) throws IOException {

        MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, fileName);
        System.out.println("fileName: " + fileName);
        System.out.println("mediaType: " + mediaType);

        //File file = new File(DIRECTORY + "/" + fileName);
        //InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return fileName;

//        return ResponseEntity.ok()
//                // Content-Disposition
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
//                // Content-Type
//                .contentType(mediaType)
//                // Contet-Length
//                .contentLength(file.length()) //
//                .body(resource);
    }

    @RequestMapping(value = "/pdfreport", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> savePDFfile(){
        LocalDateTime data = LocalDateTime.now();
        String fileName = "inline; filename=measurement_"+data.toString()+".pdf";
        ByteArrayInputStream pdf = Test.createByteStream();
        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");
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

//        MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, fileName);
//        System.out.println("fileName: " + fileName);
//        System.out.println("mediaType: " + mediaType);
//

//
//        // Content-Type
//        // application/pdf
//        resonse.setContentType(mediaType.getType());

        // Content-Disposition
//        resonse.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName());
        System.out.println(resonse.getHeaderNames());
        // Content-Length
//        resonse.setContentLength((int) file.length());
//
//        BufferedInputStream inStream = new BufferedInputStream(new FileInputStream(file));
//        BufferedOutputStream outStream = new BufferedOutputStream(resonse.getOutputStream());
//
//        byte[] buffer = new byte[1024];
//        int bytesRead = 0;
//        while ((bytesRead = inStream.read(buffer)) != -1) {
//            outStream.write(buffer, 0, bytesRead);
//        }
//        outStream.flush();
//        inStream.close();
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
