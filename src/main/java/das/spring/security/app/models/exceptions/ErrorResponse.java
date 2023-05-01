package das.spring.security.app.models.exceptions;


public class ErrorResponse {
    private String errorDesc;
    private int status;
    private String uuid;

    public ErrorResponse(int status, String uuid, String errorDesc) {
        this.errorDesc = errorDesc;
        this.status = status;
        this.uuid = uuid;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
