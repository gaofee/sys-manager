package com.gaofei.sysmanager.controller;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gaofei.sysmanager.common.CommonResult;
import com.gaofei.sysmanager.domain.User;
import com.gaofei.sysmanager.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : gaofee
 * @date : 16:06 2021/7/30
 * @码云地址 : feege.gitee.io
 */
@Controller
@CrossOrigin
@Slf4j
public class MayunLoginController {

    @Autowired
    IUserService userService;

    @RequestMapping("callback")
    public void callback(String code, HttpServletResponse resp) throws IOException {

        //1.拿着授权码请求token
        Map<String, Object> map = new HashMap<>();
        map.put("client_id","503244e5c6cb286fe8f48d822bcb306404d22208380b1775121c71b9ec117aae");
        map.put("client_secret","9bd329f1b51bbc70b2fac74c77f65db08bb3defb88588fdae53cbc560f9966ec");
        map.put("grant_type","authorization_code");
        map.put("code",code);
        map.put("redirect_uri","http://127.0.0.1:9001/callback");

        //HttpUtil.buildBasicAuth("503244e5c6cb286fe8f48d822bcb306404d22208380b1775121c71b9ec117aae","9bd329f1b51bbc70b2fac74c77f65db08bb3defb88588fdae53cbc560f9966ec", Charset.forName("UTF-8"));
        //2.如果token过期,使用refesh_token 重新获取一个access_token  这步省略
        //当 access_token 过期后（有效期为一天），你可以通过以下 refresh_token 方式重新获取 access_token（ POST请求 ）
        //https://gitee.com/oauth/token?grant_type=refresh_token&refresh_token={refresh_token}
        //3.获取token,失效时间,刷新token等
        String tokenResult = HttpUtil.post("https://gitee.com/oauth/token", map);
        Map parse = (Map) JSON.parse(tokenResult);
        String access_token = (String) parse.get("access_token");
        log.info("请求的token是:{}",tokenResult);
        //4.根据token获取用户的信息
        Map<String, Object> map1 = new HashMap<>();
        map1.put("access_token",access_token);
        String userInfo = HttpUtil.get("https://gitee.com/api/v5/user", map1);

        Map<String,Object> user = (Map<String, Object>) JSON.parse(userInfo);
        //获取码云上的giteeId
        String giteeId = user.get("id")+"";
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("giteeid",giteeId);
        //根据giteeId查询本地用户id和姓名
        User one = userService.getOne(wrapper);
        //如果one不存在,则让用户登录
        //然后将登录的用户和giteeId绑定

        //4.登录成功
        resp.sendRedirect("http://localhost:8080/toLogin?uid="+one.getId()+"&name="+one.getName());

    }
}
