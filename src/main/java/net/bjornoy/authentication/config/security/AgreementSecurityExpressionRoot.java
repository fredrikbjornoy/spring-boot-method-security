package net.bjornoy.authentication.config.security;

import net.bjornoy.authentication.access.AccessProductAttributes;
import net.bjornoy.authentication.config.security.domain.Role;
import net.bjornoy.authentication.domain.Agreement;
import net.bjornoy.authentication.domain.ProductCode;
import net.bjornoy.authentication.service.AgreementService;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class AgreementSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

    private Object filterObject;
    private Object returnObject;
    private Object target;

    private final MethodInvocation methodInvocation;
    private AgreementService agreementService;

    public AgreementSecurityExpressionRoot(Authentication authentication, MethodInvocation invocation) {
        super(authentication);
        this.methodInvocation = invocation;
    }

    public boolean isAdmin() {
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .map(Role::fromSpringAuthority)
                .anyMatch(Role::isAdministrator);
    }

    public boolean canWrite() {
        Object[] arguments = methodInvocation.getArguments();
        if (arguments.length != 1) {
            return false;
        }

        String id = (String) arguments[0];
        Agreement agreement = agreementService.getAgreements().stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (agreement == null) {
            return false;
        }

        Role role = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .map(Role::fromSpringAuthority)
                .findFirst()
                .orElse(null);

        if (role == null) {
            return false;
        }

        return AccessProductAttributes.ROLE_PRODUCT_CODE_MAP.get(role) == ProductCode.fromCode(agreement.getProductCode());
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

    @Autowired
    public void setAgreementService(AgreementService agreementService) {
        this.agreementService = agreementService;
    }
}
