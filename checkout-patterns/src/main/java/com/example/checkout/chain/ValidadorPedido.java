package com.example.checkout.chain;

import com.example.checkout.domain.Pedido;

/** PADRÃO: Chain of Responsibility - cada validador cuida de uma regra e repassa ao próximo. */
public abstract class ValidadorPedido {

    private ValidadorPedido proximo;

    public ValidadorPedido encadear(ValidadorPedido proximo) {
        this.proximo = proximo;
        return proximo;
    }

    public final void validar(Pedido pedido) {
        verificar(pedido);
        if (proximo != null) proximo.validar(pedido);
    }

    protected abstract void verificar(Pedido pedido);
}
