//package com.gofun.ms.feign;
//
//import java.util.List;
//
//import com.alibaba.druid.sql.ast.SQLStatement;
//import com.alibaba.druid.sql.parser.SQLParserUtils;
//import com.alibaba.druid.sql.parser.SQLStatementParser;
//import com.alibaba.druid.sql.visitor.ParameterizedOutputVisitorUtils;
//import com.alibaba.druid.sql.visitor.ParameterizedVisitor;
//
///**
// * 清洗sql
// * @author tonfay
// */
//public class Test {
//	public static void main(String[] args) {
//		String sql = "SELECT PAYREFUNDID, TOTALMONEY, REFUNDMONEY, THIRDPAYNO, REFUNDNO, STATUS, BUSINESSTYPE, CREATETIME,CAUSE, PAYTYPE, TRADESTATUS,PAYREFUNDTYPE, t.userId,t.refundSuccessTime FROM pay_refund bb left join a aa on a.w =bb.w  t where 1=1 and BUSINESSTYPE in ( 9 , 12 ) AND (t.STATUS = 1 or (t.STATUS=4 and t.tradeStatus='TRADE_FINISHED')) ORDER BY createTime DESC limit 0,20";
//		String a = parameterize(sql, "mysql");
//		System.out.println(a);
//	}
//	
//	public static String parameterize(String sql, String dbType) {
//        SQLStatementParser parser = SQLParserUtils.createSQLStatementParser(sql, dbType);
//        List<SQLStatement> statementList = parser.parseStatementList();
//        if (statementList.size() == 0) {
//            return sql;
//        }
//
//        StringBuilder out = new StringBuilder();
//        ParameterizedVisitor visitor = ParameterizedOutputVisitorUtils.createParameterizedOutputVisitor(out, dbType);
//
//        for (int i = 0; i < statementList.size(); i++) {
//            SQLStatement stmt = statementList.get(i);
//            stmt.accept(visitor);
//        }
//
//        if (visitor.getReplaceCount() == 0 && !parser.getLexer().hasComment()) {
//            return sql;
//        }
//
//        return out.toString().replaceAll("\n", " ").replaceAll("\t", " ");
//    }
//}
