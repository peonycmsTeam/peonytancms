package com.peony.peonyfront.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.peony.core.common.utils.UUIDGenerator;
import com.peony.peonyfront.event.service.ExportSupportService;
import com.peony.peonyfront.login.model.PhoneLogin;
import com.peony.peonyfront.login.model.User;
import com.peony.peonyfront.login.service.PhoneLoginService;
import com.peony.peonyfront.util.export.AssignedCell;
import com.peony.peonyfront.util.export.ExportExcelParameter;
import com.peony.peonyfront.util.export.ManagerException;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class DocumentHandler {
    private Configuration configuration = null;

    public DocumentHandler() {
        configuration = new Configuration();
        configuration.setDefaultEncoding("utf-8");
    }

    /**
     * @param fileName
     *            //文件名字
     * @param dataMap
     *            //模板内需要的数据 导出word
     */
    public void createDoc(String fileName, String template, Map<String, Object> dataMap, HttpServletRequest request, HttpServletResponse response) {
        // Map<String, Object> dataMap = new HashMap<String, Object>();
        // dataMap.put("list", dataList);
        // dataMap.put("username", username);
        // dataMap.put("date", yesday);
        // dataMap.put("areaList", areaList);
        // 设置模本装置方法和路�?FreeMarker支持多种模板装载方法。可以重servlet，classpath，数据库装载�?
        // 这里我们的模板是放在com.havenliu.document.template包下�?
        SimpleDateFormat datatime = new SimpleDateFormat("yyyyMMdd");
        String ctxPath = request.getSession().getServletContext().getRealPath("/") + "files/" + datatime.format(new Date()) + "/";
        String path = request.getSession().getServletContext().getRealPath("/") + "template/briefreport/";
        // 输出文档路径及名�?
        File templateFile = new File(path);
        if (!templateFile.isDirectory()) {
            templateFile.mkdirs();
        }
        Template t = null;
        try {
            configuration.setDirectoryForTemplateLoading(templateFile);
            // test.ftl为要装载的模�?
            t = configuration.getTemplate(template);
        } catch (IOException e) {
            e.printStackTrace();
        }
        File dirFile = new File(ctxPath);
        if (!dirFile.isDirectory()) {
            dirFile.mkdirs();
        }

        File outFile = new File(ctxPath + fileName);
        Writer out = null;
        try {
            try {
                out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"));
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }

        try {
            t.process(dataMap, out);
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 添加到response，提供下载
        try {
            export(ctxPath + fileName, fileName, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 导出专报
     * 
     * @param fileName
     * @param template
     * @param dataMap
     * @param request
     * @param response
     */
    public void createTopicDoc(String fileName, String template, Map<String, Object> dataMap, HttpServletRequest request, HttpServletResponse response) {
        // 设置模本装置方法和路�?FreeMarker支持多种模板装载方法。可以重servlet，classpath，数据库装载�?
        // 这里我们的模板是放在com.havenliu.document.template包下�?
        SimpleDateFormat datatime = new SimpleDateFormat("yyyyMMdd");
        String ctxPath = request.getSession().getServletContext().getRealPath("/") + "files/" + datatime.format(new Date()) + "/";
        String path = request.getSession().getServletContext().getRealPath("/") + "template/topic/";
        // 输出文档路径及名�?
        File templateFile = new File(path);
        if (!templateFile.isDirectory()) {
            templateFile.mkdirs();
        }
        Template t = null;
        try {
            configuration.setDirectoryForTemplateLoading(templateFile);
            // test.ftl为要装载的模�?
            t = configuration.getTemplate(template);
        } catch (IOException e) {
            e.printStackTrace();
        }
        File dirFile = new File(ctxPath);
        if (!dirFile.isDirectory()) {
            dirFile.mkdirs();
        }

        File outFile = new File(ctxPath + fileName);
        Writer out = null;
        try {
            try {
                out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"));
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }

        try {
            t.process(dataMap, out);
            out.flush();
            out.close();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 添加到response，提供下载
        try {
            export(ctxPath + fileName, fileName, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * @param fileName
     * @param request
     * @param response
     * @throws Exception
     */
    public void export(String downLoadPath, String fileName, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/html;charset=utf-8");
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        java.io.BufferedInputStream bis = null;
        java.io.BufferedOutputStream bos = null;
        try {
            long fileLength = new File(downLoadPath).length();
            response.setContentType("application/x-msdownload;");
            response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("gb2312"), "ISO8859-1"));
            response.setHeader("Content-Length", String.valueOf(fileLength));
            bis = new BufferedInputStream(new FileInputStream(downLoadPath));
            bos = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
        }

    }

    /**
     * 
     * @param fileName
     *            生成文件名
     * @param template
     *            模板名称
     * @param dataMap
     *            数据
     * @param request
     * @param response
     *            导出html
     */
    public String createHtml(String ctxPath, String fileName, String template, Map<String, Object> dataMap, HttpServletRequest request, HttpServletResponse response) {

        // 设置模本装置方法和路�?FreeMarker支持多种模板装载方法。可以重servlet，classpath，数据库装载�?
        // 这里我们的模板是放在com.havenliu.document.template包下�?
        String path = request.getSession().getServletContext().getRealPath("/") + "template/mail/";
        // String imgUrl = request.getScheme() + "://" + request.getServerName()
        // + ":" + request.getServerPort() + request.getContextPath()
        // + "/images/";
        // dataMap.put("img", imgUrl);
        // 输出文档路径及名�?
        File templateFile = new File(path);
        if (!templateFile.isDirectory()) {
            templateFile.mkdirs();
        }

        Template t = null;
        try {
            configuration.setDirectoryForTemplateLoading(templateFile);
            // test.ftl为要装载的模�?
            t = configuration.getTemplate(template);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 输出文档路径及名�?
        File dirFile = new File(ctxPath);
        if (!dirFile.isDirectory()) {
            dirFile.mkdirs();
        }

        File outFile = new File(ctxPath + fileName);

        Writer out = null;
        try {
            try {
                out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }

        try {
            t.process(dataMap, out);
            out.flush();
            out.close();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String lines = getHtml(ctxPath, fileName);
        return lines;
    }

    /**
     * 获取生成完的html内容
     * 
     * @param ctxPath
     * @param fileName
     */
    private String getHtml(String ctxPath, String fileName) {
        File f = new File(ctxPath + fileName);
        String lines = "";
        if (f.exists()) {
            InputStreamReader read;
            try {
                read = new InputStreamReader(new FileInputStream(f), "utf-8");
                BufferedReader reader = new BufferedReader(read);
                String line;
                while ((line = reader.readLine()) != null) {
                    lines += line;
                }
                read.close();
                reader.close();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        f.delete();
        return lines;
    }

    /**
     * 发送邮件
     * 
     * @param dataMap（title：邮件标题；content：邮件内容）
     * @param request
     * @param response
     */
    public int SendMailtest(Map<String, Object> dataMap, HttpServletRequest request, HttpServletResponse response) {
        int i = 0;
        java.util.Properties props = new java.util.Properties();
        props.setProperty("mail.smtp.auth", "true");// 指定是否需要SMTP验证
        props.setProperty("mail.smtp.host", "mail.peony.cn");// 指定SMTP服务器
        props.put("mail.transport.protocol", "smtp");
        Session mailSession = Session.getDefaultInstance(props);
        mailSession.setDebug(true);// 是否在控制台显示debug信息
        InternetAddress fromAddress;
        try {
            fromAddress = new InternetAddress("peonydata@peony.cn");
            InternetAddress toAddress = new InternetAddress((String) dataMap.get("email"));
            MimeMessage testMessage = new MimeMessage(mailSession);
            // testMessage.setFrom(new InternetAddress("public@peony.cn"));
            testMessage.setFrom(new InternetAddress(javax.mail.internet.MimeUtility.encodeText("云网端舆情服务系统") + " <" + "peonydata@peony.cn" + ">"));
            testMessage.addRecipient(javax.mail.Message.RecipientType.TO, toAddress);
            testMessage.setSentDate(new java.util.Date());
            testMessage.setSubject(MimeUtility.encodeText((String) dataMap.get("title"), "gb2312", "B"));
            testMessage.setContent((String) dataMap.get("content"), "text/html;charset=gb2312");
            Transport transport = mailSession.getTransport("smtp");
            transport.connect("mail.peony.cn", "peonydata@peony.cn", "O^Z7wyjk*scO71Ji");
            transport.sendMessage(testMessage, testMessage.getAllRecipients());
            transport.close();
            i = 1;
        } catch (AddressException e) {
            e.printStackTrace();
            i = 0;
        } catch (MessagingException e) {
            e.printStackTrace();
            i = 0;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            i = 0;
        } catch (Exception e) {
            e.printStackTrace();
            i = 0;
        }
        return i;
    }

    /**
     * 预警发送到手机客户端
     * 
     * @param userId
     *            用户id
     * @param warningId
     *            预警id
     * @param subjectPageTitle
     *            预警标题
     * @param list
     *            PhoneLoginList
     */
    public void warningSendPhone(int userId, String warningId, String subjectPageTitle, List<PhoneLogin> list) {
        JSONArray array = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            JSONObject object = new JSONObject();
            PhoneLogin phone = list.get(i);
            object.put("token", phone.getMacId());
            object.put("time", phone.getDndTime() + "");
            array.add(object);
        }

        try {
            toSendYJ(String.valueOf(userId), subjectPageTitle, warningId, "1", array.toString());
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 手机端 发送预警 userId/title/uuid/type//token_time
     * 
     * @throws IOException
     * @throws ClientProtocolException
     */
    private void toSendYJ(String userId, String title, String uuid, String type, String token_time) throws ClientProtocolException, IOException {
        String url = "http://down.peonydata.com/Push/PushService";
        // url="http://58.30.49.21:8080/Push/PushService";
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("userId", userId));
        params.add(new BasicNameValuePair("title", title));
        params.add(new BasicNameValuePair("uuid", uuid));
        params.add(new BasicNameValuePair("type", type));
        params.add(new BasicNameValuePair("token_time", token_time));
        topost(url, params);
    }

    public String topost(String url, List<NameValuePair> ce) throws ClientProtocolException, IOException {
        HttpPost httpPost = new HttpPost(url);
        HttpResponse httpResponse = null;
        // 设置httpPost请求参数
        httpPost.setEntity(new UrlEncodedFormEntity(ce, HTTP.UTF_8));
        httpResponse = new DefaultHttpClient().execute(httpPost);
        if (httpResponse.getStatusLine().getStatusCode() == 200) {
            // 第三步，使用getEntity方法活得返回结果
            String str = EntityUtils.toString(httpResponse.getEntity());
            return str;
        }
        return "";
    }

}
