package guru.sfg.brewery.security.permission.customer;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @Project ssc-brewery
 * @Author kalvens on 2/25/23
 */

@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAuthority('customer.delete')")
public @interface CustomerDeletePermission {
}
