package _2190300408hw4;
import _2190300408hw4.mylog.Logger;
public class AccountTester {
    public static void main(String args[]) {
        BankAccount anAccount;
        anAccount = new BankAccount("ZhangLi", 100023, 0);
        anAccount.setBalance(anAccount.getBalance() + 100);
        Logger.write(anAccount);
        anAccount = new BankAccount("WangFang", 100024, 0);
        Logger.write(anAccount);
        anAccount.deposit(225.67f);
        anAccount.deposit(300.00f);
        Logger.write(anAccount);
        anAccount.withdraw(400.17f);
        Logger.write(anAccount);
    }
}
