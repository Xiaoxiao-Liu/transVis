package translationVisualization;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.ScrollPaneConstants;
import javax.swing.ScrollPaneLayout;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import TranslationVisualizatonGUI.ColorLegendPanel;
import TranslationVisualizatonGUI.ConcordancePanel;

public class TranslationVisualization {
	
//	/** the width of the frame */
//	private final static int FRAME_WIDTH=800;
// 	
//	/** the height of the frame */
//	private final static int FRAME_HEIGHT=800;

	/** the width of the ConcordancePanel */
	private final static int CONCORDANCE_PANEL_WIDTH=3500;
	
	/** the height of the ConcordancePanel */
	private final static int CONCORDANCE_PANEL_HEIGHT=2900;
	
	/** the width of the ScrollPanel */
	private final static int SCROLL_PANEL_WIDTH=500;
	
	/** the height of the ScrollPanel */
	private final static int SCROLL_PANEL_HEIGHT=300;
	
	/** a way to access the JFrame object (Layer 1) */
	private JFrame m_ConcordanceFrame;

	/** a way to access the ConcordancePanel object (Layer 2)*/
	private ConcordancePanel m_ConcordancePanel;

	/** a JPanel object containing visualization components (Layer 3)*/
	private JPanel m_visuallizationPanel;

	/** a ColorLegendPanel object containing color legend for frequency colors (Layer 4) */
	private  ColorLegendPanel m_ColorLegendPanel=new ColorLegendPanel();

	/** a way to access the JScrollPane object (Layer 4)*/ 
	private JScrollPane m_ScrollPane;

	/** a JPanel object containing user-option components (Layer 3)*/
	private JPanel m_UserOptionPanel;

	/** a JButton to initiate concordancePanel */
	private JButton m_ConcordanceButton;

	/** a JSlider to zoom in and out concordancePanel */
	private JSlider m_ConcordanceSlider;
	
	private JSlider m_ScrollPaneSlder;

	/** an arrayList to pass version list to other classes */
	private List<Version> m_VersionList=new ArrayList<Version>();

	/**  */
	private static int m_scaleValue=0;
	
	//accessor methods
	/**
	 *  Use this method to access m_ConcordanceSlider
	 * @return m_ConcordanceSlider
	 */
	public JSlider getM_ConcordanceSlider() {
		return m_ConcordanceSlider;
	}

	/**
	 * Use this method to create and set m_ConcordanceSlider
	 * @param m_Slider
	 */
	public void setM_ConcordanceSlider(JSlider m_Slider) {
		this.m_ConcordanceSlider = m_Slider;
		int tickSpacing=10; //set tick space: 0, 10, 20...100
		m_Slider.setMajorTickSpacing(tickSpacing);
		m_Slider.setPaintLabels(true);
		m_Slider.setBackground(Color.WHITE);
	}

	/**
	 *  Use this method to access m_ConcordanceFrame
	 * @return m_ConcordanceFrame
	 */
	public JFrame getConcordanceFrame() {
		return m_ConcordanceFrame;
	}

	/**
	 * Use this method to create and set m_ConcordanceFrame
	 * @param concordanceFrame
	 */
	public void setConcordanceFrame(JFrame concordanceFrame) {
		this.m_ConcordanceFrame = concordanceFrame;
//		getConcordanceFrame().setSize(FRAME_WIDTH, FRAME_HEIGHT);	
	}
	
	/**
	 * Use this method to access m_ScrollPanel
	 * @return m_ScrollPanel
	 */
	public JScrollPane getScrollPane() {
		return m_ScrollPane;
	}

	/**
	 * Use this method to create and set m_ScrollPanel
	 * @param scrollPanel
	 */
	public void setScrollPane(JScrollPane scrollPanel) {
		this.m_ScrollPane = scrollPanel;
		//we need both horizontal and vertical scroll bars
		getScrollPane().setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		getScrollPane().setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
//		getScrollPanel().setPreferredSize(new Dimension(SCROLL_PANEL_WIDTH+m_scaleValue*4, SCROLL_PANEL_HEIGHT+m_scaleValue*4));//magic number
		
		//The layout manager used by JScrollPane. JScrollPaneLayout is responsible for nine components: 
		//a viewport, two scrollbars, a row header, a column header, and four "corner" components.
		getScrollPane().setLayout(new ScrollPaneLayout());
		//the m_ScrollPanel only display after concordance button is clicked
		getScrollPane().setVisible(false); 
	}
	
	/**
	 * Use this method to access m_ConcordanceButton
	 * @return m_ConcordanceButton
	 */
	public JButton getConcordanceButton() {
		return m_ConcordanceButton;
	}

	/**
	 * Use this method to create and set m_ConcordanceButton
	 * @param concordanceButton
	 */
	public void setConcordanceButton(JButton concordanceButton) {
		m_ConcordanceButton = concordanceButton;
	}

	/**
	 * Use this method to access m_visuallizationPanel
	 * @return m_visuallizationPanel
	 */
	public JPanel getM_visuallizationPanel() {
		return m_visuallizationPanel;
	}

	/**
	 * Use this method to create and set m_visuallizationPanel
	 * @param m_visuallizationPanel
	 */
	public void setM_visuallizationPanel(JPanel m_visuallizationPanel) {
		this.m_visuallizationPanel = m_visuallizationPanel;
		getM_visuallizationPanel().setVisible(true);
		getM_visuallizationPanel().setBackground(Color.WHITE);
	}

	/**
	 * Use this method to access m_buttonPanel
	 * @return m_buttonPanel
	 */
	public JPanel getM_UserOptionPanel() {
		return m_UserOptionPanel;
	}

