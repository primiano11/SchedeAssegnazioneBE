package schedeass.app.entities;

import lombok.*;

import javax.persistence.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "obiettivoIndividuale")
public class ObiettivoIndividuale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codice", nullable = false)
    private Integer codice;

    @ManyToOne
    @JoinColumn(name = "obiettivoStrategico", nullable = false)
    private ObiettivoStrategico obiettivoStrategico;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "responsabilePolitico", nullable = false)
    private String responsabilePolitico;

    @Column(name = "responsabile", nullable = false)
    private String responsabile;

    @ManyToOne
    @JoinColumn(name = "area", nullable = false)
    private Area area;

    @Column(name = "tipologia", nullable = false)
    private String tipologia;

    @Column(name = "indicatore", nullable = false)
    private String indicatore;

    @Column(name = "peso", nullable = false)
    private Integer peso;

    @Column(name = "anno", nullable = false)
    private Integer anno;

    @ManyToOne
    @JoinColumn(name = "dipendente", nullable = true)
    private Dipendente dipendente;

}
