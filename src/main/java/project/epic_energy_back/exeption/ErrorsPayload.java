package project.epic_energy_back.exeption;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorsPayload {
	private String message;
	private LocalDateTime timestamp;
}