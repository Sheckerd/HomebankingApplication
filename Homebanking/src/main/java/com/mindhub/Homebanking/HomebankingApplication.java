package com.mindhub.Homebanking;
import com.mindhub.Homebanking.models.*;
import com.mindhub.Homebanking.repositories.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import java.time.LocalDateTime;
import java.util.Set;
import static com.mindhub.Homebanking.models.TransactionType.CREDIT;
import static com.mindhub.Homebanking.models.TransactionType.DEBIT;

@SpringBootApplication
public class HomebankingApplication {
    @Autowired
	private PasswordEncoder passwordEncoder;


	public static void main(String[] args) {

		SpringApplication.run(HomebankingApplication.class, args);


		System.out.println("Hola Mundo");
	}
	@Bean
	public CommandLineRunner initData(ClientRepository clientrepository, AccountRepository accountrepository, TransactionRepository transactionrepository, LoanRepository loanrepository, ClientLoanRepository clientloanrepository, CardRepository cardrepository){
		return (args) -> {




            Client client1 = new Client("Melba","Morel","melbamorel@mindhub.com", passwordEncoder.encode("123"));
			clientrepository.save(client1);

			Client client2 = new Client("Nicolas","Sandrin","nrsandrin@hotmail.com", passwordEncoder.encode("AguanteMelba"));
			clientrepository.save(client2);

			Client admin = new Client("admin", "admin", "admin@admin.com", passwordEncoder.encode("admin123"));

			clientrepository.save(admin);
			Account accountMelba1 = new Account(true,"VIN001", LocalDateTime.now(), 5000,client1,AccountType.CHECKING);

			accountrepository.save(accountMelba1);

			Account accountMelba2 = new Account(true,"VIN002", LocalDateTime.now(), 7500,client1,AccountType.SAVINGS);

			accountrepository.save(accountMelba2);

			Account accountAdmin = new Account (true,"VIN003",LocalDateTime.now(),5000,admin,AccountType.SAVINGS);

			accountrepository.save(accountAdmin);

			Transaction transaction1 = new Transaction(CREDIT,5000,"deposito", LocalDateTime.now(),accountMelba1,2000);

			transactionrepository.save(transaction1);

			Transaction transaction2 = new Transaction(DEBIT,-2000,"retiro", LocalDateTime.now(),accountMelba1,-1000);

			transactionrepository.save(transaction2);

			Transaction transaction3 = new Transaction(DEBIT,2000,"hola", LocalDateTime.now(),accountMelba2,2000);

			transactionrepository.save(transaction3);

			Loan loan1 = new Loan("Hipotecario", 500000, Set.of(12,24,36,48,60),50);
			loanrepository.save(loan1);

			Loan loan2 = new Loan("Personal",100000, Set.of(6,12,24),15);
			loanrepository.save(loan2);

			Loan loan3 = new Loan("Automotriz",300000, Set.of(6,12,24,36),35);
			loanrepository.save(loan3);

			ClientLoan clientloan1 = new ClientLoan(400000D,60,client1,loan1);

			clientloanrepository.save(clientloan1);

			ClientLoan clientloan2 = new ClientLoan(50000D,12,client1,loan2);
			clientloanrepository.save(clientloan2);

			ClientLoan clientloan3 = new ClientLoan(100000D,24,client2,loan2);
			clientloanrepository.save(clientloan3);

			ClientLoan clientloan4 = new ClientLoan(200000D,36,client2,loan3);
			clientloanrepository.save(clientloan4);

			Card card1 = new Card(false, true, "Melba Morel",CardType.DEBIT,CardColor.GOLD,4500,6585,1254,9876,455,LocalDateTime.now(),LocalDateTime.now().minusYears(1),client1);
			cardrepository.save(card1);

			Card card2 = new Card(false,true, "Melba Morel",CardType.CREDIT,CardColor.TITANIUM,4050, 2587, 6549, 3258,759,LocalDateTime.now(),LocalDateTime.now().plusYears(5),client1);
			cardrepository.save(card2);

			Card card3 = new Card(false,true, "Nicolas Sandrin",CardType.CREDIT,CardColor.SILVER,2659, 3568, 1548, 6592,246,LocalDateTime.now(),LocalDateTime.now().plusYears(5),client2);
			cardrepository.save(card3);




		};
	}

}
