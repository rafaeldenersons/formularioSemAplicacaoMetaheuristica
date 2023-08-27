package janela;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import janela.SemAplicarMetaheuristicaEquipamento.RenderTable;

public class ResumoEquipamento extends JFrame{

	JLabel 	postoTarifarioPonta, 
		    postoTarifarioIntermediario, 
		    postoTarifarioForaPonta,
		    resumoKw,
		    totalPostoTarifarioPonta, 
		    totalPostoTarifarioIntermediario, 
		    totalPostoTarifarioForaPonta,
		    totalPostoTarifario;
		    
	JLabel 	postoTarifarioPontaDinheiro, 
		    postoTarifarioIntermediarioDinheiro, 
		    postoTarifarioForaPontaDinheiro,
		    resumoDinheiro,
		    totalPostoTarifarioPontaDinheiro, 
		    totalPostoTarifarioIntermediarioDinheiro, 
		    totalPostoTarifarioForaPontaDinheiro,
		    totalPostoTarifarioDinheiro;	

	JLabel observacaoDinheiro;
	
	JTextField totalForaPontaTxtFld, 
			   totalIntermediarioTxtFld, 
			   totalPontaTxtFld, 
			   totalPostoTarifarioTxtFld;
	
	JTextField totalForaPontaTxtFldDinheiro, 
			   totalIntermediarioTxtFldDinheiro, 
			   totalPontaTxtFldDinheiro, 
			   totalPostoTarifarioTxtFldDinheiro;
	
	JTable tabelaTotalKw;
	
	public int x, y, largura, altura;
	
	public JButton graspBasico,
				   graspReativo,
				   algoritmoGenetico,
				   metaheuristicaHibrida;
	
