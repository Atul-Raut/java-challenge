package jp.co.axa.apidemo.filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

	  /**
     * Configures and registers the RestFilter.
     *
     * @return A FilterRegistrationBean for the RestFilter.
     */
    @Bean
    public FilterRegistrationBean<RestFilter> trackingIdFilter() {
        FilterRegistrationBean<RestFilter> registrationBean = new FilterRegistrationBean<>();
        
        // Set the RestFilter as the filter to be registered.
        registrationBean.setFilter(new RestFilter());
        
        // Define URL patterns for filtering (in this case, any URL starting with "/api/").
        registrationBean.addUrlPatterns("/api/*");
        
        return registrationBean;
    }
}