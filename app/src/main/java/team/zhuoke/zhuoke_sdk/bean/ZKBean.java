package team.zhuoke.zhuoke_sdk.bean;

public class ZKBean {


    /**
     * code : 0
     * msg : 成功
     * result : 请求成功
     */

    private String code;
    private String msg;
    private String result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "{" +
                "code:'" + code + '\'' +
                ", msg:'" + msg + '\'' +
                ", result:'" + result + '\'' +
                '}';
    }
}
