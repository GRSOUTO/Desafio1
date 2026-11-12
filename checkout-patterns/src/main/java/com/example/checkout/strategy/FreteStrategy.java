package com.example.checkout.strategy;

import com.example.checkout.domain.TipoFrete;
import java.math.BigDecimal;

/** PADRÃO: Strategy - cada tipo de frete é um algoritmo intercambiável. */
public interface FreteStrategy {
    TipoFrete tipo();
    BigDecimal calcular(BigDecimal distanciaKm);
}
