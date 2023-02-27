package guru.sfg.brewery.security.permission.order;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @Project ssc-brewery
 * @Author kalvens on 2/27/23
 */

@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAuthority('order.create') OR hasAuthority('customer.order.create') AND @beerOrderAuthenticationManager.customerIdMatches" +
        "(authentication, #customerId)")
public @interface OrderCreatePermission {
}
