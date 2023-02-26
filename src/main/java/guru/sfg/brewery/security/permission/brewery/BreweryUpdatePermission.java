package guru.sfg.brewery.security.permission.brewery;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @Project ssc-brewery
 * @Author kalvens on 2/25/23
 */

@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAuthority('brewery.update')")
public @interface BreweryUpdatePermission {
}
