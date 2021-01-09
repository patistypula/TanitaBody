package pl.soroczynskadietetyk.tanitabody.pdf.test;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import lombok.SneakyThrows;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class Test {

    public static ByteArrayInputStream createByteStream (){
        String adressFile = "src/main/java/pl/coderslab/tanitabody/pdf/test/test.pdf";
        PdfDocument pdf = new PdfDocument();
        pdf.addTitle("Tabela pomiarów");
        Document pdfDoc = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(pdfDoc, out);
            pdfDoc.open();
            pdfDoc.setPageSize(PageSize.A4.rotate());
            pdfDoc.newPage();
            Paragraph paragraph1 = new Paragraph();
            Anchor anchor = new Anchor("d.soroczynskadietetyk@gmail.com");
            anchor.setReference("mailto:\"+email+\"?subject=ReferenceNumber:1234");
            paragraph1.add(anchor);
            paragraph1.setAlignment(Element.ALIGN_RIGHT);
            Paragraph paragraph2 = new Paragraph();
            float[] columnsWidths = new float[13];
            Arrays.fill(columnsWidths, 500f);
            columnsWidths[0] = 400;
            PdfPTable table = new PdfPTable(13);
            table.setWidthPercentage(100);
            table.setWidths(columnsWidths);
            table.setSpacingBefore(10);
            PdfPCell cell = new PdfPCell();
            //row1
            //List<PdfPCell> row1 = new ArrayList<>();
            for (int i = 0; i < 13; i++) {
                if (i >= 2) {
                    cell.setPhrase(new Phrase("Data/ Wyniki"));
                }
                table.addCell(cell);
            }
            //row2
            //List<PdfPCell> row2 = new ArrayList<>();
            cell.setPhrase(new Phrase(" "));
            cell.setRowspan(2);
            table.addCell(cell);
            for (int i = 0; i < 12; i++) {
                table.addCell(" ");
            }
            //row3
            for (int i = 0; i < 12; i++) {
                table.addCell(" ");
            }
            //row4
            table.addCell(createImageCell("src/main/resources/static/pdf/images/body_fat_percentage.jpg"));
            table.addCell(createTextCell("Procentowa zawartość tkanki tłuszczowej  w organizmie", 9));
            for (int i = 0; i < 11; i++) {
                table.addCell(" ");
            }
            //row5
            table.addCell(createImageCell("src/main/resources/static/pdf/images/body_weight.jpg"));
            table.addCell(createTextCell("Masa ciała", 9));
            for (int i = 0; i < 11; i++) {
                table.addCell(" ");
            }
            //row6
            table.addCell(createImageCell("src/main/resources/static/pdf/images/body_water_percentage.jpg"));
            table.addCell(createTextCell("Procentowa zawartość wody w organizmie", 9));
            for (int i = 0; i < 11; i++) {
                table.addCell(" ");
            }
            //row7
            table.addCell(createImageCell("src/main/resources/static/pdf/images/vicera_fat_rating.jpg"));
            table.addCell(createTextCell("Poziom tłuszczu wisceralnego", 9));
            for (int i = 0; i < 11; i++) {
                table.addCell(" ");
            }
            //row8
            table.addCell(createImageCell("src/main/resources/static/pdf/images/muscle_mass.jpg"));
            table.addCell(createTextCell("Masa mięśniowa", 9));
            for (int i = 0; i < 11; i++) {
                table.addCell(" ");
            }
            //row8
            table.addCell(createImageCell("src/main/resources/static/pdf/images/physique_rating.jpg"));
            table.addCell(createTextCell("Wskaźnik budowy ciała", 9));
            for (int i = 0; i < 11; i++) {
                table.addCell(" ");
            }
            //row9
            table.addCell(createImageCell("src/main/resources/static/pdf/images/bone_mass_ranges.jpg"));
            table.addCell(createTextCell("Poziom mineralny kości (wapnia i innych minerałów)", 9));
            for (int i = 0; i < 11; i++) {
                table.addCell(" ");
            }
            //row10
            table.addCell(createImageCell("src/main/resources/static/pdf/images/bmi.jpg"));
            table.addCell(createTextCell("BMI\n" +
                    "Wiek  metaboliczny \n" +
                    "PPM", 9));
            for (int i = 0; i < 11; i++) {
                table.addCell(" ");
            }

            paragraph2.add(table);
            pdfDoc.add(paragraph1);
            pdfDoc.add(paragraph2);
            try {
                pdfDoc.add(Image.getInstance("src/main/resources/static/pdf/images/explanations.jpg"));
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
        Font font = new Font(Font.FontFamily.COURIER, fontSize, Font.ITALIC);
        PdfPCell cellText = new PdfPCell(new Phrase(text, font));
        return cellText;
    }
}

