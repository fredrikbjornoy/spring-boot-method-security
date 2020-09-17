package net.bjornoy.authentication.resources;

import net.bjornoy.authentication.domain.Agreement;
import net.bjornoy.authentication.service.AgreementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/agreements")
public class AgreementResource {

    private final AgreementService agreementService;

    @Autowired
    public AgreementResource(AgreementService agreementService) {
        this.agreementService = agreementService;
    }

    @GetMapping
    public ResponseEntity<List<Agreement>> get() {
        return ResponseEntity.ok(agreementService.getAgreements());
    }

    @GetMapping("{id}")
    public ResponseEntity<Agreement> getById(@PathVariable final String id) {
        return ResponseEntity.of(agreementService.getAgreement(id));
    }
}
