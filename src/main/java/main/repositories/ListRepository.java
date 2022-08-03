package main.repositories;

import main.models.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ListRepository extends MongoRepository<List, String> {
    ArrayList<List> findByName(String name);
}
