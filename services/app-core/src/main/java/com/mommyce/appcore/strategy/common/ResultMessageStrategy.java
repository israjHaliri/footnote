package com.mommyce.appcore.strategy.common;

import com.mommyce.appcore.constant.ResponseStatus;
import com.mommyce.appcore.domain.common.ResultMessage;

/**
 * Created by israjhaliri on 12/6/17.
 */
public interface ResultMessageStrategy {

    ResultMessage setResult(ResponseStatus responseStatus, String erroMessage, Object data);

}
