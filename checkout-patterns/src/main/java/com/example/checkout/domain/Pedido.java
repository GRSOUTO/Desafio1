package com.example.checkout.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/** PADRÃO: Builder - construção fluente e imutável dos dados essenciais do pedido. */
public class Pedido {

    private Long id;
    private final String cliente;
    private final TipoCliente tipoCliente;
    private final List<Item> itens;
    private final TipoFrete tipoFrete;
    private final BigDecimal distanciaKm;
    private final FormaPagamento pagamento;
    private StatusPedido status = StatusPedido.CRIADO;
    private BigDecimal desconto = BigDecimal.ZERO;
    private BigDecimal frete = BigDecimal.ZERO;
    private String comprovante;

    private Pedido(Builder b) {
        this.cliente = b.cliente;
        this.tipoCliente = b.tipoCliente;
        this.itens = List.copyOf(b.itens);
        this.tipoFrete = b.tipoFrete;
        this.distanciaKm = b.distanciaKm;
        this.pagamento = b.pagamento;
    }

    public static Builder builder() { return new Builder(); }

    public BigDecimal subtotal() {
        return itens.stream().map(Item::subtotal).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal total() { return subtotal().subtract(desconto).add(frete); }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCliente() { return cliente; }
    public TipoCliente getTipoCliente() { return tipoCliente; }
    public List<Item> getItens() { return itens; }
    public TipoFrete getTipoFrete() { return tipoFrete; }
    public BigDecimal getDistanciaKm() { return distanciaKm; }
    public FormaPagamento getPagamento() { return pagamento; }
    public StatusPedido getStatus() { return status; }
    public void setStatus(StatusPedido status) { this.status = status; }
    public BigDecimal getDesconto() { return desconto; }
    public void setDesconto(BigDecimal desconto) { this.desconto = desconto; }
    public BigDecimal getFrete() { return frete; }
    public void setFrete(BigDecimal frete) { this.frete = frete; }
    public String getComprovante() { return comprovante; }
    public void setComprovante(String comprovante) { this.comprovante = comprovante; }

    public static class Builder {
        private String cliente;
        private TipoCliente tipoCliente = TipoCliente.REGULAR;
        private final List<Item> itens = new ArrayList<>();
        private TipoFrete tipoFrete = TipoFrete.PADRAO;
        private BigDecimal distanciaKm = BigDecimal.ZERO;
        private FormaPagamento pagamento = FormaPagamento.PIX;

        public Builder cliente(String v) { this.cliente = v; return this; }
        public Builder tipoCliente(TipoCliente v) { this.tipoCliente = v; return this; }
        public Builder item(Item v) { this.itens.add(v); return this; }
        public Builder tipoFrete(TipoFrete v) { this.tipoFrete = v; return this; }
        public Builder distanciaKm(BigDecimal v) { this.distanciaKm = v; return this; }
        public Builder pagamento(FormaPagamento v) { this.pagamento = v; return this; }
        public Pedido build() { return new Pedido(this); }
    }
}
