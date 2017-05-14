package com.reslib.exception;

import com.reslib.exception.base.BaseException;

/**
 * Created by 阿飞 on 2017/4/10.
 */

public class ActivityNotFoundException extends BaseException {
    public static final String MESSAGE = "Context is Null or Not Found.";

    public ActivityNotFoundException(String message) {
        super(message);
    }
}
