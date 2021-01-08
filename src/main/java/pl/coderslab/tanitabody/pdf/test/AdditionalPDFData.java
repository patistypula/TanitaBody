package pl.coderslab.tanitabody.pdf.test;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.coderslab.tanitabody.additional.Additional;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class AdditionalPDFData {
    protected String firstName;
    protected String lastName;

    protected List<Additional> measurements = new ArrayList<>();
}
