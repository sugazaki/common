/**
 * Created by caiyusong on 2017/6/22.
 */
public class ResultDTO<T> {
    private T data;
    private boolean success;
    private String msg;
    private int code;

    public static<T> ResultDTO<T> success(T data){
        return new ResultDTO<>(data,true,"");
    }

    public static<T> ResultDTO<T> fail(String msg){
        return new ResultDTO<>(null,false,msg);
    }

    public ResultDTO() {
    }

    public ResultDTO(T data, boolean success, String msg) {
        this.data = data;
        this.success = success;
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
