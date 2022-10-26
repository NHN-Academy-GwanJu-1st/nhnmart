package com.nhnacademy.nhnmart.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*", initParams = {
        @WebInitParam(name = "exclude-urls",
                value = "/\n"+
                        "/loginForm.jsp\n" +
                        "/login\n" +
                        "/index.jsp\n" +
                        "/init\n" +
                        "/foods\n")
})
public class LoginCheckFilter implements Filter {

    private Set<String> excludeUrls = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String urls = filterConfig.getInitParameter("exclude-urls");

        excludeUrls = Arrays.stream(urls.trim().split("\n"))
                .map(String::trim)
                .collect(Collectors.toSet());

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        log.info("LoginCheckFilter doFilter()");

        HttpSession session = ((HttpServletRequest) request).getSession(false);

        if (Objects.isNull(session)) {
            // 세션 null
            if (excludeUrls.contains(((HttpServletRequest) request).getRequestURI())) {
                chain.doFilter(request, response);
            } else {
                ((HttpServletResponse) response).sendRedirect("/loginForm.jsp");
            }
        } else {
            chain.doFilter(request, response);
        }
    }
}
