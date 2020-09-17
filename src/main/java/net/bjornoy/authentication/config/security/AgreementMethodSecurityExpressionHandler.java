package net.bjornoy.authentication.config.security;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class AgreementMethodSecurityExpressionHandler extends DefaultMethodSecurityExpressionHandler {

    private final ApplicationContext applicationContext;

    @Autowired
    public AgreementMethodSecurityExpressionHandler(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    protected MethodSecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication, MethodInvocation invocation) {
        final AgreementSecurityExpressionRoot securityExpressionRoot = (AgreementSecurityExpressionRoot) applicationContext.getBean(MethodSecurityExpressionOperations.class, authentication, invocation);
        securityExpressionRoot.setPermissionEvaluator(getPermissionEvaluator());
        securityExpressionRoot.setTrustResolver(getTrustResolver());
        securityExpressionRoot.setRoleHierarchy(getRoleHierarchy());
        securityExpressionRoot.setThis(invocation.getThis());

        return securityExpressionRoot;
    }
}
