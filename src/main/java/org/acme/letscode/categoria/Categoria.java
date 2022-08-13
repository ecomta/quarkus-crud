package org.acme.letscode.categoria;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "categoria")
public class Categoria extends PanacheEntityBase {

    /*
     * Informações sobre ID da tabela Categoria.
     */
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonbTransient
    private Long id;

    /*
     * Informações sobre Nome da tabela Categoria.
     */
    @Size(min = 5, max = 128, message = "Nome inválido. Digite um nome com no mínimo 5 caracteres.")
    @Column(nullable = false, length = 128)
    @NotBlank(message = "Nome inválido. Nome dá categoria não pode estar vazio.")
    private String nome;

    public Categoria() {}

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }

}