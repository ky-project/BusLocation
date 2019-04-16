package com.ky.gps.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Daye
 * 取消Access-Control-Allow-Origin（以下简称CORS）的限制
 */
public class CorsFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.addHeader("Access-Control-Allow-Origin", "*");
        httpResponse.addHeader("Access-Control-Allow-Methods", "POST");
        httpResponse.addHeader("Access-Control-Allow-Methods", "GET");
        httpResponse.addHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
