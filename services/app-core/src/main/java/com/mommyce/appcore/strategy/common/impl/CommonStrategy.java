package com.mommyce.appcore.strategy.common.impl;

import com.mommyce.appcore.constant.Response;
import com.mommyce.appcore.constant.ResponseStatus;
import com.mommyce.appcore.domain.common.ResultMessage;
import com.mommyce.appcore.strategy.common.ResultMessageStrategy;
import org.apache.commons.httpclient.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * Created by israjhaliri on 8/28/17.
 */
@Service
public class CommonStrategy {

    public ResultMessage setResultMessage(ResponseStatus paramteresResponseStatus, String parametersErroMessage, Object paramtersData) {
        ResultMessageStrategy resultMessageStrategy = (ResponseStatus responseStatus, String erroMessage, Object data) -> {
            ResultMessage resultMessage = new ResultMessage();
            if(responseStatus.equals(ResponseStatus.SUCCESS)){
                resultMessage.setMessage(Response.SUCCESS_PROCESS);
                resultMessage.setStatus(HttpStatus.SC_OK);
                resultMessage.setError(erroMessage);
                resultMessage.setData(data);
            }else
            {
                resultMessage.setMessage(Response.FAILED_PROCESS);
                resultMessage.setStatus(HttpStatus.SC_METHOD_FAILURE);
                resultMessage.setError(erroMessage);
                resultMessage.setData(data);
            }
            return resultMessage;
        };
        return resultMessageStrategy.setResult(paramteresResponseStatus, parametersErroMessage, paramtersData);
    }
}
