package watoo.calendar;

import java.lang.Math;

public class Calendar {

    private final int[] MAX_DAYS = {31 , 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private final int[] LEAP_MAX_DAYS = {31 , 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private final int WEEKDAY_NUMBER = 7;
    //윤년 계산
    public boolean isLeapYear(int year) {
        if(year % 4==0 && year % 100!=0 || year % 400 ==0) {
            return true;
        }
        return false;
    }

    public int getMaxDaysOfMonth(int year, int month) {
        if(isLeapYear(year)) {
            return LEAP_MAX_DAYS[month-1];
        }
        else {
            return MAX_DAYS[month-1];
        }
    }

    public int getWeekday(int year, int month) {
        //2022년 1월 1일은 토요일이더-기준점
        int dayPlus=0;
        int weekday=0;
        int plusWeekday=0;

        for (int i = 0; i < year; i++) {
            if(isLeapYear(year)) {
                dayPlus+=2;
            }
            else {
                dayPlus++;
            }
        }
        weekday = dayPlus%7; //여기서 weekday는 해당 년도의 1월1일의 일수를 의미함
        //여기까진 각 년도의 1월1일을 맞췃음
        //이제 각 월의 날짜를 계산해 그만틈 날짜를 미뤄주면 끝!
        for (int i = 0; i < month-1; i++) {
            if(isLeapYear(year)) {
                plusWeekday+=LEAP_MAX_DAYS[i];
            }
            else {
                plusWeekday+=MAX_DAYS[i];
            }
        }
        weekday=(plusWeekday%7)-(WEEKDAY_NUMBER-weekday);

        return Math.abs(weekday);
        //
    }


    //첫번째 요일을 입력하세요 (일, 월, 화, 수, 목, 금 , 토 )
    //WEEKDAY> 수
    //수요일부터 시작하는 달력 출력

    public void printCalendar(int year, int month) {
        System.out.printf("  <<%4d년 %3d월>>\n",year, month);
        System.out.println(" SU MO TU WE TH FR SA");
        System.out.println("----------------------");

        //get weekday automatically
        int weekday = getWeekday(year, month);
        //print blank space
        for (int i = 0; i < weekday; i++) {
            System.out.print("   ");
        }
        int maxDay = getMaxDaysOfMonth(year, month);
        for (int i = 1; i <= maxDay; i++) {
            System.out.printf("%3d",i);
            if((weekday+i)%7==0) {
                System.out.println();
            }
        }
        System.out.println();


        /*날짜계산
        1년도 1월 1일은 월요일이다.
        각 해가 지날수록 같은 년도의 요일이 1일씩 밀린다
        but 윤년은 2일씩 밀림
        먼저 각 월 1일의 요일을 계산하고
        그 요일을 미는데 7로 나눈 나머지를 민다.
         */
    }

    public static void main(String[] args) {

    }
}

