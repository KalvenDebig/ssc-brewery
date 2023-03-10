package guru.sfg.brewery.security;

import guru.sfg.brewery.domain.security.User;
import guru.sfg.brewery.repositories.security.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Project ssc-brewery
 * @Author kalvens on 3/5/23
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class UserUnlockService {
    private final UserRepository userRepository;
    @Scheduled(fixedRate = 300000)
    public void unlockAccounts() {
        log.debug("Running Unlock Accounts");

        List<User> lockedUsers = userRepository.
                findAllByAccountNonLockedAndLastModifiedDateIsBefore(false,
                        Timestamp.valueOf(LocalDateTime.now().minusSeconds(30)));

        if (!lockedUsers.isEmpty()) {
            log.debug("Locked Accounts Found");
            lockedUsers.forEach(user -> {
                user.setAccountNonLocked(true);
            });
        }

        userRepository.saveAll(lockedUsers);
    }
}
