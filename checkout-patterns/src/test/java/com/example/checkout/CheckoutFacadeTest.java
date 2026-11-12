package com.example.checkout;

import static org.junit.jupiter.api.Assertions.*;

import com.example.checkout.chain.RegraDeNegocioException;
import com.example.checkout.domain.*;
import com.example.checkout.facade.CheckoutFacade;
import com.example.checkout.infra.AuditoriaLog;
import com.example.checkout.web.CheckoutRequest;
import com.example.checkout.web.CheckoutRequest.ItemRequest;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CheckoutFacadeTest {

    @Autowired CheckoutFacade facade;

    @Test
    void clienteVipComFreteExpressoEPix() {
        var req = new CheckoutRequest("Ana", TipoCliente.VIP,
                List.of(new ItemRequest("Teclado", new BigDecimal("100"), 2)),
                TipoFrete.EXPRESSO, new BigDecimal("10"), FormaPagamento.PIX);

        Pedido p = facade.finalizar(req);

        assertEquals(0, new BigDecimal("20").compareTo(p.getDesconto()));  // 10% de 200
        assertEquals(0, new BigDecimal("37").compareTo(p.getFrete()));     // 25 + 1,20 x 10km
        assertEquals(0, new BigDecimal("217").compareTo(p.total()));
        assertEquals(StatusPedido.PAGO, p.getStatus());
        assertTrue(p.getComprovante().startsWith("PIX-"));
        assertTrue(AuditoriaLog.INSTANCE.listar().stream().anyMatch(l -> l.contains("Fidelidade")));
    }

    @Test
    void boletoAcimaDoLimiteERejeitadoPelaCadeia() {
        var req = new CheckoutRequest("Bia", TipoCliente.REGULAR,
                List.of(new ItemRequest("Notebook", new BigDecimal("6000"), 1)),
                TipoFrete.RETIRADA, BigDecimal.ZERO, FormaPagamento.BOLETO);

        assertThrows(RegraDeNegocioException.class, () -> facade.finalizar(req));
    }
}
