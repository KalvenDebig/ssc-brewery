package kalven.springsecurity.kalvenbreweryapplication.domain.security;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

/**
 * @Project kalven-brewery-application
 * @Author kalvens on 2/23/23
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String username;
    private String password;
    @Singular
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "user_authority",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID")})
    private Set<Authority> authorities;

    @Builder.Default
    private Boolean accountNonExpired = true;
    @Builder.Default
    private Boolean accountNonBlocked = true;
    @Builder.Default
    private Boolean credentialNonExpired = true;
    @Builder.Default
    private Boolean enabled = true;
}
