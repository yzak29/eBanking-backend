package org.sid.eBankingbackend.repositories;

import org.sid.eBankingbackend.entities.AccountOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountOperationRepository extends JpaRepository<AccountOperation,Long> {
}
