package com.example.checkout.strategy;

import com.example.checkout.domain.TipoFrete;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.stereotype.Component;

@Component
public class FreteRetirada implements FreteStrategy {
    public TipoFrete tipo() { return TipoFrete.RETIRADA; }
    public BigDecimal calcular(BigDecimal km) {
        return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
    }
}
