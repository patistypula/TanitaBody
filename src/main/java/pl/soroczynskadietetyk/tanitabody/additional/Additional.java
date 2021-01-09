package pl.soroczynskadietetyk.tanitabody.additional;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import pl.soroczynskadietetyk.tanitabody.person.Person;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter @Setter
@Table(name = Additional.TABLE)
public class Additional {
    public static final String TABLE = "additional";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Double rightArmFat;
    private Double rightArmMuscle;

    private Double leftArmFat;
    private Double leftArmMuscle;

    private Double rightLegFat;
    private Double rightLegMuscle;

    private Double leftLegFat;
    private Double leftLegMuscle;

    private Double bodyFat;
    private Double bodyMuscle;

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
