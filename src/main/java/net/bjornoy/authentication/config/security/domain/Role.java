package net.bjornoy.authentication.config.security.domain;

public enum Role {

    ADMINISTRATOR("Administrator"),
    HR("HR"),
    DAMAGE("Damage");

    public static final String SPRING_ROLE_PREFIX = "ROLE_";

    private final String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public boolean isAdministrator() {
        return this == ADMINISTRATOR;
    }

    public static Role fromSpringAuthority(String authority) {
        if (authority == null || authority.length() == 0) {
            return null;
        }

        try {
            return authority.startsWith(SPRING_ROLE_PREFIX) ? Role.valueOf(authority.replace(SPRING_ROLE_PREFIX, "")) : Role.valueOf(authority);
        } catch (IllegalArgumentException iae) {
            return null;
        }
    }
}
