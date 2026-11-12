package com.example.checkout.chain;

import com.example.checkout.domain.Pedido;
import java.math.BigDecimal;

public class ValidaValorMinimo extends ValidadorPedido {
    @Override
    protected void verificar(Pedido p) {
        if (p.subtotal().compareTo(new BigDecimal("10")) < 0)
            throw new RegraDeNegocioException("Valor mínimo do pedido: R$ 10,00.");
    }
}
