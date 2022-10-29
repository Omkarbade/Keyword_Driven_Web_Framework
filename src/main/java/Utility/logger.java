package Utility;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;



public class logger extends Base{
    public static Logger log = LogManager.getLogger(Base.class);
    public static void main(String[] args) {
        log.info("This is an information message");
        log.error("This is an information message");
        log.warn("This is an information message");
        log.fatal("This is an information message");
    }
}
