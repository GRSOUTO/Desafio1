package com.example.checkout.web;

import com.example.checkout.domain.FormaPagamento;
import com.example.checkout.domain.Pedido;
import com.example.checkout.domain.StatusPedido;
import java.math.BigDecimal;

public record PedidoResponse(Long id, String cliente, StatusPedido status, BigDecimal subtotal,
                             BigDecimal desconto, BigDecimal frete, BigDecimal total,
                             FormaPagamento pagamento, String comprovante) {

    public static PedidoResponse de(Pedido p) {
        return new PedidoResponse(p.getId(), p.getCliente(), p.getStatus(), p.subtotal(),
                p.getDesconto(), p.getFrete(), p.total(), p.getPagamento(), p.getComprovante());
    }
}
