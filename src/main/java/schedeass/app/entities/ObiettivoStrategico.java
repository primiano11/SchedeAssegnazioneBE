package schedeass.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "obiettivoStrategico")
public class ObiettivoStrategico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codice", nullable = false)
    private Integer codice;

    @ManyToOne
    @JoinColumn(name = "area_id", nullable = false)
    private Area area;

    @Column(name = "tipologia", nullable = false)
    private String tipologia;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "presidio", nullable = false)
    private String presidio;

    @Column(name = "stakeholder", nullable = false)
    private String stakeholder;

    @Column(name = "anno", nullable = false)
    private Integer anno;

}
