package cn.ccsu.store.controller.ex;

/**
 * @Author 潇洒哥queen
 * @Date 2022/3/28 18:43
 * @Version 1.0
 */
public class FileStateException extends FileUploadException{
    public FileStateException() {
        super();
    }

    public FileStateException(String message) {
        super(message);
    }

    public FileStateException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileStateException(Throwable cause) {
        super(cause);
    }

    protected FileStateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
