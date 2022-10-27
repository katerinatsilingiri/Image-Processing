package ce326.hw2;

public class UnsupportedFileFormatException extends java.lang.Exception{
    private String message;
    static final long serialVersionUID = -4567891456L;
    public UnsupportedFileFormatException() {
    }

    public UnsupportedFileFormatException(String msg) {
        message = msg;
    }

    public String returnMessage() {
        return(message);
    }

}
