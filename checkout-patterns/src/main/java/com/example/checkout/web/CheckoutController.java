package com.example.checkout.web;

import com.example.checkout.facade.CheckoutFacade;
import com.example.checkout.infra.AuditoriaLog;
import com.example.checkout.infra.PedidoRepository;
import jakarta.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CheckoutController {

    private final CheckoutFacade facade;
    private final PedidoRepository repository;

    public CheckoutController(CheckoutFacade facade, PedidoRepository repository) {
        this.facade = facade;
        this.repository = repository;
    }

    @PostMapping("/checkout")
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoResponse checkout(@Valid @RequestBody CheckoutRequest request) {
        return PedidoResponse.de(facade.finalizar(request));
    }

    @GetMapping("/pedidos/{id}")
    public PedidoResponse buscar(@PathVariable Long id) {
        return repository.buscar(id).map(PedidoResponse::de)
                .orElseThrow(() -> new NoSuchElementException("Pedido " + id + " não encontrado."));
    }

    @GetMapping("/auditoria")
    public List<String> auditoria() { return AuditoriaLog.INSTANCE.listar(); }
}
