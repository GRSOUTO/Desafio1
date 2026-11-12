package com.example.checkout.strategy;

import com.example.checkout.domain.FormaPagamento;
import com.example.checkout.domain.TipoCliente;
import com.example.checkout.domain.TipoFrete;
import com.example.checkout.payment.ProcessadorPagamento;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

/**
 * PADRÃO: Factory (registro de fábricas) - o Spring injeta todas as implementações
 * e a fábrica devolve a certa. Adicionar um novo tipo = criar um @Component novo (Open/Closed).
 */
@Component
public class EstrategiaFactory {

    private final Map<TipoFrete, FreteStrategy> fretes;
    private final Map<TipoCliente, DescontoStrategy> descontos;
    private final Map<FormaPagamento, ProcessadorPagamento> pagamentos;

    public EstrategiaFactory(List<FreteStrategy> fretes,
                             List<DescontoStrategy> descontos,
                             List<ProcessadorPagamento> pagamentos) {
        this.fretes = fretes.stream().collect(Collectors.toMap(FreteStrategy::tipo, Function.identity()));
        this.descontos = descontos.stream().collect(Collectors.toMap(DescontoStrategy::tipo, Function.identity()));
        this.pagamentos = pagamentos.stream().collect(Collectors.toMap(ProcessadorPagamento::forma, Function.identity()));
    }

    public FreteStrategy frete(TipoFrete t) { return fretes.get(t); }
    public DescontoStrategy desconto(TipoCliente t) { return descontos.get(t); }
    public ProcessadorPagamento pagamento(FormaPagamento f) { return pagamentos.get(f); }
}