	JButton csv;
	JButton relatorio;

			
	public ResumoEquipamento(EquipamentoConfigurado _equipamentoConfigurado,
			
							 double _totalForaPonta,
			
							 double _totalIntermediario,
			
							 double _totalPonta,					
							 
							 double _foraPontaDinheiro,
							 
							 double _intermediarioDinheiro,
							 
							 double _pontaDinheiro
							 
							) throws ParseException {

		
		
		this.setTitle("Resumo: sem aplicação de metaheurística");
		
		this.setLayout(null);
				
		this.setVisible(true);
		
		this.setBounds(200, 225, 493, 455);
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		// não permite maximizar ou alterar o tamanho do formulário/frame
		this.setResizable(false);
		
		// No topo da tela
		this.setLocation(550, 0);

		/** #################################################### */
		/** #################### RESUMO KW ##################### */
		/** #################################################### */
		
		this.postoTarifarioForaPonta 	 = new JLabel("Fora de Ponta");  
		this.postoTarifarioIntermediario = new JLabel("Intermediário"); 
		this.postoTarifarioPonta 		 = new JLabel("Ponta");
		
		this.resumoKw					 = new JLabel("Total de consumo em kWh");
		this.totalPostoTarifario 	 	 = new JLabel("Posto Tarifário");
		
		// painel do cabeçalho
		JPanel cabecalhoResumoKw,
		
			   cabecalhoPostoTarifario,
					   
			   cabecalhoEquipamentoEspecificoForaPonta,
					   
			   cabecalhoEquipamentoEspecificoIntermediario,
					   
			   cabecalhoEquipamentoEspecificoPonta; 		
		
		x = 5; y = 1; largura = 469; altura = 26;		
		cabecalhoResumoKw = new JPanel();		
		cabecalhoResumoKw.setBorder( new TitledBorder( "" ) );
		cabecalhoResumoKw.setLayout( new FlowLayout(FlowLayout.CENTER) );
		cabecalhoResumoKw.setBounds(x, y, largura, altura);		
		cabecalhoResumoKw.add(this.resumoKw);		
		cabecalhoResumoKw.setBackground(new Color(232, 232, 232));
		cabecalhoResumoKw.setFont(new Font ("Tahome", Font.BOLD, 12));
		
		cabecalhoPostoTarifario = new JPanel();
		cabecalhoPostoTarifario.setBorder( new TitledBorder( "" ) );
		cabecalhoPostoTarifario.setLayout( new FlowLayout(FlowLayout.CENTER) );
		y = y + 25;
		cabecalhoPostoTarifario.setBounds(x, y, largura, altura);		
		cabecalhoPostoTarifario.add(this.totalPostoTarifario);
		cabecalhoPostoTarifario.setBackground(new Color(248, 248, 255));
		cabecalhoPostoTarifario.setFont(new Font ("Tahome", Font.BOLD, 12));


		cabecalhoEquipamentoEspecificoForaPonta = new JPanel();			
		cabecalhoEquipamentoEspecificoForaPonta.setBorder(new TitledBorder(""));
		cabecalhoEquipamentoEspecificoForaPonta.setLayout(new FlowLayout(FlowLayout.CENTER));		
		y = y + 25; largura = 154;		
		cabecalhoEquipamentoEspecificoForaPonta.setBounds(x, y, largura, altura);
		cabecalhoEquipamentoEspecificoForaPonta.add(this.postoTarifarioForaPonta);		
		cabecalhoEquipamentoEspecificoForaPonta.setBackground(new Color(240, 255, 255));
		cabecalhoEquipamentoEspecificoForaPonta.setFont(new Font ("Tahome", Font.BOLD, 12));
		
		cabecalhoEquipamentoEspecificoIntermediario = new JPanel();		
		cabecalhoEquipamentoEspecificoIntermediario.setBorder(new TitledBorder(""));
		cabecalhoEquipamentoEspecificoIntermediario.setLayout(new FlowLayout(FlowLayout.CENTER));
		x = x + largura;		
		largura = 154;	
		cabecalhoEquipamentoEspecificoIntermediario.setBounds(x, y, largura, altura);
		cabecalhoEquipamentoEspecificoIntermediario.add(this.postoTarifarioIntermediario);
		cabecalhoEquipamentoEspecificoIntermediario.setBackground(new Color(240, 255, 255));
		cabecalhoEquipamentoEspecificoIntermediario.setFont(new Font ("Tahome", Font.BOLD, 12));
		 
		cabecalhoEquipamentoEspecificoPonta = new JPanel();
		cabecalhoEquipamentoEspecificoPonta.setBorder(new TitledBorder(""));
		cabecalhoEquipamentoEspecificoPonta.setLayout(new FlowLayout(FlowLayout.CENTER));
		x = x + 154;
		largura =161;
		cabecalhoEquipamentoEspecificoPonta.setBounds(x, y, largura, altura);
		cabecalhoEquipamentoEspecificoPonta.add(this.postoTarifarioPonta);
		cabecalhoEquipamentoEspecificoPonta.setBackground(new Color(240, 255, 255));
		cabecalhoEquipamentoEspecificoPonta.setFont(new Font ("Tahome", Font.BOLD, 12));
				
		double total = ( 
						_totalForaPonta + 
						_totalIntermediario +
						_totalPonta 
						);
		
		Locale br = new Locale("pt", "Brazil");
		NumberFormat formatoNumero = NumberFormat.getInstance(br);
		
		
		DecimalFormat formatarDecimal = new DecimalFormat("#,###,##0.00");
		
		String numeroFormatado = new String();
		
		numeroFormatado = formatarDecimal.format( _totalForaPonta );
		
		totalForaPontaTxtFld = new JTextField( numeroFormatado ); 
		x = 5;
		y = y + 25; largura = 154;
		totalForaPontaTxtFld.setBounds(x, y, largura, altura);
		totalForaPontaTxtFld.setHorizontalAlignment(JTextField.CENTER);		
		totalForaPontaTxtFld.setEditable(false);
		totalForaPontaTxtFld.setBackground(new Color(245, 245, 245));
		
		numeroFormatado = formatarDecimal.format( _totalIntermediario);
		
		totalIntermediarioTxtFld 	= new JTextField( numeroFormatado ); 
		x = x + largura;		
		largura = 154;	
		totalIntermediarioTxtFld.setBounds(x, y, largura, altura);
		totalIntermediarioTxtFld.setHorizontalAlignment(JTextField.CENTER);		
		totalIntermediarioTxtFld.setEditable(false);
		totalIntermediarioTxtFld.setBackground(new Color(245, 245, 245));
		
		numeroFormatado = formatarDecimal.format( _totalPonta);
				
		totalPontaTxtFld 			= new JTextField( numeroFormatado );
		x = x + (154);		
		largura = 161;	
		totalPontaTxtFld.setBounds(x, y, largura, altura);
		totalPontaTxtFld.setHorizontalAlignment(JTextField.CENTER);	
		totalPontaTxtFld.setEditable(false);
		totalPontaTxtFld.setBackground(new Color(245, 245, 245));
		
		numeroFormatado = formatarDecimal.format( total );
		
		totalPostoTarifarioTxtFld 	= new JTextField( numeroFormatado  + "  " );
		x = 5; 
		y = y + 25; 
		largura = 469; altura = 26;
		totalPostoTarifarioTxtFld.setBounds(x, y, largura, altura);
		totalPostoTarifarioTxtFld.setHorizontalAlignment(JTextField.CENTER);
		totalPostoTarifarioTxtFld.setEditable(false);
		totalPostoTarifarioTxtFld.setBackground(new Color(248, 248, 255));
		
		/** #################################################### */
		/** #################### RESUMO R$ ##################### */
		/** #################################################### */
		
		JSeparator separador = new JSeparator(JSeparator.HORIZONTAL);
		y = y + 37;
		separador.setBounds(5,

							y,

							465,

							40);

		separador.setVisible(true);
		
		y= y - 37;
		
		this.postoTarifarioForaPontaDinheiro 	 = new JLabel("Fora de Ponta");  
		this.postoTarifarioIntermediarioDinheiro = new JLabel("Intermediário"); 
		this.postoTarifarioPontaDinheiro 		 = new JLabel("Ponta");
		
		this.resumoDinheiro						 = new JLabel("Valor da fatura (R$)*");
		this.totalPostoTarifarioDinheiro 	 	 = new JLabel("Posto Tarifário");
		
		JLabel observacaoDinheiro 				 = new JLabel("* Considerando apenas o valor base de cada posto tarifário, não incluindo impostos,");
		
		JLabel observacaoDinheiroComplementar = new JLabel("tributos e bandeiras tarifária.");
		
		// painel do cabeçalho
		JPanel cabecalhoResumoDinheiro,
		
			   cabecalhoPostoTarifarioDinheiro,
					   
			   cabecalhoEquipamentoEspecificoForaPontaDinheiro,
					   
			   cabecalhoEquipamentoEspecificoIntermediarioDinheiro,
					   
			   cabecalhoEquipamentoEspecificoPontaDinheiro; 		

		
		x = 5; 
		y = y + 50; 
		largura = 469; altura = 26;		
		cabecalhoResumoDinheiro = new JPanel();		
		cabecalhoResumoDinheiro.setBorder( new TitledBorder( "" ) );
		cabecalhoResumoDinheiro.setLayout( new FlowLayout(FlowLayout.CENTER) );
		cabecalhoResumoDinheiro.setBounds(x, y, largura, altura);		
		cabecalhoResumoDinheiro.add(this.resumoDinheiro);		
		cabecalhoResumoDinheiro.setBackground(new Color(232, 232, 232));
		cabecalhoResumoDinheiro.setFont(new Font ("Tahome", Font.BOLD, 12));
		
		cabecalhoPostoTarifarioDinheiro = new JPanel();
		cabecalhoPostoTarifarioDinheiro.setBorder( new TitledBorder( "" ) );
		cabecalhoPostoTarifarioDinheiro.setLayout( new FlowLayout(FlowLayout.CENTER) );
		y = y + 25;
		cabecalhoPostoTarifarioDinheiro.setBounds(x, y, largura, altura);		
		cabecalhoPostoTarifarioDinheiro.add(this.totalPostoTarifarioDinheiro);
		cabecalhoPostoTarifarioDinheiro.setBackground(new Color(248, 248, 255));
		cabecalhoPostoTarifarioDinheiro.setFont(new Font ("Tahome", Font.BOLD, 12));

		
		cabecalhoEquipamentoEspecificoForaPontaDinheiro = new JPanel();			
		cabecalhoEquipamentoEspecificoForaPontaDinheiro.setBorder(new TitledBorder(""));
		cabecalhoEquipamentoEspecificoForaPontaDinheiro.setLayout(new FlowLayout(FlowLayout.CENTER));		
		y = y + 25; largura = 154;		
		cabecalhoEquipamentoEspecificoForaPontaDinheiro.setBounds(x, y, largura, altura);
		cabecalhoEquipamentoEspecificoForaPontaDinheiro.add(this.postoTarifarioForaPontaDinheiro);		
		cabecalhoEquipamentoEspecificoForaPontaDinheiro.setBackground(new Color(240, 255, 255));
		cabecalhoEquipamentoEspecificoForaPontaDinheiro.setFont(new Font ("Tahome", Font.BOLD, 12));

		
		
		cabecalhoEquipamentoEspecificoIntermediarioDinheiro = new JPanel();		
		cabecalhoEquipamentoEspecificoIntermediarioDinheiro.setBorder(new TitledBorder(""));
		cabecalhoEquipamentoEspecificoIntermediarioDinheiro.setLayout(new FlowLayout(FlowLayout.CENTER));
		x = x + largura;		
		largura = 154;	
		cabecalhoEquipamentoEspecificoIntermediarioDinheiro.setBounds(x, y, largura, altura);
		cabecalhoEquipamentoEspecificoIntermediarioDinheiro.add(this.postoTarifarioIntermediarioDinheiro);
		cabecalhoEquipamentoEspecificoIntermediarioDinheiro.setBackground(new Color(240, 255, 255));
		cabecalhoEquipamentoEspecificoIntermediarioDinheiro.setFont(new Font ("Tahome", Font.BOLD, 12));

		
		
		cabecalhoEquipamentoEspecificoPontaDinheiro = new JPanel();
		cabecalhoEquipamentoEspecificoPontaDinheiro.setBorder(new TitledBorder(""));
		cabecalhoEquipamentoEspecificoPontaDinheiro.setLayout(new FlowLayout(FlowLayout.CENTER));
		x = x + 154;
		largura =161;
		cabecalhoEquipamentoEspecificoPontaDinheiro.setBounds(x, y, largura, altura);
		cabecalhoEquipamentoEspecificoPontaDinheiro.add(this.postoTarifarioPontaDinheiro);
		cabecalhoEquipamentoEspecificoPontaDinheiro.setBackground(new Color(240, 255, 255));
		cabecalhoEquipamentoEspecificoPontaDinheiro.setFont(new Font ("Tahome", Font.BOLD, 12));

		
		double totalDinheiro = ( 
								_foraPontaDinheiro 	   +
				 
								_intermediarioDinheiro +
				 
								_pontaDinheiro 
								);
		
		NumberFormat formatacaoMoeda = NumberFormat.getCurrencyInstance();
		
		String moedaBrasileira = formatacaoMoeda.format(_foraPontaDinheiro);
		
		
		totalForaPontaTxtFldDinheiro 		= new JTextField(  moedaBrasileira  ); 
		x = 5;
		y = y + 25; largura = 154;
		totalForaPontaTxtFldDinheiro.setBounds(x, y, largura, altura);
		totalForaPontaTxtFldDinheiro.setHorizontalAlignment(JTextField.CENTER);		
		totalForaPontaTxtFldDinheiro.setEditable(false);
		totalForaPontaTxtFldDinheiro.setBackground(new Color(245, 245, 245));
		
		moedaBrasileira = formatacaoMoeda.format(_intermediarioDinheiro);
		
		totalIntermediarioTxtFldDinheiro 	= new JTextField(  moedaBrasileira ); 
		x = x + largura;		
		largura = 154;	
		totalIntermediarioTxtFldDinheiro.setBounds(x, y, largura, altura);
		totalIntermediarioTxtFldDinheiro.setHorizontalAlignment(JTextField.CENTER);		
		totalIntermediarioTxtFldDinheiro.setEditable(false);
		totalIntermediarioTxtFldDinheiro.setBackground(new Color(245, 245, 245));
		
		moedaBrasileira = formatacaoMoeda.format(_pontaDinheiro);
		
		totalPontaTxtFldDinheiro 			= new JTextField( moedaBrasileira );
		x = x + (154);		
		largura = 161;	
		totalPontaTxtFldDinheiro.setBounds(x, y, largura, altura);
		totalPontaTxtFldDinheiro.setHorizontalAlignment(JTextField.CENTER);	
		totalPontaTxtFldDinheiro.setEditable(false);
		totalPontaTxtFldDinheiro.setBackground(new Color(245, 245, 245));
		
		moedaBrasileira = formatacaoMoeda.format(totalDinheiro);		
		
		totalPostoTarifarioTxtFldDinheiro 	= new JTextField( moedaBrasileira   + "  " );
		x = 5; 
		y = y + 25; 
		largura = 469; altura = 26;
		totalPostoTarifarioTxtFldDinheiro.setBounds(x, y, largura, altura);
		totalPostoTarifarioTxtFldDinheiro.setHorizontalAlignment(JTextField.CENTER);
		totalPostoTarifarioTxtFldDinheiro.setEditable(false);
		totalPostoTarifarioTxtFldDinheiro.setBackground(new Color(248, 248, 255));
		
		y = y + 15;
		altura += 26; 
		observacaoDinheiro.setBounds(x + 10, y, largura, altura);
		observacaoDinheiro.setFont(new Font ("Tahome", Font.PLAIN, 12));
		observacaoDinheiro.setForeground(new Color(205, 0, 0));
		
		y = y + 15;
		
		observacaoDinheiroComplementar.setBounds(x + 10, y, largura, altura);
		observacaoDinheiroComplementar.setFont(new Font ("Tahome", Font.PLAIN, 12));
		observacaoDinheiroComplementar.setForeground(new Color(205, 0, 0));
		
		y = y + 40;
		
		JPanel conteudoPNL = new JPanel();
        conteudoPNL.setBorder( new TitledBorder( "Aplicar Metaheurística" ) );
        
      
		graspBasico 			= new JButton("GRASP Básico");
		graspReativo 			= new JButton("GRASP Reativo");
		algoritmoGenetico 		= new JButton("Algoritmo Genético");
		metaheuristicaHibrida 	= new JButton("Metaheurística Híbrida");				
        
        // redistribui os botões com um espaço maior entre o painel
        //conteudoPNL.setLayout( new FlowLayout(FlowLayout.CENTER, 70, 0) );
      	conteudoPNL.setLayout( new FlowLayout() );
        conteudoPNL.setBounds(10, y, 350, 90);


        
        graspBasico.setPreferredSize(new Dimension(163, 25));
        
        graspBasico.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
								
				double [] totalResumo = new double [6];
				
				totalResumo[0] = _totalForaPonta;
				
				//numeroDouble = formatoNumero.parse(getTotalIntermediario().getText());
				
				//totalIntermediario 		= numeroDouble.doubleValue();
				totalResumo[1] = _totalIntermediario;

				//numeroDouble = formatoNumero.parse(getTotalPonta().getText());
				
				//totalPonta = numeroDouble.doubleValue();
				totalResumo[2] = _totalPonta;
				
				//foraPontaDinheiro 		= (totalForaPonta * FORA_PONTA_DINHEIRO);
				totalResumo[3] = _foraPontaDinheiro;
				
				//intermediarioDinheiro 	= (totalIntermediario * INTERMEDIARIO_DINHEIRO);
				totalResumo[4] = _intermediarioDinheiro;
				
				//pontaDinheiro 			= (totalPonta * PONTA_DINHEIRO);
				totalResumo[5] = _pontaDinheiro;
				
				GraspBasicoFormulario janelaGraspBasicoFormulario = new GraspBasicoFormulario(_equipamentoConfigurado, totalResumo);
				
				janelaGraspBasicoFormulario.setVisible(true);				
				
				// desabilita o formulário ao abrir outro
				
				ResumoEquipamento.this.setEnabled(true);
														
			}
			
		});
        
        conteudoPNL.add(graspBasico);
        graspReativo.setPreferredSize(new Dimension(163, 25));
        conteudoPNL.add(graspReativo);
        algoritmoGenetico.setPreferredSize(new Dimension(163, 25));
        conteudoPNL.add(algoritmoGenetico);
        metaheuristicaHibrida.setPreferredSize(new Dimension(163, 25));
        conteudoPNL.add(metaheuristicaHibrida);

		
		JPanel exportarPNL = new JPanel();
		exportarPNL.setBorder( new TitledBorder( "Exportar" ) );
        
        // redistribui os botões com um espaço maior entre o painel
        exportarPNL.setLayout( new FlowLayout());
        exportarPNL.setBounds(370, y, 100, 90);		
		
		csv = new JButton(".CSV");		
		csv.setBounds(10, 700, 50, 50);
		
		csv.setPreferredSize(new Dimension(70, 25));
        exportarPNL.add(csv);
        
		csv.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				String nomeArquivo = "semAplicacaoMetaheuristicaResumo.csv";
				
				try {
					
					escreveArquivoEmCsv(nomeArquivo,							
										(String) resumoKw.getText(),
										
										(String)totalPostoTarifario.getText(),
										
										_totalForaPonta,								
										
										_totalIntermediario,									
										_totalPonta,															
										
										total,
																			    
										(String) resumoDinheiro.getText(),									
										_foraPontaDinheiro,									
										_intermediarioDinheiro,
							 			
										_pontaDinheiro,										
										totalDinheiro, 										
										observacaoDinheiro.getText(),										
										
										observacaoDinheiroComplementar.getText()
									    
										);
					
					Desktop.getDesktop().open(new File(nomeArquivo));
				
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
								
			}
		});

        relatorio = new JButton(".PDF");		
		
		relatorio.setBounds(400, 700, 50, 50);
		
		relatorio.setPreferredSize(new Dimension(70, 25));

		exportarPNL.add(relatorio);
		
		
		relatorio.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				JOptionPane.showMessageDialog(null, "Arquivo no formato PDF criado com sucesso!");
				
				try {
					
					String arquivoPDF = "C:\\Ifes\\workspace\\RespostaDemanda\\relatorioResumoSemAplicacaoMetaheuristica.pdf";
					
					Document documento = new Document();
					
					PdfWriter.getInstance(documento, new FileOutputStream(arquivoPDF));
					
					documento.open();
					
					documento.setPageSize(PageSize.A4);
					 
					/**############################################################### 
					 * ######################## RELATÓRIO Kw #########################
					 * ###############################################################
					 */
					
					Paragraph paragrafo = new Paragraph("Resumo: sem aplicação de metaheurística" + "\n\n");
					
					paragrafo.setAlignment(Element.ALIGN_CENTER);
					
					documento.add(paragrafo);

					
					PdfPTable tabela0 = new PdfPTable(1);
					tabela0.setWidthPercentage(100);
					float[] columnWidths = new float[]{268};
					tabela0.setWidths(columnWidths);
					
					String resumoKwSemAplicacao = resumoKw.getText();
					
					PdfPCell celula01 	= new PdfPCell(new Paragraph( resumoKwSemAplicacao ));
					celula01.setHorizontalAlignment(Element.ALIGN_CENTER);
					celula01.setBackgroundColor(new BaseColor(181, 181, 181));
					
					String postoTarifarioResumo = totalPostoTarifario.getText();
					
					PdfPCell celula02 	= new PdfPCell(new Paragraph( postoTarifarioResumo ));
					celula02.setHorizontalAlignment(Element.ALIGN_CENTER);
					celula02.setBackgroundColor(new BaseColor(181, 181, 181));
					
					tabela0.addCell(celula01);
					
					tabela0.addCell(celula02);
					
					PdfPTable tabela1 = new PdfPTable(3);
					tabela1.setWidthPercentage(100);
					float[] columnWidths1 = new float[]{89, 90, 89};
					tabela1.setWidths(columnWidths1);					
					
					PdfPCell celula1 	= new PdfPCell(new Paragraph( "Fora de Ponta" ));
					celula1.setHorizontalAlignment(Element.ALIGN_CENTER);
					celula1.setBackgroundColor(new BaseColor(232, 232, 232));
					
					tabela1.addCell(celula1);
					
					PdfPCell celula2 	= new PdfPCell(new Paragraph( "Intermediário" ));
					celula2.setHorizontalAlignment(Element.ALIGN_CENTER);
					celula2.setBackgroundColor(new BaseColor(232, 232, 232));
					
					tabela1.addCell(celula2);
					
					PdfPCell celula3 	= new PdfPCell(new Paragraph( "Ponta" ));
					celula3.setHorizontalAlignment(Element.ALIGN_CENTER);
					celula3.setBackgroundColor(new BaseColor(232, 232, 232));
					
					tabela1.addCell(celula3);
					
					celula1 = new PdfPCell(new Paragraph( Double.toString( (double)_totalForaPonta ) ) );					
					celula1.setHorizontalAlignment(Element.ALIGN_CENTER);
					celula1.setBackgroundColor(new BaseColor(245, 245, 245));					
					
					tabela1.addCell(celula1);
					
					celula2 = new PdfPCell(new Paragraph( Double.toString( (double)_totalIntermediario ) ) );					
					celula2.setHorizontalAlignment(Element.ALIGN_CENTER);
					celula2.setBackgroundColor(new BaseColor(245, 245, 245));
					
					tabela1.addCell(celula2);
					
					celula3 = new PdfPCell(new Paragraph( Double.toString( (double)_totalPonta ) ) );					
					celula3.setHorizontalAlignment(Element.ALIGN_CENTER);
					celula3.setBackgroundColor(new BaseColor(245, 245, 245));
					
					tabela1.addCell(celula3);
					
					PdfPTable tabela2 = new PdfPTable(1);
					tabela2.setWidthPercentage(100);
					float[] columnWidths2 = new float[]{268};
					tabela2.setWidths(columnWidths2);					
					
					PdfPCell celulaTabela2_1 	= new PdfPCell(new Paragraph( Double.toString((double)total) ) );
					celulaTabela2_1.setHorizontalAlignment(Element.ALIGN_CENTER);
					celulaTabela2_1.setBackgroundColor(new BaseColor(181, 181, 181));
					tabela2.addCell(celulaTabela2_1);
					
					
					// deve ser colocada a borda para branco (mas não foi possível ainda)
					PdfPTable tabelaInvisivel = new PdfPTable(1);
					tabelaInvisivel.setWidthPercentage(100);
					float[] columnWidthsInvisivel = new float[]{268};
					tabelaInvisivel.setWidths(columnWidthsInvisivel);
					tabelaInvisivel.getDefaultCell().setBorderColor(new BaseColor(255, 255, 255));
					
					PdfPCell celulaInvisivel 	= new PdfPCell(new Paragraph( "\n\n" ));
					celulaInvisivel.setHorizontalAlignment(Element.ALIGN_CENTER);					
					tabelaInvisivel.addCell(celulaInvisivel);
					celulaInvisivel.setBorderColor(BaseColor.WHITE);;													
					 
					/**############################################################### 
					 * ######################## RELATÓRIO R$ #########################
					 * ###############################################################
					 */
										
					PdfPTable tabela0Dinheiro = new PdfPTable(1);
					tabela0Dinheiro.setWidthPercentage(100);
					float[] columnWidthsDinheiro = new float[]{268};
					tabela0Dinheiro.setWidths(columnWidthsDinheiro);
					
					String resumoDinheiroSemAplicacao = resumoDinheiro.getText();
					
					PdfPCell celula01Dinheiro 	= new PdfPCell(new Paragraph( resumoDinheiroSemAplicacao ));
					celula01Dinheiro.setHorizontalAlignment(Element.ALIGN_CENTER);
					celula01Dinheiro.setBackgroundColor(new BaseColor(181, 181, 181));
					
					String postoTarifarioResumoDinheiro = totalPostoTarifario.getText();
					
					PdfPCell celula02Dinheiro 	= new PdfPCell(new Paragraph( postoTarifarioResumo ));
					celula02Dinheiro.setHorizontalAlignment(Element.ALIGN_CENTER);
					celula02Dinheiro.setBackgroundColor(new BaseColor(181, 181, 181));
					
					tabela0Dinheiro.addCell(celula01Dinheiro);
					
					tabela0Dinheiro.addCell(celula02Dinheiro);
					
					PdfPTable tabela1Dinheiro = new PdfPTable(3);
					tabela1Dinheiro.setWidthPercentage(100);
					float[] columnWidths1Dinheiro = new float[]{89, 90, 89};
					tabela1Dinheiro.setWidths(columnWidths1Dinheiro);					
					
					PdfPCell celula1Dinheiro 	= new PdfPCell(new Paragraph( "Fora de Ponta" ));
					celula1Dinheiro.setHorizontalAlignment(Element.ALIGN_CENTER);
					celula1Dinheiro.setBackgroundColor(new BaseColor(232, 232, 232));
					
					tabela1Dinheiro.addCell(celula1Dinheiro);
					
					PdfPCell celula2Dinheiro 	= new PdfPCell(new Paragraph( "Intermediário" ));
					celula2Dinheiro.setHorizontalAlignment(Element.ALIGN_CENTER);
					celula2Dinheiro.setBackgroundColor(new BaseColor(232, 232, 232));
					
					tabela1Dinheiro.addCell(celula2Dinheiro);
					
					PdfPCell celula3Dinheiro 	= new PdfPCell(new Paragraph( "Ponta" ));
					celula3Dinheiro.setHorizontalAlignment(Element.ALIGN_CENTER);
					celula3Dinheiro.setBackgroundColor(new BaseColor(232, 232, 232));
					
					tabela1Dinheiro.addCell(celula3Dinheiro);
					
					NumberFormat formatacaoMoeda = NumberFormat.getCurrencyInstance();
					
					String moedaBrasileira;
					
					moedaBrasileira = formatacaoMoeda.format(_foraPontaDinheiro);
					
					celula1Dinheiro = new PdfPCell(new Paragraph( moedaBrasileira  ) );					
					celula1Dinheiro.setHorizontalAlignment(Element.ALIGN_CENTER);
					celula1Dinheiro.setBackgroundColor(new BaseColor(245, 245, 245));					
					
					tabela1Dinheiro.addCell(celula1Dinheiro);
					
					moedaBrasileira = formatacaoMoeda.format(_intermediarioDinheiro);
					
					celula2Dinheiro = new PdfPCell(new Paragraph( moedaBrasileira ) );					
					celula2Dinheiro.setHorizontalAlignment(Element.ALIGN_CENTER);
					celula2Dinheiro.setBackgroundColor(new BaseColor(245, 245, 245));
					
					tabela1Dinheiro.addCell(celula2Dinheiro);
					
					moedaBrasileira = formatacaoMoeda.format(_pontaDinheiro);
					
					celula3Dinheiro = new PdfPCell(new Paragraph( moedaBrasileira ) );					
					celula3Dinheiro.setHorizontalAlignment(Element.ALIGN_CENTER);
					celula3Dinheiro.setBackgroundColor(new BaseColor(245, 245, 245));
					
					tabela1Dinheiro.addCell(celula3Dinheiro);
										
					PdfPTable tabela2Dinheiro = new PdfPTable(1);
					tabela2Dinheiro.setWidthPercentage(100);
					float[] columnWidths2Dinheiro = new float[]{268};
					tabela2Dinheiro.setWidths(columnWidths2Dinheiro);					
					
					moedaBrasileira = formatacaoMoeda.format(totalDinheiro);
					
					PdfPCell celulaTabela2_1Dinheiro 	= new PdfPCell(new Paragraph( moedaBrasileira ) );
					celulaTabela2_1Dinheiro.setHorizontalAlignment(Element.ALIGN_CENTER);
					celulaTabela2_1Dinheiro.setBackgroundColor(new BaseColor(232, 232, 232));
					tabela2Dinheiro.addCell(celulaTabela2_1Dinheiro);
					
					String observacaoConcatenada = new String();
					
					observacaoConcatenada = observacaoDinheiro.getText() +									
							
											observacaoDinheiroComplementar.getText();
					
					PdfPCell observacao 	= new PdfPCell(new Paragraph( observacaoConcatenada ));
					observacao.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
					observacao.setBackgroundColor(new BaseColor(181, 181, 181));
					tabela2Dinheiro.addCell(observacao);
					
					documento.add(tabela0);
					
					documento.add(tabela1);
					
					documento.add(tabela2);
					
					documento.add(tabelaInvisivel);
					
					documento.add(tabela0Dinheiro);
					
					documento.add(tabela1Dinheiro);
					
					documento.add(tabela2Dinheiro);
					
;					
					documento.close();
					
					Desktop.getDesktop().open(new File(arquivoPDF));
					
				} 
				catch (DocumentException de) {
					
					de.printStackTrace();
					
				}
				catch (IOException ioe) {
					
					ioe.printStackTrace();
					
				}
								
			}
		});

		this.add(cabecalhoResumoKw);
		this.add(cabecalhoPostoTarifario);
		this.add(cabecalhoEquipamentoEspecificoForaPonta);
		this.add(cabecalhoEquipamentoEspecificoIntermediario);
		this.add(cabecalhoEquipamentoEspecificoPonta);
		
		this.add(totalForaPontaTxtFld);
		this.add(totalIntermediarioTxtFld);
		this.add(totalPontaTxtFld);
		this.add(totalPostoTarifarioTxtFld);
		
		
		this.add(separador);
		
		
		this.add(cabecalhoResumoDinheiro);
		this.add(cabecalhoPostoTarifarioDinheiro);
		this.add(cabecalhoEquipamentoEspecificoForaPontaDinheiro);
		this.add(cabecalhoEquipamentoEspecificoIntermediarioDinheiro);
		this.add(cabecalhoEquipamentoEspecificoPontaDinheiro);
		
		this.add(totalForaPontaTxtFldDinheiro);
		this.add(totalIntermediarioTxtFldDinheiro);
		this.add(totalPontaTxtFldDinheiro);
		this.add(totalPostoTarifarioTxtFldDinheiro);
		
		this.add(observacaoDinheiro);
		this.add(observacaoDinheiroComplementar);
		
		this.add(conteudoPNL);
		
		this.add(exportarPNL);
		
	}
	
	public class RenderTable implements TableCellRenderer{
		
		public Component getTableCellRendererComponent (JTable jtable, Object o, boolean selected, boolean focus, int row, int cel) {
		
			JPanel painel = new JPanel();
			
			JLabel label = new JLabel((o).toString());			
			
			if (row%2 == 0) {
			
				painel.setBackground(new Color(245, 245, 245));

			}
			else {
		
				painel.setBackground(new Color(232, 232, 232));
			
			}
			
			label.setForeground(Color.BLACK);
			
			/** Altera a letra e o tamanho dos dados */
			label.setFont(new Font ("Tahome", Font.PLAIN, 11));
			
			painel.add(label);
			
			return painel;
			
		}
		
	}	

	public void escreveArquivoEmCsv(String _nomeArquivo,
			
									String _resumoKw,
									
									String _totalPostoTarifario,
													
									double _totalForaPonta,
										
									double _totalIntermediario,

									double _totalPonta,
									
									double _total,
												    
									String _resumoDinheiro,
						 
									double _foraPontaDinheiro,
						 
									double _intermediarioDinheiro,
									
									double _pontaDinheiro,
									
									double _totalDinheiro, 
									
									String _observacaoDinheiro,
									
									String _observacaoDinheiroComplementar
												
									) throws FileNotFoundException {
		
		try (PrintWriter escreverCsv = new PrintWriter(_nomeArquivo)){
			
			escreverCsv.write( "" + ",");
			
			escreverCsv.write( _resumoKw + ",\n");
			
			escreverCsv.write( "" + ",");
						
			escreverCsv.write( _totalPostoTarifario + ",\n");
			
			escreverCsv.write( "Fora de Ponta" + ",");
			
			escreverCsv.write( "Intermediário" + ",");

			escreverCsv.write( "Ponta" + ",\n");
			
			escreverCsv.write((double)_totalForaPonta + ",");
			
			escreverCsv.write((double)_totalIntermediario + ",");

			escreverCsv.write((double)_totalPonta + ",\n");					
			
			escreverCsv.write( "" + ",");

			escreverCsv.write( (double)_total + ",\n");
			
			escreverCsv.write( "\n");			

			escreverCsv.write( "" + ",");
			
			escreverCsv.write( _resumoDinheiro + ",\n");
			
			escreverCsv.write( "" + ",");
			
			escreverCsv.write( "Posto Tarifário" + ",\n");
		
			escreverCsv.write( "Fora de Ponta" + ",");
			
			escreverCsv.write( "Intermediário" + ",");

			escreverCsv.write( "Ponta" + ",\n");
			
			DecimalFormat fmt = new DecimalFormat("0.00");      
			 
			String numeroDouble;
			 
			numeroDouble = fmt.format((double)_foraPontaDinheiro);
			
			escreverCsv.write( numeroDouble.replaceAll( "," , "." ) + ",");
			
			numeroDouble = fmt.format((double)_intermediarioDinheiro);
			
			escreverCsv.write( numeroDouble.replaceAll( "," , "." ) + ",");
			
			numeroDouble = fmt.format((double)_pontaDinheiro);

			escreverCsv.write( numeroDouble.replaceAll( "," , "." ) + ",\n");					
			
			escreverCsv.write( "" + ",");
			
			numeroDouble = fmt.format((double)_totalDinheiro);

			escreverCsv.write( numeroDouble.replaceAll( "," , "." ) + ",\n");
			
			escreverCsv.write( "" + ",");
			
			String observacao = _observacaoDinheiro + _observacaoDinheiroComplementar; 

			escreverCsv.write(observacao + ",\n");
					
			JOptionPane.showMessageDialog(null, "Arquivo no formato CSV gerado com sucesso!");
			
		}
		catch (Exception e) {
			
			System.out.println("Erro ao escrever o arquivo");
			
			e.printStackTrace();
			
		}
		
	};
	
}
