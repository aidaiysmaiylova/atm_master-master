package atm.service.impl;

import atm.dao.AccountDao;
import atm.model.UserAccount;
import atm.service.AccountService;

import java.util.Random;
import java.util.Scanner;

public class AccountServiceImpl implements AccountService {
    private final AccountDao accountDao = new AccountDao();
    private final Random random = new Random();
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void signUp(String name, String lastName) {


        UserAccount userAccount = new UserAccount();
        try {
            userAccount.setName(name);
            userAccount.setLastName(lastName);
            String cardNumber = String.valueOf(random.nextInt(9999999, 99999999));
            String pinCode = String.valueOf(random.nextInt(1000, 9999));
            userAccount.setCardNumber(cardNumber);
            userAccount.setPinCode(pinCode);

            accountDao.getUserAccounts().add(userAccount);
            System.out.println(userAccount);


        } catch (RuntimeException exception) {
            System.out.println("Введите точные данные");
        }
    }


    @Override
    public void signIn() {
        try {
            System.out.println("Welcome to ATM  -  Добро пожаловать в банкомат");
            System.out.println("Введите имя пользователя");
            String name = scanner.nextLine();
            System.out.println("Введите фамилия пользователя");
            String lastName = scanner.nextLine();
            UserAccount user = accountDao.getUserAccounts().stream().filter(userAccount -> userAccount.getName().
                            equalsIgnoreCase(name) && userAccount.getLastName().equalsIgnoreCase(lastName))
                    .findFirst().orElseThrow(() -> new RuntimeException("Введите точные данные"));
            System.out.println(user);

            while (true) {

                System.out.println("Выберите операцию,которую хотите совершить");
                System.out.println("1.Проверить баланс");
                System.out.println("2.Отправить деньги");
                System.out.println("3.Пополнить баланс");
                System.out.println("4.Снять деньги");
                System.out.println("5.Завершение операции и выход");
                int person = scanner.nextInt();
                if (person == 5) {
                    break;
                } else if (person == 1) {
                    System.out.println("Введите номер карты");
                    String cardNumber = String.valueOf(scanner.nextInt());
                    System.out.println("Введите пин-код");
                    String pinCode = String.valueOf(scanner.nextInt());
                    balance(cardNumber, pinCode);
                } else if (person == 2) {
                    System.out.println("Введите номер карты получателя");
                    String cardNumberOfFriend = String.valueOf(scanner.nextInt());
                    System.out.println("Введите свой номер карты");
                    String userCardNumber = String.valueOf(scanner.nextInt());
                    System.out.println("Введите свой пин-код");
                    String userPinCode = String.valueOf(scanner.nextInt());
                    sendToFriend(cardNumberOfFriend);
                } else if (person == 3) {
                    System.out.println("Введите номер карты");
                    String cardNumber = String.valueOf(scanner.nextInt());
                    System.out.println("Введите пин-код");
                    String pinCode = String.valueOf(scanner.nextInt());
                    deposit(cardNumber, pinCode);
                } else if (person == 4) {
                    System.out.println("Введите сумму , которую хотите снять");
                    int sumDrawingMoney = scanner.nextInt();
                    withdrawMoney(sumDrawingMoney);
                }
            }

        } catch (RuntimeException exception) {
            System.out.println(exception.getMessage());
        }
    }


    @Override
    public void balance(String cardNumber, String pinCode) {
        for (UserAccount user : accountDao.getUserAccounts()) {
            if (user.getCardNumber().equals(cardNumber) && user.getPinCode().equals(pinCode)) {
                System.out.println("Ваш текущий баланс : " + user.getBalance());
            }
        }
    }

    @Override
    public void deposit(String cardNumber, String pinCode) {
        for (UserAccount user : accountDao.getUserAccounts()) {
            if (user.getCardNumber().equals(cardNumber) && user.getPinCode().equals(pinCode)) {
                System.out.println("Введите сумму пополнения");
                int sumOfDeposit = scanner.nextInt();
                System.out.println("Ваш баланс успешно пополнен на сумму : " + sumOfDeposit);
                user.setBalance(user.getBalance() + sumOfDeposit);
                System.out.println("Ваш баланс : " + user.getBalance());
            }

        }


    }

