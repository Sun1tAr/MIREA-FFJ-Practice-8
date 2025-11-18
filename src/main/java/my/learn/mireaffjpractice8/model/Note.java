package my.learn.mireaffjpractice8.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


import java.time.LocalDateTime;
import java.util.UUID;

@Document(collection = "notes")
@Data
@Builder
public class Note {

    @Id
    private UUID id;

    @Indexed(unique = true)
    private String title;

    private String content;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
