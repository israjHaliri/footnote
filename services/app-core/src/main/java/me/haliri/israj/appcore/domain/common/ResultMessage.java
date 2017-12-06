package me.haliri.israj.appcore.domain.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by israjhaliri on 11/21/17.
 */
public class ResultMessage {
    private int status;
    private String error;
    private String message;
    private Object data;

    private static ResultMessage instance = null;

    protected ResultMessage() {
    }

    public static ResultMessage getInstance() {
        if (instance == null) {
            instance = new ResultMessage();
        }
        return instance;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static void setInstance(ResultMessage instance) {
        ResultMessage.instance = instance;
    }

    @Override
    public String toString() {
        return "ResultMessage{" +
                "status=" + status +
                ", error='" + error + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public Map getResponse() {
        Map res = new HashMap();
        res.put("status",this.status);
        res.put("error",this.error);
        res.put("message",this.message);
        res.put("data",this.data);
        return res;
    }
}
