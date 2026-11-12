package com.example.checkout.strategy;

import com.example.checkout.domain.TipoCliente;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.stereotype.Component;

@Component
public class DescontoVip implements DescontoStrategy {
    public TipoCliente tipo() { return TipoCliente.VIP; }
    public BigDecimal calcular(BigDecimal subtotal) {
        return subtotal.multiply(new BigDecimal("0.10")).setScale(2, RoundingMode.HALF_UP);
    }
}
