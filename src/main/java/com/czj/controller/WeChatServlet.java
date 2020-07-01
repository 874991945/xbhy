package com.czj.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.czj.constants.SysConstant;
import com.czj.entity.Dept;
import com.czj.entity.User;
import com.czj.service.DeptService;
import com.czj.service.UserService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

/**
 * @auth czj
 * @date 2020/6/23
 * @Description
 */
@WebServlet("/weChat/*")
public class WeChatServlet extends BaseServlet {

    private UserService userService = new UserService();

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Properties prop = new Properties();
        prop.load(this.getClass().getClassLoader().getResourceAsStream("config.properties"));

        String appid = prop.getProperty("wx.AppID");

        //微信授权成功后的回调地址
        String redirect_uri = prop.getProperty("wx.redirect_uri");

        String url = "https://open.weixin.qq.com/connect/qrconnect?response_type=code" +
                "&appid=" + appid +
                "&redirect_uri=" + URLEncoder.encode(redirect_uri) +
                "&scope=snsapi_login";

        //重定向到微信登录指定的地址进行微信登录授权,授权成功后返回code
        response.sendRedirect(url);
    }

    public void wxLoginCallBack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Properties prop = new Properties();
        prop.load(this.getClass().getClassLoader().getResourceAsStream("config.properties"));

        HttpSession session = request.getSession();

        String code = request.getParameter("code");
        String appid = prop.getProperty("wx.AppID");
        String AppSecret = prop.getProperty("wx.AppSecret");

        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appid +
                "&secret=" + AppSecret +
                "&code=" + code +
                "&grant_type=authorization_code";
        // 获取AccessToken、openid等数据
        JSONObject authInfo = auth(url);
//        System.out.println(authInfo);


        //获取用户信息
        String userUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + authInfo.getString("access_token") +
                "&openid=" + authInfo.getString("openid");

        JSONObject userInfo = auth(userUrl);

        String openid = userInfo.getString("openid");
        User user = userService.getUserByWxOpenId(openid);
        if (user == null) {
            //首次登录，注册一个账号
            user = new User();
            //设置头像
            user.setPic(userInfo.getString("headimgurl"));
            //设置性别
            user.setSex(userInfo.getString("sex") == null ? 2 : Integer.valueOf(userInfo.getString("sex")));
            //设置用户名
            user.setUsername(UUID.randomUUID().toString().substring(36 - 15));
            //设置openid
            user.setWxOpenid(openid);
            //设置成一个新的用户
            userService.add(user);
        }
        session.setAttribute(SysConstant.SESSION_LOGIN, user);
        session.setMaxInactiveInterval(60);
        response.sendRedirect("/jsp/common/main.jsp");
    }

    private JSONObject auth(String url) {
        try {
            // 创建一个http Client请求
            CloseableHttpClient client = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            HttpResponse response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            // 获取响应数据(json)
            if (entity != null) {
                String result = EntityUtils.toString(entity, Charset.forName("UTF8"));
                return JSONObject.parseObject(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
