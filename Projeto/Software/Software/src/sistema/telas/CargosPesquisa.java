package sistema.telas;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import Controller.ControllerCargo;
import sistema.Navegador;
import sistema.entidades.Cargo;
import sistema.entidades.Funcionario;
import sqlite.Conexao;

public class CargosPesquisa extends JFrame {

	DefaultListModel<Cargo> listCargosModel = new DefaultListModel();
	JList<Cargo> listCargos;
	String txtCampoCargo;
	JLabel lblTitle;
	JButton btnVoltar, btnEditar, btnExcluir, btnConsultarDados;
	ImageIcon imgIconDeletar = new ImageIcon(
			"C:\\Users\\Eduardo\\Desktop\\Projeto\\Software\\Software\\img\\delete01.png");
	ImageIcon imgIconEditar = new ImageIcon(
			"C:\\Users\\Eduardo\\Desktop\\Projeto\\Software\\Software\\img\\edit01.png");
	ImageIcon imgIconBack = new ImageIcon("C:\\Users\\Eduardo\\Desktop\\Projeto\\Software\\Software\\img\\back02.png");
	ImageIcon imgPesquisar = new ImageIcon(
			"C:\\Users\\Eduardo\\Desktop\\Projeto\\Software\\Software\\img\\search03.png");
	Container container;

	public CargosPesquisa(String ptxtCampoCargo) {
		this.txtCampoCargo = ptxtCampoCargo;
		CriarComponentes();
		CriarEventos();
	}

	private void CriarComponentes() {
		setLayout(new FlowLayout(FlowLayout.CENTER));
		container = getContentPane();

		lblTitle = new JLabel("Consulta de Cargos", JLabel.CENTER);
		lblTitle.setFont(new Font(lblTitle.getFont().getName(), Font.PLAIN, 20));
		btnVoltar = new JButton("Voltar", imgIconBack);
		btnEditar = new JButton("Editar", imgIconEditar);
		btnExcluir = new JButton("Excluir", imgIconDeletar);
		btnConsultarDados = new JButton("Consultar", imgPesquisar);

		add(lblTitle);

		listCargosModel = new DefaultListModel();
		listCargos = new JList();
		listCargos.setModel(listCargosModel);
		listPesquisarCargos();

		add(btnVoltar);
		add(btnEditar);
		add(btnExcluir);
		add(btnConsultarDados);
	}

	private void CriarEventos() {

		// Editar
		btnEditar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String cargoSelecionado;
				// Recebo um objeto
				Cargo listCargoSelecionado = (Cargo) listCargos.getSelectedValue();
				if (listCargoSelecionado == null) {
					JOptionPane.showMessageDialog(null, "Selecione um campo");
				} else {
					// Seleciono o nome do cargo pelo objeto recebido
					cargoSelecionado = listCargoSelecionado.getCargoNome();
					ControllerCargo controllerCargo = new ControllerCargo();
					controllerCargo.atualizarCargo(cargoSelecionado);
				}
				voltaFrame();
			}
		});

		// Excluir
		btnExcluir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String cargoSelecionado;
				// Recebo um objeto
				Cargo listCargoSelecionado = (Cargo) listCargos.getSelectedValue();
				if (listCargoSelecionado == null) {
					JOptionPane.showMessageDialog(null, "Selecione um campo");
				} else {
					// Seleciono o nome do cargo pelo objeto recebido
					cargoSelecionado = listCargoSelecionado.getCargoNome();
					ControllerCargo controllerCargo = new ControllerCargo();
					controllerCargo.deletarCargoController(cargoSelecionado);
				}
				voltaFrame();
			}
		});

		// Voltar
		btnVoltar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				voltaFrame();
			}
		});

		btnConsultarDados.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String cargoSelecionado;
				// Recebo um objeto
				Cargo listCargoSelecionado = (Cargo) listCargos.getSelectedValue();
				if (listCargoSelecionado == null) {
					JOptionPane.showMessageDialog(null, "Selecione um campo");
				} else {
					// Seleciono o nome do cargo pelo objeto recebido
					cargoSelecionado = listCargoSelecionado.getCargoNome();
					ControllerCargo controllerCargo = new ControllerCargo();
					controllerCargo.consultarCargo(cargoSelecionado); // especifico
				}
			}
		});
	}

	private void voltaFrame() {
		Navegador.cargosConsultar();
		Navegador.cargosList.setVisible(false);
	}

	private void listPesquisarCargos() {
		// Conexao BD
		Conexao conexao = new Conexao();
		// Statement
		Statement statement = null;
		// resultados
		ResultSet resultSet = null;

		try {
			conexao.conectar();
			String sqlSelect = "SELECT * FROM T_CARGOS WHERE nome LIKE '%" + txtCampoCargo + "%' ORDER BY nome ASC;";
			statement = conexao.criarStatement();
			resultSet = statement.executeQuery(sqlSelect);
			listCargosModel.clear();
			while (resultSet.next()) {
				Cargo cargo = new Cargo();
				cargo.setCargoId(resultSet.getInt("id"));
				cargo.setCargoNome(resultSet.getString("nome"));
				listCargosModel.addElement(cargo);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao consultar cargos");
			Logger.getLogger(CargosPesquisa.class.getName()).log(Level.SEVERE, null, ex);
		}
		listCargos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		listCargos.setVisibleRowCount(10);
		add(new JScrollPane(listCargos));
	}
}
