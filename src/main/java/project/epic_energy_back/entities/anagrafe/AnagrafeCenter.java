package project.epic_energy_back.entities.anagrafe;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "anagrafe_center")
public class AnagrafeCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

}