package shop.mtcoding.project._core.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FormatDate {

    // yyyy-mm-dd 날짜 포맷 메소드
    public static String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    // yyyy-mm-dd 날짜 포맷 리스트타입 메소드
    public static List<String> formatDateList(List<Date> dates) {
        List<String> formatDateList = new ArrayList<>();
        for (Date date : dates) {
            formatDateList.add(formatDate(date));
        }
        return formatDateList;
    }

    // D-day 마감일
    public static String DdayFormatDate(LocalDate dueDate) {
        // 마감일 Integer로 변환
        Integer year = dueDate.getYear();
        Integer month = dueDate.getMonthValue();
        Integer day = dueDate.getDayOfMonth();

        // 현재일 - 마감일
        LocalDate deadLine = LocalDate.of(year, month, day);
        LocalDate currentDate = LocalDate.now();
        Long daysDifference = ChronoUnit.DAYS.between(currentDate, deadLine);

        if (daysDifference == 0) {
            return "D-Day";
        } else {
            return "D - " + daysDifference;
        }

    }

    // yyyy 날짜 포맷 메소드
    public static String formatDateYear(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        return sdf.format(date);
    }
}