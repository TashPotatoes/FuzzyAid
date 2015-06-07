import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.LookAndFeelInfo;

import Controller.MembershipPage;
import Controller.Page;


public class Runner {
	/**
	 * Returns the class name of the installed LookAndFeel with a name
	 * containing the name snippet or null if none found.
	 * 
	 * @param nameSnippet a snippet contained in the Laf's name
	 * @return the class name if installed, or null
	 */
	public static String getLookAndFeelClassName(String nameSnippet) {
	    LookAndFeelInfo[] plafs = UIManager.getInstalledLookAndFeels();
	    for (LookAndFeelInfo info : plafs) {
	        if (info.getName().contains(nameSnippet)) {
	            return info.getClassName();
	        }
	    }
	    return null;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String className = getLookAndFeelClassName("Nimbus");
		try {
			UIManager.setLookAndFeel(className);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String descr = "blablabl this is a horrible description /n erterrtertert rt rggggggggggggggggggggggggggggs fg dgdgd dfd"
				+ "alerkgelk seiuh erpiau eiu eioj  rtiowazskf ereths rryhtyj "
				+ "gljdf ;  woeuth ethg srt  jwairojf aht hutgie tgsrtg hsr t " ;
		Object[][] inData = {{0, 0.1, 0.44, 0.324, 0.5}, {0, 0.1, 0.44, 0.324, 0.5}};
		Object[][] outData = {{0, 0.1, 0.44, 0.324, 0.5}, {0, 0.1, 0.44, 0.324, 0.5}};
		String[] cols = {"something", "a", "b", "c", "d"};
		
		Page page = new MembershipPage(descr, cols, inData, outData);
		
		TemplateGui gui = new TemplateGui(page);
		
		//for parsing into the template GUI
		/*TableColumn column = jTable1.getColumnModel().getColumn(3);
	    JComboBox cb = new JComboBox(ServerIntf.DataModificationType.getStringList().toArray());
	    column.setCellEditor(new DefaultCellEditor(cb));*/
		
	}

}
