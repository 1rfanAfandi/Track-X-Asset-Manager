package loginauth.utils;

import java.time.LocalDateTime;

public class AuthLogger {

    public static void log(String message) {
        System.out.println(
                "[" + LocalDateTime.now() + "] AUTH : " + message
        );
    }
}