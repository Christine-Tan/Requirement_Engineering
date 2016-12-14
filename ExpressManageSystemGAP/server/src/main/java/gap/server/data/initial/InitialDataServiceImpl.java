package gap.server.data.initial;

import gap.common.dataservice.initialdata.InitialDataService;
import gap.common.po.InitialHistoryPO;
import gap.common.util.ResultMessage;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;


public class InitialDataServiceImpl extends UnicastRemoteObject implements InitialDataService{

	protected InitialDataServiceImpl() throws RemoteException {
		super();
	}

	private static InitialDataServiceImpl initialDataServiceImpl;
	
	public static InitialDataService getInstance() throws RemoteException{
		if(initialDataServiceImpl==null){
			initialDataServiceImpl = new InitialDataServiceImpl();
		}
		return initialDataServiceImpl;
	}
	
	@Override
	public ResultMessage addInitial(InitialHistoryPO initialPO) throws RemoteException {
		InitialAdder adder = new InitialAdder();
		try{
			adder.addInitial(initialPO);
			return ResultMessage.SUCCEED;
		}catch(Exception e){
			e.printStackTrace();
			return ResultMessage.FAILED;
		}
	}

	@Override
	public List<InitialHistoryPO> getHistory() throws RemoteException {
		InitialGetter getter = new InitialGetter();
		ArrayList<InitialHistoryPO> pos = getter.getHistoryList();
		return pos;
	}


}
