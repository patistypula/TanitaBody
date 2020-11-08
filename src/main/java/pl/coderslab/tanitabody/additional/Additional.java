package pl.coderslab.tanitabody.additional;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.tanitabody.person.Person;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    private LocalDateTime created;
    private LocalDateTime updated;

    @PreUpdate
    public void preUpdate(){
        updated = LocalDateTime.now().withNano(0);
    }

    @ManyToOne
    private Person person;
}
