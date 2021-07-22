package com.Assignment1;

import com.Assignment1.Models.Transaction;
import com.Assignment1.Models.UserWallet;
import com.Assignment1.Repositories.PaginationRepository;
import com.Assignment1.Repositories.TransactionRepository;
import com.Assignment1.Repositories.UserWalletRepository;
import com.Assignment1.Models.User;
import com.Assignment1.Repositories.UserRepository;
import com.Assignment1.Service.TransactionService;
import com.Assignment1.Service.WalletService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import com.Assignment1.Service.UserService;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;


/*@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)*/
@RunWith(SpringRunner.class)
@SpringBootTest
class UthAssignmentApplicationTests {

/*@Autowired
	service service;
	@Autowired
	private UserRepository userRepository;

	@Test
	public void createUserTest()
	{
		User user = new User("alex123","alex","dutch","9898989898","alex@usa.com","XYZ","PQR");
	//	userRepository.save(user);
		assertNotEquals(null);
	}*/


	@Autowired
	UserService UserService;
	@Autowired
	WalletService walletService;
	@Autowired
	TransactionService transactionService;
	@MockBean
	UserRepository userRepository;
	@MockBean
	UserWalletRepository userWalletRepository;
	@MockBean
	TransactionRepository transactionRepository;
	@MockBean
	PaginationRepository paginationRepository;

	@Test
	public void createUserTest()
	{
		User user = new User("alex123","alex","dutch","9898989898","alex@usa.com","XYZ","PQR","abc@123");
		when(userRepository.save(user)).thenReturn(user);
		assertEquals(user, UserService.createUser(user));
	}
	@Test
	public void findUserTest()
	{
		User user = new User("alex123","alex","dutch","9898989898","alex@usa.com","XYZ","PQR","abc@123");
		when(userRepository.findByUserID((long)1)).thenReturn(user);
		assertEquals(user, UserService.findUser((long)1));
	}
	@Test
	public void updateUserTest()
	{
		User user = new User("alex1234","alex","dutch","9898989898","alex@usa.com","XYZ","PQR","abc@123");
		when(userRepository.save(user)).thenReturn(user);
		when(userRepository.findByUserID((long)1)).thenReturn(user);
		assertEquals(user, UserService.updateUser(1L,user));

	}
	@Test
	public void deleteUserTest()
	{
		User user = new User("alex123","alex","dutch","9898989898","alex@usa.com","XYZ","PQR","abc@123");

		assertEquals("User does not exist.", UserService.deleteUser(1L));

	}

	@Test
	public void createWalletTest()
	{
		User user = new User("alex123","alex","dutch","9898989898","alex@usa.com","XYZ","PQR","abc@123");
		String mobileNumber = "9898989898";
		UserWallet userWallet = new UserWallet(mobileNumber);

		when(userWalletRepository.findByID(mobileNumber)).thenReturn(null);
		when(userRepository.findByMobileNumber(mobileNumber)).thenReturn(user);
		when(userWalletRepository.save(userWallet)).thenReturn(userWallet);

		assertEquals(userWallet, walletService.createWallet(mobileNumber));

	}

	@Test
	public void doTransactionTest()
	{
		User user = new User("alex123","alex","dutch","9898989898","alex@usa.com","XYZ","PQR","abc@123");
		String mobileNumber1 = "9898989898";
		String mobileNumber2 = "9999999999";
		Double amount =100D;
		UserWallet userWallet1 = new UserWallet(mobileNumber1);
		UserWallet userWallet2 = new UserWallet(mobileNumber2);
		Double payerBalance=userWallet1.getBalance();
		Double payeeBalance=userWallet2.getBalance();
		Transaction transaction =  new Transaction(mobileNumber1, mobileNumber2, amount, "Success");

		when(userWalletRepository.findByID(mobileNumber1)).thenReturn(userWallet1);
		when(userWalletRepository.findByID(mobileNumber2)).thenReturn(userWallet2);
		when(userWalletRepository.save(userWallet1)).thenReturn(userWallet1);
		when(userWalletRepository.save(userWallet2)).thenReturn(userWallet2);
		when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

		assertEquals(transaction, transactionService.doTransaction(mobileNumber1,mobileNumber2,amount));
		verify(transactionRepository, times(1)).save(transaction);
		assertEquals(userWallet1.getBalance(),payerBalance-amount);
		assertEquals(userWallet2.getBalance(),payeeBalance+amount);


	}

	@Test
	public void getTransactionSummaryTest()
	{

		User user = new User("alex123","alex","dutch","9898989898","alex@usa.com","XYZ","PQR","abc@123");
		String mobileNumber1 = "9898989898";
		String mobileNumber2 = "9999999999";
		Double amount =100D;
		UserWallet userWallet1 = new UserWallet(mobileNumber1);
		UserWallet userWallet2 = new UserWallet(mobileNumber2);
		Transaction transaction =  new Transaction(mobileNumber1, mobileNumber2, amount, "Success");
		Pageable firstPageWithOneElement = PageRequest.of(0, 15, Sort.by("Date").descending());
		Page<Transaction> tasks = Mockito.mock(Page.class);

		when(userRepository.findByUserID(user.getUserID())).thenReturn(user);
		when(userWalletRepository.findByID(user.getMobileNumber())).thenReturn(userWallet1);
		when(paginationRepository.findByUserID(firstPageWithOneElement,user.getMobileNumber())).thenReturn(tasks);

		assertEquals(tasks, transactionService.getTransactionSummary(user.getUserID()));

	}

	@Test
	public void getTransactionStatusTest()
	{

		String mobileNumber1 = "9898989898";
		String mobileNumber2 = "9999999999";
		Double amount =100D;
		UserWallet userWallet1 = new UserWallet(mobileNumber1);
		UserWallet userWallet2 = new UserWallet(mobileNumber2);

		Transaction transaction =  new Transaction(mobileNumber1, mobileNumber2, amount, "Success");
		when(transactionRepository.findByID(transaction.getTransactionID())).thenReturn(transaction);
		assertEquals(transaction,transactionService.getTransactionStatus(null));
	}



/*	public void findUserTest1()
			throws Exception {

		User alex = new User("alex123","alex","dutch","9898989898","alex@usa.com","XYZ","ABC");


		given(service.findUser((long)1)).willReturn(alex);

		mvc.perform(get("/user")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].name", is(alex.getUserName())));
	}*/


}
