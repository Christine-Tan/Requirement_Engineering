package gap.client.ui.excelUI;

import gap.client.ui.BaseComponents.MainFrame;

import java.awt.FileDialog;

public class FileChooser {

	public static String chooseFile(MainFrame mainFrame){
		FileDialog fileDialog = new FileDialog(mainFrame, "excel路径选择", FileDialog.SAVE);
		fileDialog.setVisible(true);
		String name = fileDialog.getDirectory()+fileDialog.getFile()+".xls";
		System.out.println(name);
		return name;
	}
}
