package main.repositories;

import main.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    ArrayList<Product> findByName(String name);
}
