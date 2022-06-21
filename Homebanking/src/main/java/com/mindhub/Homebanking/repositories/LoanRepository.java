package com.mindhub.Homebanking.repositories;
import com.mindhub.Homebanking.models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface LoanRepository extends JpaRepository<Loan,Long> {
      Loan findById(long id);
}
