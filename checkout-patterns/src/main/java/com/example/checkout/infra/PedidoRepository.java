package com.example.checkout.infra;

import com.example.checkout.domain.Pedido;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

/** PADRÃO: Repository (em memória). Os beans do Spring também são Singletons por padrão. */
@Repository
public class PedidoRepository {
    private final Map<Long, Pedido> banco = new ConcurrentHashMap<>();
    private final AtomicLong sequencia = new AtomicLong();

    public Pedido salvar(Pedido p) {
        p.setId(sequencia.incrementAndGet());
        banco.put(p.getId(), p);
        return p;
    }

    public Optional<Pedido> buscar(Long id) { return Optional.ofNullable(banco.get(id)); }
}
