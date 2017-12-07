package com.net.common.models;

/**
 * Pojo for error response
 *
 * @author hardiku
 */
public class ErrorResponse extends Throwable
{

    private static final long serialVersionUID = 1L;

    private String errorMsg;
    private String errorCode;

    public ErrorResponse(String errorMsg, String errorCode)
    {
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
    }

    public ErrorResponse(String errorMsg)
    {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg()
    {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg)
    {
        this.errorMsg = errorMsg;
    }

    public String getErrorCode()
    {
        return errorCode;
    }

    public void setErrorCode(String errorCode)
    {
        this.errorCode = errorCode;
    }

}
