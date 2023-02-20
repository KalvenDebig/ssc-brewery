package kalven.springsecurity.kalvenbreweryapplication.web.model;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * @Project kalven-brewery-application
 * @Author kalvens on 2/20/23
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BeerOrderLineDto extends BaseItem{
    @Builder
    public BeerOrderLineDto(UUID id, Integer version, OffsetDateTime createdDate, OffsetDateTime lastModifiedDate, UUID beerId, Integer orderQuantity) {
        super(id, version, createdDate, lastModifiedDate);
        this.beerId = beerId;
        this.orderQuantity = orderQuantity;
    }

    private UUID beerId;
    private Integer orderQuantity = 0;
}
