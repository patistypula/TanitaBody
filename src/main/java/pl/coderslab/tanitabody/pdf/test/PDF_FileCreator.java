package pl.coderslab.tanitabody.pdf.test;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.SneakyThrows;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

public class PDF_FileCreator {


    public static ByteArrayInputStream createByteStream (PdfData pdfData){
        //String adressFile = "src/main/java/pl/coderslab/tanitabody/pdf/test/test.pdf";

//        PdfWriter writer = new PdfWriter();

        PdfDocument pdf = new PdfDocument();
        pdf.addTitle("Tabela pomiarów");
        Document pdfDoc = new Document();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(pdfDoc, out);
            pdfDoc.open();
            pdfDoc.setPageSize(PageSize.A4.rotate());
            pdfDoc.setMargins(20, 20, 10, 20);
            pdfDoc.newPage();
            //pdfDoc.newPage();
            Font f1 = new Font(Font.FontFamily.COURIER, 8);
            LocalDateTime data = LocalDateTime.now();
            int year = data.getYear();
            int day  = data.getDayOfMonth();
            int monthValue = data.getMonthValue();
            String dataString = day+"."+monthValue+"."+year;
            String  patient = pdfData.getFirstName()+" "+pdfData.getLastName();
            System.out.println(patient);
            Paragraph paragraph1 = new Paragraph();
            Anchor dietician = new Anchor();
            dietician.setFont(f1);
            dietician.add("Utworzono: "+dataString+"     Pacjent: "+patient+"     d.soroczynskadietetyk@gmail.com");
            dietician.setReference("mailto:\"+email+\"?subject=ReferenceNumber:1234");
            paragraph1.add(dietician);
            paragraph1.setAlignment(Element.ALIGN_RIGHT);
            Paragraph paragraph2 = new Paragraph();
            float[] columnsWidths = new float[13];
            Arrays.fill(columnsWidths, 500f);
            columnsWidths[0] = 400;
            PdfPTable table = new PdfPTable(13);
            table.setWidthPercentage(100);
            //table.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.setWidths(columnsWidths);
            table.setSpacingBefore(10);
            Font f = new Font(Font.FontFamily.TIMES_ROMAN, 7);
            //row1
            //List<PdfPCell> row1 = new ArrayList<>();
            for (int i = 0; i < 13; i++) {
                PdfPCell cell1 = new PdfPCell();
                cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
                if (i >= 2) {
                    Phrase p = new Phrase();
                    p.setFont(f);
                    p.add("Data/ Wyniki");
                    cell1.setPhrase(p);
                }
                table.addCell(cell1);
            }
            //row2
            //List<PdfPCell> row2 = new ArrayList<>();
            PdfPCell cell2 = new PdfPCell();
            cell2.setPhrase(new Phrase(""));
            //table.addCell(cell2);
            //PdfPCell cell3 = new PdfPCell();
            cell2.setRowspan(2);
            table.addCell(cell2);
            for (int i = 0; i < 12; i++) {
                //table.addCell(" ");
                PdfPCell cell = new PdfPCell();
                String text = " ";
                if(i>=1){
                    LocalDate date = pdfData.getMeasurements().get(i-1).getCreated();
                    if(date!= null){
                        text = date.toString();
                    }
                }
                Phrase p = new Phrase();
                p.setFont(f1);
                p.add(text);
                cell.setPhrase(p);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }
            //row3
            for (int i = 0; i < 12; i++) {
                PdfPCell cell = new PdfPCell();
                Phrase p = new Phrase();
                p.setFont(f1);
                p.add(" ");
                cell.setPhrase(p);
                table.addCell(cell);
            }
            //row4
            table.addCell(createImageCell("src/main/resources/static/pdf/images/body_fat_percentage.jpg"));
            table.addCell(createTextCell("Procentowa zawartość tkanki tłuszczowej  w organiźmie", 6));
            for (int i = 0; i < 11; i++) {
                PdfPCell cell = new PdfPCell();
                Double value = pdfData.getMeasurements().get(i).getBodyFatPercentage();
                String text;
                if(value == null){
                    text = "";
                }else
                {
                    text = String.valueOf(value);
                }
                Phrase p = new Phrase();
                p.setFont(f1);
                p.add(text);
                cell.setPhrase(p);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }
            //row5
            table.addCell(createImageCell("src/main/resources/static/pdf/images/body_weight.jpg"));
            table.addCell(createTextCell("Masa ciała", 6));
            for (int i = 0; i < 11; i++) {
                PdfPCell cell = new PdfPCell();
                Double value = pdfData.getMeasurements().get(i).getBodyWeight();
                String text;
                if(value == null){
                    text = "";
                }else
                {
                    text = String.valueOf(value);
                }
                Phrase p = new Phrase();
                p.setFont(f1);
                p.add(text);
                cell.setPhrase(p);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }
            //row6
            table.addCell(createImageCell("src/main/resources/static/pdf/images/body_water_percentage.jpg"));
            table.addCell(createTextCell("Procentowa zawartość wody w organiźmie", 6));
            for (int i = 0; i < 11; i++) {
                PdfPCell cell = new PdfPCell();
                Double value = pdfData.getMeasurements().get(i).getBodyWaterPercentage();
                String text;
                if(value == null){
                    text = "";
                }else
                {
                    text = String.valueOf(value);
                }
                Phrase p = new Phrase();
                p.setFont(f1);
                p.add(text);
                cell.setPhrase(p);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }
            //row7
            table.addCell(createImageCell("src/main/resources/static/pdf/images/vicera_fat_rating.jpg"));
            table.addCell(createTextCell("Poziom tłuszczu wisceralnego", 6));
            for (int i = 0; i < 11; i++) {
                PdfPCell cell = new PdfPCell();
                Double value = pdfData.getMeasurements().get(i).getVisceralFat();
                String text;
                if(value == null){
                    text = "";
                }else
                {
                    text = String.valueOf(value);
                }
                Phrase p = new Phrase();
                p.setFont(f1);
                p.add(text);
                cell.setPhrase(p);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }
            //row8
            table.addCell(createImageCell("src/main/resources/static/pdf/images/muscle_mass.jpg"));
            table.addCell(createTextCell("Masa mięśniowa", 6));
            for (int i = 0; i < 11; i++) {
                PdfPCell cell = new PdfPCell();
                Double value = pdfData.getMeasurements().get(i).getMuscleMass();
                String text;
                if(value == null){
                    text = "";
                }else
                {
                    text = String.valueOf(value);
                }
                Phrase p = new Phrase();
                p.setFont(f1);
                p.add(text);
                cell.setPhrase(p);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }
            //row8
            table.addCell(createImageCell("src/main/resources/static/pdf/images/physique_rating.jpg"));
            table.addCell(createTextCell("Wskaźnik budowy ciała", 6));
            for (int i = 0; i < 11; i++) {
                PdfPCell cell = new PdfPCell();
                Integer value = pdfData.getMeasurements().get(i).getBodyBuildingIndex();
                String text =" ";
                if(value != null){
                    text = String.valueOf(value);
                }
                Phrase p = new Phrase();
                p.setFont(f1);
                p.add(text);
                cell.setPhrase(p);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }
            //row9
            table.addCell(createImageCell("src/main/resources/static/pdf/images/bone_mass_ranges.jpg"));
            table.addCell(createTextCell("Poziom mineralny kości (wapnia i innych minerałów)", 6));
            for (int i = 0; i < 11; i++) {
                PdfPCell cell = new PdfPCell();
                Double value = pdfData.getMeasurements().get(i).getBoneMass();
                String text;
                if(value == null){
                    text = "";
                }else
                {
                    text = String.valueOf(value);
                }
                Phrase p = new Phrase();
                p.setFont(f1);
                p.add(text);
                cell.setPhrase(p);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }
            //row10
            table.addCell(createImageCell("src/main/resources/static/pdf/images/bmi.jpg"));
            table.addCell(createTextCell("BMI\n" +
                    "Wiek  metaboliczny \n" +
                    "PPM", 6));
            for (int i = 0; i < 11; i++) {
                PdfPCell cell = new PdfPCell();
                Double value = pdfData.getMeasurements().get(i).getBMI();
                String text;
                if(value == null){
                    text = "";
                }else
                {
                    text = String.valueOf(value);
                }
                Integer value2 = pdfData.getMeasurements().get(i).getMetabolicAge();
                if(value2 == null){
                    text += "\n";
                }else{
                    text += "\n";
                    text += String.valueOf(value2);
                }
                Phrase p = new Phrase();
                p.setFont(f1);
                p.add(text);
                cell.setPhrase(p);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }

            paragraph2.add(table);
            //pdfDoc.setMargins(36, 36, 18, 18);
            pdfDoc.add(paragraph1);
            pdfDoc.add(paragraph2);
            try {
                Image image = Image.getInstance("src/main/resources/static/pdf/images/explanations.jpg");
                image.scaleToFit(1200,150);
                pdfDoc.add(image);
                //pdfDoc.add(Image.getInstance("src/main/resources/static/pdf/images/explanations.jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            pdfDoc.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return new ByteArrayInputStream(out.toByteArray());
    }


    public static PdfPTable createTable(int columns, int rows){
        PdfPTable table = new PdfPTable(columns);
        String [] columnName = {"L.P.", "Kolumna1", "Kolumna2"};
        Arrays.stream(columnName)
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
        for(int i=1;   i<rows+1;   i++){
            String text = "wiersz "+i;
            table.addCell(text);
        }
        return table;
    }

    public static Chunk addChunk(String text){
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        Chunk chunk = new Chunk(text, font);
        return chunk;
    }

    @SneakyThrows
    public static PdfPCell createImageCell(String path){
        PdfPCell cellImage = new PdfPCell();
        cellImage.setImage(Image.getInstance(path));
        cellImage.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellImage.setVerticalAlignment(Element.ALIGN_MIDDLE);
        return cellImage;
    }

    public static PdfPCell createTextCell(String text, int  fontSize){
        //PdfPCell cellText = new PdfPCell();
        Font font = new Font(Font.FontFamily.COURIER, fontSize, Font.ITALIC);
        PdfPCell cellText = new PdfPCell(new Phrase(text, font));
        return cellText;
    }


}

