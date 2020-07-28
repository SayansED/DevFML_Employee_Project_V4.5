package Controller;

import DAO.DAO;
import sistema.entidades.Funcionario;

public class ControllerFuncionario {
	
	DAO dao = new DAO();
	
	public boolean inserirUsuarioController(Funcionario pFuncionario){
		return this.dao.inserirFuncionario(pFuncionario);
	}
	
	public boolean editarFuncionarioController(Funcionario pFuncionario, String pSearchFun) {
		return this.dao.editarFuncionario(pFuncionario, pSearchFun);
	}
	
	public boolean buscaFuncionario(String pFuncionario) { // para verificação
		return this.dao.buscaFuncionario(pFuncionario);
	}
	
	public boolean deletarFuncionario(String pFuncionario) {
		return this.dao.deletarFuncionario(pFuncionario);
	}
	
	public boolean consultarDadosFuncionario(String pNameConsult) {
		return this.dao.consultarDadosFuncionario(pNameConsult);
	}
}
