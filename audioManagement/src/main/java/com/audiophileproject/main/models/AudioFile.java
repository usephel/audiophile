package com.audiophileproject.main.models;

import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@Builder
@AllArgsConstructor // test if you can work without this
@NoArgsConstructor // and this
@Entity
public class AudioFile {
	@Id
	@SequenceGenerator(
			name = "audio_id_sequence",
			sequenceName = "audio_id_sequence"
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "audio_id_sequence"
	)
	private Long id;
	private String title;
	private String filePath;
}
