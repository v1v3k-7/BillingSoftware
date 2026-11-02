
package validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author cv
 */
public class Validation 
{
    private Pattern name, email, phone, int_val, float_val;
    private Matcher match;
    String name_pattern="^[a-zA-Z0-9\\s]{3,50}$";
    String phone_pattern="^[0-9]{10}$";
    String email_pattern="^(?=.{1,100}$)[a-zA-Z0-9._%-]+@[a-zA-Z.-]+\\.[a-zA-Z]{2,}$";
    String only_int="^[0-9]{1,9}$";
    String only_float="^[0-9.]+$";
    public Validation()
    {
        name=Pattern.compile(name_pattern);
        email=Pattern.compile(email_pattern);
        phone=Pattern.compile(phone_pattern);
        int_val=Pattern.compile(only_int);
        float_val=Pattern.compile(only_float);
    }
    public boolean phoneValid(String no)
    {
        match=phone.matcher(no);
        return match.matches();
    }
    public boolean emailValid(String mail)
    {
        match=email.matcher(mail);
        return match.matches();
    }
    public boolean nameValid(String nem)
    {
        match=name.matcher(nem);
        return match.matches();
    }
    public boolean isInt(String val)
    {
        match=int_val.matcher(val);
        return match.matches();
    }
    public boolean isFloat(String val)
    {
        match=float_val.matcher(val);
        return match.matches();
    }
}
