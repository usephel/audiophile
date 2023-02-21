package com.audiophileproject.main.controllers;

import com.audiophileproject.main.models.AudioFile;
import com.audiophileproject.main.services.AudioService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/audio")
public class AudioController {
	private final AudioService audioService;

	public AudioController(AudioService audioService) {
		this.audioService = audioService;
	}

	@GetMapping
    public List<AudioFile> getAllAudioFiles() {
        return audioService.getAllAudioFiles();
    }

	@GetMapping("/{id}")
	public ResponseEntity<Resource> handleFileDownload(@PathVariable Long id, HttpServletRequest request) throws IOException {
		AudioFile audioFile = audioService.getAudioFileById(id);
		if (audioFile == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		Resource resource = audioService.downloadAudioFile(audioFile);

		String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		if (contentType == null) {
			contentType = "application/octet-stream";
		}
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}

	@PostMapping
	public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file) {

		if (file == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("file is null");
		}

		var audioFile = audioService.uploadAudioFile(file);
		if (audioFile == null)
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed to upload file: ");

		return ResponseEntity.ok("File uploaded successfully");
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteAudioFileById(@PathVariable Long id) {
		var audioFile = audioService.getAudioFileById(id);
		if (audioFile == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		if(!audioService.deleteAudioFileById(id))
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		return  ResponseEntity.ok("File is deleted successfully");
	}

}
