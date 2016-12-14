package gap.client.UI;

import gap.client.ui.tableUI.Cost_profitUI.ArcGraphPanel;
import gap.client.vo.Cost_ProfitListVO;

import javax.swing.JFrame;

public class TableUITest {

	public static void main(String[] args) {

		JFrame frame = new JFrame("test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 500);
		
		Cost_ProfitListVO vo = new Cost_ProfitListVO(1000, 500, 1, 500);
		ArcGraphPanel panel = new ArcGraphPanel(vo);
		
		frame.add(panel);
		frame.setVisible(true);
		
		panel.startAnimation();

	}
	
}
