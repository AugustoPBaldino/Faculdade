package com.example.API.DTO;

import java.util.Date;

public record AvaliacaoDTO(Long idAvaliacao, String tipoAvaliacao, Date data,int notaAvaliacao,Date dataEntrega) {
}
