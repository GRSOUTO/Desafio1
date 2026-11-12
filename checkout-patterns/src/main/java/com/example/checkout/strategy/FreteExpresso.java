package com.example.checkout.strategy;

import com.example.checkout.domain.TipoFrete;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.stereotype.Component;

@Component
public class FreteExpresso implements FreteStrategy {
    public TipoFrete tipo() { return TipoFrete.EXPRESSO; }
    public BigDecimal calcular(BigDecimal km) {
        return new BigDecimal("25").add(km.multiply(new BigDecimal("1.20"))).setScale(2, RoundingMode.HALF_UP);
    }
}
