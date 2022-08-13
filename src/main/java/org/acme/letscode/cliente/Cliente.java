package org.acme.letscode.cliente;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbDateFormat;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.acme.letscode.categoria.Categoria;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @JsonbTransient
    UUID id;

    @NotBlank(message = "Nome é obrigataório")
    @Size(min = 3, max = 64, message = "Nome de 3 a 64 dígitos.")
    String userName;
    
    @Pattern(regexp = "^[a-zA-Z]{2}\\d{9}", message = "VatNumber deve seguir o padrão XX999999999")
    @NotBlank(message = "VatNumber é obrigatório")
    @Size(min = 11, max = 11, message = "VatNumber possui 13 dígitos.")
    String vatNumber;

    @Email(message = "Digite um email válido.")
    @NotBlank(message = "Email é obrigatório")
    @Size(min = 1, max = 255, message = "Email deve ter entre 1 a 255 dígitos.")
    String email;

    @JsonbDateFormat(value = "yyyy-MM-dd")
    @NotNull(message = "Data de nascimento é obrigatório")
    LocalDate birthDate;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    Categoria categoria;

    /**
     * Panache exige um construtor vazio para fazer alguma coisa.
     * Pra que o Panache usa isso eu não sei dizer.
     */
    Cliente() {}

    /**
     * Isso daqui será usado ao criar um cliente.
     */
    @JsonbCreator
    public static Cliente of(String userName, String vatNumber, String email, LocalDate birthDate, Categoria categoria) {
        return new Cliente(userName, vatNumber, email, birthDate, categoria);
    }

    private Cliente(String name, String vatNumber, String email, LocalDate birthDate, Categoria categoria) {
        this.id = UUID.randomUUID();
        this.userName = name;
        this.vatNumber = vatNumber;
        this.email = email;
        this.birthDate = birthDate;
        this.categoria = categoria;
    }


    public UUID getId() {
        return this.id;
    }

    public String getName() {
        return this.userName;
    }

    public String getVatNumber() {
        return this.vatNumber;
    }

    public String getEmail() {
        return this.email;
    }

    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    public Categoria getCategoria() {
        return categoria;
    }

}