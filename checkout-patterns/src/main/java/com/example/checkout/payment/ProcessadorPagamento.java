package com.example.checkout.payment;

import com.example.checkout.domain.FormaPagamento;
import com.example.checkout.domain.Pedido;
import com.example.checkout.infra.AuditoriaLog;

/** PADRÃO: Template Method - o esqueleto é fixo; cada forma de pagamento preenche os passos. */
public abstract class ProcessadorPagamento {

    public abstract FormaPagamento forma();

    public final String processar(Pedido pedido) {
        preparar(pedido);                       // hook opcional
        String comprovante = autorizar(pedido); // passo obrigatório
        AuditoriaLog.INSTANCE.registrar(forma() + " autorizado: " + comprovante);
        return comprovante;
    }

    protected void preparar(Pedido pedido) { }

    protected abstract String autorizar(Pedido pedido);
}
