package net.bjornoy.authentication.access;

import net.bjornoy.authentication.config.security.domain.Role;
import net.bjornoy.authentication.domain.ProductCode;

import java.util.Map;

public final class AccessProductAttributes {

    private AccessProductAttributes() {
    }

    public static final Map<Role, ProductCode> ROLE_PRODUCT_CODE_MAP = Map.of(
            Role.DAMAGE, ProductCode.CAR,
            Role.HR, ProductCode.PERSONAL
    );
}
