package sistema.telas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
import sistema.Navegador;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.*;
import org.jfree.data.general.DefaultPieDataset;
import sistema.telas.RelatoriosCargosConstroiGrafico;

import sqlite.Conexao;

public class RelatoriosCargos extends JPanel {

	JLabel lblTitulo, lblDescricao, lblAnoAtual;
	private int anoAtual;

	public RelatoriosCargos() {
		criarComponentes();
		criarEventos();
		Navegador.habilitaMenu();
	}

	public int getAnoAtual() {
		return anoAtual;
	}

	public void setAnoAtual(int anoAtual) {
		this.anoAtual = anoAtual;
	}

	private void criarComponentes() {
		setLayout(null);

		lblTitulo = new JLabel("Relatório", JLabel.CENTER);
		lblTitulo.setFont(new Font(lblTitulo.getFont().getName(), Font.PLAIN, 20));
		lblDescricao = new JLabel("Esse gráfico representa a quantidade de cargos cadastrado por cada mês do ano correspondente", JLabel.CENTER);


		lblTitulo.setBounds(50, 20, 660, 40);
		lblDescricao.setBounds(45, 50, 660, 40);
		
		add(lblTitulo);
		add(lblDescricao);

		setVisible(true);
		
		Calendar calendario = Calendar.getInstance(); // Singleton
		anoAtual = calendario.get(Calendar.YEAR);
		lblAnoAtual = new JLabel("Ano: "+anoAtual, JLabel.CENTER);
		lblAnoAtual.setFont(new Font(lblAnoAtual.getFont().getName(), Font.PLAIN, 20));
		lblAnoAtual.setBounds(50, 160, 660, 20);
		add(lblAnoAtual);
		
		criarGrafico();
	}

	private void criarEventos() {
		
		
	}
	
	public void criarGrafico() {
		
		DefaultPieDataset dadosGrafico = this.criarDadosGrafico();

		JFreeChart someChart = ChartFactory.createPieChart3D("", dadosGrafico, false, true, false);
		PiePlot plot = (PiePlot) someChart.getPlot();
		plot.setLabelBackgroundPaint(Color.WHITE);
		plot.setBackgroundPaint(null);
		plot.setOutlinePaint(null);
		someChart.setBackgroundPaint(null);

		PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator("{0}: {1} ({2})", new DecimalFormat("0"),
				new DecimalFormat("0%"));
		plot.setLabelGenerator(gen);

		ChartPanel chartPanel = new ChartPanel(someChart) {
			@Override
			public Dimension getPreferredSize() {
				return new Dimension(660, 340);
			}
		};
		
		chartPanel.setBounds(60, 180, 660, 340);
		add(chartPanel);
	}

	private DefaultPieDataset criarDadosGrafico() {

		DefaultPieDataset dados = new DefaultPieDataset();

		try {
			
			dados.setValue("Janeiro", RelatoriosCargosConstroiGrafico.constroiGraficoMesJaneiro(anoAtual));
			dados.setValue("Fevereiro", RelatoriosCargosConstroiGrafico.constroiGraficoMesFevereiro(anoAtual));
			dados.setValue("Março", RelatoriosCargosConstroiGrafico.constroiGraficoMesMarco(anoAtual));
			dados.setValue("Abril", RelatoriosCargosConstroiGrafico.constroiGraficoMesAbril(anoAtual));
			dados.setValue("Maio", RelatoriosCargosConstroiGrafico.constroiGraficoMesMaio(anoAtual));
			dados.setValue("Junho", RelatoriosCargosConstroiGrafico.constroiGraficoMesJunho(anoAtual));
			dados.setValue("Julho", RelatoriosCargosConstroiGrafico.constroiGraficoMesJulho(anoAtual));
			dados.setValue("Agosto", RelatoriosCargosConstroiGrafico.constroiGraficoMesAgosto(anoAtual));
			dados.setValue("Setembro", RelatoriosCargosConstroiGrafico.constroiGraficoMesOutubro(anoAtual));
			dados.setValue("Novembro", RelatoriosCargosConstroiGrafico.constroiGraficoMesNovembro(anoAtual));
			dados.setValue("Dezembro", RelatoriosCargosConstroiGrafico.constroiGraficoMesDezembro(anoAtual));
			return dados;

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro criar o relatório.\n\n" + ex.getMessage());
			Logger.getLogger(RelatoriosCargos.class.getName()).log(Level.SEVERE, null, ex);
			Navegador.inicio();
		}
		return null;
	}

}