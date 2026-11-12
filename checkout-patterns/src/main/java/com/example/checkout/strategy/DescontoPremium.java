package com.example.checkout.strategy;

import com.example.checkout.domain.TipoCliente;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.stereotype.Component;

@Component
public class DescontoPremium implements DescontoStrategy {
    public TipoCliente tipo() { return TipoCliente.PREMIUM; }
    public BigDecimal calcular(BigDecimal subtotal) {
        return subtotal.multiply(new BigDecimal("0.15")).setScale(2, RoundingMode.HALF_UP);
    }
}
