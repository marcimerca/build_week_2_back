package project.epic_energy_back.dto.anagrafeDto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class AnagrafeDto {
    private MultipartFile comuniFile;
    private MultipartFile provinceFile;
}
