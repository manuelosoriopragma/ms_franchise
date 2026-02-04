package co.com.nequi.api;

import co.com.nequi.api.handler.BranchHandler;
import co.com.nequi.api.handler.FranchiseHandler;
import co.com.nequi.api.handler.ProductHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static co.com.nequi.api.helper.Constants.FRANCHISE_ID_PARAM;
import static co.com.nequi.api.helper.Constants.PRODUCT_ID_PARAM;
import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRest {
    @Bean
    public RouterFunction<ServerResponse> routerFunction(FranchiseHandler franchiseHandler
            , BranchHandler branchHandler, ProductHandler productHandler) {
        return route(POST("/franchise/create"), franchiseHandler::createFranchise)
                .andRoute(PUT("/franchise/rename"), franchiseHandler::updateName)
                .andRoute(POST("/branch/assign"), branchHandler::assingBranch)
                .andRoute(POST("/product/save"), productHandler::saveProduct)
                .andRoute(DELETE("/product/{".concat(PRODUCT_ID_PARAM).concat("}")), productHandler::deleteProduct)
                .andRoute(PUT("/product/updateStock"), productHandler::updateStock)
                .andRoute(GET("/product/maxStockProducts/{".concat(FRANCHISE_ID_PARAM).concat("}")),
                        productHandler::findProductsWithMaxStockByFranchise);

    }
}
