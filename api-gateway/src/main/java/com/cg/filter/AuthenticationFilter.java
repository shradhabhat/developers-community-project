package com.cg.filter;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
 
import com.cg.repository.UserRepository;
import com.cg.util.JwtUtil;
 
import reactor.core.publisher.Mono;
@Component
public class AuthenticationFilter implements GlobalFilter{
	 @Autowired
	    private RouteValidator validator;
 
	    @Autowired
	    private JwtUtil jwtUtil;
	    @Autowired
	    UserRepository userRepository; 
		public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		System.out.println("****my filtering*****");
        	ServerHttpRequest request=null;
            if (validator.isSecured.test(exchange.getRequest())) {
                //header contains token or not
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("missing authorization header");
                }
 
                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }
                try {
                    jwtUtil.validateToken(authHeader);
                    String userName=jwtUtil.getUsernameFromToken(authHeader);
                    int id = userRepository.getUserId(userName);
                    System.out.println(userName);
                    System.out.println(id);
                 request=  exchange.getRequest().mutate().
                		 header("loggedInUser", String.valueOf(id)).build();
 
                } catch (Exception e) {
                	e.printStackTrace();
                    System.out.println("invalid access...!");
                    throw new RuntimeException("un authorized access to application");
                }
            }
            return chain.filter(exchange.mutate().request(request).build());
	}
 
 
	
}