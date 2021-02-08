package com.cu.dao;

import com.cu.entity.Pos;
import com.cu.macro.Constants;
import com.cu.util.JdbcUtil;
import com.cu.util.LuceneUtil;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.*;
import org.apache.lucene.store.LockObtainFailedException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * POS检索的数据接口
 */
@Component
public class PosDao {

    /**
     * 将网络资源系统全量POS数据列表添加到Lucene搜索引擎
     * @return
     */
    public String getFullPosListToLucene(){
        // 初始化网络资源数据库连接/执行/结果集
        Connection connZY = null;
        PreparedStatement psZY = null;
        ResultSet rsZY = null;

        //SQL语句:从网络资源系统获取最新分光器数据信息
        String sqlZY = Constants.sqlPosFull;

        try{
            // 网络资源系统数据库连接/执行/结果集
            connZY = JdbcUtil.getZYConn();
            psZY = connZY.prepareStatement(sqlZY);
            rsZY = psZY.executeQuery();

            // POS列表计数器
            int num = 0;
            // 遍历POS信息列表结果集
            while (rsZY.next()){
                Pos pos = new Pos();
                pos.setPos_id(rsZY.getInt("POS_ID"));
                pos.setPos_bianhao(rsZY.getString("pos_bianhao"));
                pos.setLouyu_mingcheng(rsZY.getString("louyu_mingcheng"));
                pos.setAnzhuang_weizhi(rsZY.getString("anzhuang_weizhi"));
                pos.setWeizhi_leixing(rsZY.getString("weizhi_leixing"));
                pos.setWei_zhi(rsZY.getString("wei_zhi"));
                pos.setQu_ju(rsZY.getString("qu_ju"));
                pos.setPon_biaoshi(rsZY.getString("pon_biaoshi"));
                pos.setBiao_qian(rsZY.getString("biao_qian"));
                pos.setFenxian_he(rsZY.getString("fenxian_he"));
                pos.setOlt_mingcheng(rsZY.getString("olt_mingcheng"));
                pos.setCao_lu(rsZY.getInt("cao_lu"));
                pos.setPon_duankou_hao(rsZY.getInt("pon_duankou_hao"));
                pos.setOlt_ip_dizhi(rsZY.getString("olt_ip_dizhi"));
                pos.setOlt_leixing(rsZY.getString("olt_leixing"));
                pos.setChang_jia(rsZY.getString("chang_jia"));
                pos.setGengxin_shijian(new Timestamp(new Date().getTime()));

                // 初始化Lucene索引写入器
                IndexWriter indexWriter = new IndexWriter(LuceneUtil.getDirectory(), LuceneUtil.getAnalyzer(),
                        LuceneUtil.getMaxFieldLength());
                // 将POS对象转换为POS的Lucene文档
                Document document = LuceneUtil.javabean2document(pos);
                // 将POS的Lucene文档写入Lucene索引
                indexWriter.addDocument(document);
                // 优化Lucene索引写入器
                indexWriter.optimize();
                // 关闭Lucene索引写入器
                indexWriter.close();
                num++;
                System.out.println(num + "\t" + pos.getPos_bianhao());
            }
            JdbcUtil.closeStmt(psZY);
            JdbcUtil.closeConn(connZY);
        }
        catch (Exception e){
            e.printStackTrace();
            return "error";
        }
        finally {
            // 关闭网络资源数据库连接/执行/结果集
            JdbcUtil.closeRs(rsZY);
            JdbcUtil.closeStmt(psZY);
            JdbcUtil.closeConn(connZY);
        }
        return "success";
    }


