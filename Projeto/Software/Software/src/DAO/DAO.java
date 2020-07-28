package DAO;

import java.awt.FlowLayout;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import sistema.Navegador;
import sistema.entidades.Cargo;
import sistema.entidades.Funcionario;
import sistema.telas.CargosInserir;
import sistema.telas.CargosPesquisa;
import sistema.telas.FuncionariosInserir;
import sistema.telas.FuncionariosInserirCargo;
import sistema.telas.FuncionariosPesquisa;
import sqlite.Conexao;
import sqlite.CriarBancoDeDados;

public class DAO {

	Cargo cargo = new Cargo();
	Funcionario funcionario = new Funcionario();
	// conex�o
	Conexao conexao = new Conexao();
	CriarBancoDeDados criarBancoDeDados = new CriarBancoDeDados(conexao);
	// instrucao SQL
	PreparedStatement preparedStatement = null;
	// resultados
	ResultSet resultado = null;

	/**
	 * Salvar um novo Funcion�rio
	 * 
	 * @param pFuncionario
	 * @return
	 */
	public boolean inserirFuncionario(Funcionario pFuncionario) {
		funcionario = new Funcionario();
		conexao.conectar();
		String sqlInsertFuncionario = "INSERT INTO T_FUNCIONARIOS " + "(nome, " + "sobrenome, " + "dataNascimento, "
				+ "email, " + "cargo, " + "salario, " + "data_entrada_sistema) " + "VALUES(?,?,?,?,?,?,?);";
		try {
			preparedStatement = conexao.criarPreparedStatement(sqlInsertFuncionario, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, pFuncionario.getFuncionarioNome());
			preparedStatement.setString(2, pFuncionario.getFuncionarioSobrenome());
			preparedStatement.setString(3, pFuncionario.getFuncionarioDataNascimento());
			preparedStatement.setString(4, pFuncionario.getFuncionarioEmail());
			preparedStatement.setString(5, pFuncionario.getFuncionarioCargo());
			preparedStatement.setDouble(6, pFuncionario.getFuncionarioSalario());
			preparedStatement.setString(7, pFuncionario.getFuncionarioDataEntradaSistema());
			int resultados = preparedStatement.executeUpdate();
			if (resultados == 1) {
				String message = String.format("Funcion�rio: %s\nCadastrado com sucesso",
						pFuncionario.getFuncionarioNome());
				JOptionPane.showMessageDialog(null, message, "Cadastro", JOptionPane.INFORMATION_MESSAGE);
			} else
				JOptionPane.showMessageDialog(null, "Funcion�rio n�o inserido");

		} catch (SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao carregar.");
			Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
		Navegador.funcionariosCadastrar();;
		conexao.desconectar();
		return true;
	}
	
	public boolean editarFuncionario(Funcionario pFuncionario, String pSearchFun) {

		// conex�o
		Conexao conexao = new Conexao();
		// instrucao SQL
		PreparedStatement preparedStatement = null;
		// resultados
		int resultado;

		try {
			// conectando ao banco de dados
			// String busca = JOptionPane.showInputDialog("Digite o nome para a busca");
			String sqlUpdate = "UPDATE T_FUNCIONARIOS SET nome=?,sobrenome=?,dataNascimento=?,email=?,cargo=?,salario=? WHERE nome=?;";
			conexao.conectar();
			preparedStatement = conexao.criarPreparedStatement(sqlUpdate);
			
			preparedStatement.setString(1, pFuncionario.getFuncionarioNome());
			preparedStatement.setString(2, pFuncionario.getFuncionarioSobrenome());
			preparedStatement.setString(3, pFuncionario.getFuncionarioDataNascimento());
			preparedStatement.setString(4, pFuncionario.getFuncionarioEmail());
			preparedStatement.setString(5, pFuncionario.getFuncionarioCargo());
			preparedStatement.setDouble(6, pFuncionario.getFuncionarioSalario());
			preparedStatement.setString(7, pSearchFun);
			resultado = preparedStatement.executeUpdate();
			if (resultado == 0) {
				JOptionPane.showMessageDialog(null, "Funcion�rio n�o encontrado: " + pSearchFun, "Mensagem",
						JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
			JOptionPane.showMessageDialog(null, "Funcionario atualizado com sucesso!");
		} catch (SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao editar o Funcionario.");
			Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
		Navegador.funcionariosConsultar();;
		conexao.desconectar();
		return true;
	}
	
	public boolean buscaFuncionario (String pFuncionario) {
		Conexao conexao = new Conexao();
		PreparedStatement preparedStatement;
		ResultSet resultado = null;
		try {
			String sqlSelect = "SELECT * FROM T_FUNCIONARIOS WHERE nome = ?;";
			conexao.conectar();
			preparedStatement = conexao.criarPreparedStatement(sqlSelect);
			preparedStatement.setString(1, pFuncionario);
			resultado = preparedStatement.executeQuery();
			if (resultado.next() == false) {
				return false;
			}
			resultado.close();
			conexao.desconectar();
			return true;
		} catch (SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao consultar Funcion�rios");
			Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
	}
	
	public boolean deletarFuncionario(String pFuncionario) {
		String quest = "Deseja realmente excluir o Funcion�rio " + pFuncionario + "?";
		int confirmacao = JOptionPane.showConfirmDialog(null, quest, "Excluir", JOptionPane.YES_NO_OPTION);

		if (confirmacao == JOptionPane.YES_OPTION) {
			Conexao conexao = new Conexao();
			Statement statement;
			int resultado;
			
			try {
				conexao.conectar();
				statement = conexao.criarStatement();
				String sqlDelete = "DELETE FROM T_FUNCIONARIOS WHERE nome=?;";
				PreparedStatement preparedStatement = conexao.criarPreparedStatement(sqlDelete);
				preparedStatement.setString(1, pFuncionario);
				resultado = preparedStatement.executeUpdate();
				if (resultado == 1) {
					String message = String.format("Funcion�rio: %s\nDeletado com sucesso", pFuncionario);
					JOptionPane.showMessageDialog(null, message, "Excluir", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Funcion�rio n�o encontrado");
					Navegador.inicio();
				}
				conexao.desconectar();
			} catch (SQLException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Ocorreu um erro ao excluir Funcion�rio");
				Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
				return false;
			}
			return true;
		}
		return false;
	}
	
	public boolean consultarDadosFuncionario(String pNameConsult) {
		Conexao conexao = new Conexao();
		PreparedStatement preparedStatement;
		ResultSet resultado = null;

		try {
			String sqlSelect = "SELECT id, nome, sobrenome, dataNascimento, email, cargo, salario, data_entrada_sistema FROM T_FUNCIONARIOS WHERE nome = ?;";
			conexao.conectar();
			preparedStatement = conexao.criarPreparedStatement(sqlSelect);
			preparedStatement.setString(1, pNameConsult);
			resultado = preparedStatement.executeQuery();

			if (resultado.next()) {
				JOptionPane.showMessageDialog(null, "Consulta realizado com sucesso", "Consulta",
						JOptionPane.INFORMATION_MESSAGE);
				do {
					JOptionPane.showMessageDialog(null,
							"Funcion�rio" + "\n" + "Id: " + resultado.getString("id") 
									+ "\n" + "Nome: " + resultado.getString("nome") 
									+ "\n" + "Sobrenome: " + resultado.getString("sobrenome") 
									+ "\n" + "Data de Nascimento: " + resultado.getString("dataNascimento") 
									+ "\n" + "Email: " + resultado.getString("email") 
									+ "\n" + "Cargo: " + resultado.getString("cargo")
									+ "\n" + "Sal�rio: " + resultado.getString("salario")
									+ "\n" + "Data de entrada no sistema: " + resultado.getString("data_entrada_sistema"), 
									"Consulta de dados", JOptionPane.INFORMATION_MESSAGE);
				} while (resultado.next());
			} else {
				JOptionPane.showMessageDialog(null, "Funcion�rio n�o encontrado", "Mensagem",
						JOptionPane.INFORMATION_MESSAGE);
				Navegador.inicio();
			}
			resultado.close();
			conexao.desconectar();
			return true;
		} catch (SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao consultar funcion�rios");
			Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				resultado.close();
				conexao.desconectar();
			} catch (SQLException ef) {
				ef.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * Salvar um novo Cargo
	 * 
	 * @param pCargo
	 * @return
	 */
	public boolean inserirCargo(Cargo pCargo) {
		cargo = new Cargo();
		conexao.conectar();
		String sqlInsert = "INSERT INTO T_CARGOS " + "(nome, data_entrada_sistema) " + "VALUES(?,?);";
		try {
			preparedStatement = conexao.criarPreparedStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, pCargo.getCargoNome());
			preparedStatement.setString(2, pCargo.getCargoDataEntradaSistema());
			int resultados = preparedStatement.executeUpdate();
			if (resultados == 1) {
				String message = String.format("Cargo: %s\nCadastrado com sucesso", pCargo.getCargoNome());
				JOptionPane.showMessageDialog(null, message, "Cadastro", JOptionPane.INFORMATION_MESSAGE);
			} else
				JOptionPane.showMessageDialog(null, "Cargo n�o inserido");

		} catch (SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao adicionar", "ERRO", JOptionPane.ERROR_MESSAGE);
			Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
		Navegador.cargosCadastrar();;
		conexao.desconectar();
		return true;
	}
	
	public boolean deletarCargo(String pCargoSelecionado) {
		int confirmacao = JOptionPane.showConfirmDialog(null,
				"Deseja realmente " + "excluir o Cargo " + pCargoSelecionado + "?", "Excluir",
				JOptionPane.YES_NO_OPTION);
		if (confirmacao == JOptionPane.YES_OPTION) {
			// conex�o
			Conexao conexao = new Conexao();
			// instrucao SQL
			Statement statement = null;
			// resultados
			ResultSet resultados = null;
			try {
				// Conectando - Driver
				conexao.conectar();
				// Instru��o SQL
				statement = conexao.criarStatement();
				String sqlDelete = "DELETE FROM T_CARGOS WHERE nome=?;";
				PreparedStatement preparedStatement = conexao.criarPreparedStatement(sqlDelete);
				preparedStatement.setString(1, pCargoSelecionado);
				int resultado = preparedStatement.executeUpdate();
				if (resultado == 1) {
					String message = String.format("Cargo: %s\nDeletado com sucesso", pCargoSelecionado);
					JOptionPane.showMessageDialog(null, message, "Excluir", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Cargo n�o encontrado");
					return false;
				}
				conexao.desconectar();
			} catch (SQLException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Ocorreu um erro ao excluir o Cargo.");
				Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
				return false;
			}
		}
		return true;
	}
	
	public boolean atualizarCargo(String pCargoSelecionado) {
		// conex�o
		Conexao conexao = new Conexao();
		// instrucao SQL
		Statement statement;
		PreparedStatement preparedStatement;
		// resultados
		int resultado;

		try {
			conexao.conectar();
			String alterando = JOptionPane.showInputDialog("Digite o novo nome para ser inserido");
			String sqlUpdate = "UPDATE T_CARGOS SET nome = ? WHERE nome = ?;"; // Codigo de atualiza��o
			preparedStatement = conexao.criarPreparedStatement(sqlUpdate);
			preparedStatement.setString(1, alterando);
			preparedStatement.setString(2, pCargoSelecionado);
			resultado = preparedStatement.executeUpdate(); // Executando o UPDATE
			if (resultado == 0) {
				JOptionPane.showMessageDialog(null, "Cargo n�o encontrado: " + pCargoSelecionado, "Mensagem",
						JOptionPane.INFORMATION_MESSAGE);
				return false;
			}
			JOptionPane.showMessageDialog(null, "Cargo alterado com sucesso", "Mensagem",
					JOptionPane.INFORMATION_MESSAGE);
			conexao.desconectar();

		} catch (SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao atualizar o Cargo.");
			Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			conexao.desconectar();
		}
		return true;
	}
	
	public boolean consultarCargo(String pNameConsult) {
		// Conex�o
		Conexao conexao = new Conexao();
		// Instru��o SQL
		PreparedStatement preparedStatement;
		// Resultados
		ResultSet resultado = null;

		try {
			// Criando instru��o SQL
			String sqlSelect = "SELECT id, nome, data_entrada_sistema FROM T_CARGOS WHERE nome = ?;";
			// Conex�o - Driver
			conexao.conectar();
			preparedStatement = conexao.criarPreparedStatement(sqlSelect);
			preparedStatement.setString(1, pNameConsult);
			resultado = preparedStatement.executeQuery();

			if (resultado.next()) {
				// Next == true "Encontoru os dados"
				JOptionPane.showMessageDialog(null, "Consulta realizado com sucesso", "Consulta",
						JOptionPane.INFORMATION_MESSAGE);
				do {
					JOptionPane.showMessageDialog(null, "Cargo" 
							+ "\n" + "Id: " + resultado.getString("id") 
							+ "\n" + "Nome: " + resultado.getString("nome") + "\n" 
							+ "Data de entrada no sistema: " + resultado.getString("data_entrada_sistema"),
							"Consulta de dados", JOptionPane.INFORMATION_MESSAGE);
				} while (resultado.next());
			} else {
				// Next == false
				JOptionPane.showMessageDialog(null, "Cargo n�o encontrado", "Mensagem",
						JOptionPane.INFORMATION_MESSAGE);
				Navegador.inicio();
			}
			resultado.close();
			conexao.desconectar();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao consultar cargos");
			Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		} finally {
			try {
				resultado.close();
				conexao.desconectar();
			} catch (SQLException ez) {
				ez.printStackTrace();
			}
		}
		return true;
	}
	
	public ResultSet buscaCargos() {
		Conexao conexao = new Conexao();
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			conexao.conectar();
			String sqlSelect = "SELECT * FROM T_CARGOS;";
			statement = conexao.criarStatement();
			resultSet = statement.executeQuery(sqlSelect);
			return resultSet;
		} 
		catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao consultar cargos");
			Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return resultSet;
	}
}
