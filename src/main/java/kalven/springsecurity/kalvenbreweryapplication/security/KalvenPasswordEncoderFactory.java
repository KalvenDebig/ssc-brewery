package kalven.springsecurity.kalvenbreweryapplication.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.*;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Project kalven-brewery-application
 * @Author kalvens on 2/22/23
 */
public class KalvenPasswordEncoderFactory {
    private KalvenPasswordEncoderFactory() {}
    public static PasswordEncoder createDelegatingPasswordEncoder() {
        String encodingId = "bcrypt";
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put(encodingId, new BCryptPasswordEncoder());
        encoders.put("ldap", new LdapShaPasswordEncoder());
        encoders.put("noop", NoOpPasswordEncoder.getInstance());
        encoders.put("sha256", new StandardPasswordEncoder());
        return new DelegatingPasswordEncoder(encodingId, encoders);
    }

    public static PasswordEncoder createDelegatingPasswordEncoder(String encodingId) {
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("bcrypt", new BCryptPasswordEncoder());
        encoders.put("bcrypt15", new BCryptPasswordEncoder(15));
        encoders.put("ldap", new LdapShaPasswordEncoder());
        encoders.put("noop", NoOpPasswordEncoder.getInstance());
        encoders.put("sha256", new StandardPasswordEncoder());

        if (encodingId == null || StringUtils.trimAllWhitespace(encodingId).isEmpty() ||
        !encoders.containsKey(StringUtils.trimAllWhitespace(encodingId))) {
            return KalvenPasswordEncoderFactory.createDelegatingPasswordEncoder();
        }
        return new DelegatingPasswordEncoder(StringUtils.trimAllWhitespace(encodingId), encoders);
    }
}
