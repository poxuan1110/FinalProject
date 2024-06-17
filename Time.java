import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Time {

    public String getCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedNow = now.format(formatter);
        return formattedNow;
    }

    public String plusTime(double duration) { //計算轉換後時間
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime plusTime;

        if (duration % 1 == 0){
            int hours = (int) duration;
            plusTime = now.plusHours(- 8 + hours);
        } else{
            int hours = (int) duration;
            int minutes = (int) ((duration - hours) * 60);
            plusTime = now.plusHours(-8 + hours).plusMinutes(minutes); // 加上3小時30分鐘
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedPlusTime = plusTime.format(formatter);
        return formattedPlusTime;
    }
}
