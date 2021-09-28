package ru.geekbrains.web.endpoints;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.geekbrains.web.service.ProductService;
import ru.geekbrains.web.soap.products.GetAllProductsRequest;
import ru.geekbrains.web.soap.products.GetAllProductsResponse;
import ru.geekbrains.web.soap.products.GetProductByTitleRequest;
import ru.geekbrains.web.soap.products.GetProductByTitleResponse;

@Endpoint
@RequiredArgsConstructor
public class ProductEndpoint {
    private static final String NAMESPACE_URI = "http://www.flamexander.com/spring/ws/products";
    private final ProductService productService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductByNameRequest")
    @ResponsePayload
    public GetProductByTitleResponse getProductByTitleRequest(@RequestPayload GetProductByTitleRequest request) {
        GetProductByTitleResponse response = new GetProductByTitleResponse();
        response.setProduct(productService.findByTitleForSoap(request.getTitle()));
        return response;
    }

    /*
        Пример запроса: POST http://localhost:8080/shop/ws

        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:f="http://www.flamexander.com/spring/ws/products">
            <soapenv:Header/>
            <soapenv:Body>
                <f:getAllProductsRequest/>
            </soapenv:Body>
        </soapenv:Envelope>
     */

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsRequest")
    @ResponsePayload
    public GetAllProductsResponse getAllProducts(@RequestPayload GetAllProductsRequest request) {
        GetAllProductsResponse response = new GetAllProductsResponse();
        productService.getAllProductsForSoap().forEach(response.getProducts()::add);
        return response;
    }

    /*
        Пример запроса: POST http://localhost:8080/shop/ws

        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:f="http://www.flamexander.com/spring/ws/products">
            <soapenv:Header/>
            <soapenv:Body>
                <f:getProductByTitleRequest>
                    <f:title>Milk</f:title>
                </f:getProductByTitleRequest>
            </soapenv:Body>
        </soapenv:Envelope>
     */

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductByTitleRequest")
    @ResponsePayload
    public GetProductByTitleResponse getProductByNameResponse(@RequestPayload GetProductByTitleRequest request) {
        GetProductByTitleResponse response = new GetProductByTitleResponse();
        response.setProduct(productService.findByTitleForSoap(request.getTitle()));
        return response;
    }
}
