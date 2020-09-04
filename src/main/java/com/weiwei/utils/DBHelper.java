package com.weiwei.utils;//package com.utils;
//
//import com.mchange.v2.c3p0.ComboPooledDataSource;
//
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.SQLException;
//
//public class DBHelper {
//
//    public static DBHelper dbHelper;
//    private Connection conn;
////    private Properties properties;
//
//    private ComboPooledDataSource dataSource;
//
//    //连接配置文件
//    private DBHelper(){
//        dataSource = new ComboPooledDataSource("mysql");
//    }
//
//    public DataSource getDataSource(){
//        return dataSource;
//    }
//
//
//    //单例模式
//    public static DBHelper getDbHelper(){
//       if ( dbHelper == null){
//           dbHelper = new DBHelper();
//       }
//           return dbHelper;
//    }
//
//    //获取配置文件的数据
//    public Connection getConn(){
//        try {
//            conn = dataSource.getConnection();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return conn;
//    }
//
//    public void closeCon(Connection conn){
//        try {
//            if (conn!=null){
//                conn.close();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    //关闭连接方法
////    public static void closeCon(Statement state, Connection con, ResultSet rset) {
////        try
////        {
////            if(null!=rset) {
////                rset.close();
////            }
////            if(null!=state) {
////                state.close();
////            }
////            if(null!=con) {
////                con.close();
////            }
////        } catch (SQLException e)
////        {
////            e.printStackTrace();
////        }
////    }
//
//}
