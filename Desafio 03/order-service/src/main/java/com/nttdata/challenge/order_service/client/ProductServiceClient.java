package com.nttdata.challenge.order_service.client;

import com.nttdata.challenge.order_service.dto.ProductDTO;
import com.nttdata.challenge.order_service.dto.ProductPageDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "product-service", path = "/produtos")
public interface ProductServiceClient {

    @GetMapping
    ProductPageDTO findAll(); // Altere o tipo de retorno aqui

    @GetMapping("/{id}")
    ProductDTO findById(@PathVariable("id") Long id);
}
