package gap.client.ui.BaseComponents.FrameInitialler;

import gap.client.ui.BaseComponents.MainFrame;
import gap.client.util.MessageType;

public abstract class FrameInitialler {
	
	public void initialFrame(MainFrame mainFrame){
		specificInitial(mainFrame);
		MainFrame.setMessage("登录成功", MessageType.succeed, 3000);
	}
	
	protected abstract void specificInitial(MainFrame mainFrame);
}
