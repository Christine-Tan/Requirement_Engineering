package gap.server.data.util;

import java.util.ArrayList;
import java.util.List;

public class SelectSQL {
	List<String> select_column;
	List<String> constraint_field;
	List<String> constraint_value;
	String tableName;

	public SelectSQL(String tableName) {
		this.tableName = tableName;
		select_column = new ArrayList<String>();
		constraint_field = new ArrayList<String>();
		constraint_value = new ArrayList<String>();
	}

	public void addColumn(String field) {
		select_column.add(field);
	}

	public void addConstraint(String field, Object value) {
		constraint_field.add(field);
		String va;
		if (!(value instanceof Integer))
			va = "'" + value.toString() + "'";
		else
			va = value.toString();
		constraint_value.add(va);
	}

	public String createSQL() {
		String sql = "SELECT ";
		if (select_column.isEmpty()) {
			sql += "*";
		} else {
			for (int i = 0; i < select_column.size(); i++) {
				sql += select_column.get(i);
				if (i != select_column.size() - 1)
					sql += ",";
			}
		}
		sql += " FROM ";
		sql += tableName;
		if (!constraint_field.isEmpty()) {
			sql += " WHERE ";
			for (int i = 0; i < constraint_field.size(); i++) {
				sql += constraint_field.get(i) + "=" + constraint_value.get(i);
				if (i != constraint_field.size() - 1)
					sql += " AND ";
			}
		}
		sql += ";";
		return sql;
	}
}
