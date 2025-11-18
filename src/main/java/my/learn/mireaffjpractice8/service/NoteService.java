package my.learn.mireaffjpractice8.service;

import lombok.RequiredArgsConstructor;
import my.learn.mireaffjpractice8.dto.request.CreateNoteRequest;
import my.learn.mireaffjpractice8.dto.request.PatchNoteRequest;
import my.learn.mireaffjpractice8.exception.AppException;
import my.learn.mireaffjpractice8.model.Note;
import my.learn.mireaffjpractice8.repository.NoteRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class NoteService {

    private final NoteRepository noteRepository;

    public Note createNote(CreateNoteRequest req) {
        Note note = Note.builder()
                .id(UUID.randomUUID())
                .title(req.getTitle())
                .content(req.getContent())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        return noteRepository.save(note);
    }

    public List<Note> getNotes(PageRequest pageable, String title) {
        if (pageable == null && title == null) {
            return noteRepository.findAll();
        }

        if (pageable != null && title != null) {
            return noteRepository.findByTitleContainingIgnoreCase(title, pageable);
        }

        if (title != null) {
            return noteRepository.findByTitleContainingIgnoreCase(title);
        }

        return noteRepository.findAll(pageable).stream().toList();
    }

    public Note findById(String id) {
        Note note = noteRepository.findById(UUID.fromString(id)).orElse(null);
        if (note == null) {
            throw new AppException("Note with id " + id + " not found", HttpStatus.NOT_FOUND);
        }
        return note;
    }

    public Note patchNote(PatchNoteRequest req, String id) {
        Note note = findById(id);
        note.setContent(req.getContent());
        note.setUpdatedAt(LocalDateTime.now());
        return noteRepository.save(note);
    }

    public void deleteNoteById(String id) {
        Note note = findById(id);
        noteRepository.delete(note);
    }

}
