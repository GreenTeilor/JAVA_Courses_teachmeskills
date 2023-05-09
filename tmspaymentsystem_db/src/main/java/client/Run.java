package client;

import paymentsystem.bankAccount.BankAccount;
import paymentsystem.dbConnection.DbUtils;
import paymentsystem.exceptions.BankAccountNotFoundException;
import paymentsystem.exceptions.MerchantAlreadyHasBankAccountNumberException;
import paymentsystem.exceptions.NoBankAccountsFoundException;
import paymentsystem.merchant.Merchant;
import paymentsystem.service.MerchantService;

import java.sql.SQLException;
import java.util.Scanner;

public class Run {
    public static void main(String[] args) {
        try {
            MerchantService service = new MerchantService(DbUtils::getConnection);
            Scanner scanner = new Scanner(System.in);
            boolean isWorking = true;
            while (isWorking) {
                System.out.println("""
                        Options:
                        1) Get info about merchant bank accounts
                        2) Add new bank account to merchant
                        3) Edit merchant bank account number
                        4) Delete merchant bank account
                        5) Get merchant info
                        6) Get all merchants
                        7) Create merchant
                        8) Delete merchant
                        9) Exit
                        Which option you choose: """);
                switch (scanner.nextInt()) {
                    case 1 -> {
                        System.out.print("Input merchant id: ");
                        Merchant merchant = service.getMerchantById(scanner.next());
                        if (merchant != null) {
                            try {
                                service.getMerchantBankAccounts(merchant).forEach(System.out::println);
                            } catch (NoBankAccountsFoundException e) {
                                System.out.println(e.getMessage());
                            }
                        } else {
                            System.out.println("There's no such merchant!");
                        }
                    }

                    case 2 -> {
                        System.out.print("Input merchant id: ");
                        Merchant merchant = service.getMerchantById(scanner.next());
                        System.out.print("Input account number: ");
                        BankAccount account = new BankAccount(merchant.getId(), scanner.next());
                        try {
                            service.addBankAccount(merchant, account);
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                    case 3 -> {
                        System.out.print("Input merchant id: ");
                        Merchant merchant = service.getMerchantById(scanner.next());
                        if (merchant != null) {
                            System.out.print("Input bank account number: ");
                            BankAccount account = merchant.getAccountByNumber(scanner.next());
                            System.out.println("Input new bank account number: ");
                            try {
                                service.updateBankAccount(account, scanner.next());
                            } catch (MerchantAlreadyHasBankAccountNumberException | BankAccountNotFoundException e) {
                                System.out.println(e.getMessage());
                            }
                        } else {
                            System.out.println("There is no such merchant!");
                        }
                    }

                    case 4 -> {
                        System.out.print("Input merchant id: ");
                        Merchant merchant = service.getMerchantById(scanner.next());
                        if (merchant != null) {
                            System.out.print("Input bank account number: ");
                            BankAccount account = merchant.getAccountByNumber(scanner.next());
                            try {
                                service.deleteBankAccount(account);
                            } catch (BankAccountNotFoundException e) {
                                System.out.println(e.getMessage());
                            }
                        } else {
                            System.out.println("There is no such merchant!");
                        }
                    }

                    case 5 -> {
                        System.out.print("Input merchant id: ");
                        Merchant merchant = service.getMerchantById(scanner.next());
                        if (merchant != null) {
                            System.out.println(merchant);
                        } else {
                            System.out.println("There is no such merchant!");
                        }
                    }

                    case 6 -> service.getMerchants().forEach(System.out::println);

                    case 7 -> {
                        System.out.print("Input merchant name: ");
                        service.createMerchant(new Merchant(scanner.next()));
                    }

                    case 8 -> {
                        System.out.print("Input merchant id: ");
                        Merchant merchant = service.getMerchantById(scanner.next());
                        if (merchant != null) {
                            service.deleteMerchant(merchant);
                        } else {
                            System.out.println("There is no such merchant!");
                        }
                    }

                    default -> {
                        System.out.println("Exit");
                        isWorking = false;
                    }
                }
            }
            service.finishWork();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
