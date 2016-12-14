package gap.server.data.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 生成更新SQL语句的类
 * @author YangYanfei
 *
 */
public class UpdateSQL {
	List<String> fields;
	List<String> values;
	String keyField;
	Object keyValue;
	String tableName;
	boolean seted;

	public UpdateSQL(String tableName) {
		fields = new ArrayList<String>();
		values = new ArrayList<String>();
		this.tableName = tableName;
	}

	public void add(String field, Object value) {
		fields.add(field);
		String va;
		if (value == null) {
			values.add("null");
			return;
		}
		if ((value instanceof String) || (value instanceof Enum))
			va = "'" + value.toString() + "'";
		else
			va = value.toString();
		values.add(va);
	}

	public void setKey(String keyFiele, Object keyValue) {
		this.keyField = keyFiele;
		this.keyValue = keyValue;
		seted = true;
	}

	public String createSQL() throws Exception {
		if (!seted)
			throw new Exception("生成语句错误");
		String sql = "UPDATE " + tableName + " set ";
		for (int i = 0; i < fields.size(); i++) {
			sql += fields.get(i) + "=" + values.get(i);
			if (i != fields.size() - 1)
				sql += ",";
			else
				sql += " ";
		}
		sql += "WHERE " + keyField + "='" + keyValue + "';";
		return sql;
	}

	public void clear() {
		fields.clear();
		values.clear();
		seted = false;
	}
}
