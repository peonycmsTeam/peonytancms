package com.peony.peonyfront.system.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.peony.peonyfront.subject.model.Subject;
import com.peony.peonyfront.subjectkeywords.model.SubjectKeywords;

public class WhJdcbConnection {

    private Log                 logger = LogFactory.getLog(WhJdcbConnection.class);

    private static final String URL    = "jdbc:mysql://119.254.110.32:3523/peony_t?" + "user=root&password=root&useUnicode=true&characterEncoding=UTF8";

    public int InsertSubject(Subject subject) {
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

            sql = "insert into pe_t_subject (id, userId, state, name, createTime, lastUpdatedTime,  pid, isChildnodes,update_time)" + "values ('" + subject.getId() + "','" + subject.getUserid() + "','" + subject.getState() + "','" + subject.getName() + "','" + dateformat.format(subject.getCreatetime()) + "','" + dateformat.format(subject.getUpdateTime()) + "','" + subject.getPid() + "','" + subject.getIschildnodes() + "','" + dateformat.format(subject.getUpdateTime()) + "')";
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

    public int DeleteSubject(Subject subject) {
        Connection conn = null;
        String sql;
        int result = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
            conn = DriverManager.getConnection(URL);
            // Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
            Statement stmt = conn.createStatement();
            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sql = "update pe_t_subject set dropTime='" + dateformat.format(subject.getDroptime()) + "',state='0',update_time='" + dateformat.format(subject.getUpdateTime()) + "' where id='" + subject.getId() + "'";
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

    public int UpdateSubject(Subject subject) {
        Connection conn = null;
        String sql;
        int result = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
            conn = DriverManager.getConnection(URL);
            // Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
            Statement stmt = conn.createStatement();
            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sql = "update pe_t_subject set update_time='" + dateformat.format(subject.getUpdateTime()) + "',name='" + subject.getName() + "',lastUpdatedTime='" + dateformat.format(subject.getUpdateTime()) + "' where id='" + subject.getId() + "'";
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

    public int InsertSubjectKeywords(SubjectKeywords subjectKeywords) {
        Connection conn = null;
        String sql;
        int result = 0;
        try {

            Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动

            conn = DriverManager.getConnection(URL);
            Statement stmt = conn.createStatement();

            sql = "insert into pe_t_subject_keywords (id, subjectId, name, keywords,rejectFlag)" + "values ('" + subjectKeywords.getId() + "','" + subjectKeywords.getSubjectid() + "','" + subjectKeywords.getName() + "','" + subjectKeywords.getKeywords() + "','" + subjectKeywords.getRejectflag() + "')";
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

    public int DeleteSubjectKeywords(String subjectId) {
        Connection conn = null;
        String sql;
        int result = 0;
        try {

            Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动

            conn = DriverManager.getConnection(URL);
            Statement stmt = conn.createStatement();

            sql = "delete from pe_t_subject_keywords where subjectId =" + subjectId;
            logger.info("执行的delete语句 = " + sql);
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

}
