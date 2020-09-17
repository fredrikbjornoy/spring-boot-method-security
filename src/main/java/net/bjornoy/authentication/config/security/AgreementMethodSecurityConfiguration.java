package net.bjornoy.authentication.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AgreementMethodSecurityConfiguration extends GlobalMethodSecurityConfiguration {

    private final AgreementMethodSecurityExpressionHandler agreementMethodSecurityExpressionHandler;

    public AgreementMethodSecurityConfiguration(AgreementMethodSecurityExpressionHandler agreementMethodSecurityExpressionHandler) {
        this.agreementMethodSecurityExpressionHandler = agreementMethodSecurityExpressionHandler;
    }

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        return agreementMethodSecurityExpressionHandler;
    }
}
