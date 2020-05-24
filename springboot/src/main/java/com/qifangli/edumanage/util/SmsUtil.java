package com.qifangli.edumanage.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.stereotype.Component;

@Component
public class SmsUtil {

    static final String domain = "dysmsapi.aliyuncs.com";
    static final String sign_name="三组教务";
    static final String template_code="SMS_189835091";
    DefaultProfile profile = DefaultProfile.getProfile("default", "LTAI4GCbTtvm1EpRJYRDY6XP", "omQWjrKyj0IjQqz5UZi2qYh1evA88Q");
    IAcsClient client = new DefaultAcsClient(profile);

    /**
     * 发送验证码
     * @param phoneNumber
     * @return code
     */
    public String SendMessage(String phoneNumber){
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain(domain);
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("PhoneNumbers", phoneNumber);
        System.out.println(phoneNumber);
        request.putQueryParameter("SignName", sign_name);
        request.putQueryParameter("TemplateCode", template_code);
        int code = ((int) (Math.random() * 900000 + 100000));
        request.putQueryParameter("TemplateParam", "{code:"+code+"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            return String.valueOf(code);
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询发送信息
     * @param phoneNumber
     * @param sendDate
     * @param pageSize
     * @param currentPage
     */
    public void QuerySendDetails(String phoneNumber,String sendDate,String pageSize,String currentPage){
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain(domain);
        request.setSysVersion("2017-05-25");
        request.setSysAction("QuerySendDetails");
        request.putQueryParameter("PhoneNumber", phoneNumber);
        request.putQueryParameter("SendDate", sendDate);
        request.putQueryParameter("PageSize", pageSize);
        request.putQueryParameter("CurrentPage", currentPage);
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