    @Override
    public void sendToFriend(String friendCardNumber) throws RuntimeException {
        UserAccount friend = null;
        try {
            for (UserAccount user : accountDao.getUserAccounts()) {
                if (user.getCardNumber().equals(friendCardNumber)) {
                    friend = user;
                }
            }
            if (friend != null && !(friend.getCardNumber().equals(friendCardNumber))) {
                throw new RuntimeException("Введите точныЙ номер карты получателя");
            }
        } catch (RuntimeException exception) {
            System.out.println(exception.getMessage());
        }

        UserAccount ownUser = null;
        try {
            for (UserAccount userAccount : accountDao.getUserAccounts()) {
                if (userAccount.getCardNumber().equals(userAccount.getCardNumber()) &&
                        userAccount.getPinCode().equals(userAccount.getPinCode())) {
                    ownUser = userAccount;
                }
                System.out.println("Введите сумму отправки");
                int sumSending = scanner.nextInt();
                friend.setBalance(friend.getBalance() - sumSending);
                System.out.println("Сумма : " + sumSending + " успешно отправлена");
                ownUser.setBalance(ownUser.getBalance() - sumSending);
                System.out.println("Ваш текущий баланс : " + ownUser.getBalance());
            }

            if (ownUser != null && !ownUser.getCardNumber().equals(ownUser.getCardNumber())) ;
            throw new RuntimeException("Введите свои точные данные");
        } catch (RuntimeException exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public void withdrawMoney(int amount) {
        System.out.print("Напишите номер карты");
        String CardNumber = String.valueOf(scanner.nextInt());
        System.out.print("Напишите пин-код");
        String PinCode = String.valueOf(scanner.nextInt());
        if (amount % 1000 == 0) {
            int banknote = amount / 1000;
            System.out.println("Получить " + amount + " рублей по (" + banknote + ") 1000 рублёвыми купюрами (нажмите кнопку 1)");
        }
        if (amount % 500 == 0) {
            int banknote = amount / 500;
            System.out.println("Получить " + amount + " рублей по (" + banknote + ") 500 рублёвыми купюрами (нажмите кнопку 2)");
        }
        if (amount % 200 == 0) {
            int banknote = amount / 200;
            System.out.println("Получить " + amount + " рублей по (" + banknote + ") 200 рублёвыми купюрами (нажмите кнопку 3)");
        }
        if (amount % 100 == 0) {
            int banknote = amount / 100;
            System.out.println("Получить " + amount + " рублей по (" + banknote + ") 100 рублёвыми купюрами (нажмите кнопку 4)");
        }
        if (amount % 50 == 0) {
            int banknote = amount / 50;
            System.out.println("Получить " + amount + " рублей по (" + banknote + ") 50 рублёвыми купюрами (нажмите кнопку 5)");
        }
        if (amount % 10 == 0) {
            int banknote = amount / 10;
            System.out.println("Получить " + amount + " рублей по (" + banknote + ") 10 рублёвыми монетами (нажмите кнопку 6)");
        }
        System.out.print("Введите номер действия: ");
        int number = scanner.nextInt();
        for (UserAccount account : accountDao.getUserAccounts()) {
            int balance = account.getBalance() - amount;
            if (number == 1 && account.getCardNumber().equals(CardNumber) && account.getBalance() >= amount && account.getPinCode().equals(PinCode)) {
                account.setBalance(balance);
                System.out.println("Ваш баланс: " + account.getBalance());
                System.out.println("Выведено: " + amount + " рублей по 1000 рублей (" + amount / 1000 + " штук).");

            } else if (number == 2 && account.getCardNumber().equals(CardNumber) && account.getBalance() >= amount) {
                account.setBalance(balance);
                System.out.println("Ваш баланс: " + account.getBalance());
                System.out.println("Выведено: " + amount + " рублей по 500 рублей (" + amount / 500 + " штук).");

            } else if (number == 3 && account.getCardNumber().equals(CardNumber) && account.getBalance() >= amount) {
                account.setBalance(balance);
                System.out.println("Ваш баланс: " + account.getBalance());
                System.out.println("Выведено: " + amount + " рублей по 200 рублей (" + amount / 200 + " штук).");

            } else if (number == 4 && account.getCardNumber().equals(CardNumber) && account.getBalance() >= amount) {
                account.setBalance(balance);
                System.out.println("Ваш баланс: " + account.getBalance());
                System.out.println("Выведено: " + amount + " рублей по 100 рублей (" + amount / 100 + " штук).");

            } else if (number == 5 && account.getCardNumber().equals(CardNumber) && account.getBalance() >= amount) {
                account.setBalance(balance);
                System.out.println("Ваш баланс: " + account.getBalance());
                System.out.println("Выведено: " + amount + " рублей по 50 рублей (" + amount / 50 + " штук).");

            } else if (number == 6 && account.getCardNumber().equals(CardNumber) && account.getBalance() >= amount) {
                account.setBalance(balance);
                System.out.println("Ваш баланс: " + account.getBalance());
                System.out.println("Выведено: " + amount + " рублей по 10 рублей (" + amount / 10 + " штук).");

            }
        }
    }

}





