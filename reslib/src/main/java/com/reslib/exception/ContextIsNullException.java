package com.reslib.exception;

import com.reslib.exception.base.BaseException;

/**
 * Created by 阿飞 on 2017/4/10.
 */

public class ContextIsNullException extends BaseException {
    public static final String MESSAGE = "Context is Null.";

    public ContextIsNullException(String message) {
        super(message);
    }
}
