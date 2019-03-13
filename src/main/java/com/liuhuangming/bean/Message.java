package com.liuhuangming.bean;

/**
 *    消息实体
 * @author LHM
 *
 */
public class Message {
    private String message;
    private long code;

    public Message() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }
}
