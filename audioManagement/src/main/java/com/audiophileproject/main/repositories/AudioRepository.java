package com.audiophileproject.main.repositories;

import com.audiophileproject.main.models.AudioFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AudioRepository extends JpaRepository<AudioFile, Long> {
}
