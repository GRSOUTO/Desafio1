package com.example.checkout.payment;

import com.example.checkout.domain.FormaPagamento;
import com.example.checkout.domain.Pedido;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class PagamentoCartao extends ProcessadorPagamento {
    public FormaPagamento forma() { return FormaPagamento.CARTAO; }
    @Override
    protected String autorizar(Pedido p) {
        return "AUT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
