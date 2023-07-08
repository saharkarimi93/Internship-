package vault.voyage.app.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Validator {
    private Validator(){}
    public static boolean validPassword(String pass){
        Pattern p = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$");
        Matcher m = p.matcher(pass);
        return m.find();
    }
    public static boolean validUsername(String username){
        return username.length()>=4 && username.length()<=18;
    }
    public static boolean validEmail(String email){
        Pattern p = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        Matcher m = p.matcher(email);
        return m.find();
    }
    public static boolean validPhoneNumber(String number) {
        Pattern pattern = Pattern.compile("^\\+?[1-9][0-9]{7,14}");
        if (number.length() == 10 && pattern.matcher(number).find()) {
            return true;
        }
        return false;
    }
}
