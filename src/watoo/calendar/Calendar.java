package watoo.calendar;


import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;

public class Calendar {

    private final int[] MAX_DAYS = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private final int[] LEAP_MAX_DAYS = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    private HashMap<Date, PlanItem> planMap;

    public Calendar() {
        planMap = new HashMap<Date, PlanItem>();
    }


    /**
     * @param strdate ex: "2017-06-20"
     * @param plan
     */


    public void registerPlan(String strdate, String plan) {
        PlanItem p = new PlanItem(strdate, plan);
        planMap.put(p.getPlanDate(), p);
    }

    public PlanItem searchPlan(String strdate) {
        Date date = PlanItem.getDatefromString(strdate);
        return planMap.get(date);
    }

    //윤년 계산
    public boolean isLeapYear(int year) {
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            return true;
        }
        return false;
    }

    public int getMaxDaysOfMonth(int year, int month) {
        if (isLeapYear(year)) {
            return LEAP_MAX_DAYS[month];
        } else {
            return MAX_DAYS[month];
        }
    }

    public int getWeekday(int year, int month, int day) {

        int syear = 1970;
        final int STANDARD_WEEKDAY = 3; //1970/01/01 = Thurday

        int count = 0;

        for (int i = syear; i < year; i++) {
            int delta = isLeapYear(year) ? 366 : 365;
            count += delta;
        }

        for (int i = 1; i < month; i++) {
            int delta = getMaxDaysOfMonth(year, i);
            count += delta;
        }

        count += day - 1;

        int weekday = (count + STANDARD_WEEKDAY) % 7;

        return weekday;
    }

    public void printCalendar(int year, int month) {
        System.out.printf("  <<%4d년 %3d월>>\n", year, month);
        System.out.println(" SU MO TU WE TH FR SA");
        System.out.println("----------------------");

        //get weekday automatically
        int weekday = getWeekday(year, month, 1);
        //print blank space
        for (int i = 0; i < weekday; i++) {
            System.out.print("   ");
        }
        int maxDay = getMaxDaysOfMonth(year, month);

        for (int i = 1; i <= maxDay; i++) {
            System.out.printf("%3d", i);
            if ((weekday + i) % 7 == 0) {
                System.out.println();
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Calendar cal = new Calendar();
    }
}

