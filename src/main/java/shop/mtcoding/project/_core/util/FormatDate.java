package shop.mtcoding.project._core.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FormatDate {

    // yyyy-mm-dd 날짜 포맷 메소드
    public static String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    // yyyy 날짜 포맷 메소드
    public static String formatDateYear(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
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

}
