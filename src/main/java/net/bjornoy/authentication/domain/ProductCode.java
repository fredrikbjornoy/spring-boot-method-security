package net.bjornoy.authentication.domain;

import java.util.Arrays;

public enum ProductCode {
    CAR("C01"),
    PERSONAL("P01");

    private final String code;

    ProductCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static ProductCode fromCode(String code) {
        return Arrays.stream(values())
                .filter(productCode -> productCode.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }
}
