package id.co.antin.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultVO {
    private String message;
    private Object result;

    public ResultVO() {
    }

    public ResultVO(String message, Object result) {
        this.message = message;
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ResultVO{");
        sb.append("message='").append(message).append('\'');
        sb.append(", result=").append(result);
        sb.append('}');
        return sb.toString();
    }
}
