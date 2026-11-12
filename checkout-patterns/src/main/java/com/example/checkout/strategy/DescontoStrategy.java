package com.example.checkout.strategy;

import com.example.checkout.domain.TipoCliente;
import java.math.BigDecimal;

/** PADRÃO: Strategy - política de desconto por perfil de cliente. */
public interface DescontoStrategy {
    TipoCliente tipo();
    BigDecimal calcular(BigDecimal subtotal);
}