    /**
     * 将网络资源系统增量POS数据列表添加到Lucene搜索引擎
     * @return
     */
    public String getIncrementPosListToLucene(){
        String version = LogDao.getLatestDateVersion();
        // 初始化网络资源数据库连接/执行/结果集
        Connection connZY = null;
        PreparedStatement psZY = null;
        ResultSet rsZY = null;
        String sqlZY = Constants.getSqlPosIncrement;
        LogDao logDao = new LogDao();
        try{
            // 网络资源系统数据库连接/执行/结果集
            connZY = JdbcUtil.getZYConn();
            psZY = connZY.prepareStatement(sqlZY);
            psZY.setString(1, version);
            psZY.setString(2, version);
            psZY.setString(3, version);
            psZY.setString(4, version);
            psZY.setString(5, version);
            psZY.setString(6, version);
            psZY.setString(7, version);
            psZY.setString(8, version);
            psZY.setString(9, version);
            psZY.setString(10, version);
            psZY.setString(11, version);
            rsZY = psZY.executeQuery();

            // POS信息计数器
            int num = 0;
            // 遍历POS信息列表结果集
            while (rsZY.next()) {
                Pos pos = new Pos();
                pos.setPos_id(rsZY.getInt("POS_ID"));
                pos.setPos_bianhao(rsZY.getString("pos_bianhao"));
                pos.setLouyu_mingcheng(rsZY.getString("louyu_mingcheng"));
                pos.setWeizhi_leixing(rsZY.getString("weizhi_leixing"));
                pos.setAnzhuang_weizhi(rsZY.getString("anzhuang_weizhi"));
                pos.setWei_zhi(rsZY.getString("wei_zhi"));
                pos.setQu_ju(rsZY.getString("qu_ju"));
                pos.setPon_biaoshi(rsZY.getString("pon_biaoshi"));
                pos.setBiao_qian(rsZY.getString("biao_qian"));
                pos.setFenxian_he(rsZY.getString("fenxian_he"));
                pos.setOlt_mingcheng(rsZY.getString("olt_mingcheng"));
                pos.setCao_lu(rsZY.getInt("cao_lu"));
                pos.setPon_duankou_hao(rsZY.getInt("pon_duankou_hao"));
                pos.setOlt_ip_dizhi(rsZY.getString("olt_ip_dizhi"));
                pos.setOlt_leixing(rsZY.getString("olt_leixing"));
                pos.setChang_jia(rsZY.getString("chang_jia"));
                pos.setGengxin_shijian(new Timestamp(new Date().getTime()));

                IndexWriter indexWriterDel = new IndexWriter(LuceneUtil.getDirectory(), LuceneUtil.getAnalyzer(),
                        LuceneUtil.getMaxFieldLength());
                indexWriterDel.deleteDocuments(new Term("pos_id", String.valueOf(1)));
                indexWriterDel.optimize();
                indexWriterDel.close();

                IndexWriter indexWriterAdd = new IndexWriter(LuceneUtil.getDirectory(), LuceneUtil.getAnalyzer(),
                        LuceneUtil.getMaxFieldLength());
                Document document = LuceneUtil.javabean2document(pos);
                indexWriterAdd.addDocument(document);
                indexWriterAdd.optimize();
                indexWriterAdd.close();

                num++;
                //System.out.println(num + "\t" + pos.toString());
            }
            logDao.insertLog("分光器索引文件更新", "4区局分光器数据增量更新"+num+"条", 1);
            JdbcUtil.closeStmt(psZY);
            JdbcUtil.closeConn(connZY);
        }
        catch (Exception e){
            e.printStackTrace();
            logDao.insertLog("分光器索引文件更新", "4区局分光器数据增量更新失败", 0);
            return "error";
        }
        finally {
            // 关闭数据库连接/执行/结果集
            JdbcUtil.closeRs(rsZY);
            JdbcUtil.closeStmt(psZY);
            JdbcUtil.closeConn(connZY);
        }
        return "success";
    }


