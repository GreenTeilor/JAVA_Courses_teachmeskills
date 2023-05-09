package client;

import paymentsystem.bankAccount.BankAccount;
import paymentsystem.dbConnection.DbUtils;
import paymentsystem.exceptions.BankAccountNotFoundException;
import paymentsystem.exceptions.MerchantAlreadyHasBankAccountNumberException;
import paymentsystem.exceptions.NoBankAccountsFoundException;
import paymentsystem.dbOperations.DBOperationsUtils;
import paymentsystem.merchant.Merchant;
import paymentsystem.service.MerchantService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Run {
    public static void main(String[] args) {
        try (Connection connection = DbUtils.getConnection()) {
            MerchantService service = new MerchantService(connection);
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
                        if (merchant != null) {
                            System.out.print("Input account number: ");
                            try {
                                DBOperationsUtils.saveBankAccountDB(connection, service.addBankAccount(merchant, new BankAccount(merchant.getId(), scanner.next())));
                            } catch (IllegalArgumentException e) {
                                System.out.println(e.getMessage());
                            }
                        } else {
                            System.out.println("There is no such merchant!");
                        }
                    }

                    case 3 -> {
                        System.out.print("Input merchant id: ");
                        Merchant merchant = service.getMerchantById(scanner.next());
                        if (merchant != null) {
                            System.out.print("Input bank account number: ");
                            BankAccount account = merchant.getAccountByNumber(scanner.next());
                            if (account != null) {
                                System.out.print("Input new account number: ");
                                String newAccountNumber = scanner.next();
                                try {
                                    BankAccount accountCopy = new BankAccount(account.getMerchantId(), account.getStatus(), account.getAccountNumber(), account.getCreationDate());
                                    service.updateBankAccount(account, newAccountNumber);
                                    DBOperationsUtils.updateBankAccountDB(connection, accountCopy, newAccountNumber);
                                } catch (BankAccountNotFoundException | MerchantAlreadyHasBankAccountNumberException |
                                         IllegalArgumentException e) {
                                    System.out.println(e.getMessage());
                                }
                            } else {
                                System.out.println("There is no such account!");
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
                            if (account != null) {
                                try {
                                    if (service.deleteBankAccount(account)) {
                                        DBOperationsUtils.deleteBankAccountDB(connection, account);
                                    }
                                } catch (BankAccountNotFoundException e) {
                                    throw new RuntimeException(e);
                                }
                            } else {
                                System.out.println("There is no such account!");
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
                        DBOperationsUtils.saveMerchantDB(connection, service.createMerchant(new Merchant(scanner.next())));
                    }

                    case 8 -> {
                        System.out.print("Input merchant id: ");
                        Merchant merchant = service.getMerchantById(scanner.next());
                        if (merchant != null) {
                            if (service.deleteMerchant(merchant.getId())) {
                                DBOperationsUtils.deleteMerchantDB(connection, merchant);
                                try {
                                    merchant.getBankAccounts().forEach(a -> {
                                        try {
                                            service.deleteBankAccount(a);
                                        } catch (BankAccountNotFoundException e) {
                                            System.out.println(e.getMessage());
                                        }
                                    });
                                    DBOperationsUtils.deleteMerchantBankAccountsDB(connection, merchant);
                                } catch (NoBankAccountsFoundException e) {
                                    //In this case we don't need to notify user, that merchant didn't have bank accounts
                                }
                            } else {
                                System.out.println("Merchant wasn't deleted!");
                            }
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
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
