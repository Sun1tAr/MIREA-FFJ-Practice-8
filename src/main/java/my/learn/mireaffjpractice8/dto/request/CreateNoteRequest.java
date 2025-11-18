package my.learn.mireaffjpractice8.dto.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateNoteRequest {

    private String title;
    private String content;

}
