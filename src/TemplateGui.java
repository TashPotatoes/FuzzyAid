import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.GridLayout;

import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;

import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;



import Controller.Page;
import java.awt.FlowLayout;
import java.awt.BorderLayout;

public class TemplateGui extends JFrame implements ActionListener {
	/*String description;
	final int DEFAULT_ROWS = 2;
	final int DEFAULT_COLS = 6;
	Object[] inputCols;
	Object[][] inputData;
	double[][] visualData;
	*/
	private int tableColCount = 0;
	private int tableRowCount = 0;
	private JTable table;
	private DefaultTableModel model;
	
	Page page;
	private JPanel panel_1;
	
	public TemplateGui(Page page){
		this.page = page;
		tableRowCount = page.getOutData().length;
		tableColCount = page.getOutData()[0].length;
	//}
	
/*	public TemplateGui(String[] cols, Object[][] inputData, String description,
			double[][] visualData) {
*/
	/*	inputCols = cols;
		tableColCount = inputData[0].length;
		tableRowCount = inputData.length;
		this.description = description;
		this.inputData = inputData;
		this.visualData = visualData;
*/
		JFrame frame = new JFrame();
		frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		frame.setSize(600, 600);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		makeMenu(frame);

		panel_1 = new JPanel();
		panel_1.setMinimumSize(new Dimension(500, 100));
		panel_1.setSize(new Dimension(584, 150));
		frame.getContentPane().add(panel_1);
		
		makeTable(panel_1);

		// bottom third
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane);
		makeVisualisation(tabbedPane);
		makeDescription(tabbedPane);
		

		panel_1.setSize(new Dimension(584, 150));
		
		System.out.println("panel height " + panel_1.getHeight());
		System.out.println("panel width " + panel_1.getWidth());
		System.out.println("frame height " + frame.getHeight());
		System.out.println("frame width " + frame.getWidth());
		System.out.println("table height" + table.getHeight());
		
		

	}

	private void updateVisualtion() {
		page.sanityCheckData();
		page.parseInData();
		page.drawVisual();
		// TODO method
	
	}

	
	private void makeMenu(JFrame frame) {

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu file = new JMenu(" File ");
		JMenu controller = new JMenu(" Controller Pages ");
		JMenu theory = new JMenu(" Theory Pages ");
		
		menuBar.add(file);
		menuBar.add(controller);
		menuBar.add(theory);	

		JMenuItem close = new JMenuItem("Close");
		JMenuItem reset = new JMenuItem("Reset");
		
		JMenuItem [] controllerItems = {
				new JMenuItem("Universe Data"), 
				new JMenuItem("Input Membership"), 
				new JMenuItem("Output Membership"), 
				new JMenuItem("Inference Rules"), 
				new JMenuItem("Defuzzification")
		};
		
		JMenuItem [] theoryItems = {
				new JMenuItem("Equivalence"), 
				new JMenuItem("Transitive Closure"), 
				new JMenuItem("Direct Clustering"), 
				new JMenuItem("Composition")
		};
		
		file.add(close);
		file.add(reset);
				
		for(JMenuItem item : controllerItems){
			controller.add(item);
		}
		
		for(JMenuItem item : theoryItems){
			theory.add(item);
		}

	}

	
	private void makeTable(JPanel panel) {
		table = new JTable(0, tableColCount);
		model = (DefaultTableModel) table.getModel();
		model.setColumnIdentifiers(page.getColNames());
		for (int i = 0; i < tableRowCount; i++) {
			model.addRow(page.getOutData()[i]);
			for (int j = 0; j < tableColCount; j++) {
				model.isCellEditable(i, j);
			}
		}
		System.out.println("-20 hsould be table height and is panel height "+ panel.getHeight());
		Dimension tableDm = new Dimension(panel.getWidth() - 20, panel.getHeight() -20);
		panel_1.setLayout(new BorderLayout(0, 0));
		table.setPreferredScrollableViewportSize(new Dimension(tableDm));
		table.setFillsViewportHeight(true); // set visible true
		JScrollPane inputPane = new JScrollPane(table);
		panel.add(inputPane);

		JButton buttonAdd = new JButton("More");
		panel.add(buttonAdd);
		buttonAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.addRow(new Object[tableColCount]);
				table.validate();
				tableRowCount++;
			}
		});

		JButton buttonRemove = new JButton("Less");
		panel.add(buttonRemove);
		buttonRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.removeRow(model.getRowCount() - 1);
				table.validate();
				tableRowCount--;
			}
		});

		JButton buttonUpdate = new JButton("Update");
		panel.add(buttonUpdate);
		buttonUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				for (int row = 0; row < tableRowCount; row++){
					for (int col = 0; col < tableColCount; col++){
						//sanity check();
						//page.setOutDataAt(row, col, model.getValueAt(row, col));						
					}
				}
				
				updateVisualtion();
			}
		});

		
	}

	
	private void makeVisualisation(JTabbedPane tabbedPane) {
	
		JPanel visPanel = new JPanel();
		tabbedPane.addTab("Visualisation", null, visPanel, null);

	}

	
	private void makeDescription(JTabbedPane tabbedPane) {
	
		JPanel descPanel = new JPanel();
		tabbedPane.addTab("Description", null, descPanel, null);

		JTextArea descriptionArea = new JTextArea(page.getDescription());
		descriptionArea.setEditable(false);
		descriptionArea.setLineWrap(true);
		descriptionArea.setSize(getMaximumSize());
		descPanel.add(descriptionArea);
	
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
