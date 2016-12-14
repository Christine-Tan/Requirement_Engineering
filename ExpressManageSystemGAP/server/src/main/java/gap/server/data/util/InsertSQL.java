package gap.server.data.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author YangYanfei 生成插入SQL语句的类
 */
public class InsertSQL {
	List<String> fields;
	List<String> values;
	String tableName;

	public InsertSQL(String tableName) {
		fields = new ArrayList<String>();
		values = new ArrayList<String>();
		this.tableName = tableName;
	}

	public void add(String field, Object value) {
		fields.add(field);
		String va;
		if (value == null)
			va = "null";
		else if ((value instanceof String) || (value instanceof Enum))
			va = "'" + value.toString() + "'";
		else
			va = value.toString();
		values.add(va);
	}

	public String createSQL() {
		String sql = "INSERT INTO " + tableName + " (";
		for (int i = 0; i < fields.size(); i++) {
			sql += fields.get(i);
			if (i != fields.size() - 1)
				sql += ",";
			else
				sql += ") ";
		}
		sql += "VALUES (";
		for (int i = 0; i < values.size(); i++) {
			sql += values.get(i);
			if (i != values.size() - 1)
				sql += ",";
			else
				sql += ");";
		}
		return sql;
	}

	public void clear() {
		fields.clear();
		values.clear();
	}
}
