package ru.shop.worker.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.shop.worker.client.BadRequestException;
import ru.shop.worker.client.IProductsRestClient;
import ru.shop.worker.controller.payload.NewProductPayload;
import ru.shop.worker.entity.Product;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@RequestMapping("catalogue/products")
public class ProductsController {

    private final IProductsRestClient productsRestClient;

    @GetMapping("list")
    public String getProductsList(Model model) {
        model.addAttribute("products", this.productsRestClient.findAllProducts());
        return "catalogue/products/list";
    }

    @GetMapping("create")
    public String getNewProduct() {
        return "catalogue/products/new_product";
    }

    @PostMapping("create")
    public String createProduct(NewProductPayload payload,
                                Model model) {
        try {
            Product product = this.productsRestClient.createProduct(payload.productName(), payload.description());
            return "redirect:/catalogue/products/%d".formatted(product.id());
        } catch (BadRequestException exception) {
            model.addAttribute("payload", payload);
            model.addAttribute("errors", exception.getErrors());
            return "catalogue/products/new_product";
        }
    }
}
