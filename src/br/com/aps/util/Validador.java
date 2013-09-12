package br.com.aps.util;

import java.util.List;

import br.com.aps.controle.GerentePersistencia;
import br.com.aps.entidade.Aluno;
import br.com.aps.entidade.Emprestimo;
import br.com.aps.entidade.Funcionario;
import br.com.aps.entidade.Periodico;
import br.com.aps.entidade.Situacao;
import br.com.aps.entidade.TipoAcervo;
import br.com.aps.entidade.TipoPessoa;
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
		if ((emprestimo.getPessoa() == null)
				|| (emprestimo.getAcervo() == null)) {
			throw new Excecao("Empr�stimo n�o pode ser null");
		}

		emprestimo.getAcervo().getSituacao();
		if (emprestimo.getAcervo().getSituacao() != Situacao.DISPONIVEL) {
			throw new Excecao("Acervo emprestado");
		}
		
		for (Emprestimo ep : GerentePersistencia.getInstance().getListaEmprestimos()) {
			if (ep.getPessoa().getTipoPessoa() == TipoPessoa.ALUNO) {
				if (ep.getPessoa().getListaEmprestimo().size() > Aluno.QUANTIDADE_EMPRESTIMO) 
					throw new Excecao("Aluno n�o pode pegar mais de dois livros.");
			
				if(ep.getPessoa().getListaEmprestimo() instanceof Periodico)
					throw new Excecao("Aluno, nao pode pegar periodico");
			}
					
			if ((emprestimo.getPessoa().getTipoPessoa() == TipoPessoa.FUNCIONARIO)
				&& (emprestimo.getPessoa().getListaEmprestimo().size() > Funcionario.QUANTIDADE_ACERVO_EMPRESTIMO)) {
				throw new Excecao("Funcion�rio n�o pode pegar mais de cinco livros.");
			}
		}
	}
}
	
