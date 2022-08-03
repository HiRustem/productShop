package main.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.Set;


@NoArgsConstructor
@Data
@Document
@Schema(description = "Сущность списка")
public class List {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @Schema(description = "Id списка")
    private String id;
    @Schema(description = "Имя списка")
    @Indexed
    private String name;

    @Schema(description = "Продукты в списке")
    private Set<Product> products;
    public List(String name) {
        this.name = name;
    }
}
