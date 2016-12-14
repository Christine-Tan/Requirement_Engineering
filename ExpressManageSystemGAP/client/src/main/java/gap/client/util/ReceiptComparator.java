package gap.client.util;

import gap.common.ListInterface.Receipt;

import java.util.Calendar;
import java.util.Comparator;

public class ReceiptComparator implements Comparator<Receipt>{

	@Override
	public int compare(Receipt one, Receipt other) {
		
		Calendar oneDate = one.getDate();
		Calendar otherDate = other.getDate();
		
		int result = (-1)*oneDate.compareTo(otherDate);
		return result;
	}

}
