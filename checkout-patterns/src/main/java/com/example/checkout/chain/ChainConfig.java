package com.example.checkout.chain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChainConfig {

    /** Monta a corrente e devolve o primeiro elo. */
    @Bean
    public ValidadorPedido cadeiaDeValidacao() {
        ValidadorPedido inicio = new ValidaQuantidade();
        inicio.encadear(new ValidaValorMinimo())
              .encadear(new ValidaLimiteBoleto());
        return inicio;
    }
}
