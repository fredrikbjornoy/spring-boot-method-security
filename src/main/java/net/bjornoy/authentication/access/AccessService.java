package net.bjornoy.authentication.access;

import net.bjornoy.authentication.domain.ProductCode;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
public class AccessService {

    private static final Set<Person> PERSONER = Set.of(new Person("1234", ProductCode.CAR), new Person("4321", ProductCode.PERSONAL));

    public Person getPerson(String ssn) {
        return PERSONER.stream()
                .filter(p -> ssn.equals(p.ssn))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }


    public static class Person {
        final String ssn;
        final ProductCode productCode;

        public Person(String ssn, ProductCode productCode) {
            this.ssn = ssn;
            this.productCode = productCode;
        }

        public String getSsn() {
            return ssn;
        }

        public ProductCode getProductCode() {
            return productCode;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Person person = (Person) o;
            return ssn.equals(person.ssn);
        }

        @Override
        public int hashCode() {
            return Objects.hash(ssn);
        }
    }
}
