package messaging.app.config.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "security.authentication.jwt")
public class JwtProperties {
    private String secret;
    private long tokenValidityInSeconds;
    private long tokenValidityInSecondsForRememberMe;
}
