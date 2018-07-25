package net.hiskarma.stellarTest.model;

import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Data
@Document(collection = "mongocache")
public class MongoCache {
    @Id
    private long id;

    @Indexed(unique = true)
    private String mongocache;
    private boolean value;
}
