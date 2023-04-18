package petexample.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import petexample.ItemApplication;

@Entity
@Table(name = "Item_table")
@Data
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    @AttributeOverride(
        name = "id",
        column = @Column(name = "petIdId", nullable = true)
    )
    private PetId petId;

    public static ItemRepository repository() {
        ItemRepository itemRepository = ItemApplication.applicationContext.getBean(
            ItemRepository.class
        );
        return itemRepository;
    }
}
