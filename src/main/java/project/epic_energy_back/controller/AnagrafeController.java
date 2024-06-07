package project.epic_energy_back.controller;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import project.epic_energy_back.dto.anagrafeDto.FileAnagrafeDto;
import project.epic_energy_back.entities.anagrafe.AnagrafeCenter;
import project.epic_energy_back.service.AnagrafeService;

import java.util.List;

@RestController
@RequestMapping("/anagrafe")
@CrossOrigin(origins = "*")
public class AnagrafeController {
    @Autowired
    private AnagrafeService anagrafeService;

    @GetMapping
    public List<AnagrafeCenter> getAllAnagrafeCenter() {
        return anagrafeService.getAnagrafe();
    }

    @PostMapping("/loadAnagrafe")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public String loadAnagrafe(@ModelAttribute @Validated FileAnagrafeDto fileAnagrafeDto, BindingResult bindingResult) throws BadRequestException {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).
                    reduce("", (s, s2) -> s + s2));
        }
        anagrafeService.saveAnagrafe(fileAnagrafeDto);
        return "Anagrafe data loaded successfully!";
    }

}
