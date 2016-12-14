package gap.stub_driver.blservice.initial;

import gap.client.blservice.initialblservice.InitialBlService;
import gap.client.vo.InitialVO;

public class Initial_driver {

	public void drive(InitialBlService stub){
		InitialVO vo = stub.getInitialList();
		System.out.println("get initial list");
		
		stub.submitInitial(vo);
		System.out.println("set initialVO");
		
		stub.getInitialHistory();
		System.out.println("get the History");
		
		
	}
	
	public static void main(String[] args) {
		new Initial_driver().drive(new Initial_stub());
	}
	
}
