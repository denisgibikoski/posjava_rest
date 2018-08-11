package br.edu.utfpr.pb.posjava.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "serie")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString()
public class Serie implements AbstractModel, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    @Column(length = 1000, nullable = false)
    private String resumo;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataEstreia;

    @Column(nullable = true)
    @Temporal(TemporalType.DATE)
    private Date dataEncerramento;

    @Column(nullable = false)
    private BigDecimal nota;

    @ManyToOne()
    @JoinColumn(name = "produtora_id", referencedColumnName = "id")
    private Produtora produtora;

    @ManyToOne()
    @JoinColumn(name = "genero_id", referencedColumnName = "id")
    private Genero genero;
    
    @Column()
    private String imagem;
}
