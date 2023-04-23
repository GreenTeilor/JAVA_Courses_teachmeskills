package by.teachmeskills.hw_21042023.client;

import by.teachmeskills.hw_21042023.paymentsystem.bankAccount.BankAccount;
import by.teachmeskills.hw_21042023.paymentsystem.constants.FilesPaths;
import by.teachmeskills.hw_21042023.paymentsystem.exceptions.BankAccountNotFoundException;
import by.teachmeskills.hw_21042023.paymentsystem.exceptions.MerchantAlreadyHasBankAccountNumberException;
import by.teachmeskills.hw_21042023.paymentsystem.exceptions.NoBankAccountsFoundException;
import by.teachmeskills.hw_21042023.paymentsystem.filesOperations.FilesOperationsUtils;
import by.teachmeskills.hw_21042023.paymentsystem.merchant.Merchant;
import by.teachmeskills.hw_21042023.paymentsystem.service.MerchantService;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class Run {
    public static void main(String[] args) {
        File merchantsFile = new File(FilesPaths.MERCHANTS_FILE);
        File bankAccountsFile = new File(FilesPaths.BANK_ACCOUNTS_FILE);
        MerchantService service = new MerchantService(merchantsFile, bankAccountsFile);
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
                            merchant.getBankAccounts().forEach(System.out::println);
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
                            FilesOperationsUtils.saveBankAccount(bankAccountsFile, service.addBankAccount(merchant, new BankAccount(merchant.getId(), scanner.next())));
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
                                FilesOperationsUtils.updateBankAccountInFile(bankAccountsFile, accountCopy, newAccountNumber);
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
                                    FilesOperationsUtils.deleteBankAccountsFromFile(bankAccountsFile, List.of(account));
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
                    FilesOperationsUtils.saveMerchant(merchantsFile, service.createMerchant(new Merchant(scanner.next())));
                }
                case 8 -> {
                    System.out.print("Input merchant id: ");
                    Merchant merchant = service.getMerchantById(scanner.next());
                    if (merchant != null) {
                        if (service.deleteMerchant(merchant.getId())) {
                            FilesOperationsUtils.deleteMerchantFromFile(merchantsFile, merchant);
                            try {
                                merchant.getBankAccounts().forEach(a -> {
                                    try {
                                        service.deleteBankAccount(a);
                                    } catch (BankAccountNotFoundException e) {
                                        System.out.println(e.getMessage());
                                    }
                                });
                                FilesOperationsUtils.deleteBankAccountsFromFile(bankAccountsFile, merchant.getBankAccounts());
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
    }
}
