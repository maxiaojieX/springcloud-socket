package com.socket.bean;

/**
 * Created by xiaojie.Ma on 2019/1/3.
 */
public class Response {

    public Response(String responseMessage){
        this.responseMessage = responseMessage;
    }

    private String responseMessage;

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseMessage(){
        return responseMessage;
    }
}
