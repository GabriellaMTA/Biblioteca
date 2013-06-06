package br.com.aps.controle;

import java.util.ArrayList;
import java.util.List;

import br.com.aps.entidade.Professor;
import br.com.aps.excecao.Excecao;

public class GerenteProfessor {

	List<Professor> listaProfessor = new ArrayList<Professor>();

	public void addProfessor(Professor professor) {
		isExisteProfessor(professor.getCpf());
		listaProfessor.add(professor);
	}

	public void deleteProfessor(Professor professor) {
		retornarProfessor(professor.getCpf());
		listaProfessor.remove(professor);
	}

	public Professor retornarProfessor(String cpfProfessor) {
		for (Professor professor : listaProfessor) {
			if (professor.getCpf().equals(cpfProfessor))
				;
			return professor;
		}
		throw new Excecao("N�o existe professor com este cpf");
	}

	public Professor alterarDadosProfessor(Professor professor) {
		for (Professor prof : listaProfessor) {
			if (professor.getCpf().equals(prof.getCpf())) {
				prof = professor;
				listaProfessor.add(prof);
				return prof;
			}
		}
		throw new Excecao("N�o existe professor com este cpf");
	}
	
	public List<Professor> getListProfessor() {
		return listaProfessor;
	}
	

	private void isExisteProfessor(String cpfProfessor){
		for(Professor professor: listaProfessor){
			if(professor.getCpf().equals(cpfProfessor))
				throw new Excecao("Professor n�o existente"); 
		}
	}
	
}
