package com.bridgelabz.fundoonote.note.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonote.note.exceptions.DateNotProperlySetException;
import com.bridgelabz.fundoonote.note.exceptions.LabelNotFoundException;
import com.bridgelabz.fundoonote.note.exceptions.NoteCreationException;
import com.bridgelabz.fundoonote.note.exceptions.NoteNotFoundException;
import com.bridgelabz.fundoonote.note.exceptions.UnAuthorizedException;
import com.bridgelabz.fundoonote.note.exceptions.UserNotFoundException;
import com.bridgelabz.fundoonote.note.model.CreateNoteDTO;
import com.bridgelabz.fundoonote.note.model.NoteDTO;
import com.bridgelabz.fundoonote.note.model.UpdateNoteDTO;
import com.bridgelabz.fundoonote.note.services.NoteService;
import com.bridgelabz.fundoonote.user.model.ResponseDTO;

@RestController
@RequestMapping("/note")
public class NoteController {

	@Autowired
	private NoteService noteService;

	/**
	 * create note
	 * 
	 * @param createNoteDTO
	 * @param token
	 * @param request
	 * @return
	 * @throws NoteNotFoundException
	 * @throws UnAuthorizedException
	 * @throws UserNotFoundException
	 * @throws NoteCreationException
	 * @throws LabelNotFoundException
	 */
	@PostMapping("/create")
	public ResponseEntity<NoteDTO> create(@RequestBody CreateNoteDTO createNoteDTO,
			@RequestAttribute("userId") String userId) throws NoteNotFoundException, UnAuthorizedException,
			UserNotFoundException, NoteCreationException, LabelNotFoundException {

		NoteDTO noteDTO = noteService.createNote(createNoteDTO, userId);

		return new ResponseEntity<>(noteDTO, HttpStatus.CREATED);

	}

