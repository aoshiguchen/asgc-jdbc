package com.github.aoshiguchen.framework.jdbc.entity;

import com.github.aoshiguchen.framework.jdbc.util.StringUtil;
import static com.github.aoshiguchen.framework.jdbc.util.Consts.*;

public class BigHumpUnderlineNameMapping implements NameMapping{
	
	@Override
	public String codeToDb(String name) {
		if(StringUtil.isEmpty(name)){
			return "";
		}
		
		StringBuffer sb = new StringBuffer(name.length());
		for(int i = 0;i < name.length();i++){
			
			char c = name.charAt(i);
			
			if(i == 0){
				sb.append(Character.toLowerCase(c));
			}else{
				
				if(Character.isUpperCase(c)){
					sb.append(UNDERLINE);
					sb.append(Character.toLowerCase(c));
				}else{
					sb.append(c);
				}
				
			}
		}
		
		return sb.toString();
	}

	@Override
	public String dbToCode(String name) {
		if(StringUtil.isEmpty(name)){
			return "";
		}
		
		boolean flag = true;
		StringBuffer sb = new StringBuffer(name.length());
		for(int i = 0;i < name.length();i++){
			char c = name.charAt(i);
			
			if(UNDERLINE != c){
				if(flag){
					sb.append(Character.toUpperCase(c));
					flag = false;
				}else{
					sb.append(c);
				}
			}else{
				flag = true;
			}
		}
		
		return sb.toString();
	}

}
