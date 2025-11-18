package my.learn.mireaffjpractice8.controller;

import lombok.RequiredArgsConstructor;
import my.learn.mireaffjpractice8.dto.request.CreateNoteRequest;
import my.learn.mireaffjpractice8.dto.request.PatchNoteRequest;
import my.learn.mireaffjpractice8.dto.response.NoteDTO;
import my.learn.mireaffjpractice8.service.NoteService;
import my.learn.mireaffjpractice8.util.NoteDTOMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notes")
public class NoteController {

    private final NoteService noteService;
    private final NoteDTOMapper mapper;


    @PostMapping
    public ResponseEntity<NoteDTO> createNote(@RequestBody CreateNoteRequest req) {
        return new ResponseEntity<>(
                mapper.toDTO(noteService.createNote(req)),
                HttpStatus.CREATED
        );
    }



    @GetMapping
    public ResponseEntity<List<NoteDTO>> getNotes(@RequestParam(value = "q", required = false) String q,
                                                  @RequestParam(value = "limit", required = false) Integer limit,
                                                  @RequestParam(value = "skip", required = false) Integer page) {
        PageRequest p = null;

        if (limit != null && page != null) {
            p = PageRequest.of(page, limit);
        }

        return new ResponseEntity<>(
                noteService.getNotes(p, q).stream().map(mapper::toDTO).toList(),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteDTO> getNoteById(@PathVariable("id") String id) {
        return new ResponseEntity<>(
                mapper.toDTO(noteService.findById(id)),
                HttpStatus.OK
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<NoteDTO> patchNoteById(@PathVariable("id") String id,
                                                 @RequestBody PatchNoteRequest req) {
        return new ResponseEntity<>(
                mapper.toDTO(noteService.patchNote(req, id)),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<NoteDTO> deleteNoteById(@PathVariable("id") String id) {
        noteService.deleteNoteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
