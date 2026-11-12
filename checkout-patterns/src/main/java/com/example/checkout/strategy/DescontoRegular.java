package com.example.checkout.strategy;

import com.example.checkout.domain.TipoCliente;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.stereotype.Component;

@Component
public class DescontoRegular implements DescontoStrategy {
    public TipoCliente tipo() { return TipoCliente.REGULAR; }
    public BigDecimal calcular(BigDecimal subtotal) {
        return subtotal.multiply(new BigDecimal("0")).setScale(2, RoundingMode.HALF_UP);
    }
}
