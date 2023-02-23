package kalven.springsecurity.kalvenbreweryapplication.domain.security;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

/**
 * @Project kalven-brewery-application
 * @Author kalvens on 2/23/23
 */
@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String role;
    @ManyToMany(mappedBy = "authorities")
    private Set<User> users;
}
