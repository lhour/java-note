package _2190300408hw4;

import _2190300408hw4.mylog.Logger;

public class AccountTester2 {
    public static void main(String args[]) {
        BankAccount2 bobsAccount, marysAccount, biffsAccount;
        bobsAccount = BankAccount2.example1();
        marysAccount = BankAccount2.example1();
        biffsAccount = BankAccount2.example2();
        marysAccount.setOwnerName("Mary");
        marysAccount.deposit(250);
        Logger.write(bobsAccount);
        Logger.write(marysAccount);
        Logger.write(biffsAccount);
    }
}
