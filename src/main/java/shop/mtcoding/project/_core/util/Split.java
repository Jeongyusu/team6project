package shop.mtcoding.project._core.util;

public class Split {

    // 년/월/일 -> 년으로 포맷
    public static String YearDateSplit(String date) {
        String[] splitDate = date.split("-");
        String year = splitDate[0];
        return year;
    }

    // 주소 파싱 -> 예) 창원시 그린동
    public static String AddressSplit(String address) {
        String[] splitAddress = address.split(" ");
        String formatAddress = splitAddress[0] + " " + splitAddress[1];
        return formatAddress;
    }
}
