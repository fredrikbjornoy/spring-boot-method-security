package net.bjornoy.authentication.service;

import net.bjornoy.authentication.config.security.annotation.CanWrite;
import net.bjornoy.authentication.domain.Agreement;
import net.bjornoy.authentication.domain.ProductCode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AgreementService {

    private static final List<Agreement> AGREEMENTS = new ArrayList<>();

    static {
        Agreement car = Agreement.builder()
                .productCode(ProductCode.CAR.getCode())
                .id("1")
                .build();
        AGREEMENTS.add(car);

        Agreement person = Agreement.builder()
                .productCode(ProductCode.PERSONAL.getCode())
                .id("2")
                .build();
        AGREEMENTS.add(person);

        Agreement person2 = Agreement.builder()
                .productCode(ProductCode.PERSONAL.getCode())
                .id("3")
                .build();
        AGREEMENTS.add(person2);
    }

    @CanWrite
    public Optional<Agreement> getAgreement(String id) {
        return getAgreements().stream()
                .filter(agreement -> agreement.getId().equals(id))
                .findAny();
    }

    public List<Agreement> getAgreements() {
        return AGREEMENTS;
    }
}
