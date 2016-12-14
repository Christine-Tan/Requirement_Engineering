package gap.common.util;

public enum UserType {
	DELIVERY, BUSSINESSCLERK, CENTERCLERK, INVENTORY, ACCOUNTER, MANAGER, ADMINISTRATOR;
	public static UserType getUserType(String str) {
		switch (str) {
		case "DELIVERY":
			return DELIVERY;
		case "BUSSINESSCLERK":
			return BUSSINESSCLERK;
		case "CENTERCLERK":
			return CENTERCLERK;
		case "INVENTORY":
			return INVENTORY;
		case "ACCOUNTER":
			return ACCOUNTER;
		case "MANAGER":
			return MANAGER;
		case "ADMINISTRATOR":
			return ADMINISTRATOR;
		default:
			return null;
		}
	}
}
