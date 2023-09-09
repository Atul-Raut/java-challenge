package jp.co.axa.apidemo.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jp.co.axa.apidemo.common.RequestInfo;
import jp.co.axa.apidemo.common.RequestInfoContext;
import jp.co.axa.apidemo.utils.AppUtils;
import jp.co.axa.apidemo.utils.LogUtils;

import java.io.IOException;

public class RestFilter implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(RestFilter.class);
	
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
        	if(request instanceof HttpServletRequest) {       
        		HttpServletRequest req = (HttpServletRequest)request;
        		// Get the request URI
        		String requestURI = req.getRequestURI();
        				
				// Create tracking ID
				String trackingId = AppUtils.getUUID();
        		
        		RequestInfo requestInfo = new RequestInfo();
        		requestInfo.setTrackingId(trackingId);
        		
        		// Set the tracking ID into a ThreadLocal for later access.
        		RequestInfoContext.setRequestInfo(requestInfo);
        		
        		LogUtils.info(logger, "Request recived for API : " + requestURI);
        	}

            // Continue processing the request.
            chain.doFilter(request, response);
        } finally {
        	
        	LogUtils.info(logger, "Sending response.");
        	
            // Ensure the ThreadLocal is cleared after the request is processed.
            RequestInfoContext.clear();
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code, if needed.
    }

    @Override
    public void destroy() {
        // Cleanup code, if needed.
    }
}
