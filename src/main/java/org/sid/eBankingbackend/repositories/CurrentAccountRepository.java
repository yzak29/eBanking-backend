package org.sid.eBankingbackend.repositories;

import org.sid.eBankingbackend.entities.CurrentAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrentAccountRepository extends JpaRepository<CurrentAccount,Long> {
}
