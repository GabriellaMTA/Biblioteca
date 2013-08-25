package br.com.aps.util;

import br.com.aps.entidade.Aluno;
import br.com.aps.entidade.Emprestimo;
import br.com.aps.entidade.Funcionario;
import br.com.aps.entidade.Periodico;
import br.com.aps.excecao.Excecao;

public class Validador {

	public static boolean validadorCPF(String cpf) {
		if (cpf.length() == 14) {
			return true;
		} else {
			throw new Excecao("CPF inv�lido");
		}
	}

	public static void validadorEmprestimo(Emprestimo emprestimo) {
		if (emprestimo == null) {
			throw new Excecao("Empr�stimo n�o pode ser null");
		}

		if ((emprestimo.getPessoa() == null)
				|| (emprestimo.getAcervo() == null)) {
			throw new Excecao("Empr�stimo n�o pode ser null");
		}

		if (emprestimo.getAcervo().getSituacao() != emprestimo.getAcervo()
				.getSituacao().DISPONIVEL) {
			throw new Excecao("Acervo emprestado");
		}
		if (emprestimo.getPessoa().getTipoPessoa() == emprestimo.getPessoa()
				.getTipoPessoa().ALUNO) {
			for (int i = 0; i <= emprestimo.getPessoa().getListaEmprestimo()
					.size(); i++) {
				if (emprestimo.getPessoa().getListaEmprestimo() instanceof Periodico)
					throw new Excecao("Aluno, nao pode pegar periodico");
			}
		}
		if ((emprestimo.getPessoa().getTipoPessoa() == emprestimo.getPessoa()
				.getTipoPessoa().ALUNO)
				&& (emprestimo.getPessoa().getListaEmprestimo().size() > Aluno.QUANTIDADE_EMPRESTIMO)) {
			throw new Excecao("Aluno n�o pode pegar mais de tr�s livros.");
		}

		if ((emprestimo.getPessoa().getTipoPessoa() == emprestimo.getPessoa()
				.getTipoPessoa().FUNCIONARIO)
				&& (emprestimo.getPessoa().getListaEmprestimo().size() > Funcionario.QUANTIDADE_ACERVO_EMPRESTIMO)) {
			throw new Excecao(
					"Funcion�rio n�o pode pegar mais de cinco livros.");
		}

	}
}
