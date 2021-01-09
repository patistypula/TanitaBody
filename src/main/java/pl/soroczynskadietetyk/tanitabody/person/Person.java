package pl.soroczynskadietetyk.tanitabody.person;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import pl.soroczynskadietetyk.tanitabody.additional.Additional;
import pl.soroczynskadietetyk.tanitabody.measurement.Measurement;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter @Setter
@Entity
@Table(name = Person.TABLE)
public class Person {
    public static final String TABLE = "person";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String gender;
    private Integer height;
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @NotNull
    private LocalDate yearOfBirth;

    @OneToMany (cascade = CascadeType.REMOVE, mappedBy = "person")
    private List<Measurement> measurement;

    @OneToMany (cascade = CascadeType.REMOVE,mappedBy = "person")
    private List<Additional> additional;

    public Person() {
        measurement = new ArrayList<>();
        additional = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", height=" + height +
                ", yearOfBirth=" + yearOfBirth +
                '}';
    }
}
