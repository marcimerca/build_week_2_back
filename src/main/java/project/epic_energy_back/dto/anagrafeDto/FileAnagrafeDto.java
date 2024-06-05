package project.epic_energy_back.dto.anagrafeDto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class FileAnagrafeDto {
    @NotNull(message = "comuniFile cannot be null")
    private MultipartFile comuniFile;

    @NotNull(message = "provinceFile cannot be null")
    private MultipartFile provinceFile;
}