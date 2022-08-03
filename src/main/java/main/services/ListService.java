package main.services;

import main.models.List;
import main.models.Product;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class ListService {

    private final MongoTemplate mongoTemplate;

    public ListService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void addProductToList(String listId, Product product) {
        Query query = new Query(Criteria.where("id").is(listId));

        Update update = new Update().addToSet("products", product);

        mongoTemplate.findAndModify(query, update, List.class);

    }
}
