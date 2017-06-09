package id.co.antin.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {
    public static final Logger LOGGER = LoggerFactory.getLogger(JWTLoginFilter.class);

    public JWTLoginFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }


    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        if (request.getHeader("Origin") != null) {
            // CORS "pre-flight" request
            response.addHeader("Access-Control-Allow-Origin", "http://localhost:8988");
            response.addHeader("Access-Control-Allow-Credentials", "true");

            response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            response.addHeader("Access-Control-Allow-Headers", "Authorization, X-Requested-With, Content-Type, Accept");
            response.addHeader("Access-Control-Max-Age", "1800"); // 30 min
            response.addHeader("Access-Control-Expose-Headers", "Total-Count, Total-Pages, Error-Message, Authorization");
        }

        if (request.getHeader("Access-Control-Request-Method") != null && "OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpStatus.OK.value());
            chain.doFilter(request, response);

        } else {
            super.doFilter(request, response, chain);
        }
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException, IOException, ServletException {

        AccountCredentials creds = new ObjectMapper().readValue(req.getInputStream(), AccountCredentials.class);

        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                        creds.getUsername(),
                        creds.getPassword(),
                        Collections.emptyList()
                )
        );
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest req,
            HttpServletResponse res, FilterChain chain,
            Authentication auth) throws IOException, ServletException {

        TokenAuthenticationService
                .addAuthentication(res, auth.getName());
    }
}