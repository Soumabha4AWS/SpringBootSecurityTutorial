package com.springboot.security.jwt.demo.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.springboot.security.jwt.demo.service.AppUsersDetailsService;
import com.springboot.security.jwt.demo.util.JWTUtil;


@Component
public class JWTRequestFilter extends OncePerRequestFilter {
	
	@Autowired
	private AppUsersDetailsService apUsersDetailsService;
	@Autowired
	private JWTUtil jWTUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String authorizationHeader = request.getHeader("Authorization");
		String userName = null;
		String jWT = null;
		
		if ( authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			jWT = authorizationHeader.substring(7);
			userName = jWTUtil.getUsernameFromToken(jWT);
		}
		
		if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = apUsersDetailsService.loadUserByUsername(userName);	
			
			if (jWTUtil.validateToken(jWT, userDetails)) {				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
						new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
			
		}
		
		filterChain.doFilter(request, response);
	}

	
	
}
