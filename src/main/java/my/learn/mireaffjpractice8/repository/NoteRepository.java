package my.learn.mireaffjpractice8.repository;

import my.learn.mireaffjpractice8.model.Note;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NoteRepository extends MongoRepository<Note, UUID> {

    List<Note> findByTitleContainingIgnoreCase(String title);

    List<Note> findByTitleContainingIgnoreCase(String title, PageRequest pageable);



}
