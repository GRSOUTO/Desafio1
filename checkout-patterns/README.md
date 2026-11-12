# Checkout Patterns — Padrões de Projeto na prática (GoF + Spring)

API de checkout de e-commerce em **Java 17 + Spring Boot 3** que aplica padrões clássicos do GoF
junto com as abstrações do Spring.

## Padrões aplicados

| Padrão | Onde | Papel no sistema |
|---|---|---|
| **Strategy** | `strategy/Frete*`, `strategy/Desconto*` | Cálculo de frete e desconto intercambiáveis |
| **Factory** | `strategy/EstrategiaFactory` | Escolhe a estratégia/processador certo (Spring injeta a lista) |
| **Chain of Responsibility** | `chain/*` | Validações encadeadas (quantidade → valor mínimo → limite do boleto) |
| **Template Method** | `payment/ProcessadorPagamento` | Fluxo fixo de pagamento; Pix, Cartão e Boleto preenchem o passo variável |
| **Observer** | `event/*` | `ApplicationEvent` dispara e-mail, baixa de estoque e fidelidade |
| **Facade** | `facade/CheckoutFacade` | Uma única entrada orquestrando todo o subsistema |
| **Builder** | `domain/Pedido.Builder` | Construção fluente do pedido |
| **Singleton** | `infra/AuditoriaLog` (enum) + beans Spring | Log de auditoria único; beans singleton por padrão |
| **Repository** | `infra/PedidoRepository` | Persistência em memória |

## Executar

```bash
mvn spring-boot:run
mvn test
```

```bash
curl -X POST localhost:8080/api/checkout -H "Content-Type: application/json" -d '{
  "cliente": "Ana",
  "tipoCliente": "VIP",
  "itens": [{"nome": "Teclado", "preco": 100.00, "quantidade": 2}],
  "frete": "EXPRESSO",
  "distanciaKm": 10,
  "pagamento": "PIX"
}'

curl localhost:8080/api/pedidos/1
curl localhost:8080/api/auditoria
```

## Como estender (Open/Closed)

- Novo frete: crie um `@Component` que implemente `FreteStrategy` e acrescente o valor ao enum `TipoFrete`.
- Nova validação: crie uma subclasse de `ValidadorPedido` e encadeie em `ChainConfig`.
- Novo pagamento: estenda `ProcessadorPagamento` como `@Component`.
- Nova reação ao pedido: adicione um método `@EventListener` em `PedidoListeners`.

## Ideias de evolução

Decorator para adicionais (embalagem de presente), Spring Data JPA + H2, cupons como Strategy, Swagger/OpenAPI.
