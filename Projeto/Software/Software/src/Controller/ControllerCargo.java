package Controller;

import java.sql.ResultSet;

import DAO.DAO;
import sistema.entidades.Cargo;

public class ControllerCargo {
	
	DAO dao = new DAO();
	
	public boolean inserirCargoController(Cargo pCargo){
		return this.dao.inserirCargo(pCargo);
	}
	
	public boolean deletarCargoController(String pCargoSelecionado) {
		return this.dao.deletarCargo(pCargoSelecionado);
	}
	
	public boolean atualizarCargo(String pCargoSelecionado) {
		return this.dao.atualizarCargo(pCargoSelecionado);
	}
	
	public boolean consultarCargo(String pNameConsult) {
		return this.dao.consultarCargo(pNameConsult);
	}
	
	public ResultSet buscaCargos () {
		return this.dao.buscaCargos();
	}
}
