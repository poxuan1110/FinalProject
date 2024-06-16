import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;


public class FinalProject {
    public static void main(String[] args) {
        while(true){
            System.out.println("Menu");
            System.out.println("________________________________________________________\n");
            System.out.println("1. Time zone conversion");
            System.out.println("2. List all the country");
            System.out.println("________________________________________________________");
            System.out.println("Please enter the function you want to operate.");
            Scanner scanner = new Scanner(System.in);
            int selectedNumber = insertNumberParser(scanner, 2);
            
            if(selectedNumber == 1){
                System.out.println("Function: 1. Current time 2. Time converted from Taiwan to other countries");
                System.out.println("Please enter the function you want to operate.");
                while(true){
                    int selectedNumber1 = insertNumberParser(scanner, 2);
                    if(selectedNumber1 == 1){
                        String formattedNow = getCurrentTime();
                        System.out.println("The current time in Taiwan is:" + formattedNow);
                        break;
                    }
                    else if(selectedNumber1 == 2){
                        System.out.println("Please enter the country you want to convert to");
                        String formattedNow = getCurrentTime();
                        System.out.println("The current time in Taiwan is: " + formattedNow);
                        while(true){
                            String country = scanner.nextLine();
                            AllCountry change = new AllCountry();
                            if (change.getOtherCountry().containsKey(country)){//單時區國家的時區轉換
                                double duration = change.getOtherCountry().get(country);
                                System.out.println("The current time in " + country + " is: " + plusTime(duration));
                                break;
                            } else if (change.getSpecialCountry().containsKey(country)){//多時區國家的時區轉換
                                HashMap<String, Double> allTimeZoneOfTheCountry = change.getSpecialCountry().get(country);
                                //使用者選擇想轉換的時區
                                System.out.println("The country above has these time zones.");
                                System.out.println("Please enter the number for the time zone you want to convert to.");
                                int count = 1;
                                //列出所有的時區
                                for (String key : allTimeZoneOfTheCountry.keySet()){
                                    System.out.println(count++ + ". " + key);              
                                }
                                while(true){
                                    //scanner讓使用者輸入數字(還沒寫)
                                    int specialCountryNumber = insertNumberParser(scanner, count-1);
                                    int i = 1;
                                    for (String key : allTimeZoneOfTheCountry.keySet()){
                                        if (i == specialCountryNumber){
                                            double duration = allTimeZoneOfTheCountry.get(key);
                                            System.out.println("The current time in that place is: " + plusTime(duration));
                                            break;
                                        }
                                        i++;
                                    }
                                    break;
                                }
                                break;
                            } else{//查無國家 讓使用者重新輸入
                                System.out.println("Country not found. Please check if the spelling is correct.");
                            }
                        }
                        break;
                    }
                    else System.out.println("Don't have this function. Please insert again.");
                }
                break;
            } else if(selectedNumber== 2){
                AllCountry allCountry = new AllCountry();
                String[] print = new String[199];
                HashMap<String, Double>  country = allCountry.getOtherCountry();
                Set<String> keys = country.keySet();
                int i = 0;
                for (String key : keys) {
                    print[i] = key;
                    i++;
                }
                HashMap<String,HashMap<String,Double>> special = allCountry.getSpecialCountry();
                keys = special.keySet();
                for (String key : keys) {
                    print[i] = key;
                    i++;
                }
                Arrays.sort(print);
                for(int j = 0; j < print.length; j++){
                    System.out.println(print[j]);
                }
                break;
            } else {
                System.out.println("Don't have this function. Please insert again.");
            }
        }

    }

    public static int insertNumberParser(Scanner scanner, int biggestNumber) {
        int ans = 0;
        boolean validInput = false;

        while (!validInput) {
            String text = scanner.nextLine();
            try {
                ans = Integer.parseInt(text);
                if (ans >= 1 && ans <= biggestNumber) {
                    validInput = true;
                } else {
                    System.out.println("Don't have this function. Please insert again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Don't have this function. Please insert again.");
            }
        }
        return ans;
    }

    private static String getCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedNow = now.format(formatter);
        return formattedNow;
    }

    private static String plusTime(double duration) {
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



