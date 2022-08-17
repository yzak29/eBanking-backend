package org.sid.eBankingbackend.repositories;

import org.sid.eBankingbackend.entities.SavingAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingAccountRepository extends JpaRepository<SavingAccount,Long> {
}
