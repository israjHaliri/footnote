package com.haliri.israj.appcore.handler.impl;

import com.haliri.israj.appcore.constant.ResponseMessage;
import com.haliri.israj.appcore.constant.ResponseStatus;
import com.haliri.israj.appcore.domain.common.ResultMessage;
import com.haliri.israj.appcore.handler.ResponseHandler;
import org.apache.commons.httpclient.HttpStatus;

/**
 * Created by israjhaliri on 8/28/17.
 */
public class ResponseHandlerImpl implements ResponseHandler {

    @Override
    public ResultMessage setResult(ResponseStatus responseStatus, String erroMessage, Object data) {
        ResultMessage resultMessage = new ResultMessage();
        if(responseStatus.equals(ResponseStatus.SUCCESS)){
            resultMessage.setMessage(ResponseMessage.SUCCESS_PROCESS);
            resultMessage.setStatus(HttpStatus.SC_OK);
            resultMessage.setError(erroMessage);
            resultMessage.setData(data);
        }else
        {
            resultMessage.setMessage(ResponseMessage.FAILED_PROCESS);
            resultMessage.setStatus(HttpStatus.SC_METHOD_FAILURE);
            resultMessage.setError(erroMessage);
            resultMessage.setData(data);
        }
        return resultMessage;
    }
}
