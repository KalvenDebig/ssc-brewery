package kalven.springsecurity.kalvenbreweryapplication.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

/**
 * @Project kalven-brewery-application
 * @Author kalvens on 2/20/23
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BeerInventory extends BaseEntity {
    @Builder
    public BeerInventory(UUID id, Long version, Timestamp createdDate, Timestamp lastModifiedDate,
                         Beer beer, Integer quantityOnHand) {
        super(id, version, createdDate, lastModifiedDate);
        this.beer = beer;
        this.quantityOnHand = quantityOnHand;
    }
    @ManyToOne
    private Beer beer;
    private Integer quantityOnHand = 0;
}
