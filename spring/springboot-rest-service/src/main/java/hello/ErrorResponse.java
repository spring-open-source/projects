package hello;

/**
 *
 * @author hardiku
 */
public class ErrorResponse extends Throwable
{

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
