package com.mbh.jiudian.filter;

import com.alibaba.fastjson.JSON;
import com.mbh.jiudian.common.BaseContext;
import com.mbh.jiudian.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //1.获取本次请求URI
        String requestURI = request.getRequestURI();

        log.info("拦截到请求：{}",requestURI);
        String urls[] = new String[]{
                "/api/login",
                "/jiudian/**",
                "/common/**"
        };
        //2.判断本次请求是否需要处理
        boolean check = check(urls,requestURI);

        //3.如果不需要处理，则直接放行
        if(check){
            log.info("本次请求{}不需要处理",requestURI);
            filterChain.doFilter(request,response);
            return;
        }

        //4.判断登录状态，如果已登录，则直接放行
        if(request.getSession().getAttribute("custom")!=null){
            log.info("用户已登录，用户ID为：{}",request.getSession().getAttribute("custom"));

            Long empId = (Long) request.getSession().getAttribute("custom");

            BaseContext.setCurrentId(empId);
            filterChain.doFilter(request,response);
            return;
        }

        if(request.getSession().getAttribute("root")!=null){
            log.info("用户已登录，用户ID为：{}",request.getSession().getAttribute("root"));

            Long userId = (Long) request.getSession().getAttribute("root");

            BaseContext.setCurrentId(userId);
            filterChain.doFilter(request,response);
            return;
        }


        log.info("用户未登录");
        //5.如果未登录则返回未登录结果,通过输出流方式向客户端页面响应数据
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return;

    }
    public boolean check(String urls[],String requestURI){
        for (String url:urls){
            boolean match = PATH_MATCHER.match(url, requestURI);
            if(match){
                return true;
            }
        }
        return false;
    }
}
