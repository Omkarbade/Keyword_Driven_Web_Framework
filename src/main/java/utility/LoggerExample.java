package utility;

import Base.Base;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;



//create logger main method  and define log levels.
public class LoggerExample extends Base {
    public static Logger log = LogManager.getLogger(Utility.class);
    public static void main(String[] args) {
        log.info("This is an information message");
        log.error("This is an information message");
        log.warn("This is an information message");
        log.fatal("This is an information message");
    }
}

