package atm.service;

import atm.dao.AccountDao;
import atm.model.UserAccount;

import java.sql.Connection;

public interface AccountService {

    void signUp(String name, String lastName);
    void signIn();
    void balance(String cardNumber, String pinCode);
    void deposit(String cardNumber, String pinCode);
    void sendToFriend(String friendCardNumber);
    void withdrawMoney(int amount);

}