     /**
     * 根据关键字、区局和上联标识在Lucene搜索引擎中获取POS列表
     * @param keyword 关键字
     * @param qu_ju 区局
     * @param up_link 上联标识
     *
     * @return
     * @throws Exception
     */
    public List<Pos> getPosList(String keyword, String qu_ju, String up_link) throws Exception {
        // POS列表
        List<Pos> posList = new ArrayList<Pos>();

        // 定义组合查询条件
        BooleanQuery booleanQuery = new BooleanQuery();

        // 查询字段:路由名称、POS编号、位置类型、位置
        String[] queryFileds = new String[] { "louyu_mingcheng", "pos_bianhao", "weizhi_leixing", "wei_zhi" };
        // 创建多字段查询转换器
        QueryParser queryParser = new MultiFieldQueryParser(LuceneUtil.getVersion(), queryFileds,
                LuceneUtil.getAnalyzer());
        // 将输入的关键字加载到查询转换器中
        Query query = queryParser.parse(keyword);
        // 将关键字查询条件加入组合查询条件中
        booleanQuery.add(query, Occur.MUST);

        // 区局不为"*"时,增加区局的筛选条件
        if (!qu_ju.equals("*")){
            // 创建区局的查询转换器
            QueryParser parserQJ = new QueryParser(LuceneUtil.getVersion(), "qu_ju",
                    LuceneUtil.getAnalyzer());
            // 将输入的区局加载到查询转换器中
            Query queryQJ = parserQJ.parse(qu_ju);
            // 将区局查询条件加入组合查询条件中
            booleanQuery.add(queryQJ, Occur.MUST);
        }


        // 上联标识不为"全选"时,增加上联标识的筛选条件
        if(!up_link.equals("*")){
            // 创建上联标识的查询转换器
            QueryParser parserUL = new QueryParser(LuceneUtil.getVersion(), "pon_biaoshi",
                    LuceneUtil.getAnalyzer());
            // 将输入的上联标识加载到查询转换器中
            Query queryUL = parserUL.parse(up_link);
            // 将上联标识查询条件加入组合查询条件中
            booleanQuery.add(queryUL, Occur.MUST);
        }

        // 创建基于当前目录的索引搜索器
        @SuppressWarnings("resource")
        IndexSearcher indexSearcher = new IndexSearcher(LuceneUtil.getDirectory());
        // 通过组合查询条件在索引搜索器中查询前50个文档
        TopDocs topDocs = indexSearcher.search(booleanQuery, 50);

        // 格式对象
        //Formatter formatter = new SimpleHTMLFormatter("<font color='red'>", "</font>");
        // 关键字对象
        //Scorer scorer = new QueryScorer(query);
        // 高亮对象
        //Highlighter highlighter = new Highlighter(formatter, scorer);

        int count = (topDocs.totalHits >= 50) ? 50 : topDocs.totalHits;

        for (int i = 0; i < count; i++) {
            ScoreDoc scoreDoc = topDocs.scoreDocs[i];
            int no = scoreDoc.doc;
            Document document = indexSearcher.doc(no);

            // 关键字高亮
//            for (int k = 0; k < queryFileds.length; k++) {
//                String queryField = queryFileds[k];
//                Object obj = highlighter.getBestFragment(LuceneUtil.getAnalyzer(), queryField, document.get(queryField));
//                String field_hl = (obj == null) ? document.getField(queryField).stringValue() : obj.toString();
//                document.getField(queryField).setValue(field_hl);
//            }

            Pos pos = (Pos)LuceneUtil.document2javabean(document,Pos.class);
            posList.add(pos);
        }
        return posList;
    }





    /**
     * 查看全量POS列表(测试用)
     * @return
     */
    public List<Map<String,Object>> getPosListTest(){

        // 数据库连接/执行/结果集
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        // 查询编号包含"望京"的POS列表
        String sql = "SELECT" +
                " pos.pos_bianhao," +
                " pos.louyu_mingcheng," +
                " pos.anzhuang_weizhi," +
                " pos.qu_ju," +
                " pos.pon_biaoshi," +
                " pos.biao_qian," +
                " pos.fenxian_he," +
                " pos.olt_mingcheng," +
                " pos.cao_lu," +
                " pos.duankou_hao," +
                " pos.ip_dizhi," +
                " pos.olt_leixing," +
                " pos.chang_jia" +
                " FROM nlsc_pos_info pos" +
                " WHERE pos.pos_bianhao LIKE '%望京%'" +
                " LIMIT 50;";

        List<Map<String,Object>> list = new ArrayList<>();
        try {
            conn = JdbcUtil.getZTConn();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Map<String,Object> map = new HashMap<>();
                map.put("pos_bianhao", rs.getString(1));
                map.put("louyu_mingcheng", rs.getString(2));
                map.put("anzhuang_weizhi", rs.getString(3));
                map.put("qu_ju", rs.getString(4));
                map.put("pon_biaoshi", rs.getString(5));
                map.put("biao_qian", rs.getString(6));
                map.put("fenxian_he", rs.getString(7));
                map.put("olt_mingcheng", rs.getString(8));
                map.put("cao_lu", rs.getString(9));
                map.put("duankou_hao", rs.getString(10));
                map.put("ip_dizhi", rs.getString(11));
                map.put("olt_leixing", rs.getString(12));
                map.put("chang_jia", rs.getString(13));
                list.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeRs(rs);
            JdbcUtil.closeStmt(ps);
            JdbcUtil.closeConn(conn);
        }
        return list;
    }




}
