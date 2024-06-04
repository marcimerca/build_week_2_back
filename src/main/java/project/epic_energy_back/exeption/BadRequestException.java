/*
package project.epic_energy_back.exeption;

import lombok.Data;
import org.springframework.validation.ObjectError;

import java.util.List;
@Data
public class BadRequestException extends RuntimeException{

    private List<ObjectError> errorsList;

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(List<ObjectError> errorsList) {
        super("Errori nel body");
        this.errorsList = errorsList;
    }
}
*/
