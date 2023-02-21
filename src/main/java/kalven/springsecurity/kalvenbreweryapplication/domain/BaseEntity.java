package kalven.springsecurity.kalvenbreweryapplication.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.UUID;

/**
 * @Project kalven-brewery-application
 * @Author kalvens on 2/20/23
 */

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
@AllArgsConstructor
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @JdbcTypeCode(Types.VARCHAR)
    @Column(length = 36, columnDefinition = "varchar", updatable = false, nullable = false)
    private UUID id;

    @Version
    private Long version;
    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdDate;

    @UpdateTimestamp
    private Timestamp lastModifiedDate;

    public boolean isNow() {
        return this.id == null;
    }
}