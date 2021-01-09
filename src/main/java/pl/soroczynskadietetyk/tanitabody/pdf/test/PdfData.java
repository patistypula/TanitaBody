package pl.soroczynskadietetyk.tanitabody.pdf.test;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.soroczynskadietetyk.tanitabody.measurement.Measurement;

import java.util.ArrayList;
import java.util.List;

@Setter @Getter @NoArgsConstructor
public class PdfData {
    protected String firstName;
    protected String lastName;

    protected List<Measurement> measurements = new ArrayList<>();
}
