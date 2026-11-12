package com.example.checkout.facade;

import com.example.checkout.chain.ValidadorPedido;
import com.example.checkout.domain.Item;
import com.example.checkout.domain.Pedido;
import com.example.checkout.domain.StatusPedido;
import com.example.checkout.event.PedidoConfirmadoEvent;
import com.example.checkout.infra.PedidoRepository;
import com.example.checkout.strategy.EstrategiaFactory;
import com.example.checkout.web.CheckoutRequest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/** PADRÃO: Facade - uma porta de entrada simples que orquestra todos os subsistemas. */
@Service
public class CheckoutFacade {

    private final ValidadorPedido cadeia;
    private final EstrategiaFactory factory;
    private final PedidoRepository repository;
    private final ApplicationEventPublisher publisher;

    public CheckoutFacade(ValidadorPedido cadeia, EstrategiaFactory factory,
                          PedidoRepository repository, ApplicationEventPublisher publisher) {
        this.cadeia = cadeia;
        this.factory = factory;
        this.repository = repository;
        this.publisher = publisher;
    }

    public Pedido finalizar(CheckoutRequest req) {
        var builder = Pedido.builder()
                .cliente(req.cliente())
                .tipoCliente(req.tipoCliente())
                .tipoFrete(req.frete())
                .distanciaKm(req.distanciaKm())
                .pagamento(req.pagamento());
        req.itens().forEach(i -> builder.item(new Item(i.nome(), i.preco(), i.quantidade())));
        Pedido pedido = builder.build();

        cadeia.validar(pedido);                                                                    // Chain
        pedido.setDesconto(factory.desconto(pedido.getTipoCliente()).calcular(pedido.subtotal())); // Strategy
        pedido.setFrete(factory.frete(pedido.getTipoFrete()).calcular(pedido.getDistanciaKm()));   // Strategy
        pedido.setComprovante(factory.pagamento(pedido.getPagamento()).processar(pedido));         // Template Method
        pedido.setStatus(StatusPedido.PAGO);

        repository.salvar(pedido);
        publisher.publishEvent(new PedidoConfirmadoEvent(pedido));                                 // Observer
        return pedido;
    }
}
