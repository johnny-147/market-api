package com.kemiex.marketapi;

import com.kemiex.marketapi.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/market-app")
public class MarketRestController {

    @Autowired
    private MarketService marketService;

    @GetMapping("/all-products")
    public @ResponseBody
    List<Product> getAllProducts() {
        return marketService.getAllProducts();
    }

    @PostMapping("add-product")
    public void addProduct(@RequestBody Product product) {
        marketService.addProduct(product);
    }

    @PutMapping("edit-product")
    public void editProduct(@RequestBody Product product) {
        marketService.editProduct(product);
    }

    @DeleteMapping("delete-product/{id}")
    public void deleteProduct(@PathVariable(value = "id") int productId) {
        marketService.deleteProduct(productId);
    }

}
