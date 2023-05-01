package das.spring.security.app.models.exceptions;

import java.io.IOException;

public class UnAuthorizedRequest extends IOException {
    public UnAuthorizedRequest(String message){
        super(message);
    }
}
