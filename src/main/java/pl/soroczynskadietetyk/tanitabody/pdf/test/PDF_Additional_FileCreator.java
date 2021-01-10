package pl.soroczynskadietetyk.tanitabody.pdf.test;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import lombok.SneakyThrows;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

public class PDF_Additional_FileCreator {


    public static ByteArrayInputStream createByteStream (AdditionalPDFData pdfData){
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
            Font f1 = new Font(Font.FontFamily.COURIER, 8);
            BaseFont pl = BaseFont.createFont("c:/windows/fonts/arial.ttf",
                    BaseFont.CP1250, BaseFont.EMBEDDED);
            Font pl1 = new Font(pl, 8);
            LocalDateTime data = LocalDateTime.now();
            int year = data.getYear();
            int day  = data.getDayOfMonth();
            int monthValue = data.getMonthValue();
            String dataString = day+"."+monthValue+"."+year;
            String  patient = pdfData.getFirstName()+" "+pdfData.getLastName();
            System.out.println(patient);
            Paragraph paragraph1 = new Paragraph();
            Anchor dietician = new Anchor();
            dietician.setFont(pl1);
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
            table.setWidths(columnsWidths);
            table.setSpacingBefore(10);
            Font f = new Font(Font.FontFamily.TIMES_ROMAN, 7);
            Font ff = new Font(pl, 6);
            //row1
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
            PdfPCell cell2 = new PdfPCell();
            cell2.setPhrase(new Phrase(""));
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
            table.addCell(createImageCell("src/main/resources/static/pdf/images/right_hand.jpg"));
            PdfPCell right_hand_fat = new PdfPCell();
            right_hand_fat.setPhrase(new Phrase("Prawa ręka\n" +
                    "[fat %]", ff));
            right_hand_fat.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(right_hand_fat);
            for (int i = 0; i < 11; i++) {
                PdfPCell cell = new PdfPCell();
                Double value = pdfData.getMeasurements().get(i).getRightArmFat();
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
            table.addCell(createImageCell("src/main/resources/static/pdf/images/right_hand.jpg"));
            PdfPCell right_hand_mus = new PdfPCell();
            right_hand_mus.setPhrase(new Phrase("Prawa ręka\n"+
                            "[kg]", ff));
            right_hand_mus.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(right_hand_mus);
            for (int i = 0; i < 11; i++) {
                PdfPCell cell = new PdfPCell();
                Double value = pdfData.getMeasurements().get(i).getRightArmMuscle();
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
            table.addCell(createImageCell("src/main/resources/static/pdf/images/left_hand.jpg"));
            PdfPCell left_hand_fat = new PdfPCell();
            left_hand_fat.setPhrase(new Phrase("Lewa ręka\n"+
                            "[fat %]", ff));
            left_hand_fat.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(left_hand_fat);
            for (int i = 0; i < 11; i++) {
                PdfPCell cell = new PdfPCell();
                Double value = pdfData.getMeasurements().get(i).getLeftArmFat();
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
            table.addCell(createImageCell("src/main/resources/static/pdf/images/left_hand.jpg"));
            PdfPCell left_hand_mus = new PdfPCell();
            left_hand_mus.setPhrase(new Phrase("Lewa ręka\n"+
                            "[kg]", ff));
            left_hand_mus.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(left_hand_mus);
            for (int i = 0; i < 11; i++) {
                PdfPCell cell = new PdfPCell();
                Double value = pdfData.getMeasurements().get(i).getLeftArmMuscle();
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
            table.addCell(createImageCell("src/main/resources/static/pdf/images/right_leg.jpg"));
            PdfPCell right_leg_fat = new PdfPCell();
            right_leg_fat.setPhrase(new Phrase("Prawa noga\n"+
                            "[fat %]", ff));
            right_leg_fat.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(right_leg_fat);
            for (int i = 0; i < 11; i++) {
                PdfPCell cell = new PdfPCell();
                Double value = pdfData.getMeasurements().get(i).getRightLegFat();
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
            table.addCell(createImageCell("src/main/resources/static/pdf/images/right_leg.jpg"));
            PdfPCell right_leg_mus = new PdfPCell();
            right_leg_mus.setPhrase(new Phrase("Prawa noga\n"+
                            "[kg]", ff));
            right_leg_mus.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(right_leg_mus);
            for (int i = 0; i < 11; i++) {
                PdfPCell cell = new PdfPCell();
                Double value = pdfData.getMeasurements().get(i).getRightLegMuscle();
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
            table.addCell(createImageCell("src/main/resources/static/pdf/images/left_leg.jpg"));
            PdfPCell left_leg_fat = new PdfPCell();
            left_leg_fat.setPhrase(new Phrase("Lewa noga\n"+
                            "[fat %]", ff));
            left_leg_fat.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(left_leg_fat);
            for (int i = 0; i < 11; i++) {
                PdfPCell cell = new PdfPCell();
                Double value = pdfData.getMeasurements().get(i).getLeftLegFat();
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
            table.addCell(createImageCell("src/main/resources/static/pdf/images/left_leg.jpg"));
            PdfPCell left_leg_mus = new PdfPCell();
            left_leg_mus.setPhrase(new Phrase("Lew noga\n" +
                    "[kg]", ff));
            left_leg_mus.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(left_leg_mus);
            for (int i = 0; i < 11; i++) {
                PdfPCell cell = new PdfPCell();
                Double value = pdfData.getMeasurements().get(i).getLeftLegMuscle();
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
            //row11
            table.addCell(createImageCell("src/main/resources/static/pdf/images/body.jpg"));
            PdfPCell cellBodyFat = new PdfPCell();
            cellBodyFat.setPhrase(new Phrase("Body\n" +
                    "[fat %]", ff));
            cellBodyFat.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cellBodyFat);
            for (int i = 0; i < 11; i++) {
                PdfPCell cell = new PdfPCell();
                Double value = pdfData.getMeasurements().get(i).getBodyFat();
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
            //row12
            table.addCell(createImageCell("src/main/resources/static/pdf/images/body.jpg"));
            PdfPCell cellBodyMus = new PdfPCell();
            cellBodyMus.setPhrase(new Phrase("Body\n" +
                    "[kg]", ff));
            cellBodyMus.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cellBodyMus);
            for (int i = 0; i < 11; i++) {
                PdfPCell cell = new PdfPCell();
                Double value = pdfData.getMeasurements().get(i).getBodyMuscle();
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

            paragraph2.add(table);
            pdfDoc.add(paragraph1);
            pdfDoc.add(paragraph2);
            pdfDoc.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
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
        Font font = new Font(Font.FontFamily.COURIER, fontSize, Font.ITALIC);
        PdfPCell cellText = new PdfPCell(new Phrase(text, font));
        return cellText;
    }
}

