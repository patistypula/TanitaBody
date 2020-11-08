package pl.coderslab.tanitabody.measurement;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.tanitabody.person.Person;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    private LocalDateTime created;
    private LocalDateTime updated;

    @PreUpdate
    public void preUpdate(){
        updated = LocalDateTime.now().withNano(0);
    }

    @ManyToOne
    private Person person;

}
