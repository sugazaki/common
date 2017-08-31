import org.joda.time.DateTime;

import java.util.Date;

/**
 * Created by caiyusong on 2017/8/21.
 */
public class TimeUtils {
    public static Date today(){
        DateTime today = new DateTime().withTimeAtStartOfDay();
        return today.toDate();
    }

}
