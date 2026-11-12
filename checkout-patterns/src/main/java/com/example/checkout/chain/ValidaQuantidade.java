package com.example.checkout.chain;

import com.example.checkout.domain.Pedido;

public class ValidaQuantidade extends ValidadorPedido {
    @Override
    protected void verificar(Pedido p) {
        boolean excedeu = p.getItens().stream().anyMatch(i -> i.quantidade() > 100);
        if (excedeu) throw new RegraDeNegocioException("Quantidade máxima por item: 100 unidades.");
    }
}
