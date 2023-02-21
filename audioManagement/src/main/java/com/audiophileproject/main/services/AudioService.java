package com.audiophileproject.main.services;

import com.audiophileproject.main.models.AudioFile;
import com.audiophileproject.main.repositories.AudioRepository;
import lombok.NonNull;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Service
public class AudioService {
	private final AudioRepository audioRepository;

	public AudioService(AudioRepository audioRepository) {
		this.audioRepository = audioRepository;
	}

    public AudioFile getAudioFileById(Long id) {
        return audioRepository.findById(id).orElse(null);
    }

    public List<AudioFile> getAllAudioFiles() {
        return audioRepository.findAll();
    }

    public boolean deleteAudioFileById(Long id) {

        var audioFile = audioRepository.findById(id).orElse(null);
        if (audioFile == null)
            return false;

        audioRepository.deleteById(id);

        var filepath = audioFile.getFilePath();
        File file = new File(filepath);

        file.delete();

        return true;
    }

    public Resource downloadAudioFile(AudioFile audioFile) throws FileNotFoundException {
        File file = new File(audioFile.getFilePath());
        if (file.exists()) {
            return new FileSystemResource(file);
        } else {
            throw new FileNotFoundException("File not found: " + audioFile.getFilePath());
        }
    }

    public AudioFile uploadAudioFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();

        String filePath = "/tmp/upload/" + fileName;

        try {
            file.transferTo(new File(filePath));
        } catch (IOException e) {
            return null;
        }

        AudioFile audioFile = AudioFile.builder()
                .title(fileName)
                .filePath(filePath)
                .build();

        return audioRepository.save(audioFile);
    }
}
