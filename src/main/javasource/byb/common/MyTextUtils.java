package byb.common;

import java.io.PrintWriter;
import java.io.StringWriter;

public final class MyTextUtils {

    public MyTextUtils() {
    }

    public static boolean isEmpty(CharSequence s) {
        return s == null?true:s.length() == 0;
    }

    public static boolean isBlank(CharSequence s) {
        if(s == null) {
            return true;
        } else {
            for(int i = 0; i < s.length(); ++i) {
                if(!Character.isWhitespace(s.charAt(i))) {
                    return false;
                }
            }

            return true;
        }
    }


    public static String getStackTraceString(Exception e) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);
        return printWriter.toString();
    }


}
