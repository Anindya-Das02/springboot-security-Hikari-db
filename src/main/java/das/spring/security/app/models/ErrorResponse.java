package das.spring.security.app.models;

import das.spring.security.app.util.CommonUtil;

public class ErrorResponse {

    private String uuid;
    private String errorDesc;
    private int status;
    private String message;

    private ErrorResponse(String uuid, String errorDesc, int status, String message) {
        this.uuid = uuid;
        this.errorDesc = errorDesc;
        this.status = status;
        this.message = message;
    }

    public static class ErrorResponseBuilder{
        private String uuid;
        private String errorDesc;
        private int status;
        private String message;

        public ErrorResponseBuilder uuid(String uuid){
            this.uuid = uuid;
            return this;
        }

        public ErrorResponseBuilder errorDesc(String errorDesc){
            this.errorDesc = errorDesc;
            return this;
        }

        public ErrorResponseBuilder status(int status){
            this.status = status;
            return this;
        }

        public ErrorResponseBuilder message(String message){
            this.message = message;
            return this;
        }

        public ErrorResponse build(){
            return new ErrorResponse(uuid,errorDesc,status,message);
        }
    }
}
