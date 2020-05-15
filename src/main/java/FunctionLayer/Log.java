package FunctionLayer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;


public class Log {

    private static final String FILENAME = "demo.log";
    private static final String FILEPATH = "";

    private static final Level THRESHOLD = Level.FINEST;

    //private static final String FILEPATH = "/var/log/tomcat8/";
    private static final String PATH = FILEPATH + FILENAME;

    private Log() {
    }

    private static void log(Level lvl, String decription) throws Exception {

        Logger logger = Logger.getLogger(Log.class.getName());   // All operations on logger are thread safe

        FileHandler fh = new FileHandler(PATH, true);
        fh.setFormatter(new VerySimpleFormatter());
        logger.addHandler(fh);

        logger.setLevel(THRESHOLD);  //Thresshold for logging

        logger.log(lvl ,  decription  );

        fh.close();

    }

    public static void severe(String description ) {

        try {
            log(Level.SEVERE,description );

        } catch (Exception e) {
        }
    }

    public static void warning(String description){
        try{
            log(Level.WARNING,description);

        }catch (Exception e){
        }
    }

    public static void info(String description ) {

        try {
            log(Level.INFO,description );
        } catch (Exception e) {
        }
    }

    public static void fine(String description ) {

        try {
            log(Level.FINE,description );
        } catch (Exception e) {
        }
    }


}



class VerySimpleFormatter extends Formatter {

    private static final String PATTERN = "yyyy-MM-dd'T'HH:mm:ss";


    @Override
    public String format(final LogRecord record) {
        return String.format("%1$s %2$-7s %3$s\n",
                new SimpleDateFormat(PATTERN).format(
                        new Date(record.getMillis())),
                            record.getLevel().getName(),
                            formatMessage(record));
    }
}
