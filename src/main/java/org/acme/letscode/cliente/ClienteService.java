package org.acme.letscode.cliente;

import org.acme.letscode.categoria.Categoria;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.*;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

@RequestScoped
public class ClienteService {

    @Inject
    ClienteRepository clienteRepository;

    @Inject
    Validator validator;

    public Cliente getCliente(UUID id) {
        return clienteRepository.findClienteById(id);
    }

    /**
     * Vou receber os dados JSON, vou criar outro Objeto e vou fazer as modificações necessárias.
     * Se eu quissese outras validações (que não fossem possíveis lá na classe Models), eu faria na classe Service.
     * Banco possui classes especiais (@Trace, @Metrics e essas coisas): se vier dado invalido, eu usaria essas classes aqui.
     */
    @Transactional(rollbackOn = Exception.class)
    private Response addCliente(@Valid final Cliente cliente) {
            new ClienteRepository().persist(cliente);
            return Response.status(Response.Status.OK).entity("Cadastrado com sucesso").build();
    }

    /**
     * ****************************** Eu preciso de SETTERS e GETTERS no DTO.
     * Tava dando erro por o JSONB não conseguir atribuir dentro da instância da classe.
     */
    @Transactional(rollbackOn = Exception.class)
    public Response validateClienteBeforeInsertIt(@Valid final ClienteDTO clienteDTO) {
        Optional<Categoria> categoria = Categoria.findByIdOptional(clienteDTO.getCategoria_id());
        AtomicReference<Cliente> insertCliente = new AtomicReference<>();

        categoria.ifPresentOrElse(
                (categoriaEncontrada) -> {
                    Cliente cliente = Cliente.of(
                            clienteDTO.getNome().toUpperCase(),
                            clienteDTO.getVatNumber().toUpperCase(),
                            clienteDTO.getEmail().toUpperCase(),
                            clienteDTO.getBirthDate(),
                            categoriaEncontrada
                    );

                    validator.validate(cliente);
                    insertCliente.set(cliente);
                },
                () -> { throw new WebApplicationException(Response.Status.NOT_FOUND); }
        );

        return this.addCliente(insertCliente.get());
    }

    /*
     * Tinha um @PostConstruct, mas substitui pelo arquivo resources/import.sql
     * @PostConstruct só vai executar quando essa classe for instanciada.
     * import.sql (já reconhecido pelo Quarkus sem eu fazer nada) já insere os elementos antes de tudo começar.
     */


}
