package ru.shop.worker.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import ru.shop.worker.controller.payload.UpdateProductPayload;
import ru.shop.worker.entity.Product;
import ru.shop.worker.service.IProductService;

import java.util.Locale;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("catalogue/products/{productId:\\d+}")
@RequiredArgsConstructor
public class ProductController {

    private final IProductService productService;
    private final MessageSource messageSource;

    @ModelAttribute("product")
    public Product product(@PathVariable("productId") int productId){
        return this.productService.findAllProduct(productId)
                .orElseThrow(() -> new NoSuchElementException("catalogue.errors.product.not_found"));
    }

    @GetMapping
    public String getProduct(){
        return "catalogue/products/product";
    }

    @GetMapping("edit")
    public  String getProductEdit(){
        return "catalogue/products/edit_product";
    }

    @PostMapping("edit")
    public String updateProduct(@ModelAttribute(name="product", binding = false) Product product,@Valid UpdateProductPayload payload,
                                BindingResult bindingResult,
                                Model model){
            if(bindingResult.hasErrors()) {
                model.addAttribute("payload", payload);
                model.addAttribute("errors", bindingResult.getAllErrors()
                        .stream().map(ObjectError::getDefaultMessage).toList());
                return "catalogue/products/edit_product";
            }else {
                this.productService.updateProduct(product.getId(), payload.productName(), payload.description());
                return "redirect:/catalogue/products/%d".formatted(product.getId());
            }
    }

    @PostMapping("delete")
    public String deleteProduct(@ModelAttribute("product") Product product){
        this.productService.deleteProduct(product.getId());
        return "redirect:/catalogue/products/list";
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String handleNoSuchElementException(NoSuchElementException exception,
                                               Model model, HttpServletResponse response,
                                               Locale locale){
        response.setStatus(HttpStatus.NOT_FOUND.value());
        model.addAttribute("error",
                this.messageSource.getMessage(exception.getMessage(), new Object[0],
                        exception.getMessage(), locale));
        return "errors/404";
    }
}
