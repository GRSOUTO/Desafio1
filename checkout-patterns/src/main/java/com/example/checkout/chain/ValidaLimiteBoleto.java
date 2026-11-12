package com.example.checkout.chain;

import com.example.checkout.domain.FormaPagamento;
import com.example.checkout.domain.Pedido;
import java.math.BigDecimal;

public class ValidaLimiteBoleto extends ValidadorPedido {
    @Override
    protected void verificar(Pedido p) {
        if (p.getPagamento() == FormaPagamento.BOLETO && p.subtotal().compareTo(new BigDecimal("5000")) > 0)
            throw new RegraDeNegocioException("Boleto aceita pedidos de até R$ 5.000,00.");
    }
}
