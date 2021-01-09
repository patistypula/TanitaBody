package pl.soroczynskadietetyk.tanitabody.measurement;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import pl.soroczynskadietetyk.tanitabody.person.Person;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter @Setter
@Table(name = Measurement.TABLE)
public class Measurement {
    public static final String TABLE = "measurement";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Double bodyFatPercentage; //%
    private Double bodyWeight; //kg
    private Double bodyWaterPercentage; //%
    private Double visceralFat; //level
    private Double muscleMass; //kg
    private Integer bodyBuildingIndex; //-
    private Double boneMass; //kg
    private Double BMI; //-
    private Integer metabolicAge; //-
    private Integer basalMetabolicRate; //kcal

    @Column(updatable = false)
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate created;

    private LocalDate updated;

    @PreUpdate
    public void preUpdate(){
        updated = LocalDate.now();
    }

    @ManyToOne
    private Person person;

}
