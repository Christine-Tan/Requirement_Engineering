package gap.client.ui.AccountUI.ComponentBehave;

import gap.client.ui.AccountUI.AddBox;

public class BoxCancelBehave implements ComponentBehave{
	AddBox box;
	public BoxCancelBehave(AddBox box) {
		this.box = box;
	}
	@Override
	public void behave() {
		// TODO Auto-generated method stub
		box.cancel();
	}

}
