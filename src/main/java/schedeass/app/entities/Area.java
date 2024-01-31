package schedeass.app.entities;

import lombok.*;

import javax.persistence.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "area")
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codice", nullable = false)
    private Integer codice;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "tipologia", nullable = false)
    private String tipologia;

    @Column(name = "descrizione", nullable = false)
    private String descrizione;

    @Column(name = "stakeholder", nullable = false)
    private String stakeholder;

    @Column(name = "anno", nullable = false)
    private Integer anno;


}