	/**
	 * @param noteId
	 * @param token
	 * @param request
	 * @return
	 * @throws NoteNotFoundException
	 * @throws UnAuthorizedException
	 * @throws UserNotFoundException
	 */
	@DeleteMapping("/delete/{noteId}")
	public ResponseEntity<ResponseDTO> delete(@PathVariable("noteId") String noteId,
			@RequestAttribute("userId") String userId)
			throws NoteNotFoundException, UnAuthorizedException, UserNotFoundException {

		noteService.deleteNote(userId, noteId);
		ResponseDTO responseDTO = new ResponseDTO();
		responseDTO.setMessage("deleted note successfully");
		responseDTO.setStatus(1);
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);

	}

	/**
	 * @param updateNoteDTO
	 * @param token
	 * @param request
	 * @return
	 * @throws NoteNotFoundException
	 * @throws UnAuthorizedException
	 * @throws UserNotFoundException
	 * @throws NoteCreationException
	 */
	@PutMapping("/update")
	public ResponseEntity<ResponseDTO> update(@RequestBody UpdateNoteDTO updateNoteDTO,
			@RequestAttribute("userId") String userId)
			throws NoteNotFoundException, UnAuthorizedException, UserNotFoundException, NoteCreationException {

		noteService.updateNote(userId, updateNoteDTO);
		ResponseDTO responseDTO = new ResponseDTO();
		responseDTO.setMessage("update note successfully");
		responseDTO.setStatus(1);
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);

	}

	/**
	 * @param token
	 * @param request
	 * @param noteId
	 * @return
	 * @throws UserNotFoundException
	 * @throws NoteNotFoundException
	 * @throws UnAuthorizedException
	 */
	@PostMapping("/restore/{noteId}")
	public ResponseEntity<ResponseDTO> restore(@RequestAttribute("userId") String userId,
			@PathVariable("noteId") String noteId)
			throws UserNotFoundException, NoteNotFoundException, UnAuthorizedException {

		noteService.restoreNote(userId, noteId);
		ResponseDTO responseDTO = new ResponseDTO();
		responseDTO.setMessage("restore note successfully");
		responseDTO.setStatus(1);
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);

	}

	/**
	 * @param token
	 * @param request
	 * @return
	 * @throws NoteNotFoundException
	 * @throws UserNotFoundException
	 */
	@GetMapping("/read")
	public ResponseEntity<List<NoteDTO>> readAll(@RequestAttribute("userId") String userId)
			throws NoteNotFoundException, UserNotFoundException {

		List<NoteDTO> list = noteService.readNote(userId);

		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	/**
	 * @param noteId
	 * @param token
	 * @param request
	 * @return
	 * @throws NoteNotFoundException
	 * @throws UnAuthorizedException
	 * @throws UserNotFoundException
	 */
	@PutMapping("/trash/{noteId}")
	public ResponseEntity<ResponseDTO> trash(@PathVariable("noteId") String noteId,
			@RequestAttribute("userId") String userId)
			throws NoteNotFoundException, UnAuthorizedException, UserNotFoundException {

		noteService.trashNote(userId, noteId);
		ResponseDTO responseDTO = new ResponseDTO();
		responseDTO.setMessage("trash note successfully");
		responseDTO.setStatus(1);
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);

	}

	/**
	 * @param noteId
	 * @param token
	 * @param remaindAt
	 * @param request
	 * @return
	 * @throws NoteNotFoundException
	 * @throws UnAuthorizedException
	 * @throws UserNotFoundException
	 * @throws DateNotProperlySetException
	 */
	@PutMapping("/addreminder/{noteId}")
	public ResponseEntity<ResponseDTO> addRemainder(@PathVariable("noteId") String noteId,
			@RequestAttribute("userId") String userId, @RequestParam Date remaindAt)
			throws NoteNotFoundException, UnAuthorizedException, UserNotFoundException, DateNotProperlySetException {

		noteService.addReminder(userId, noteId, remaindAt);

		ResponseDTO responseDTO = new ResponseDTO();
		responseDTO.setMessage("remainder set successfully");
		responseDTO.setStatus(1);
		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}

	/**
	 * @param noteId
	 * @param token
	 * @param request
	 * @return
	 * @throws NoteNotFoundException
	 * @throws UnAuthorizedException
	 * @throws UserNotFoundException
	 */
	@PutMapping("/removereminder/{noteId}")
	public ResponseEntity<ResponseDTO> removeRemainder(@PathVariable("noteId") String noteId,
			@RequestAttribute("userId") String userId)
			throws NoteNotFoundException, UnAuthorizedException, UserNotFoundException {

		noteService.removeReminder(userId, noteId);

		ResponseDTO responseDTO = new ResponseDTO();
		responseDTO.setMessage("remainder removed");
		responseDTO.setStatus(1);

		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}

	/**
	 * @param token
	 * @param request
	 * @return
	 * @throws UserNotFoundException
	 */
	@GetMapping("/getalltrash")
	public ResponseEntity<List<NoteDTO>> getAllTrashNotes(@RequestAttribute("userId") String userId)
			throws UserNotFoundException {

		List<NoteDTO> list = noteService.getAllTrashNotes(userId);

		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	/**
	 * @param token
	 * @param request
	 * @return
	 * @throws UserNotFoundException
	 */
	@GetMapping("/Getallarchive")
	public ResponseEntity<List<NoteDTO>> getAllArchiveNotes(@RequestAttribute("userId") String userId)
			throws UserNotFoundException {

		List<NoteDTO> list = noteService.getAllArchiveNotes(userId);

		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	/**
	 * @param noteId
	 * @param token
	 * @param request
	 * @return
	 * @throws UserNotFoundException
	 * @throws NoteNotFoundException
	 * @throws UnAuthorizedException
	 */
	@PutMapping("/setarchives/{noteId}")
	public ResponseEntity<ResponseDTO> setArchives(@PathVariable("noteId") String noteId,
			@RequestAttribute("userId") String userId, @RequestParam boolean isArchive)
			throws UserNotFoundException, NoteNotFoundException, UnAuthorizedException {

		noteService.setArchiveNotes(userId, noteId, isArchive);

		ResponseDTO responseDTO = new ResponseDTO();
		responseDTO.setMessage("note added to archive");
		responseDTO.setStatus(1);

		return new ResponseEntity<>(responseDTO, HttpStatus.OK);

	}

	/**
	 * @param noteId
	 * @param token
	 * @param color
	 * @param request
	 * @return
	 * @throws UserNotFoundException
	 * @throws NoteNotFoundException
	 * @throws UnAuthorizedException
	 */
	@PutMapping("/changecolor/{noteId}")
	public ResponseEntity<ResponseDTO> changeColor(@PathVariable("noteId") String noteId,
			@RequestAttribute("userId") String userId, @RequestParam String color)
			throws UserNotFoundException, NoteNotFoundException, UnAuthorizedException {

		noteService.changeColor(userId, noteId, color);

		ResponseDTO responseDTO = new ResponseDTO();
		responseDTO.setMessage("color changed");
		responseDTO.setStatus(1);

		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}

	/**
	 * @param noteId
	 * @param token
	 * @param request
	 * @return
	 * @throws UserNotFoundException
	 * @throws NoteNotFoundException
	 * @throws UnAuthorizedException
	 */
	@PutMapping("/unarchive/{noteId}")
	public ResponseEntity<ResponseDTO> unArchiveNote(@PathVariable("noteId") String noteId,
			@RequestAttribute("userId") String userId, @RequestParam boolean isArchive)
			throws UserNotFoundException, NoteNotFoundException, UnAuthorizedException {

		noteService.unArchiveNotes(userId, noteId, isArchive);

		ResponseDTO responseDTO = new ResponseDTO();
		responseDTO.setMessage("unarchive");
		responseDTO.setStatus(1);

		return new ResponseEntity<>(responseDTO, HttpStatus.OK);

	}

	/**
	 * @param noteId
	 * @param token
	 * @param request
	 * @return
	 * @throws UserNotFoundException
	 * @throws NoteNotFoundException
	 * @throws UnAuthorizedException
	 */
	@PutMapping("/pin/{noteId}")
	public ResponseEntity<ResponseDTO> pinAndUnPin(@PathVariable("noteId") String noteId,
			@RequestAttribute("userId") String userId, @RequestParam boolean isPin)
			throws UserNotFoundException, NoteNotFoundException, UnAuthorizedException {

		noteService.pinAndUnPinNote(userId, noteId, isPin);

		ResponseDTO responseDTO = new ResponseDTO();
		responseDTO.setMessage("pinned note");
		responseDTO.setStatus(1);

		return new ResponseEntity<>(responseDTO, HttpStatus.OK);
	}

	/**
	 * @param noteId
	 * @param userId
	 * @param description
	 * @return
	 * @throws IOException
	 * @throws UserNotFoundException
	 * @throws NoteNotFoundException
	 * @throws UnAuthorizedException
	 */
	@PutMapping("/contentscrapping/{noteId}")
	public ResponseEntity<ResponseDTO> contentScrap(@PathVariable("noteId") String noteId,
			@RequestAttribute("userId") String userId, @RequestParam String description)
			throws IOException, UserNotFoundException, NoteNotFoundException, UnAuthorizedException {

		noteService.contentScrapping(description, noteId, userId);

		ResponseDTO responseDTO = new ResponseDTO();
		responseDTO.setMessage("content scrapped");
		responseDTO.setStatus(1);

		return new ResponseEntity<>(responseDTO, HttpStatus.OK);

	}
}
