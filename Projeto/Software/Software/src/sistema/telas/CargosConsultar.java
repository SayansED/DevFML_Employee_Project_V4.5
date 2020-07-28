package sistema.telas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Controller.ControllerCargo;
import sistema.Navegador;
import sistema.Sistema;
import sistema.entidades.Cargo;
import sqlite.Conexao;
import sqlite.CriarBancoDeDados;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class CargosConsultar extends JPanel {

	Cargo cargoAtual;
	JLabel lblTitle, lblCargo;
	JTextField txtCampoCargo;
	JButton btnPesquisar, btnEditar, btnExcluir;
	ImageIcon imgIconPesquisar = new ImageIcon(
			"C:\\Users\\Eduardo\\Desktop\\Projeto\\Software\\Software\\img\\search03.png");
	ImageIcon imgIconDeletar = new ImageIcon(
			"C:\\Users\\Eduardo\\Desktop\\Projeto\\Software\\Software\\img\\delete01.png");
	ImageIcon imgIconEditar = new ImageIcon(
			"C:\\Users\\Eduardo\\Desktop\\Projeto\\Software\\Software\\img\\edit01.png");

	public CargosConsultar() {
		criarComponentes();
		criarEventos();
		Navegador.habilitaMenu();
	}

	private void criarComponentes() {
		setLayout(null);

		lblTitle = new JLabel("Consulta de Cargos", JLabel.CENTER);
		lblTitle.setFont(new Font(lblTitle.getFont().getName(), Font.PLAIN, 20));
		lblCargo = new JLabel("Nome do cargo", JLabel.LEFT);
		txtCampoCargo = new JTextField();
		btnPesquisar = new JButton("Pesquisar Cargo", imgIconPesquisar);
		btnEditar = new JButton("Editar Cargo", imgIconEditar);
		btnExcluir = new JButton("Excluir Cargo", imgIconDeletar);
		btnEditar.setEnabled(true);
		btnExcluir.setEnabled(true);

		lblTitle.setBounds(20, 20, 660, 40);
		lblCargo.setBounds(150, 120, 400, 20);
		txtCampoCargo.setBounds(150, 140, 400, 40);
		btnPesquisar.setBounds(560, 140, 200, 40);
		btnEditar.setBounds(180, 210, 180, 40);
		btnExcluir.setBounds(440, 210, 180, 40);

		add(lblTitle);
		add(lblCargo);
		add(txtCampoCargo);
		add(btnPesquisar);
		add(btnEditar);
		add(btnExcluir);

		setVisible(true);
	}

	private void criarEventos() {
		// Pesquisar
		btnPesquisar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listPesquisarCargos(txtCampoCargo.getText());
			}
		});

		// Editar
		btnEditar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!txtCampoCargo.getText().isEmpty()) {
					ControllerCargo controllerCargo = new ControllerCargo();
					controllerCargo.atualizarCargo(txtCampoCargo.getText());
				}
				else {
					JOptionPane.showMessageDialog(null, "Preencha o campo", "Mensagem",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		// Excluir
		btnExcluir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Validando campo e nome
				if (txtCampoCargo.getText().isEmpty() || txtCampoCargo.getText().length() <= 3) {
					JOptionPane.showMessageDialog(null, "Preencha o campo corretamente", "Mensagem",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				ControllerCargo controllerCargo = new ControllerCargo();
				controllerCargo.deletarCargoController(txtCampoCargo.getText());
			}
		});
	}

	private void listPesquisarCargos(String ptxtCampoCargo) {
		Navegador.CargosPesquisa(ptxtCampoCargo);
	}
}
