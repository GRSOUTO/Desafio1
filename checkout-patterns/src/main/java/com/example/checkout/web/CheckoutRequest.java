package com.example.checkout.web;

import com.example.checkout.domain.FormaPagamento;
import com.example.checkout.domain.TipoCliente;
import com.example.checkout.domain.TipoFrete;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.List;

public record CheckoutRequest(
        @NotBlank String cliente,
        @NotNull TipoCliente tipoCliente,
        @NotEmpty @Valid List<ItemRequest> itens,
        @NotNull TipoFrete frete,
        @NotNull @PositiveOrZero BigDecimal distanciaKm,
        @NotNull FormaPagamento pagamento) {

    public record ItemRequest(
            @NotBlank String nome,
            @NotNull @Positive BigDecimal preco,
            @Positive int quantidade) { }
}
