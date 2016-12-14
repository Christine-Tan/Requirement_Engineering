package gap.client.ui.BaseComponents;

import gap.client.ui.UITools.ColorAndFonts;
import gap.client.ui.UITools.RenderSetter;
import gap.client.util.LocalInfo;
import gap.common.util.Gender;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class UserBox extends JLabel {

	String job;
	String name;
	String number;
	Gender gender;
	Image photo;

	public UserBox() {
		// TODO Auto-generated constructor stub
		this.job = LocalInfo.getJob();
		this.name = LocalInfo.getName();
		this.number = LocalInfo.getUserID();
		this.gender = LocalInfo.getGendar();

		setBounds(0, 5, 220, 125);

		String photoURL = "images/";
		switch (gender) {
		case MALE:
			photo = new ImageIcon(photoURL + "manPhoto.png").getImage();
			break;
		case FEMALE:
			photo = new ImageIcon(photoURL + "womanPhoto.png").getImage();
			break;
		}

	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		Graphics2D graphics2d = RenderSetter.OpenRender(g);

		int startX = 105;
		int startY = 30;
		int gap = 35;

		if (photo != null) {
			graphics2d.drawImage(photo, 2, 0, null);
		}
		graphics2d.setFont(ColorAndFonts.getChinese(19));
		graphics2d.drawString(job, startX, startY);
		graphics2d.drawString(name, startX, startY + gap);
		graphics2d.drawString(number, startX, startY + 2 * gap);

	}

}
