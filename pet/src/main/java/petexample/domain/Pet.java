package petexample.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import petexample.PetApplication;

@Entity
@Table(name = "Pet_table")
@Data
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String type;

    private Integer age;

    public static PetRepository repository() {
        PetRepository petRepository = PetApplication.applicationContext.getBean(
            PetRepository.class
        );
        return petRepository;
    }
}
