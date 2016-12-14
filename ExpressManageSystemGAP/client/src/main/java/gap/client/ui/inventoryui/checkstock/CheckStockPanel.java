package gap.client.ui.inventoryui.checkstock;

import gap.client.blcontroller.InventoryController;
import gap.client.ui.BaseComponents.MainFrame;
import gap.client.ui.BaseComponents.MainPanel;
import gap.client.ui.UITools.ColorAndFonts;
import gap.client.ui.UITools.Default;
import gap.client.ui.UITools.SwingConsole;
import gap.client.ui.gapcomponents.ChooseButton;
import gap.client.ui.gapcomponents.GAPJScrollPane;
import gap.client.util.LocalInfo;
import gap.client.util.MessageType;
import gap.client.util.WareHouseSize;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

public class CheckStockPanel extends MainPanel {
	StockCheckButtonArea buttonArea;
	public ListPanel list;
	ChoosePanel choose;
	int shelves;
	GridBagLayout gb;
	GridBagConstraints gcons;
	MainFrame mainFrame;
	CheckStockPanel self;

	public CheckStockPanel(MainFrame frame) {
		super(frame);
		// TODO Auto-generated constructor stub
		mainFrame = frame;
		shelves = WareHouseSize.SHELF.getSize();
		choose = new ChoosePanel();
		buttonArea = new StockCheckButtonArea();
		shelves = WareHouseSize.SHELF.getSize();
		
		initial();
		
		gb = new GridBagLayout();
		gcons = new GridBagConstraints();
		gcons.fill = GridBagConstraints.BOTH;
		setLayout(gb);
		
		reLayout();
		
		choose.car.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				list = new ListPanel(shelves, LocalInfo.ins_id + "1");
				choose.flex.toNomal();
				choose.train.toNomal();
				choose.plane.toNomal();
				
				reLayout();
			}

		});

		choose.train.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				list = new ListPanel(shelves, LocalInfo.ins_id + "2");

				choose.flex.toNomal();
				choose.car.toNomal();
				choose.plane.toNomal();
				reLayout();
			}
		});

		choose.plane.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				list = new ListPanel(shelves, LocalInfo.ins_id + "3");
				choose.flex.toNomal();
				choose.train.toNomal();
				choose.car.toNomal();
				reLayout();

			}
		});
		
		choose.flex.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				list = new ListPanel(shelves, LocalInfo.ins_id + "0");
				choose.car.toNomal();
				choose.train.toNomal();
				choose.plane.toNomal();
				reLayout();

			}
		});
		
		buttonArea.confirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mainFrame.load(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						refresh();
						MainFrame.setMessage("统计成功", MessageType.succeed, 2000);
					}
				});
			}
		});
		
		buttonArea.export.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				JFileChooser jfilech = new JFileChooser();
				jfilech.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				jfilech.showOpenDialog(null);
				File f = jfilech.getSelectedFile();
				if(f!=null){
					final String path = f.getAbsolutePath();
					System.out.println(path);
					mainFrame.load(new Runnable() {
						public void run() {
							InventoryController.exportExcel(path);
						}
					});
					
					MainFrame.setMessage("报表导出成功", MessageType.succeed, 2000);
				}
				
				
			}
		});

	}
	

	
	public void setButtonNomal(ChooseButton button){
		button.setBackground(Color.white);
		button.setForeground(ColorAndFonts.blue);
		button.clicked = false;
		
	}
	
	public void reLayout(){
		removeAll();
		SwingConsole.addComponent(gb, gcons, this, choose, 0, 0, 1, 1, 1, 0);
		
		GAPJScrollPane js = new GAPJScrollPane(list);
		js.setPreferredSize(new Dimension(Default.PANEL_WIDTH,400));
		
		SwingConsole.addComponent(gb, gcons, this, js, 0, 1, 1, 1, 1, 1);
		SwingConsole
				.addComponent(gb, gcons, this, buttonArea, 0, 3, 1, 1, 1, 0);
		mainFrame.validate();

	}
	
	public void initial(){
		choose.car.setSelected();
		choose.flex.toNomal();
		choose.train.toNomal();
		choose.plane.toNomal();
		list = new ListPanel(shelves, LocalInfo.ins_id + "1");
	}

	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		initial();
		reLayout();
	}

}
