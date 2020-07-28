package sistema.telas;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import sistema.Navegador;
import sqlite.Conexao;

public class RelatoriosCargosConstroiGrafico {
	
	private int anoAtual;
	
	public RelatoriosCargosConstroiGrafico (int pAnoAtual) {
		this.anoAtual = pAnoAtual;
	}
	
	// Janeiro
	public static int constroiGraficoMesJaneiro(int pAnoAtual) {
		int anoJaneiro=0;
		
		// Conexão
		Conexao conexao = new Conexao();
		// Instrução SQL
		Statement instrucaoSQL;
		// Resultados
		ResultSet resultados;
		
		try {
			// Conexao
			conexao.conectar();
			instrucaoSQL = conexao.criarStatement();
			String query = "SELECT data_entrada_sistema, COUNT(data_entrada_sistema) as quantidade "
					+ "FROM T_CARGOS WHERE data_entrada_sistema LIKE '%_/1/%"+pAnoAtual+"';";
			resultados = instrucaoSQL.executeQuery(query);
			while (resultados.next()) {
				anoJaneiro = resultados.getInt("quantidade");
			}
			return anoJaneiro;
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro criar o relatório.\n\n" + ex.getMessage());
		}
		return anoJaneiro;
	}
	
	// Fevereiro
	public static int constroiGraficoMesFevereiro(int pAnoAtual) {
		int anoFevereiro=0;
		
		// Conexão
		Conexao conexao = new Conexao();
		// Instrução SQL
		Statement instrucaoSQL;
		// Resultados
		ResultSet resultados;
		
		try {
			// Conexao
			conexao.conectar();
			instrucaoSQL = conexao.criarStatement();
			String query = "SELECT data_entrada_sistema, COUNT(data_entrada_sistema) as quantidade "
					+ "FROM T_CARGOS WHERE data_entrada_sistema LIKE '%_/2%"+pAnoAtual+"';";
			resultados = instrucaoSQL.executeQuery(query);
			while (resultados.next()) {
				anoFevereiro = resultados.getInt("quantidade");
			}
			return anoFevereiro;
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro criar o relatório.\n\n" + ex.getMessage());
		}
		return anoFevereiro;
	}
	
	// Março
	public static int constroiGraficoMesMarco(int pAnoAtual) {
		int anoMarco=0;
		
		// Conexão
		Conexao conexao = new Conexao();
		// Instrução SQL
		Statement instrucaoSQL;
		// Resultados
		ResultSet resultados;
		
		try {
			// Conexao
			conexao.conectar();
			instrucaoSQL = conexao.criarStatement();
			String query = "SELECT data_entrada_sistema, COUNT(data_entrada_sistema) as quantidade "
					+ "FROM T_CARGOS WHERE data_entrada_sistema LIKE '%_/3%"+pAnoAtual+"';";
			resultados = instrucaoSQL.executeQuery(query);
			while (resultados.next()) {
				anoMarco = resultados.getInt("quantidade");
			}
			return anoMarco;
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro criar o relatório.\n\n" + ex.getMessage());
		}
		return anoMarco;
	}
	
	// Abril
	public static int constroiGraficoMesAbril(int pAnoAtual) {
		int anoAbril=0;
		
		// Conexão
		Conexao conexao = new Conexao();
		// Instrução SQL
		Statement instrucaoSQL;
		// Resultados
		ResultSet resultados;
		
		try {
			// Conexao
			conexao.conectar();
			instrucaoSQL = conexao.criarStatement();
			String query = "SELECT data_entrada_sistema, COUNT(data_entrada_sistema) as quantidade "
					+ "FROM T_CARGOS WHERE data_entrada_sistema LIKE '%_/4%"+pAnoAtual+"';";
			resultados = instrucaoSQL.executeQuery(query);
			while (resultados.next()) {
				anoAbril = resultados.getInt("quantidade");
			}
			return anoAbril;
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro criar o relatório.\n\n" + ex.getMessage());
		}
		return anoAbril;
	}
	
	// Maio
	public static int constroiGraficoMesMaio(int pAnoAtual) {
		int anoMaio=0;
		
		// Conexão
		Conexao conexao = new Conexao();
		// Instrução SQL
		Statement instrucaoSQL;
		// Resultados
		ResultSet resultados;
		
		try {
			// Conexao
			conexao.conectar();
			instrucaoSQL = conexao.criarStatement();
			String query = "SELECT data_entrada_sistema, COUNT(data_entrada_sistema) as quantidade "
					+ "FROM T_CARGOS WHERE data_entrada_sistema LIKE '%_/5%"+pAnoAtual+"';";
			resultados = instrucaoSQL.executeQuery(query);
			while (resultados.next()) {
				anoMaio = resultados.getInt("quantidade");
			}
			return anoMaio;
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro criar o relatório.\n\n" + ex.getMessage());
		}
		return anoMaio;
	}
	
	// Junho
	public static int constroiGraficoMesJunho(int pAnoAtual) {
		int anoJunho=0;
		
		// Conexão
		Conexao conexao = new Conexao();
		// Instrução SQL
		Statement instrucaoSQL;
		// Resultados
		ResultSet resultados;
		
		try {
			// Conexao
			conexao.conectar();
			instrucaoSQL = conexao.criarStatement();
			String query = "SELECT data_entrada_sistema, COUNT(data_entrada_sistema) as quantidade "
					+ "FROM T_CARGOS WHERE data_entrada_sistema LIKE '%_/6%"+pAnoAtual+"';";
			resultados = instrucaoSQL.executeQuery(query);
			while (resultados.next()) {
				anoJunho = resultados.getInt("quantidade");
			}
			return anoJunho;
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro criar o relatório.\n\n" + ex.getMessage());
		}
		return anoJunho;
	}
	
