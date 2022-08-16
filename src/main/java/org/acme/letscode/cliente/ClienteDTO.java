package org.acme.letscode.cliente;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.validation.constraints.*;
import java.time.LocalDate;

public class ClienteDTO {

    @NotBlank(message = "Nome é obrigataório")
    @Size(min = 3, max = 64, message = "Nome de 3 a 64 dígitos.")
    private String nome;

    @Pattern(regexp = "^[a-zA-Z]{2}\\d{9}", message = "VatNumber deve seguir o padrão XX999999999")
    @NotBlank(message = "VatNumber é obrigatório")
    @Size(min = 11, max = 11, message = "VatNumber deve possuir 11 dígitos.")
    private String vatNumber;

    @Email(message = "Digite um email válido.")
    @NotBlank(message = "Email é obrigatório")
    @Size(min = 1, max = 255, message = "Email deve ter entre 1 a 255 dígitos.")
    private String email;

    @JsonbDateFormat(value = "yyyy-MM-dd")
    @NotNull(message = "Data de nascimento é obrigatório")
    private LocalDate birthDate;

    private Long categoria_id;

    public ClienteDTO() {
    }

    /*
     * Preciso dos Setters para que ele consiga atribuir valores nessa classe.
     */
    public ClienteDTO setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public ClienteDTO setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
        return this;
    }

    public ClienteDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public ClienteDTO setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public ClienteDTO setCategoria_id(Long categoria_id) {
        this.categoria_id = categoria_id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Long getCategoria_id() {
        return categoria_id;
    }

    @Override
    public String toString() {
        return "ClienteDTO{" +
                "nome='" + nome + '\'' +
                ", vatNumber='" + vatNumber + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                ", categoria_id=" + categoria_id +
                '}';
    }
}
