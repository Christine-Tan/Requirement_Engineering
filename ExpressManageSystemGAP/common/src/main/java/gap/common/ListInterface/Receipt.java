package gap.common.ListInterface;

import java.util.Calendar;

public interface Receipt extends Order {
	public Calendar getDate();
	public double getTotal();
}
