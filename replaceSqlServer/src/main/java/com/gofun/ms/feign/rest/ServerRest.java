package com.gofun.ms.feign.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.parser.SQLParserUtils;
import com.alibaba.druid.sql.parser.SQLStatementParser;
import com.alibaba.druid.sql.visitor.ParameterizedOutputVisitorUtils;
import com.alibaba.druid.sql.visitor.ParameterizedVisitor;

@RestController
@RequestMapping("/server")
public class ServerRest {
	public Logger logger = LoggerFactory.getLogger(this.getClass());
	@RequestMapping(value = "/replace", method = {RequestMethod.GET,RequestMethod.POST})
	public String replace(
			@RequestParam(name="sql",required =true) String sql,
			@RequestParam(name="dbtype",required = false,defaultValue="mysql") String dbtype){
		logger.info("sql:[{}],,dbType:[{}]",sql,dbtype);
		String res = null;
		try {
			res = parameterize(sql, dbtype);
		} catch (Exception e) {
			e.printStackTrace();
			return "sql error";
		}
		return res;
	}
	
	@RequestMapping(value = "/replaces", method = {RequestMethod.GET,RequestMethod.POST})
	public String replaces(
			@RequestBody(required =true) String sql[],
			@RequestParam(name="dbtype",required = false,defaultValue="mysql") String dbtype){
		logger.info("sql:[{}],,dbType:[{}]",sql,dbtype);
		StringBuffer res = new StringBuffer();
		try {
			for (String sqlStr : sql) {
				res.append(parameterize(sqlStr, dbtype));
				res.append(";");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "sql error";
		}
		return res.toString();
	}
	
	public static String parameterize(String sql, String dbType) {
        SQLStatementParser parser = SQLParserUtils.createSQLStatementParser(sql, dbType);
        List<SQLStatement> statementList = parser.parseStatementList();
        if (statementList.size() == 0) {
            return sql;
        }

        StringBuilder out = new StringBuilder();
        ParameterizedVisitor visitor = ParameterizedOutputVisitorUtils.createParameterizedOutputVisitor(out, dbType);

        for (int i = 0; i < statementList.size(); i++) {
            SQLStatement stmt = statementList.get(i);
            stmt.accept(visitor);
        }

        if (visitor.getReplaceCount() == 0 && !parser.getLexer().hasComment()) {
            return sql;
        }

        return out.toString().replaceAll("\n", " ").replaceAll("\t", " ");
    }
}
