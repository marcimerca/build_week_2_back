package project.epic_energy_back.entity.ErrorPaylod;

import java.util.Date;
import java.util.List;

public record ErrorDeiPayload(
        String message,
        Date timestamp,
        List<String> errorsList
) {
}
