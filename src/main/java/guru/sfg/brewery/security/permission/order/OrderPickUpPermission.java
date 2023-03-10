package guru.sfg.brewery.security.permission.order;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @Project ssc-brewery
 * @Author kalvens on 2/27/23
 */
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAuthority('order.update') OR hasAuthority('customer.order.update') AND @beerOrderAuthenticationManager.customerIdMatches" +
        "(authentication, #customerId)")
public @interface OrderPickUpPermission {
}
