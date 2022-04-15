package com.gs.configuration;

import com.gs.dto.user.UserDetailDto;
import com.gs.exception.MasterException;
import com.gs.service.user.IUserService;
import com.gs.util.JwtTokenUtil;
import com.gs.util.Utils;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final String HEADER_AUTORIZATION = "Authorization";
    private final String PREFIX_BEARER = "Bearer ";

    @Autowired
    private IUserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String username = null;
        String jwtToken = null;
        UserDetailDto userDetailDto = null;
        final String requestTokenHeader = request.getHeader(HEADER_AUTORIZATION);

        // JWT Token is in the form "Bearer token". Remove Bearer word and get
        // only the Token
        if (requestTokenHeader != null && requestTokenHeader.startsWith(PREFIX_BEARER)) {

            jwtToken = requestTokenHeader.substring(PREFIX_BEARER.length());

            try {
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                System.out.println("JWT Token has expired");
            }
        } else {
            logger.warn("JWT Token does not begin with Bearer String");
        }

        if (!Utils.isEmptyOrWhiteSpaceOrNUll(username)) {
            try {
                userDetailDto = this.userService.getByEmployeeNumber(username);
            } catch (MasterException e) {
                System.out.println(e.getDetalles());
            }
        }
        // Once we get the token validate it.
        if (userDetailDto != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // if token is valid configure Spring Security to manually set
            // authentication
            if (jwtTokenUtil.validateToken(jwtToken, userDetailDto.getNumeroEmpleado())) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetailDto, null, null);
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // After setting the Authentication in the context, we specify
                // that the current user is authenticated. So it passes the
                // Spring Security Configurations successfully.
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        chain.doFilter(request, response);
    }
}
