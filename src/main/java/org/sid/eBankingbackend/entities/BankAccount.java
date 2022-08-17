package org.sid.eBankingbackend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.eBankingbackend.enums.AccountStatus;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE", length = 4)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankAccount {
    @Id
    private String id;
    private Date createdAt;
    private double balance;
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    @ManyToOne()
    private Customer customer;

    @OneToMany(mappedBy = "bankAccount", fetch = FetchType.EAGER)
    private List<AccountOperation> accountOperations;
}
