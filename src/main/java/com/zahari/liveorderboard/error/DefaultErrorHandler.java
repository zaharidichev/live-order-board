package com.zahari.liveorderboard.error;

import com.zahari.liveorderboard.error.exception.LiveOrderBookServiceException;
import com.zahari.liveorderboard.error.exception.OrderNotFoundException;
import com.zahari.liveorderboard.error.exception.ValidationException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * This error handler is responsible for
 * mapping expections that have occured in the service
 * to a pretty Json output, so the user is not presented
 * with unclear stacktraces and no internals of the system
 * are revealed
 *
 * Created by zahari on 26/03/2017.
 */
@ControllerAdvice
public class DefaultErrorHandler {

    protected static final Logger logger = LogManager.getLogger(DefaultErrorHandler.class);

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({OrderNotFoundException.class})
    public ServerError handleException(OrderNotFoundException ex) {
        logger.error(ex);
        return new ServerError(ex.getMessage(),"NOT_FOUND");
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler({ValidationException.class})
    public ServerError handleException(ValidationException ex) {
        logger.error(ex); // log it to sonsole for sure..
        return new ServerError(ex.getMessage(),"NOT_ACCEPTABLE");
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({LiveOrderBookServiceException.class})
    private ServerError handleException(LiveOrderBookServiceException ex) {
        logger.error(ex);
        return new ServerError(ex.getMessage(),"INTERNAL_SERVER_ERROR");
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Throwable.class})
    public ServerError handleException(Throwable ex) {
        logger.error(ex); // log it to sonsole for sure..
        return new ServerError(ex.getMessage(),"INTERNAL_SERVER_ERROR");
    }


}
