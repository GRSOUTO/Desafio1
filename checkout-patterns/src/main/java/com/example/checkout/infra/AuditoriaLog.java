package com.example.checkout.infra;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** PADRÃO: Singleton (clássico, via enum - thread-safe e à prova de serialização). */
public enum AuditoriaLog {
    INSTANCE;

    private final List<String> eventos = Collections.synchronizedList(new ArrayList<>());

    public void registrar(String mensagem) {
        eventos.add(LocalDateTime.now() + " | " + mensagem);
    }

    public List<String> listar() { return List.copyOf(eventos); }
}