	// Julho
	public static int constroiGraficoMesJulho(int pAnoAtual) {
		int anoJulho=0;
		
		// Conexão
		Conexao conexao = new Conexao();
		// Instrução SQL
		Statement instrucaoSQL;
		// Resultados
		ResultSet resultados;
		
		try {
			// Conexao
			conexao.conectar();
			instrucaoSQL = conexao.criarStatement();
			String query = "SELECT data_entrada_sistema, COUNT(data_entrada_sistema) as quantidade "
					+ "FROM T_CARGOS WHERE data_entrada_sistema LIKE '%_/7%"+pAnoAtual+"';";
			resultados = instrucaoSQL.executeQuery(query);
			while (resultados.next()) {
				anoJulho = resultados.getInt("quantidade");
			}
			return anoJulho;
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro criar o relatório.\n\n" + ex.getMessage());
		}
		return anoJulho;
	}
	
	// Agosto
	public static int constroiGraficoMesAgosto(int pAnoAtual) {
		int anoAgosto=0;
		
		// Conexão
		Conexao conexao = new Conexao();
		// Instrução SQL
		Statement instrucaoSQL;
		// Resultados
		ResultSet resultados;
		
		try {
			// Conexao
			conexao.conectar();
			instrucaoSQL = conexao.criarStatement();
			String query = "SELECT data_entrada_sistema, COUNT(data_entrada_sistema) as quantidade "
					+ "FROM T_CARGOS WHERE data_entrada_sistema LIKE '%_/8%"+pAnoAtual+"';";
			resultados = instrucaoSQL.executeQuery(query);
			while (resultados.next()) {
				anoAgosto = resultados.getInt("quantidade");
			}
			return anoAgosto;
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro criar o relatório.\n\n" + ex.getMessage());
		}
		return anoAgosto;
	}
	
	// Setembro
	public static int constroiGraficoMesSetembro(int pAnoAtual) {
		int anoSetembro=0;
		
		// Conexão
		Conexao conexao = new Conexao();
		// Instrução SQL
		Statement instrucaoSQL;
		// Resultados
		ResultSet resultados;
		
		try {
			// Conexao
			conexao.conectar();
			instrucaoSQL = conexao.criarStatement();
			String query = "SELECT data_entrada_sistema, COUNT(data_entrada_sistema) as quantidade "
					+ "FROM T_CARGOS WHERE data_entrada_sistema LIKE '%_/9%"+pAnoAtual+"';";
			resultados = instrucaoSQL.executeQuery(query);
			while (resultados.next()) {
				anoSetembro = resultados.getInt("quantidade");
			}
			return anoSetembro;
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro criar o relatório.\n\n" + ex.getMessage());
		}
		return anoSetembro;
	}
	
	// Outubro
	public static int constroiGraficoMesOutubro(int pAnoAtual) {
		int anoOutubro=0;
		
		// Conexão
		Conexao conexao = new Conexao();
		// Instrução SQL
		Statement instrucaoSQL;
		// Resultados
		ResultSet resultados;
		
		try {
			// Conexao
			conexao.conectar();
			instrucaoSQL = conexao.criarStatement();
			String query = "SELECT data_entrada_sistema, COUNT(data_entrada_sistema) as quantidade "
					+ "FROM T_CARGOS WHERE data_entrada_sistema LIKE '%_/10%"+pAnoAtual+"';";
			resultados = instrucaoSQL.executeQuery(query);
			while (resultados.next()) {
				anoOutubro = resultados.getInt("quantidade");
			}
			return anoOutubro;
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro criar o relatório.\n\n" + ex.getMessage());
		}
		return anoOutubro;
	}
	
	// Novembro
	public static int constroiGraficoMesNovembro(int pAnoAtual) {
		int anoNovembro=0;
		
		// Conexão
		Conexao conexao = new Conexao();
		// Instrução SQL
		Statement instrucaoSQL;
		// Resultados
		ResultSet resultados;
		
		try {
			// Conexao
			conexao.conectar();
			instrucaoSQL = conexao.criarStatement();
			String query = "SELECT data_entrada_sistema, COUNT(data_entrada_sistema) as quantidade "
					+ "FROM T_CARGOS WHERE data_entrada_sistema LIKE '%_/11%"+pAnoAtual+"';";
			resultados = instrucaoSQL.executeQuery(query);
			while (resultados.next()) {
				anoNovembro = resultados.getInt("quantidade");
			}
			return anoNovembro;
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro criar o relatório.\n\n" + ex.getMessage());
		}
		return anoNovembro;
	}
	
	// Dezembro
	public static int constroiGraficoMesDezembro(int pAnoAtual) {
		int anoDezembro=0;
		
		// Conexão
		Conexao conexao = new Conexao();
		// Instrução SQL
		Statement instrucaoSQL;
		// Resultados
		ResultSet resultados;
		
		try {
			// Conexao
			conexao.conectar();
			instrucaoSQL = conexao.criarStatement();
			String query = "SELECT data_entrada_sistema, COUNT(data_entrada_sistema) as quantidade "
					+ "FROM T_CARGOS WHERE data_entrada_sistema LIKE '%_/12%"+pAnoAtual+"';";
			resultados = instrucaoSQL.executeQuery(query);
			while (resultados.next()) {
				anoDezembro = resultados.getInt("quantidade");
			}
			return anoDezembro;
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro criar o relatório.\n\n" + ex.getMessage());
		}
		return anoDezembro;
	}
}
