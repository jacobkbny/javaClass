import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// hdd 

// Ram  

//  사용자 -> ram(sort안에 있는것들) 저장  -> hdd (sort) 

// 사용자 -> ram(sort안에 있는것들) 저장  -> hdd (sort, kakao) (X)

public class Sort {

    public static class Bank {
        // 계좌주
        public String owner;
        // 계좌번호
        public String Account;
        // 활성여부
        public boolean activation;
        // 잔액
        public int balance;

        public String password;
    }

    public static void main(String[] args) {
        List<Bank> KB = new ArrayList<Bank>();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("원하시는 서비스를 선택해주세요");
            System.out.println("1.계좌생성 2.잔액 조회 3. 잔액 충전 4. 송금");

            String input = sc.nextLine();

            // select
            // switch(input){
            // case "1": createBank(sc, KB);
            // case "2": checkBalance(sc, KB);
            // case "3": addBalance(sc, KB);
            // case "4": transfer(sc, KB);
            // }

            if (input.equals("1")) {
                // 계좌 생성
                createBank(sc, KB);
            }
            if (input.equals("2")) {
                // 잔액 조회
                checkBalance(sc, KB);
            }
            if (input.equals("3")) {
                // 잔액 충전\
                addBalance(sc, KB);
            }
            if (input.equals("4")) {
                // 송금
                transfer(sc, KB);
            }
            if (input.equals("5")) {
                break;
            } else {
                System.out.println("존재하지 않는 숫자입니다 다시 입력해주세요.");
            }

        }

        sc.close();

    }

    public static void createBank(Scanner sc, List<Bank> KB) {
        Bank bank = new Bank();
        System.out.println("성함을 입력해주세요");
        bank.owner = sc.nextLine();
        bank.Account = generateBankAccount();
        bank.activation = true;
        System.out.println("설정할 비밀번호 4자리를 입력해주세요");
        String password = sc.nextLine();
        System.out.println("비밀번호 4자리를 다시 입력해주세요");
        if (password.equals(sc.nextLine())) {
            bank.password = password;
        } else {
            System.out.println("입력하신 비밀번호가 올바르지 않습니다.");
        }
        bank.balance = 0;
        System.out.println("계좌번호:" + bank.Account);
        KB.add(bank);
    }

    public static void checkBalance(Scanner sc, List<Bank> KB) {
        int index = validate(sc, KB);
        if (index != -1) {
            System.out.println(KB.get(index).balance);
        }
    }

    public static void addBalance(Scanner sc, List<Bank> KB) {

        int index = validate(sc, KB);
        if (index != -1) {
            System.out.println("충전하실 액수를 입력해주세요");
            int add = Integer.parseInt(sc.nextLine());
            KB.get(index).balance += add;
            System.out.println("충전후 잔액은 " + KB.get(index).balance + "원 입니다");
        } else {
            System.out.println("비밀번호가 틀렸습니다.");
        }

       
    }

    public static int validate(Scanner sc, List<Bank> KB) {
        int index = -1;
        System.out.println("계좌번호를 입력해주세요.");
        String account = sc.nextLine();
        for (int i = 0; i < KB.size(); i++) {
            Bank sender = KB.get(i);
            if (sender.Account.equals(account)) {
                System.out.println("비밀번호를 입력해주세요");
                String password = sc.nextLine();
                if (sender.password.equals(password)) {
                    index = i;
                }
            }
        }

        return index;
    }

    // 계좌번호 생성
    public static String generateBankAccount() {
        // 000000-00-000000
        int numbers = (int) (Math.random() * 999999 + 1000000);

        String firstPart = String.valueOf(numbers);

        numbers = (int) (Math.random() * 99 + 10);

        String secondPart = String.valueOf(numbers);

        numbers = (int) (Math.random() * 999999 + 1000000);

        String lastPart = String.valueOf(numbers);

        return firstPart + "-" + secondPart + "-" + lastPart;
    }
    // 잔액 확인

    // 송금
    public static void transfer(Scanner sc, List<Bank> KB) {
        int index = validate(sc, KB);
        if (index != -1) {
            Bank sender = KB.get(index);
            System.out.println("받으실 분의 계좌번호를 작성해주세요");
            String receiver = sc.nextLine();
            for (int j = 0; j < KB.size(); j++) {
                Bank Receiver = KB.get(j);
                if (Receiver.Account.equals(receiver)) {
                    System.out.println("송금하실 금액을 입력해주세요");
                    int amount = Integer.parseInt(sc.nextLine());
                    if (amount > KB.get(index).balance) {
                        System.out.println("잔액이 부족합니다");
                    } else {
                        sender.balance -= amount; // !!
                        Receiver.balance += amount;
                        System.out.println("송금 후 잔액은 " + KB.get(index).balance + "원 입니다.");
                    }
                }
            }

        }

    }

    /*
     * System.out.println("받으실 분의 계좌번호를 작성해주세요");
     * String receiver = sc.nextLine();
     * for (int j = 0; j < KB.size(); j++) {
     * Bank Receiver = KB.get(j);
     * if (Receiver.Account.equals(receiver)) {
     * System.out.println("송금하실 금액을 입력해주세요");
     * int amount = Integer.parseInt(sc.nextLine());
     * if (amount > KB.get(i).balance) {
     * System.out.println("잔액이 부족합니다");
     * } else {
     * sender.balance -= amount; // !!
     * Receiver.balance += amount;
     * System.out.println("송금 후 잔액은 " + KB.get(i).balance + "원 입니다.");
     * }
     * }
     * }
     * 
     */

}