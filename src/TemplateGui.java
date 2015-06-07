import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
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
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;














import net.ericaro.surfaceplotter.JSurfacePanel;
import net.ericaro.surfaceplotter.surface.ArraySurfaceModel;

import com.googlecode.surfaceplotter.SurfacePlotter;

import Controller.Page;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Random;

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
	EmptyBorder noPadding = new EmptyBorder(5, 5, 5, 5);
	
	Page page;
	private JPanel panel_1;
	private JPanel panel_2;
	
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
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		frame.setSize(600, 600);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		makeMenu(frame);

		panel_1 = new JPanel();
		frame.getContentPane().add(panel_1);
		
		makeTable(panel_1);

		// bottom third
		panel_2 = new JPanel();
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(noPadding);
		//tabbedPane.setBackground(new Color(10,100,20));
		frame.getContentPane().add(tabbedPane);
		makeDescription(tabbedPane);
		makeVisualisation(tabbedPane);
		panel_2.setBorder(noPadding);
		panel_2.setBackground(new Color(10,100,20));
		panel_1.setSize(new Dimension(584, 180));
		panel_2.setSize(new Dimension(584, 380));
		
		//panel_2.add(tabbedPane);
		frame.getContentPane().add(panel_2);
		frame.pack();

		
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
		panel.setSize(new Dimension(584, 180));
		panel.setLayout(new BorderLayout(0,0));
		panel.setBackground(new Color(50, 100, 150));
		
		JPanel fakePanel = new JPanel();
		fakePanel.setBorder(noPadding);
		fakePanel.setSize(0, 0);
		fakePanel.setVisible(false);
		
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
		
		
		JScrollPane inputPane = new JScrollPane(table);
		
		Dimension tableDm = new Dimension(panel.getWidth() - 20, 118);//(int) Math.floor(panel.getHeight()*0.66));		
		table.setPreferredScrollableViewportSize(new Dimension(tableDm));
		table.setFillsViewportHeight(true); // set visible true
		
		
		JPanel buttonPanel = new JPanel();
		FlowLayout buttonLayout = new FlowLayout();		
		buttonPanel.setLayout(buttonLayout);
		buttonPanel.setSize(new Dimension(panel.getWidth()-20, 30));
		buttonLayout.setAlignment(FlowLayout.CENTER);
		
		//panel.add(fakePanel, BorderLayout.SOUTH);
		panel.add(inputPane, BorderLayout.NORTH);
		panel.add(buttonPanel, BorderLayout.CENTER);
		
		
		buttonPanel.setBorder(noPadding);
		inputPane.setBorder(noPadding);
		fakePanel.setBorder(noPadding);
		
		
		JButton buttonAdd = new JButton("More");
		buttonPanel.add(buttonAdd);
		buttonAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.addRow(new Object[tableColCount]);
				table.validate();
				tableRowCount++;
			}
		});

		JButton buttonRemove = new JButton("Less");
		buttonPanel.add(buttonRemove);
		buttonRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.removeRow(model.getRowCount() - 1);
				table.validate();
				tableRowCount--;
			}
		});

		JButton buttonUpdate = new JButton("Update");
		buttonPanel.add(buttonUpdate);
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
		buttonPanel.validate();
		table.validate();
		panel.validate();
		
		System.out.println("x pos of res more, less, update " + buttonUpdate.getX() + ", " + buttonUpdate.getX());
		System.out.println((int) Math.floor(panel.getHeight()*0.66)+" hsould be table height but at the end "+ table.getHeight());

	}

	
	private void makeVisualisation(JTabbedPane tabbedPane) {
	
		JPanel visPanel = new JPanel();
		tabbedPane.addTab("Visualisation", null, visPanel, null);

		SurfacePlotter plot = new SurfacePlotter();
		visPanel.add(plot);
		/*JPanel chart = new ChartPanel(
				createChart(createDataSeries(inputData)));
		Plane plane = new Plane(cards, this);
		
        plane.setPlot(variablesCard.getData());
        */
		
		JSurfacePanel jsp = new JSurfacePanel();
		jsp.setTitleText("Making things happen");
		visPanel.add(jsp);
		
		Random rand = new Random();
		int max = 10;
		float[][] z1 = new float[max][max];
		float[][] z2 = new float[max][max];
		for (int i = 0; i < max; i++) {
			for (int j = 0; j < max; j++) {
				z1[i][j] = rand.nextFloat() * 20 - 10f;
				z2[i][j] = rand.nextFloat() * 20 - 10f;
			}
		}
		ArraySurfaceModel sm = new ArraySurfaceModel();
		sm.setValues(0f,10f,0f,10f,max, z1, z2);
		jsp.setModel(sm);


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
