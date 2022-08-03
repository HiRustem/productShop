package main.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import main.models.List;
import main.models.Product;
import main.repositories.ListRepository;
import main.repositories.ProductRepository;
import main.services.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Map;
import java.util.Optional;

@RestController
@Tag(name = "Главный контроллер", description = "Позволяет управлять приложением")
public class ApplicationController {
    @Autowired
    ListRepository listRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ListService listService;

    @GetMapping("/list")
    @Operation(summary = "Получение списка", description = "Позволяет получить список по Id")
    public Optional<List> getList(@RequestParam @Parameter(description = "Id списка") String listId) {
        return listRepository.findById(listId);
    }

    @PostMapping("/list")
    @Operation(summary = "Добавление списка", description = "Позволяет добавить список в базу данных")
    public Map<String, Boolean> addList(@RequestParam @Parameter(description = "Имя списка") String name) {
        if (listRepository.findByName(name).size() > 0) {
            return Map.of("result", false);
        }

        listRepository.save(new List(name));

        return Map.of("result", true);
    }

    @GetMapping("/product")
    @Operation(summary = "Получение списка продуктов", description = "Позволяет получить полный список продуктов")
    public java.util.List<Product> getProducts() {
        return productRepository.findAll();
    }

    @PostMapping("/product")
    @Operation(summary = "Добавление продукта", description = "Позволяет добавить продукт в базу данных")
    public Map<String, Boolean> addProduct(@RequestParam @Parameter(description = "Имя продукта") String name, @RequestParam @Parameter(description = "Описание продукта") String description, @RequestParam @Parameter(description = "Количество ккал") Integer kcal) {
        if (productRepository.findByName(name).size() > 0) {
            return Map.of("result", false);
        }

        productRepository.save(new Product(name, description, kcal));

        return Map.of("result", true);
    }

    @PostMapping("/productToList")
    @Transactional
    @Operation(summary = "Добавить продукт в список", description = "Позволяет добавить продукт в список по id")
    public Map<String, Boolean> addProductToList(@RequestParam @Parameter(description = "Id списка") String listId, @RequestParam @Parameter(description = "Имя продукта") String name, @RequestParam @Parameter(description = "Описание продукта") String description, @RequestParam @Parameter(description = "Количество ккал") Integer kcal) {
        if (listId.isEmpty() || name.isEmpty() || description.isEmpty() || kcal == null) {
            return Map.of("result", false);
        }

        Product product = new Product(name, description, kcal);
        productRepository.save(product);

        Product newProduct = productRepository.findByName(name).stream().findFirst().get();

        listService.addProductToList(listId, newProduct);

        return Map.of("result", true);
    }
}
