package main.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@Data
@Document
@Schema(description = "Сущность продукта")
public class Product {
    @Id
    @Schema(description = "Id продукта")
    private String id;
    @Indexed
    @Schema(description = "Имя продукта")
    private String name;
    @Schema(description = "Описание продукта")
    private String description;
    @Schema(description = "Количество ккал")
    private Integer kcal;

    public Product(String name, String description, Integer kcal) {
        this.name = name;
        this.description = description;
        this.kcal = kcal;
    }
}
