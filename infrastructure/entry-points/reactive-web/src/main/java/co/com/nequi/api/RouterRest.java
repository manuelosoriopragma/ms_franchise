package co.com.nequi.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRest {
    @Bean
    public RouterFunction<ServerResponse> routerFunction(FranchiseHandler franchiseHandler) {
        return route(POST("/franchise/create"), franchiseHandler::createFranchise)
                .andRoute(POST("/api/usecase/otherpath"), franchiseHandler::listenPOSTUseCase)
                .and(route(GET("/api/otherusercase/path"), franchiseHandler::listenGETOtherUseCase));
    }
}
