package br.com.aps.controle;

import java.io.Serializable;
import java.util.List;

import br.com.aps.entidade.Funcionario;
import br.com.aps.excecao.Excecao;
import br.com.aps.util.Validador;

public class GerenteFuncionario implements Serializable{

	
	public void addFuncionario(Funcionario funcionario) {
		if((funcionario.getCpf()==null) || (funcionario.getSetor()==null)){
			throw new Excecao("Campos obrigat�rios n�o preenchidos");
		}
		if(Validador.validadorCPF(funcionario.getCpf())== false){
			throw new Excecao("CPF inv�lido");
		}
		isExisteFuncionario(funcionario.getCpf());
		
		GerentePersistencia.getInstance().getListaFuncionarios().add(funcionario);
		GerentePersistencia.persistir();
		
	}

	public Funcionario deleteFuncionario(Funcionario funcionario) {
		Funcionario funRemovido;
		for (Funcionario f : GerentePersistencia.getInstance().getListaFuncionarios()) {
			if (f.getCpf().equals(funcionario.getCpf())) {
				GerentePersistencia.getInstance().getListaFuncionarios().remove(f);
				GerentePersistencia.persistir();
				funRemovido = f;
				return funRemovido;
			}
		}
		throw new Excecao("Funcionario n�o existente");
	}


	public Funcionario retornarFuncionario(String cpfFuncionario) {
		for (Funcionario funcionario : GerentePersistencia.getInstance().getListaFuncionarios()) {
			if (funcionario.getCpf().equals(cpfFuncionario))
				return funcionario;
		}
		throw new Excecao("N�o existe funcionario com este cpf");
	}

	public Funcionario alterarDadosFuncionario(Funcionario funcionario) {
		for (Funcionario func : GerentePersistencia.getInstance().getListaFuncionarios()) {
			if (funcionario.getCpf().equals(func.getCpf())) {
				func = funcionario;
				GerentePersistencia.persistir();
				return func;
			}
		}
		throw new Excecao("N�o existe funcionairo com este cpf");
	}
	
	public List<Funcionario> getListFuncionario(){
		return GerentePersistencia.getInstance().getListaFuncionarios();
	}
	
	
	private void isExisteFuncionario(String cpfFuncionario){
		for(Funcionario funcionario: GerentePersistencia.getInstance().getListaFuncionarios()){
			if(funcionario.getCpf().equals(cpfFuncionario))
				throw new Excecao("Funcion�rio j� existente"); 
		}
	}
	

}
