package gap.client.ui.BaseComponents.FrameInitialler;

import gap.common.util.UserType;

import java.util.HashMap;

public class InitiallerFactory {
	static HashMap<UserType, FrameInitialler> initiallerMap;
	
	static{
		initiallerMap = new HashMap<>();
		initiallerMap.put(UserType.ACCOUNTER, 		new AccountorInitialler());
		initiallerMap.put(UserType.ADMINISTRATOR,	new AdminInitialler());
		initiallerMap.put(UserType.CENTERCLERK, 	new CenterClerkInitaller());
		initiallerMap.put(UserType.INVENTORY, 		new InventoryInitailler());
		initiallerMap.put(UserType.MANAGER, 		new ManagerInitialler());
		initiallerMap.put(UserType.DELIVERY,		new DeliveryInitialler());
		initiallerMap.put(UserType.BUSSINESSCLERK,  new BusinessInitialler());
	}
	
	public static FrameInitialler getInitialler(UserType type){
		return initiallerMap.get(type);
	}

}
