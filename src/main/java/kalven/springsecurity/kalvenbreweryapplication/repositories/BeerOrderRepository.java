package kalven.springsecurity.kalvenbreweryapplication.repositories;

import jakarta.persistence.LockModeType;
import kalven.springsecurity.kalvenbreweryapplication.domain.BeerOrder;
import kalven.springsecurity.kalvenbreweryapplication.domain.Customer;
import kalven.springsecurity.kalvenbreweryapplication.domain.OrderStatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.List;
import java.util.UUID;

/**
 * @Project kalven-brewery-application
 * @Author kalvens on 2/20/23
 */
public interface BeerOrderRepository extends JpaRepository<BeerOrder, UUID> {
    Page<BeerOrder> findAllByCustomer(Customer customer, Pageable pageable);
    List<BeerOrder> findAllByOrderStatus(OrderStatusEnum orderStatusEnum);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    BeerOrder findOneById(UUID id);
}
