package co.com.nequi.api.router;

import co.com.nequi.api.handler.FranchiseHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class BranchRouterRest {
    @Bean
    public RouterFunction<ServerResponse> branchRouterFunction(FranchiseHandler franchiseHandler) {
        return route(POST("/franchise/create"), franchiseHandler::createFranchise);
    }
}
