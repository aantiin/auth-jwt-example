package id.co.antin.vo;

import id.co.antin.exception.ServiceException;
import id.co.antin.util.RestUtil;
import id.co.antin.util.StatusCode;
import org.springframework.http.ResponseEntity;

public abstract class AbstractRequestHandler {

    public ResponseEntity<ResultVO> getResult() {
        ResultVO result = new ResultVO();
        try {
            Object obj = processRequest();
            if (obj != null) {
                if (obj instanceof ResponseEntity) {
                    return (ResponseEntity) obj;
                } else {
                    result.setMessage(StatusCode.OK.name());
                    result.setResult(obj);
                }
            } else {
                result.setMessage(StatusCode.OK.name());
                result.setResult(null);
            }
        } catch (ServiceException e) {
            result.setMessage(e.getCode().name());
            result.setResult(e.getMessage());
        }

        return RestUtil.getJsonResponse(result);
    }

    public abstract Object processRequest();
}
