import java.util.concurrent.TimeUnit;

/**
 * Created by caiyusong on 2017/6/28.
 */
public class TimeRecorder {

    private long startMillis;

    private TimeRecorder(){}

    public static TimeRecorder start(){
        TimeRecorder timeRecord = new TimeRecorder();
        timeRecord.startMillis = System.currentTimeMillis();
        return timeRecord;
    }

    public long recordSecond(){
        long nowMilles = System.currentTimeMillis();
        return TimeUnit.MILLISECONDS.toSeconds(nowMilles-startMillis);
    }

    public long recordMillis(){
        long nowMilles = System.currentTimeMillis();
        return nowMilles-startMillis;
    }

    public long recordMillisAndReset(){
        long nowMilles = System.currentTimeMillis();
        long result = nowMilles-startMillis;
        startMillis = nowMilles;
        return result;
    }

}
