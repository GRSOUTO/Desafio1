package com.example.checkout.strategy;

import com.example.checkout.domain.TipoFrete;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.stereotype.Component;

@Component
public class FretePadrao implements FreteStrategy {
    public TipoFrete tipo() { return TipoFrete.PADRAO; }
    public BigDecimal calcular(BigDecimal km) {
        return new BigDecimal("10").add(km.multiply(new BigDecimal("0.50"))).setScale(2, RoundingMode.HALF_UP);
    }
}
