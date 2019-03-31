package org.zstuky.wechat.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.zstuky.wechat.message.resp.Article;
import org.zstuky.wechat.message.resp.NewsMessage;
import org.zstuky.wechat.message.resp.TextMessage;
import org.zstuky.wechat.util.MessageUtil;

import com.hibernatespring.*;
//import com.myeclipse.persistencelayer.PersistenceLayerLinyx;
import com.persistencelayer.PersistenceLayerLiuhx;
//import com.myeclipse.persistencelayer.PersistenceLayerHuangzy;
/** 
 * 核心服务类 
 *  
 * @author liufeng 
 * @date 2013-05-20 
 */  
public class CoreService {
	
	static ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml"); 
    /** 
     * 处理微信发来的请求 
     *  
     * @param request 
     * @return 
     */  
    public static String processRequest(HttpServletRequest request) {  
        String respMessage = null;
        //网站地址
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
      //  PersistenceLayerHuangzy persistenceLayer3 = (PersistenceLayerHuangzy) ctx.getBean("persistenceLayerHuangzy"); 
        try {  
            // 默认返回的文本消息内容  
            String respContent = "请求处理异常，请稍候尝试！";  
  
            // xml请求解析  
            Map<String, String> requestMap = MessageUtil.parseXml(request);  
  
            // 发送方帐号（open_id）  
            String fromUserName = requestMap.get("FromUserName");  
            // 公众帐号  
            String toUserName = requestMap.get("ToUserName");  
            // 消息类型  
            String msgType = requestMap.get("MsgType");  
  
            // 默认的回复消息  
            TextMessage textMessage = new TextMessage();  
            textMessage.setToUserName(fromUserName);  
            textMessage.setFromUserName(toUserName);  
            textMessage.setCreateTime(new Date().getTime());  
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);  
            textMessage.setFuncFlag(0);
 
            // 文本消息  
          //接收用户的指令信息
            String content = requestMap.get("Content"); 
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
            	//默认回复主菜单
            	respContent = getMainMenu();
                textMessage.setContent(respContent);  
                respMessage = MessageUtil.textMessageToXml(textMessage);
                
                //将指令进行分割
                String[] sourceStrArray = content.split("\\+");
                
                PersistenceLayerLiuhx persistenceLayer2 = (PersistenceLayerLiuhx) ctx.getBean("persistenceLayerLiuhx");
                
                if(sourceStrArray[0].equalsIgnoreCase("c1"))
                {
                	//处理回复的消息，验证工号信息
            		int flag2 = persistenceLayer2.testJobNumber(fromUserName,sourceStrArray[1]);
        			if (flag2==0) {
        				respContent = "您回复的工号和验证码不正确，或者是格式错误，请重新输入！";  
                        textMessage.setContent(respContent);  
                        respMessage = MessageUtil.textMessageToXml(textMessage);
        			}
        			else {
        				respContent = "您已通过验证，谢谢关注！";  
                        textMessage.setContent(respContent);  
                        respMessage = MessageUtil.textMessageToXml(textMessage);
        			}
                }
                else  if(sourceStrArray[0].equalsIgnoreCase("c2"))
                {
                	   //处理回复的消息，更改办公电话
            		int flag2 = persistenceLayer2.updateTelphone(fromUserName,sourceStrArray[1]);
        			if (flag2==1) {
        				respContent = "您的办公电话已经成功更改";  
                        textMessage.setContent(respContent);  
                        respMessage = MessageUtil.textMessageToXml(textMessage);
        			}
        			else {
        				respContent = "请求失败，请稍后！";  
                        textMessage.setContent(respContent);  
                        respMessage = MessageUtil.textMessageToXml(textMessage);
        			}
                }
                else if(sourceStrArray[0].equalsIgnoreCase("c3"))
                {
                	   //处理回复的消息，更改手机号码
            		int flag2 = persistenceLayer2.updateMobile(fromUserName,sourceStrArray[1]);
        			if (flag2==1) {
        				respContent = "您的手机号码已经成功更改";  
                        textMessage.setContent(respContent);  
                        respMessage = MessageUtil.textMessageToXml(textMessage);
        			}
        			else {
        				respContent = "请求失败，请稍后再试！";  
                        textMessage.setContent(respContent);  
                        respMessage = MessageUtil.textMessageToXml(textMessage);
        			}
                }
                else{ 
                	respContent = "您没有按照要求进行输入，请重新输入！！"; 
                    textMessage.setContent(respContent);  
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                }
            	/*respContent = getMainMenu();
                textMessage.setContent(respContent);  
                respMessage = MessageUtil.textMessageToXml(textMessage);
                //接收用户的指令信息
                String content = requestMap.get("Content"); 
                // 创建图文消息  
                NewsMessage newsMessage = new NewsMessage();  
                newsMessage.setToUserName(fromUserName);  
                newsMessage.setFromUserName(toUserName);  
                newsMessage.setCreateTime(new Date().getTime());  
                newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);  
                newsMessage.setFuncFlag(0);      
                List<Article> articleList = new ArrayList<Article>();
                
                // 学院总揽的更多
                if ("A00".equals(content)) {
                	Article article1 = new Article();  
                    article1.setTitle("现任领导");  
                    article1.setDescription("");  
                    article1.setPicUrl(basePath+"images/KyMen.jpg");  
                    article1.setUrl("http://www.ky.zstu.edu.cn/mypage/index.php?name=leader");
                    
                    Article article2 = new Article();  
                    article2.setTitle("学院位置图");  
                    article2.setDescription("");  
                    article2.setPicUrl(basePath+"images/KyTi.jpg");  
                    article2.setUrl("http://cn.bing.com/ditu/default.aspx?rtp=adr.~pos.30.28429_120.0224_%e6%b5%99%e6%b1%9f%e7%9c%81%e6%9d%ad%e5%b7%9e%e5%b8%82%e6%96%87%e4%b8%80%e8%a5%bf%e8%b7%af960%e5%8f%b7_%e6%b5%99%e6%b1%9f%e7%90%86%e5%b7%a5%e5%a4%a7%e5%ad%a6%e7%a7%91%e6%8a%80%e4%b8%8e%e8%89%ba%e6%9c%af%e5%ad%a6%e9%99%a2-%e7%a7%91%e5%ad%a6%e9%a6%86_&where1=%e6%b5%99%e6%b1%9f%e7%9c%81%e6%9d%ad%e5%b7%9e%e5%b8%82%e6%96%87%e4%b8%80%e8%a5%bf%e8%b7%af960%e5%8f%b7&mode=T&FORM=LLDP#JnE9LiUyNXU2ZDU5JTI1dTZjNWYlMjV1NzcwMSUyNXU2NzZkJTI1dTVkZGUlMjV1NWUwMiUyNXU2NTg3JTI1dTRlMDAlMjV1ODk3ZiUyNXU4ZGVmOTYwJTI1dTUzZjclN2Vzc3QuMCU3ZXBnLjEmYmI9MzAuMjg5MzM5MTM4NzQxMiU3ZTEyMC4wMzMyODk3Njg2JTdlMzAuMjc5MjQwNjAxNDAwOCU3ZTEyMC4wMTE1MTAyMzE0");
                    
                    Article article3 = new Article();  
                    article3.setTitle("学院地图");  
                    article3.setDescription("");  
                    article3.setPicUrl(basePath+"images/KyKe.jpg");  
                    article3.setUrl("http://www.ky.zstu.edu.cn/mypage/index.php?name=pics");
  
                    articleList.add(article1);  
                    articleList.add(article2);  
                    articleList.add(article3); 

                    newsMessage.setArticleCount(articleList.size());  
                    newsMessage.setArticles(articleList);  
                    respMessage = MessageUtil.newsMessageToXml(newsMessage); 
                }
                // 办公服务的更多
                else if ("B00".equals(content)) {
                	Article article1 = new Article();  
                    article1.setTitle("办公电话");  
                    article1.setDescription("");  
                    article1.setPicUrl(basePath+"images/KyMen.jpg");  
                    article1.setUrl("http://www.ky.zstu.edu.cn/mypage/index.php?name=zuzhi");
                    
                    Article article2 = new Article();  
                    article2.setTitle("校车安排");  
                    article2.setDescription("");  
                    article2.setPicUrl(basePath+"images/KyZhong.jpg");  
                    article2.setUrl("http://www.zucc.edu.cn/index.php?c=index&a=tlist&catid=58");
                    
                    Article article3 = new Article();  
                    article3.setTitle("学院校历");  
                    article3.setDescription("");  
                    article3.setPicUrl(basePath+"images/KyMei.jpg");  
                    article3.setUrl("http://www.zucc.edu.cn/index.php?c=index&a=tlist&catid=55");
  
                    articleList.add(article1);  
                    articleList.add(article2);  
                    articleList.add(article3); 

                    newsMessage.setArticleCount(articleList.size());  
                    newsMessage.setArticles(articleList);  
                    respMessage = MessageUtil.newsMessageToXml(newsMessage); 
                }
                // 个人操作，回复个人操作的菜单
                else if ("3".equals(content)) {
                	//respContent = getPersonalMenu();
                    //textMessage.setContent(respContent);  
                    //respMessage = MessageUtil.textMessageToXml(textMessage);
                }*/
                
            }  
            // 图片消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {  
                respContent = "您发送的是图片消息！"; 
                textMessage.setContent(respContent);  
                respMessage = MessageUtil.textMessageToXml(textMessage);
            }  
            // 地理位置消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {  
                respContent = "您发送的是地理位置消息！"; 
                textMessage.setContent(respContent);  
                respMessage = MessageUtil.textMessageToXml(textMessage);
            }  
            // 链接消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {  
                respContent = "您发送的是链接消息！"; 
                textMessage.setContent(respContent);  
                respMessage = MessageUtil.textMessageToXml(textMessage);
            }  
            // 音频消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {  
                respContent = "您发送的是音频消息！"; 
                textMessage.setContent(respContent);  
                respMessage = MessageUtil.textMessageToXml(textMessage);
            }  
            // 事件推送  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {  
                // 事件类型  
                String eventType = requestMap.get("Event");  
                // 订阅  
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {  
                    respContent = "谢谢您的关注！";  
                    textMessage.setContent(respContent);  
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                }  
                // 取消订阅  
                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {  
                    // TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息  
                }  
                // 自定义菜单点击事件  
                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
                	// 事件KEY值，与创建自定义菜单时指定的KEY值对应  
                	String eventKey = requestMap.get("EventKey"); 
                	// 创建图文消息  
                    NewsMessage newsMessage = new NewsMessage();  
                    newsMessage.setToUserName(fromUserName);  
                    newsMessage.setFromUserName(toUserName);  
                    newsMessage.setCreateTime(new Date().getTime());  
                    newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);  
                    newsMessage.setFuncFlag(0);   
                    
                    
                	if ("C00".equalsIgnoreCase(eventKey)) {
                		PersistenceLayerLiuhx persistenceLayer2 = (PersistenceLayerLiuhx) ctx.getBean("persistenceLayerLiuhx");
                		int flag = persistenceLayer2.getWeChatOpenIdFlag(fromUserName);
                    	if (flag==0) {
            				respContent = "请回复您的工号，验证码。如：c1+20082254,123456,其中20082254是工号，123456是验证码！";  
                            textMessage.setContent(respContent);  
                            respMessage = MessageUtil.textMessageToXml(textMessage);
            			}
            			else {
            				respContent = "您已经绑定，无需重复绑定！";  
                            textMessage.setContent(respContent);  
                            respMessage = MessageUtil.textMessageToXml(textMessage);
            			}
            			
            			
                    }	//end of 绑定您的工号
                    
                    // 更改办公电话
                    else if ("C03".equalsIgnoreCase(eventKey)) {
                		//PersistenceLayerLinyx persistenceLayer = (PersistenceLayerLinyx) ctx.getBean("persistenceLayerLinyx");
                		PersistenceLayerLiuhx persistenceLayer2 = (PersistenceLayerLiuhx) ctx.getBean("persistenceLayerLiuhx");
                		String jobNumber = persistenceLayer2.getJobNumberByOpenID(fromUserName);
                		String telephone = persistenceLayer2.getTelephone(fromUserName);
            			if (jobNumber==null || jobNumber.equalsIgnoreCase("")) {
            				respContent = "您的微信账户还未与工号绑定！";  
                            textMessage.setContent(respContent);  
                            respMessage = MessageUtil.textMessageToXml(textMessage);
            			}
            			else {
            				respContent = "请回复输入您的新办公电话！如:c2+85857153,您现在的办公电话是:"+telephone;  
                            textMessage.setContent(respContent);  
                            respMessage = MessageUtil.textMessageToXml(textMessage);

            			}
                    }	//end of 更改办公电话
                    
                    // 更改手机号码
                    else if ("C04".equalsIgnoreCase(eventKey)) {
                		//PersistenceLayerLinyx persistenceLayer = (PersistenceLayerLinyx) ctx.getBean("persistenceLayerLinyx");
                		PersistenceLayerLiuhx persistenceLayer2 = (PersistenceLayerLiuhx) ctx.getBean("persistenceLayerLiuhx");
                		String jobNumber = persistenceLayer2.getJobNumberByOpenID(fromUserName);
                		String mobile = persistenceLayer2.getMobile(fromUserName);
                		if (jobNumber==null || jobNumber.equalsIgnoreCase("")) {
            				respContent = "您的微信账户还未与工号绑定！";  
                            textMessage.setContent(respContent);  
                            respMessage = MessageUtil.textMessageToXml(textMessage);
            			}
            			else {
            				respContent = "请回复输入您的新手机号码！如:c3+15868483883,您现在的办公电话是:"+mobile;  
                            textMessage.setContent(respContent);  
                            respMessage = MessageUtil.textMessageToXml(textMessage);
                         
            			}
                    }	//end of 更改手机号码
               /*     
                    List<Article> articleList = new ArrayList<Article>();
                    // 学院总揽的更多
                    if ("A00".equals(eventKey)) {
                    	Article article1 = new Article();  
                        article1.setTitle("现任领导");  
                        article1.setDescription("");  
                        article1.setPicUrl(basePath+"images/KyMen.jpg");  
                        article1.setUrl("http://www.ky.zstu.edu.cn/mypage/index.php?name=leader");
                        
                        Article article2 = new Article();  
                        article2.setTitle("学院位置图");  
                        article2.setDescription("");  
                        article2.setPicUrl(basePath+"images/KyTi.jpg");  
                        article2.setUrl("http://cn.bing.com/ditu/default.aspx?rtp=adr.~pos.30.28429_120.0224_%e6%b5%99%e6%b1%9f%e7%9c%81%e6%9d%ad%e5%b7%9e%e5%b8%82%e6%96%87%e4%b8%80%e8%a5%bf%e8%b7%af960%e5%8f%b7_%e6%b5%99%e6%b1%9f%e7%90%86%e5%b7%a5%e5%a4%a7%e5%ad%a6%e7%a7%91%e6%8a%80%e4%b8%8e%e8%89%ba%e6%9c%af%e5%ad%a6%e9%99%a2-%e7%a7%91%e5%ad%a6%e9%a6%86_&where1=%e6%b5%99%e6%b1%9f%e7%9c%81%e6%9d%ad%e5%b7%9e%e5%b8%82%e6%96%87%e4%b8%80%e8%a5%bf%e8%b7%af960%e5%8f%b7&mode=T&FORM=LLDP#JnE9LiUyNXU2ZDU5JTI1dTZjNWYlMjV1NzcwMSUyNXU2NzZkJTI1dTVkZGUlMjV1NWUwMiUyNXU2NTg3JTI1dTRlMDAlMjV1ODk3ZiUyNXU4ZGVmOTYwJTI1dTUzZjclN2Vzc3QuMCU3ZXBnLjEmYmI9MzAuMjg5MzM5MTM4NzQxMiU3ZTEyMC4wMzMyODk3Njg2JTdlMzAuMjc5MjQwNjAxNDAwOCU3ZTEyMC4wMTE1MTAyMzE0");
                        
                        Article article3 = new Article();  
                        article3.setTitle("学院地图");  
                        article3.setDescription("");  
                        article3.setPicUrl(basePath+"images/KyKe.jpg");  
                        article3.setUrl("http://www.ky.zstu.edu.cn/mypage/index.php?name=pics");
      
                        articleList.add(article1);  
                        articleList.add(article2);  
                        articleList.add(article3); 

                        newsMessage.setArticleCount(articleList.size());  
                        newsMessage.setArticles(articleList);  
                        respMessage = MessageUtil.newsMessageToXml(newsMessage); 
                    }
                    // 办公服务的更多
                    else if ("B00".equals(eventKey)) {
                    	Article article1 = new Article();  
                        article1.setTitle("办公电话");  
                        article1.setDescription("");  
                        article1.setPicUrl(basePath+"images/KyMen.jpg");  
                        article1.setUrl("http://www.ky.zstu.edu.cn/mypage/index.php?name=tele");
                        
                        Article article2 = new Article();  
                        article2.setTitle("校车安排");  
                        article2.setDescription("");  
                        article2.setPicUrl(basePath+"images/KyZhong.jpg");  
                        article2.setUrl("http://www.ky.zstu.edu.cn/mypage/index.php?name=xichengbus");
                        
                        Article article3 = new Article();  
                        article3.setTitle("学院校历");  
                        article3.setDescription("");  
                        article3.setPicUrl(basePath+"images/KyMei.jpg");  
                        article3.setUrl("http://www.ky.zstu.edu.cn/mypage/index.php?name=xiaoli");
      
                        articleList.add(article1);  
                        articleList.add(article2);  
                        articleList.add(article3); 

                        newsMessage.setArticleCount(articleList.size());  
                        newsMessage.setArticles(articleList);  
                        respMessage = MessageUtil.newsMessageToXml(newsMessage); 
                    }
                    
                    //显示五条最新新闻
                    else if ("B01".equalsIgnoreCase(eventKey)) {
                    	String serviceType="通知管理";
                    	List<TService> listService;
                    	listService=persistenceLayer3.findByProperty(serviceType);
                    	int serviceSize=listService.size();
                    	if (serviceSize>=4) {
                			TService service1=listService.get(serviceSize-1);
                			String serviceTitle1=service1.getServiceTitle();
                			long serviceId1=service1.getServiceId();
                			
                			TService service2=listService.get(serviceSize-2);
                			String serviceTitle2=service2.getServiceTitle();
                			long serviceId2=service2.getServiceId();
                			
                			TService service3=listService.get(serviceSize-3);
                			String serviceTitle3=service3.getServiceTitle();
                			long serviceId3=service3.getServiceId();
                			
                			TService service4=listService.get(serviceSize-4);
                			String serviceTitle4=service4.getServiceTitle();
                			long serviceId4=service4.getServiceId();
                        	
                			Article article1 = new Article();  
                            article1.setTitle(serviceTitle1);  
                            article1.setDescription("显示第一条通知，即最新的一条通知");  
                            article1.setPicUrl(basePath+"images/KyMen.jpg");  
                            article1.setUrl(basePath+"OfficeService/serviceView.jsp?id=" + serviceId1);
                            Article article2 = new Article();  
                            article2.setTitle(serviceTitle2);  
                            article2.setDescription("显示第二条通知，即最新的二条通知");  
                            article2.setPicUrl(basePath+"images/KyZhong.jpg");  
                            article2.setUrl(basePath+"OfficeService/serviceView.jsp?id=" + serviceId2);
                            Article article3 = new Article();  
                            article3.setTitle(serviceTitle3);  
                            article3.setDescription("显示第三条通知，即最新的三条通知");  
                            article3.setPicUrl(basePath+"images/KyMei.jpg");  
                            article3.setUrl(basePath+"OfficeService/serviceView.jsp?id=" + serviceId3); 
                            Article article4 = new Article();  
                            article4.setTitle(serviceTitle4);  
                            article4.setDescription("显示第四条通知，即最新的四条通知");  
                            article4.setPicUrl(basePath+"images/KyMei.jpg");  
                            article4.setUrl(basePath+"OfficeService/serviceView.jsp?id=" + serviceId4);

                            articleList.add(article1);  
                            articleList.add(article2);  
                            articleList.add(article3); 
                            articleList.add(article4); 
                            newsMessage.setArticleCount(articleList.size());  
                            newsMessage.setArticles(articleList);  
                            respMessage = MessageUtil.newsMessageToXml(newsMessage); 
                		    }
                		else if(serviceSize==3) {
                			TService service1=listService.get(serviceSize-1);
                			String serviceTitle1=service1.getServiceTitle();
                			long serviceId1=service1.getServiceId();
                			
                			TService service2=listService.get(serviceSize-2);
                			String serviceTitle2=service2.getServiceTitle();
                			long serviceId2=service2.getServiceId();
                			
                			TService service3=listService.get(serviceSize-3);
                			String serviceTitle3=service3.getServiceTitle();
                			long serviceId3=service3.getServiceId();
                			
                			Article article1 = new Article();  
                            article1.setTitle(serviceTitle1);  
                            article1.setDescription("显示第一条通知，即最新的一条通知");  
                            article1.setPicUrl(basePath+"images/KyMen.jpg");  
                            article1.setUrl(basePath+"OfficeService/serviceView.jsp?id=" + serviceId1);
                            Article article2 = new Article();  
                            article2.setTitle(serviceTitle2);  
                            article2.setDescription("显示第二条通知，即最新的二条通知");  
                            article2.setPicUrl(basePath+"images/KyZhong.jpg");  
                            article2.setUrl(basePath+"OfficeService/serviceView.jsp?id=" + serviceId2);
                            Article article3 = new Article();  
                            article3.setTitle(serviceTitle3);  
                            article3.setDescription("显示第三条通知，即最新的三条通知");  
                            article3.setPicUrl(basePath+"images/KyMei.jpg");  
                            article3.setUrl(basePath+"OfficeService/serviceView.jsp?id=" + serviceId3); 

                            articleList.add(article1);  
                            articleList.add(article2);  
                            articleList.add(article3); 
                            newsMessage.setArticleCount(articleList.size());  
                            newsMessage.setArticles(articleList);  
                            respMessage = MessageUtil.newsMessageToXml(newsMessage); 
                		    }
                		else if(serviceSize==2){
                			TService service1=listService.get(serviceSize-1);
                			String serviceTitle1=service1.getServiceTitle();
                			long serviceId1=service1.getServiceId();
                			TService service2=listService.get(serviceSize-2);
                			String serviceTitle2=service2.getServiceTitle();
                			long serviceId2=service2.getServiceId();
                			Article article1 = new Article();  
                            article1.setTitle(serviceTitle1);  
                            article1.setDescription("显示第一条通知，即最新的一条通知");  
                            article1.setPicUrl(basePath+"images/KyMen.jpg");  
                            article1.setUrl(basePath+"OfficeService/serviceView.jsp?id=" + serviceId1);
                            Article article2 = new Article();  
                            article2.setTitle(serviceTitle2);  
                            article2.setDescription("显示第二条通知，即最新的二条通知");  
                            article2.setPicUrl(basePath+"images/KyZhong.jpg");  
                            article2.setUrl(basePath+"OfficeService/serviceView.jsp?id=" + serviceId2);

                            articleList.add(article1);  
                            articleList.add(article2);  
                            newsMessage.setArticleCount(articleList.size());  
                            newsMessage.setArticles(articleList);  
                            respMessage = MessageUtil.newsMessageToXml(newsMessage); 
                		    }
                		else if(serviceSize==1){
                			TService service1=listService.get(serviceSize-1);
                			String serviceTitle1=service1.getServiceTitle();
                			long serviceId1=service1.getServiceId();
                			Article article1 = new Article();  
                            article1.setTitle(serviceTitle1);  
                            article1.setDescription("显示第一条通知，即最新的一条通知");  
                            article1.setPicUrl(basePath+"images/KyMen.jpg");  
                            article1.setUrl(basePath+"OfficeService/serviceView.jsp?id=" + serviceId1);

                            articleList.add(article1);  
                            newsMessage.setArticleCount(articleList.size());  
                            newsMessage.setArticles(articleList);  
                            respMessage = MessageUtil.newsMessageToXml(newsMessage); 

                		}
                		else{
                			Article article1 = new Article();  
                            article1.setTitle("暂时没有通知，请迟些再来关注，谢谢！");  
                            articleList.add(article1);  
                            newsMessage.setArticleCount(articleList.size());  
                            newsMessage.setArticles(articleList);  
                            respMessage = MessageUtil.newsMessageToXml(newsMessage); 
                		}
                    } //end of 最新新闻
                        
                    //最近会议
                    else if ("B02".equals(eventKey)) {
                    	String serviceType="会议管理";
                    	List<TService> listService;
                    	listService=persistenceLayer3.findByProperty(serviceType);
                    	int serviceSize=listService.size();
            			TService service1=listService.get(serviceSize-1);
            		    //String serviceTitle1=service1.getServiceTitle();
            			long serviceId1=service1.getServiceId();

                       	Article article1 = new Article();  
                        article1.setTitle("最新一周会议查询");  
                        article1.setDescription("亲，只能查询最近一周的会议，请点击查看全文进行查询！如需查询更多会议内容，请登录党政办公室网页进行查询");  
                        article1.setPicUrl(basePath+"images/KyHuiYi.jpg");  
                        article1.setUrl(basePath+"OfficeService/serviceView.jsp?id=" + serviceId1);  
     
                        articleList.add(article1);  

                        newsMessage.setArticleCount(articleList.size());  
                        newsMessage.setArticles(articleList);  
                        respMessage = MessageUtil.newsMessageToXml(newsMessage); 
                    }
                    
                    //显示五条最新新闻
                    else if ("B03".equalsIgnoreCase(eventKey)) {
                    	String serviceType="新闻管理";
                    	List<TService> listService;
                    	listService=persistenceLayer3.findByProperty(serviceType);
                    	int serviceSize=listService.size();
                    	if (serviceSize>=4) {
                			TService service1=listService.get(serviceSize-1);
                			String serviceTitle1=service1.getServiceTitle();
                			long serviceId1=service1.getServiceId();
                			
                			TService service2=listService.get(serviceSize-2);
                			String serviceTitle2=service2.getServiceTitle();
                			long serviceId2=service2.getServiceId();
                			
                			TService service3=listService.get(serviceSize-3);
                			String serviceTitle3=service3.getServiceTitle();
                			long serviceId3=service3.getServiceId();
                			
                			TService service4=listService.get(serviceSize-4);
                			String serviceTitle4=service4.getServiceTitle();
                			long serviceId4=service4.getServiceId();
                        	
                			Article article1 = new Article();  
                            article1.setTitle(serviceTitle1);  
                            article1.setDescription("显示第一条新闻，即最新的一条新闻");  
                            article1.setPicUrl(basePath+"images/KyMen.jpg");  
                            article1.setUrl(basePath+"OfficeService/serviceView.jsp?id=" + serviceId1);
                            Article article2 = new Article();  
                            article2.setTitle(serviceTitle2);  
                            article2.setDescription("显示第二条新闻，即最新的二条新闻");  
                            article2.setPicUrl(basePath+"images/KyZhong.jpg");  
                            article2.setUrl(basePath+"OfficeService/serviceView.jsp?id=" + serviceId2);
                            Article article3 = new Article();  
                            article3.setTitle(serviceTitle3);  
                            article3.setDescription("显示第三条新闻，即最新的三条新闻");  
                            article3.setPicUrl(basePath+"images/KyMei.jpg");  
                            article3.setUrl(basePath+"OfficeService/serviceView.jsp?id=" + serviceId3); 
                            Article article4 = new Article();  
                            article4.setTitle(serviceTitle4);  
                            article4.setDescription("显示第四条新闻，即最新的四条新闻");  
                            article4.setPicUrl(basePath+"images/KyMei.jpg");  
                            article4.setUrl(basePath+"OfficeService/serviceView.jsp?id=" + serviceId4);

                            articleList.add(article1);  
                            articleList.add(article2);  
                            articleList.add(article3); 
                            articleList.add(article4); 
                            newsMessage.setArticleCount(articleList.size());  
                            newsMessage.setArticles(articleList);  
                            respMessage = MessageUtil.newsMessageToXml(newsMessage); 
                		   }
                		else if(serviceSize==3) {
                			TService service1=listService.get(serviceSize-1);
                			String serviceTitle1=service1.getServiceTitle();
                			long serviceId1=service1.getServiceId();
                			
                			TService service2=listService.get(serviceSize-2);
                			String serviceTitle2=service2.getServiceTitle();
                			long serviceId2=service2.getServiceId();
                			
                			TService service3=listService.get(serviceSize-3);
                			String serviceTitle3=service3.getServiceTitle();
                			long serviceId3=service3.getServiceId();
                			
                			Article article1 = new Article();  
                            article1.setTitle(serviceTitle1);  
                            article1.setDescription("显示第一条新闻，即最新的一条新闻");  
                            article1.setPicUrl(basePath+"images/KyMen.jpg");  
                            article1.setUrl(basePath+"OfficeService/serviceView.jsp?id=" + serviceId1);
                            Article article2 = new Article();  
                            article2.setTitle(serviceTitle2);  
                            article2.setDescription("显示第二条新闻，即最新的二条新闻");  
                            article2.setPicUrl(basePath+"images/KyZhong.jpg");  
                            article2.setUrl(basePath+"OfficeService/serviceView.jsp?id=" + serviceId2);
                            Article article3 = new Article();  
                            article3.setTitle(serviceTitle3);  
                            article3.setDescription("显示第三条新闻，即最新的三条新闻");  
                            article3.setPicUrl(basePath+"images/KyMei.jpg");  
                            article3.setUrl(basePath+"OfficeService/serviceView.jsp?id=" + serviceId3); 

                            articleList.add(article1);  
                            articleList.add(article2);  
                            articleList.add(article3); 
                            newsMessage.setArticleCount(articleList.size());  
                            newsMessage.setArticles(articleList);  
                            respMessage = MessageUtil.newsMessageToXml(newsMessage); 
                		   }
                		else if(serviceSize==2){
                			TService service1=listService.get(serviceSize-1);
                			String serviceTitle1=service1.getServiceTitle();
                			long serviceId1=service1.getServiceId();
                			TService service2=listService.get(serviceSize-2);
                			String serviceTitle2=service2.getServiceTitle();
                			long serviceId2=service2.getServiceId();
                			Article article1 = new Article();  
                            article1.setTitle(serviceTitle1);  
                            article1.setDescription("显示第一条新闻，即最新的一条新闻");  
                            article1.setPicUrl(basePath+"images/KyMen.jpg");  
                            article1.setUrl(basePath+"OfficeService/serviceView.jsp?id=" + serviceId1);
                            Article article2 = new Article();  
                            article2.setTitle(serviceTitle2);  
                            article2.setDescription("显示第二条新闻，即最新的二条新闻");  
                            article2.setPicUrl(basePath+"images/KyZhong.jpg");  
                            article2.setUrl(basePath+"OfficeService/serviceView.jsp?id=" + serviceId2);

                            articleList.add(article1);  
                            articleList.add(article2);  
                            newsMessage.setArticleCount(articleList.size());  
                            newsMessage.setArticles(articleList);  
                            respMessage = MessageUtil.newsMessageToXml(newsMessage); 
                		   }
                		else if(serviceSize==1){
                			TService service1=listService.get(serviceSize-1);
                			String serviceTitle1=service1.getServiceTitle();
                			long serviceId1=service1.getServiceId();
                			Article article1 = new Article();  
                            article1.setTitle(serviceTitle1);  
                            article1.setDescription("显示第一条新闻，即最新的一条新闻");  
                            article1.setPicUrl(basePath+"images/KyMen.jpg");  
                            article1.setUrl(basePath+"OfficeService/serviceView.jsp?id=" + serviceId1);

                            articleList.add(article1);  
                            newsMessage.setArticleCount(articleList.size());  
                            newsMessage.setArticles(articleList);  
                            respMessage = MessageUtil.newsMessageToXml(newsMessage); 

                		   }
                		else{
                			Article article1 = new Article();  
                            article1.setTitle("暂时没有新闻，请迟些再来关注，谢谢！");  
                            articleList.add(article1);  
                            newsMessage.setArticleCount(articleList.size());  
                            newsMessage.setArticles(articleList);  
                            respMessage = MessageUtil.newsMessageToXml(newsMessage); 
                		}
                    }
                    
                    // 监考查询
                    else if ("C05".equalsIgnoreCase(eventKey)) {
                		PersistenceLayerLinyx persistenceLayer = (PersistenceLayerLinyx) ctx.getBean("persistenceLayerLinyx");
                		String jobNumber = persistenceLayer.getJobNumberByOpenID(fromUserName);
            			if (jobNumber==null || jobNumber.equalsIgnoreCase("")) {
            				respContent = "您的微信账户还未与工号绑定！";  
                            textMessage.setContent(respContent);  
                            respMessage = MessageUtil.textMessageToXml(textMessage);
            			}
            			else {
	                    	Article article = new Article();  
	                        article.setTitle("监考任务信息查询");  
	                        article.setDescription("亲，只能查询当前日期之后的监考任务哦！");  
	                        article.setPicUrl(basePath+"images/KyJianKao.jpg");  
	                        article.setUrl(basePath+"Invigilation/invigilationTask.jsp?jobNumber=" + jobNumber);  
	                        //article.setUrl(basePath+"Invigilation/invigilationTask.jsp?jobNumber=20010888");
	                        articleList.add(article);  
	                        // 设置图文消息个数  
	                        newsMessage.setArticleCount(articleList.size());  
	                        // 设置图文消息包含的图文集合  
	                        newsMessage.setArticles(articleList);  
	                        // 将图文消息对象转换成xml字符串  
	                        respMessage = MessageUtil.newsMessageToXml(newsMessage);
            			}
                    }	//end of 监考  // 值班查询
                    else if ("C06".equalsIgnoreCase(eventKey)) {
                		PersistenceLayerLinyx persistenceLayer = (PersistenceLayerLinyx) ctx.getBean("persistenceLayerLinyx");
                		String jobNumber = persistenceLayer.getJobNumberByOpenID(fromUserName);
            			if (jobNumber==null || jobNumber.equalsIgnoreCase("")) {
            				respContent = "您的微信账户还未与工号绑定！";  
                            textMessage.setContent(respContent);  
                            respMessage = MessageUtil.textMessageToXml(textMessage);
            			}
            			else {
	                    	Article article = new Article();  
	                        article.setTitle("值班任务信息查询");  
	                        article.setDescription("亲，只能查询当前日期之后的值班任务哦！");  
	                        article.setPicUrl(basePath+"images/KyZhiBan.jpg");  
	                        article.setUrl(basePath+"Duty/dutyTask.jsp?jobNumber=" + jobNumber);  
	                        articleList.add(article);  
	                        // 设置图文消息个数  
	                        newsMessage.setArticleCount(articleList.size());  
	                        // 设置图文消息包含的图文集合  
	                        newsMessage.setArticles(articleList);  
	                        // 将图文消息对象转换成xml字符串  
	                        respMessage = MessageUtil.newsMessageToXml(newsMessage);
            			}
                    }	//end of 值班
                    */
                    
                    // 绑定您的工号值班查询
                    //else 
                    

                }  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
        return respMessage;  
    }
    
    /**
     * 手动主菜单
     * @return
     */
    private static String getMainMenu() {  
        StringBuffer buffer = new StringBuffer();  
        buffer.append("请回复数字选择服务：").append("\n\n");  
        buffer.append("1  学院总揽").append("\n");  
        buffer.append("2  办公服务").append("\n");  
        buffer.append("3  个人操作").append("\n");   
        return buffer.toString();  
    }
    
    
}