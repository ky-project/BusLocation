package org.zstuky.wechat.message.req;

/** 
 * Í¼Æ¬ÏûÏ¢ 
 *  
 * @author liufeng 
 * @date 2013-05-19 
 */  
public class ImageMessage extends BaseMessage {  
    // Í¼Æ¬Á´½Ó  
    private String PicUrl;  
  
    public String getPicUrl() {  
        return PicUrl;  
    }  
  
    public void setPicUrl(String picUrl) {  
        PicUrl = picUrl;  
    }  
}