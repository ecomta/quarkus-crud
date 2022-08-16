package org.acme.letscode.cliente;

import java.time.LocalDate;
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
import org.acme.letscode.categoria.Categoria;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @JsonbTransient
    private UUID id;

    @NotBlank(message = "Nome é obrigataório")
    @Size(min = 3, max = 64, message = "Nome de 3 a 64 dígitos.")
    private String userName;
    
    @Pattern(regexp = "^[a-zA-Z]{2}\\d{9}", message = "VatNumber deve seguir o padrão XX999999999")
    @NotBlank(message = "VatNumber é obrigatório")
    @Size(min = 11, max = 11, message = "VatNumber possui 13 dígitos.")
    private String vatNumber;

    @Email(message = "Digite um email válido.")
    @NotBlank(message = "Email é obrigatório")
    @Size(min = 1, max = 255, message = "Email deve ter entre 1 a 255 dígitos.")
    private String email;

    @JsonbDateFormat(value = "yyyy-MM-dd")
    @NotNull(message = "Data de nascimento é obrigatório")
    private LocalDate birthDate;

    @ManyToOne(targetEntity = Categoria.class)
    @JoinColumn(name = "categoria_id", referencedColumnName = "id")
    private Categoria categoria;

    /**
     * Panache exige um construtor vazio para fazer alguma coisa.
     * Pra que o Panache usa isso eu não sei dizer.
     */
    public Cliente() {}

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

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getVatNumber() {
        return this.vatNumber;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", userName='" + getUserName() + "'" +
            ", vatNumber='" + getVatNumber() + "'" +
            ", email='" + getEmail() + "'" +
            ", birthDate='" + getBirthDate() + "'" +
            ", categoria='" + getCategoria() + "'" +
            "}";
    }


}