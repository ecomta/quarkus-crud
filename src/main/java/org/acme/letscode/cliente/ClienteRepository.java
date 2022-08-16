package org.acme.letscode.cliente;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


/**
 * Lembrando:
 * @ApplicationScoped (mais alto): ciclo de vida da aplicação, se durar 3 horas: essa instância vai existir.
 * @SessionScoped (médio): ideia de sessão de usuário, dura enquanto o usuário estiver online.
 * @RequestScoped (baixo): faz o que tem que fazer e é destruído.
 */
@RequestScoped
public class ClienteRepository implements PanacheRepositoryBase<Cliente, UUID> {

    public List<Cliente> getAllClientes() {
        return listAll();
    }

    public Cliente findClienteById(UUID uuid) {
        Optional<Cliente> cliente = findByIdOptional(uuid);
        return cliente.orElseThrow(() -> new WebApplicationException("Cliente não existe", Response.Status.NOT_FOUND));
    }

}
