package ru.chernyshev.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.chernyshev.persist.Product;
import ru.chernyshev.persist.ProductRepository;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;

//    @GetMapping
//    public String listPage(@RequestParam (required = false) String productFilter, @RequestParam (required = false) Double costFilter, Model model) {
//
//        costFilter = costFilter == null || costFilter == 0.0 ? null : costFilter;
//        model.addAttribute("products", productRepository.productsByCost(costFilter));
//
//        productFilter = productFilter == null || productFilter.isBlank() ? null : "%" + productFilter.trim() + "%";
//        model.addAttribute("products", productRepository.productsByTitle(productFilter));
//
//        return "product";
//    }

    @GetMapping
    public String listPage(@RequestParam(required = false) String productFilter,
                           @RequestParam(required = false) Double costFilter,
                           @RequestParam(required = false) Double minCostFilter,
                           @RequestParam(required = false) Double maxCostFilter,
                           @RequestParam(required = false) Double costMinToMaxFilter,
                           @RequestParam(required = false) Double costMaxToMinFilter,
                           Model model) {
        if (costFilter != null) {
            model.addAttribute("products", productRepository.productsByCost(costFilter));
        } else if (productFilter != null) {
            productFilter = "%" + productFilter.trim() + "%";
            model.addAttribute("products", productRepository.productsByTitle(productFilter));
        } else if (minCostFilter != null && maxCostFilter != null) {
            model.addAttribute("products", productRepository.productsSortMinAndMax(minCostFilter, maxCostFilter));
        } else if (costMinToMaxFilter != null) {
            model.addAttribute("products", productRepository.minToMaxSort(costMinToMaxFilter));
        } else if (costMaxToMinFilter != null) {
            model.addAttribute("products", productRepository.maxToMinSort(costMaxToMinFilter));
        } else {
            model.addAttribute("products", productRepository.findAll());
        }
        return "product";
    }

    @GetMapping("/add")
    public String addProduct(Model model) {
        model.addAttribute("product", new Product("", 0.0, 0));
        return "product_form";
    }

    @GetMapping("/{id}")
    public String form(@PathVariable("id") long id, Model model) {
        model.addAttribute("product", productRepository.findById(id));
        return "product_form";
    }

    @PostMapping
    public String saveProduct(@Valid Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "product_form";
        }
        productRepository.save(product);
        return "redirect:/product";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(Product product) {
        productRepository.deleteById(product.getId());
        return "redirect:/product";
    }

}
