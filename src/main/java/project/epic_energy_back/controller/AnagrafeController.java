package project.epic_energy_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import project.epic_energy_back.dto.anagrafeDto.AnagrafeDto;
import project.epic_energy_back.service.AnagrafeService;

@RestController
@RequestMapping("/anagrafe")
public class AnagrafeController {
    @Autowired
    private AnagrafeService anagrafeService;

    @PostMapping("/loadAnagrafe")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public String loadAnagrafe(@ModelAttribute AnagrafeDto anagrafeDto) {
        anagrafeService.saveAnagrafe(anagrafeDto);
        return "Anagrafe data loaded successfully!";
    }

}
