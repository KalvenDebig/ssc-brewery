package kalven.springsecurity.kalvenbreweryapplication.service;

import kalven.springsecurity.kalvenbreweryapplication.web.model.BeerOrderDto;
import kalven.springsecurity.kalvenbreweryapplication.web.model.BeerOrderPagedList;

import org.springframework.data.domain.Pageable;
import java.util.UUID;

/**
 * @Project kalven-brewery-application
 * @Author kalvens on 2/20/23
 */
public interface BeerOrderService {
    BeerOrderPagedList listOrders(UUID customerId, Pageable pageable);

    BeerOrderDto placeOrder(UUID customerId, BeerOrderDto beerOrderDto);

    BeerOrderDto getOrderById(UUID customerId, UUID orderId);

    void pickUpOrder(UUID customerId, UUID orderId);
}
