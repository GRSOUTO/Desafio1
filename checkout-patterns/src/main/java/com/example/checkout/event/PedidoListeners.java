package com.example.checkout.event;

import com.example.checkout.infra.AuditoriaLog;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/** PADRÃO: Observer - o Spring ApplicationEvent desacopla quem publica de quem reage. */
@Component
public class PedidoListeners {

    @EventListener
    public void enviarEmail(PedidoConfirmadoEvent e) {
        AuditoriaLog.INSTANCE.registrar("E-mail de confirmação enviado para " + e.pedido().getCliente());
    }

    @EventListener
    public void baixarEstoque(PedidoConfirmadoEvent e) {
        e.pedido().getItens().forEach(i ->
            AuditoriaLog.INSTANCE.registrar("Estoque: -" + i.quantidade() + " x " + i.nome()));
    }

    @EventListener
    public void pontuarFidelidade(PedidoConfirmadoEvent e) {
        BigDecimal pontos = e.pedido().total().divide(BigDecimal.TEN, 0, RoundingMode.DOWN);
        AuditoriaLog.INSTANCE.registrar("Fidelidade: +" + pontos + " pontos para " + e.pedido().getCliente());
    }
}
