package com.example.checkout.payment;

import com.example.checkout.domain.FormaPagamento;
import com.example.checkout.domain.Pedido;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class PagamentoPix extends ProcessadorPagamento {
    public FormaPagamento forma() { return FormaPagamento.PIX; }
    @Override
    protected String autorizar(Pedido p) {
        return "PIX-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
