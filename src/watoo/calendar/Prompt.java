package watoo.calendar;

import java.util.Scanner;

public class Prompt {

    public void printMenu() {
        System.out.println("+---------------------+");
        System.out.println("| 1. 일정 목록");
        System.out.println("| 2. 일정 검색");
        System.out.println("| 3. 달력 보기");
        System.out.println("| h. 도움말 q. 종료");
        System.out.println("+---------------------+");
    }

    /**
     * 1. switch case - String
     * 2. Plan class - refactoring?
     *
     */

    public void runPrompt() {
        printMenu();
        Scanner sc = new Scanner(System.in);
        Calendar cal = new Calendar();

        label:
        while(true) {
            System.out.println("1.일정 목록 2. 일정 검색 3. 달력보기 h. 도움말 q. 종료");
            String cmd = sc.next();
            switch (cmd) {
                case "1":
                    cmdRegister(sc, cal);
                    break;
                case "2":
                    cmdSearch(sc, cal);
                    break;
                case "3":
                    cmdCal(sc, cal);
                    break;
                case "h":
                    printMenu();
                    break;
                case "q":
                    break label;
            }

        }

        System.out.println("Bye~");
        sc.close();
    }

    private void cmdCal(Scanner sc, Calendar c) {
        int month = -1;
        int year = -1;

        System.out.println("년도를 입력하세요");
        System.out.print("YEAR> ");
        year = sc.nextInt();
        System.out.println("달을 입력하세요");
        System.out.print("MONTH> ");
        month = sc.nextInt();

        if(month>12 || month<1) {
            System.out.println("잘못된 입력입니다.");
            return;
        }
        c.printCalendar(year,month);
    }

    private void cmdSearch(Scanner sc, Calendar c) {
        System.out.println("[일정 검색]");
        System.out.println("날짜를 입력해 주세요(yyyy-MM-dd).");
        String date = sc.next();
        PlanItem plan;
        plan = c.searchPlan(date);
        if (plan != null) {
            System.out.println(plan.detail);
        }
        else {
            System.out.println("일정이 없습니다.");
        }
    }

    private void cmdRegister(Scanner sc, Calendar c) {
        System.out.println("[새 일정 등록]");
        System.out.println("날짜를 입력해 주세요(yyy-MM-dd).");
        String date = sc.next();
        String text = "";
        System.out.println("일정을 입력해 주세요.(문장의 끝에 ;을 입력해 주세요)");
        while(true) {
            String word = sc.next();
            text +=word+"";
            if(word.endsWith(";")) {
                break;
            }
        }
        c.registerPlan(date, text);
    }


    public static void main(String[] args) {
        //셸 실행
        Prompt p = new Prompt();
        p.runPrompt();
    }
}

