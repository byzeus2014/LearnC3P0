package com.zhuby.c3p0;
import com.zhuby.c3p0.connimpl.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by zhuby on 2016/5/4.
 */
public class QueryHandler {

    public String queryOne( ){
        Connection conn = ConnectionPool.getInstance().getConnection();
        String sysdate = null;
        String sql = "select sysdate from dual";
        PreparedStatement pstat = null;
        ResultSet rs = null;

        try {
            pstat = conn.prepareStatement(sql);
            rs = pstat.executeQuery();
            while(rs.next()){
                sysdate=rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if(rs==null){
                    rs.close();
                }
                if(pstat==null){
                    pstat.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return sysdate;
    }
}
