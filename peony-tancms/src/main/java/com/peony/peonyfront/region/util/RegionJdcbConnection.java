package com.peony.peonyfront.region.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.peony.peonyfront.topic.model.Topic;
import com.peony.peonyfront.topickeywords.model.TopicKeywords;

public class RegionJdcbConnection {
    SimpleDateFormat            datatime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private Log                 logger   = LogFactory.getLog(RegionJdcbConnection.class);

    private static final String URL      = "jdbc:mysql://119.254.110.32:8096/peony?" + "user=peony&password=peony2014&useUnicode=true&characterEncoding=UTF8";

    public int InsertTopic(Topic topic) {
        Connection conn = null;
        String sql;
        // MySQL的JDBC URL编写方式：jdbc:mysql://主机名称：连接端口/数据库的名称?参数=值
        // 避免中文乱码要指定useUnicode和characterEncoding
        // 执行数据库操作之前要在数据库管理系统上创建一个数据库，名字自己定，
        // 下面语句之前就要先创建javademo数据库
        int result = 0;
        try {

            Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动

            // logger.info("成功加载MySQL驱动程序");
            conn = DriverManager.getConnection(URL);
            // Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
            Statement stmt = conn.createStatement();

            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            // dateformat.format(new Date());

            sql = " insert into pe_t_topic (id, userId, name, abstruct, state, start_time, end_time, create_time, update_time) " + "values ('" + topic.getId() + "', '" + topic.getUserid() + "','" + topic.getName() + "', '" + topic.getAbstruct() + "', '" + topic.getState() + "', '" + datatime.format(topic.getStartTime()) + "', '" + datatime.format(topic.getEndTime()) + "', '" + datatime.format(topic.getCreateTime()) + "', '" + datatime.format(topic.getUpdateTime()) + "')";
            logger.info("执行的insert语句 = " + sql);
            result = stmt.executeUpdate(sql);
        } catch (Exception e) {
            // logger.info("数据库操作失败");
            e.printStackTrace();
        } finally {
            if (null != conn) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public int DeleteTopic(Topic topic) {
        Connection conn = null;
        String sql;
        int result = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
            conn = DriverManager.getConnection(URL);
            // Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
            Statement stmt = conn.createStatement();
            sql = "update pe_t_topic SET state = 0, update_time = '" + datatime.format(topic.getUpdateTime()) + "' where id = " + topic.getId();
            logger.info("执行的update语句 = " + sql);
            result = stmt.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != conn) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public int UpdateTopic(Topic topic) {
        Connection conn = null;
        String sql;
        int result = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
            conn = DriverManager.getConnection(URL);
            // Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
            Statement stmt = conn.createStatement();
            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sql = "update pe_t_topic SET name = '" + topic.getName() + "', abstruct = '" + topic.getAbstruct() + "', update_time = '" + dateformat.format(topic.getUpdateTime()) + "' where id = '" + topic.getId() + "'";
            logger.info("执行的update语句 = " + sql);
            result = stmt.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != conn) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public int InsertTopicKeywords(TopicKeywords topicKeywords) {
        Connection conn = null;
        String sql;
        int result = 0;
        try {

            Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动

            conn = DriverManager.getConnection(URL);
            Statement stmt = conn.createStatement();

            sql = "insert into pe_t_topic_keywords ( id, topicId, name, keywords,rejectflag )" + " values ( '" + topicKeywords.getId() + "','" + topicKeywords.getTopicid() + "', '" + topicKeywords.getName() + "', '" + topicKeywords.getKeywords() + "','" + topicKeywords.getRejectflag() + "' )";
            logger.info("执行的insert语句 = " + sql);
            result = stmt.executeUpdate(sql);
        } catch (Exception e) {
            // logger.info("数据库操作失败");
            e.printStackTrace();
        } finally {
            if (null != conn) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public static int SelectRegion() {
        Connection conn = null;
        String sql;
        int result = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动

            conn = DriverManager.getConnection(URL);
            Statement stmt = conn.createStatement();
            Statement stmt1 = conn.createStatement();
            sql = "select regionId,regionName,regionAbbr,regionLevel from wdyq_region ";

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                // System.out.println(rs.getString("regionId"));
                String value = "";
                String regionId = "";
                if (rs.getString("regionName").equals(rs.getString("regionAbbr"))) {
                    value = rs.getString("regionName");
                } else {
                    value = rs.getString("regionName") + " " + rs.getString("regionAbbr");
                }
                if (rs.getString("regionLevel").equals("1")) {
                    regionId = rs.getString("regionId").substring(rs.getString("regionId").length() - 2, rs.getString("regionId").length());
                } else if (rs.getString("regionLevel").equals("2")) {
                    regionId = rs.getString("regionId").substring(rs.getString("regionId").length() - 4, rs.getString("regionId").length());
                } else {
                    regionId = rs.getString("regionId").substring(rs.getString("regionId").length() - 6, rs.getString("regionId").length());
                }
                sql = "insert into wdyq_region_keywords ( regionId, regionwords) values ('" + regionId + "','" + value + "')";
                // System.out.println(sql);
                stmt1.executeUpdate(sql);
            }
        } catch (Exception e) {
            // logger.info("数据库操作失败");
            e.printStackTrace();
        } finally {
            if (null != conn) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public static String SelectRegionValue(String regionId) {
        Connection conn = null;
        String sql;
        String result = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动

            conn = DriverManager.getConnection(URL);
            Statement stmt = conn.createStatement();
            Statement stmt1 = conn.createStatement();
            sql = "select regionwords from wdyq_region_keywords where regionId LIKE '" + regionId + "%' ";

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                if ("".equals(result)) {
                    result = rs.getString("regionwords");
                } else {
                    result = result + " " + rs.getString("regionwords");
                }
            }
        } catch (Exception e) {
            // logger.info("数据库操作失败");
            e.printStackTrace();
        } finally {
            if (null != conn) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        // SelectRegion();
        // System.out.println(SelectRegionValue("64"));
    }
}
