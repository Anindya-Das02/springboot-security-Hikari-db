package das.spring.security.app.util;

import java.util.UUID;

public interface CommonUtil {
    static String generateUUID(){
        return UUID.randomUUID().toString();
    }
}
