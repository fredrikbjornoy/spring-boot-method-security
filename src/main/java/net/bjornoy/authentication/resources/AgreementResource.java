package net.bjornoy.authentication.resources;

import net.bjornoy.authentication.domain.Agreement;
import net.bjornoy.authentication.domain.ProductCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/agreements")
public class AgreementResource {

    @GetMapping
    public ResponseEntity<List<Agreement>> get() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        ProductCode productCode = authentication.getAuthorities().stream()
                .findFirst()
                .map(s -> s.getAuthority().replace("ROLE_", ""))
                .map(ProductCode::valueOf)
                .orElse(null);

        if (productCode == null) {
            return ResponseEntity.badRequest().build();
        }

        List<Agreement> collect = getAgreements().stream()
                .filter(agreement -> productCode.equals(ProductCode.fromCode(agreement.getProductCode())))
                .collect(Collectors.toList());


        return ResponseEntity.ok(collect);
    }

    @GetMapping("{id}")
    public ResponseEntity<Agreement> getById(@PathVariable final String id) {
        Optional<Agreement> agreementOptional = getAgreements().stream()
                .filter(agreement -> agreement.getId().equals(id))
                .findFirst();
        return ResponseEntity.of(agreementOptional);
    }

    private List<Agreement> getAgreements() {
        Agreement car = Agreement.builder()
                .productCode(ProductCode.CAR.getCode())
                .id("1")
                .build();

        Agreement person = Agreement.builder()
                .productCode(ProductCode.PERSONAL.getCode())
                .id("2")
                .build();

        return List.of(car, person);
    }
}
