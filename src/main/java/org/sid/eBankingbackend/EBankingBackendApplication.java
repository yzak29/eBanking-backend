package org.sid.eBankingbackend;

import org.sid.eBankingbackend.entities.*;
import org.sid.eBankingbackend.enums.AccountStatus;
import org.sid.eBankingbackend.enums.OperationType;
import org.sid.eBankingbackend.repositories.AccountOperationRepository;
import org.sid.eBankingbackend.repositories.BankAccountRepository;
import org.sid.eBankingbackend.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class EBankingBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EBankingBackendApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(AccountOperationRepository accountOperationRepository,
										BankAccountRepository bankAccountRepository,
										CustomerRepository customerRepository){
		return args -> {

			BankAccount bankAccount = bankAccountRepository.findById("20ce1caa-79f1-4ccc-829c-a9d125a339c9").orElse(null);
			System.out.println("--------------------------------------");
			System.out.println(bankAccount.getAccountStatus());
			System.out.println(bankAccount.getBalance());
			System.out.println(bankAccount.getCreatedAt());
			System.out.println(bankAccount.getCustomer().getName());
			System.out.println(bankAccount.getClass().getName());
			if (bankAccount instanceof CurrentAccount){
				System.out.println("Over Draft=>"+((CurrentAccount) bankAccount).getOverDraft());
			}
			if (bankAccount instanceof SavingAccount){
				System.out.println("Rate=>"+((SavingAccount) bankAccount).getInterestRate());
			}

			bankAccount.getAccountOperations().forEach(accountOperation -> {
				System.out.println("==================================");
				System.out.println(accountOperation.getOperationType());
				System.out.println(accountOperation.getAmount());
				System.out.println(accountOperation.getDate());
			});

		};
	}

	//@Bean
	CommandLineRunner start(AccountOperationRepository accountOperationRepository,
							BankAccountRepository bankAccountRepository,
							CustomerRepository customerRepository){
		return args -> {
			Stream.of("zakaria","saad","youssra").forEach(name->{
				Customer customer = new Customer();
				customer.setName(name);
				customer.setEmail(name+"@gmail.com");
				customerRepository.save(customer);
			});

			customerRepository.findAll().forEach(customer -> {
				CurrentAccount currentAccount = new CurrentAccount();
				currentAccount.setId(UUID.randomUUID().toString());
				currentAccount.setCreatedAt(new Date());
				currentAccount.setBalance(Math.random()*9000);
				currentAccount.setAccountStatus(AccountStatus.CREATED);
				currentAccount.setCustomer(customer);
				currentAccount.setOverDraft(9000);

				bankAccountRepository.save(currentAccount);

				SavingAccount savingAccount = new SavingAccount();
				savingAccount.setId(UUID.randomUUID().toString());
				savingAccount.setCreatedAt(new Date());
				savingAccount.setBalance(Math.random()*9000);
				savingAccount.setAccountStatus(AccountStatus.CREATED);
				savingAccount.setCustomer(customer);
				savingAccount.setInterestRate(5.5);

				bankAccountRepository.save(savingAccount);
			});

			bankAccountRepository.findAll().forEach(bankAccount -> {
				for (int i=0;i<10;i++){
					AccountOperation accountOperation = new AccountOperation();
					accountOperation.setOperationType(Math.random()>0.5? OperationType.CREDIT:OperationType.DEBIT);
					accountOperation.setDate(new Date());
					accountOperation.setAmount(Math.random()*1200);
					accountOperation.setBankAccount(bankAccount);

					accountOperationRepository.save(accountOperation);
				}
			});
		};
	}

}
