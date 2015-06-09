package com.swust.kelab;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MSSQLTest {
	private Connection conn;
	private Statement stmt;

	// private String url =
	// "jdbc:jtds:sqlserver://127.0.0.1:1433;;DatabaseName=LocationDataBase2;";
	private String url = "jdbc:jtds:sqlserver://10.10.4.107:1433;DatabaseName=LocationDataBase;instance=MSSQLSERVERNEW";
	private String classforname = "net.sourceforge.jtds.jdbc.Driver";
	private String uid = "sa";
	private String pwd = "123456";

	public Statement getStatementSubEduLogV2() throws ClassNotFoundException,
			SQLException, InstantiationException, IllegalAccessException {
		Class.forName(classforname).newInstance();
		if (conn == null || conn.isClosed())
			conn = DriverManager.getConnection(url, uid, pwd);

		stmt = conn.createStatement();

		return stmt;
	}

	public static void main(String[] args) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException, SQLException {
		MSSQLTest sqlserverTest = new MSSQLTest();
		System.out.println(sqlserverTest.getStatementSubEduLogV2());

		Statement statement = sqlserverTest.getStatementSubEduLogV2();
		ResultSet set = statement
		// .executeQuery("select top 10 LocationNewID, IMEI, IMSI, PosTime, Longitude, Latitude, LocType, TransferFlag, LongitudeBaidu, LatitudeBaidu, AreaID, SrcIP, DstIP, PhoneType, AppType from LocationNew where LocationNewID not in (select top 100 LocationNewID from LocationNew)");
				.executeQuery("select count(*) from LocationNew");
		// BigInteger LocationID = null;
		// while (set.next()) {
		// LocationID = BigInteger.valueOf(set.getLong("LocationNewID"));
		// System.out.println("LocationNewID:" + LocationID.toString());
		// }
		set.next();
		System.out.println("Total: " + set.getInt(1));

		set.close();
		statement.close();
	}

}
