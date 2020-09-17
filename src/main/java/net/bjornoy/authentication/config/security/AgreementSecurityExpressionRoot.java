package net.bjornoy.authentication.config.security;

import net.bjornoy.authentication.domain.ProductCode;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

@SuppressWarnings("unused")
public class AgreementSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

    private Object filterObject;
    private Object returnObject;
    private Object target;
    private final MethodInvocation methodInvocation;


    public AgreementSecurityExpressionRoot(Authentication authentication, MethodInvocation invocation) {
        super(authentication);
        this.methodInvocation = invocation;
    }

    public boolean isCar() {
        return authentication.getAuthorities().stream()
                .findFirst()
                .map(authority -> ProductCode.valueOf(authority.getAuthority().replace("ROLE_", "")))
                .filter(ProductCode::isCar)
                .isPresent();
    }

    @Override
    public void setFilterObject(Object filterObject) {
        this.filterObject = filterObject;
    }

    @Override
    public Object getFilterObject() {
        return filterObject;
    }

    @Override
    public void setReturnObject(Object returnObject) {
        this.returnObject = returnObject;
    }

    @Override
    public Object getReturnObject() {
        return returnObject;
    }

    @Override
    public Object getThis() {
        return target;
    }

    public void setThis(Object target) {
        this.target = target;
    }

    public MethodInvocation getMethodInvocation() {
        return methodInvocation;
    }
}
