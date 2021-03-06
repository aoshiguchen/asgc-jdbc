package com.github.aoshiguchen.framework.jdbc.jdbctemplate;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public abstract class PreparedStatementCallback<T> implements IJdbcCallback<T>{
	
	private Logger logger = Logger.getLogger(PreparedStatementCallback.class);
	
	public abstract Object[] getParams();
	public abstract String getSql();
	public abstract T execute(PreparedStatement ps);
	public abstract Connection getConnection();
	
	@Override
	public T execute() {
		PreparedStatement pstm = null;
		Object[] params = this.getParams();
		Connection conn = getConnection();
		
		T res = null;
		try {
			
			logger.info("sql:" + this.getSql());
			
			StringBuffer sb = new StringBuffer();
			for(Object o : params){
				sb.append(o.toString()).append(",");
			}
			
			if(sb.length() > 0 && sb.charAt(sb.length() - 1) == ','){
				sb.deleteCharAt(sb.length() - 1);
			}
			
			if(sb.length() > 0){
				logger.info("params:" + sb.toString());
			}

			pstm = conn.prepareStatement(this.getSql());
			
			for(int i = 0;i < params.length;i++){
				pstm.setObject(i + 1, params[i]);
			}
			
			res = this.execute(pstm);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return res;
	}
}
