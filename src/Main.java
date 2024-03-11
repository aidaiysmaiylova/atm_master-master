import atm.model.UserAccount;
import atm.service.AccountService;
import atm.service.impl.AccountServiceImpl;

import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        AccountService accountService=new AccountServiceImpl();
        accountService.signUp("Aidai","Ysmaiylova");
        accountService.signUp("Aizirek","Toktosunova");
        accountService.signUp("Oglai","Muhammadova");
accountService.signIn();


        }

        }

