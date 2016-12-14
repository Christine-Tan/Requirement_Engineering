package gap.client.ui.bussinessui.arrivedorder;

import gap.client.ui.UITools.Default;
import gap.client.ui.gapcomponents.GAPLabel;
import gap.client.ui.gapcomponents.GAPTextField;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class CommentPanel extends JPanel {
	JLabel comment;
	GAPTextField comment_text;

	public CommentPanel() {
		super();
		setPreferredSize(new Dimension(Default.PANEL_WIDTH, 50));
		setOpaque(false);
		comment = new GAPLabel("备注：");
		comment_text = new GAPTextField(30);
		setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		add(comment);
		add(comment_text);
	}

	public String getComment() {
		return comment_text.getText();
	}
}
