package com.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import com.util.StaticSql;

/**
 * Servlet Filter implementation class Filter2
 */
@WebFilter("/Filter2")
public class Filter2 implements Filter {

    
    public Filter2() {
    	StaticSql.refresh();
    	System.out.println("进入过滤器");
    }

	
	public void destroy() {
		System.out.println("过滤器销毁");
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		response.setCharacterEncoding("UTF-8");
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
