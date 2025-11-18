package my.learn.mireaffjpractice8.util;

import my.learn.mireaffjpractice8.dto.response.NoteDTO;
import my.learn.mireaffjpractice8.model.Note;
import org.springframework.stereotype.Component;

@Component
public class NoteDTOMapper {

    public NoteDTO toDTO(Note note) {
        return NoteDTO.builder()
                .id(note.getId())
                .title(note.getTitle())
                .content(note.getContent())
                .createdAt(note.getCreatedAt())
                .updatedAt(note.getUpdatedAt())
                .build();
    }

}