	/**
	 * Use this method to create and set m_buttonPanel
	 * @param buttonPanel
	 */
	public void setM_UserOptionPanel(JPanel buttonPanel) {
		this.m_UserOptionPanel = buttonPanel;
		getM_UserOptionPanel().setVisible(true);
		getM_UserOptionPanel().setBackground(Color.WHITE);
	}
	
	/**
	 * Use this method to access m_ColorLegendPanel
	 * @return m_ColorLegendPanel
	 */
	public ColorLegendPanel getM_ColorLegendPanel() {
		return m_ColorLegendPanel;
	}

	/**
	 * Use this method to create and set m_ColorLegendPanel
	 * @param colorLegendPanel
	 * @param dataReader
	 */
	public void setM_ColorLegendPanel(ColorLegendPanel colorLegendPanel, DataReader dataReader) {
		this.m_ColorLegendPanel = colorLegendPanel;
		getM_ColorLegendPanel().setColorLegend(dataReader.sortColorIndex(dataReader.getM_frequencyColorIndex()));
	}

	/**
	 * Use this method to access m_VersionList
	 * @return m_VersionList
	 * @throws Exception
	 */
	public List<Version> getVersionList() throws Exception{
		DataReader dataReader=new DataReader();
		m_VersionList=dataReader.readAllFile();
		
		/** pass DataReader object to setColorLegend method */
		setM_ColorLegendPanel(new ColorLegendPanel(), dataReader); 
		return m_VersionList;
	}

	/**
	 * Use this method to access m_ConcordancePanel
	 * @return m_ConcordancePanel
	 */
	public ConcordancePanel getConcordancePanel() {
		return m_ConcordancePanel;
	}

	/**
	 * Use this method to create and set m_ConcordancePanel
	 * @param concordancePanel
	 */
	public void setConcordancePanel(ConcordancePanel concordancePanel) {
		this.m_ConcordancePanel = concordancePanel;
		getConcordancePanel().setBackground(Color.white);
		getConcordancePanel().setVisible(true);
		getConcordancePanel().setPreferredSize(new Dimension(CONCORDANCE_PANEL_WIDTH,CONCORDANCE_PANEL_HEIGHT));
		
	}

	public static void main(String[] args) throws Exception{
		
		TranslationVisualization transVis=new TranslationVisualization();
		
		//layer 1 - Concordance Frame
		transVis.setConcordanceFrame(new JFrame("Translation Visualization"));
		
		//layer 2 - Concordance Panel
		transVis.setConcordancePanel(new ConcordancePanel(transVis.getVersionList()));
		
		//layer 3 - Visualization Panel
		transVis.setM_visuallizationPanel(new JPanel());
		
		//layer 3 - User-option Panel
		transVis.setM_UserOptionPanel(new JPanel());
		
		//layer 4 - Scroll Panel
		transVis.setScrollPane(new JScrollPane(transVis.getConcordancePanel()));	
		transVis.getM_visuallizationPanel().add( transVis.getScrollPane());
		
		//concordance button
		transVis.setConcordanceButton(new JButton("Concordances"));
		transVis.getM_UserOptionPanel().add(transVis.getConcordanceButton());
		transVis.getConcordanceButton().addActionListener(new ActionListener(){
			@Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button pressed");
                transVis.getScrollPane().setVisible(true);
                transVis.getConcordanceFrame().revalidate(); 
            }
		});
		
		//concordance slider
		int min=10; //minimum value
		int max=100; //maximum value
		int initialVar=20; //initial value
		//JSlider(int orientation, int min, int max, int value)
		//JSlider(orientation, minimum value, maximum value, and initial value)
		transVis.setM_ConcordanceSlider(new JSlider(SwingConstants.HORIZONTAL, min, max, initialVar));
		transVis.getM_UserOptionPanel().add(transVis.getM_ConcordanceSlider());
		transVis.getM_ConcordanceSlider().addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent event) {
				m_scaleValue=transVis.getM_ConcordanceSlider().getValue();
				transVis.getConcordancePanel().setZoomValue(m_scaleValue);
				transVis.getScrollPane().setVisible(true);
				transVis.getConcordanceFrame().revalidate(); 
			}
		});
		
		transVis.getM_visuallizationPanel().add( transVis.getM_ColorLegendPanel());
		
		// set layout for visualization panel
		GridBagLayout panelLayout = new GridBagLayout();
		transVis.getM_visuallizationPanel().setLayout(panelLayout);
		GridBagConstraints constraint=new GridBagConstraints();
		constraint.fill=GridBagConstraints.BOTH;
		constraint.gridwidth=4;
		constraint.weightx=1;
		constraint.weighty=0;
		panelLayout.setConstraints(transVis.getScrollPane(), constraint);
		constraint.gridwidth=1;
		constraint.weightx=0;
		constraint.weighty=1;
		panelLayout.setConstraints(transVis.getM_ColorLegendPanel(), constraint);
		
		GridBagLayout layout = new GridBagLayout();
		transVis.getConcordanceFrame().setLayout(layout);
		GridBagConstraints s=new GridBagConstraints();
		s.fill=GridBagConstraints.BOTH;
		s.gridwidth=1;
		s.weightx=0;
		s.weighty=0;
		layout.setConstraints(transVis.getM_UserOptionPanel(), s);
		s.gridwidth=5;
		s.weightx=1;
		s.weighty=1;
		layout.setConstraints(transVis.getM_visuallizationPanel(), s);
		

		transVis.getConcordanceFrame().add(transVis.getM_UserOptionPanel());
		transVis.getConcordanceFrame().add(transVis.getM_visuallizationPanel());
		transVis.getConcordanceFrame().setVisible(true);
		transVis.getConcordanceFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		transVis.getConcordanceFrame().setLocationRelativeTo(null);
		transVis.getConcordanceFrame().setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
		
	}
}