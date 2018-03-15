package br.com.caelum.ingresso.validacao;

import java.time.LocalTime;
import java.util.List;

import br.com.caelum.ingresso.model.Sessao;

public class GerenciadorDeSessao {
	private List<Sessao> sessoes;

	public GerenciadorDeSessao(List<Sessao> sessoes) {
		super();
		this.sessoes = sessoes;
	}

	public boolean cabe(Sessao nova) {
		return sessoes.stream().noneMatch(antiga -> conflita(nova, antiga));
	}

	private boolean conflita(Sessao nova, Sessao antiga) {
		LocalTime inicioNova = nova.getHorario();
		LocalTime fimNova = nova.getHorarioTermino();
		LocalTime inicioAntiga = antiga.getHorario();
		LocalTime fimAntiga = antiga.getHorarioTermino();

		if (inicioNova.equals(inicioAntiga)) {
			return true;
		}

		boolean ehAntes = inicioNova.isBefore(inicioAntiga);

		if (ehAntes) {
			return !fimNova.isBefore(inicioAntiga);
		} else {
			return !fimAntiga.isBefore(inicioNova);
		}
	}
}
