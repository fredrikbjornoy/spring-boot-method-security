package net.bjornoy.authentication.config.security;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class AgreementMethodSecurityExpressionHandler extends DefaultMethodSecurityExpressionHandler {

    @Override
    protected MethodSecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication, MethodInvocation invocation) {
        AgreementSecurityExpressionRoot securityExpressionRoot = new AgreementSecurityExpressionRoot(authentication, invocation);
        securityExpressionRoot.setPermissionEvaluator(getPermissionEvaluator());
        securityExpressionRoot.setTrustResolver(getTrustResolver());
        securityExpressionRoot.setRoleHierarchy(getRoleHierarchy());
        securityExpressionRoot.setThis(invocation.getThis());

        return securityExpressionRoot;
    }
}
