package com.cu.util;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.IndexWriter.MaxFieldLength;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Lucene工具类
 * 
 * @author yjz
 */

public class LuceneUtil {

	// Lucene操作目录
	private static Directory directory;
	// Lucene版本
	private static Version version;
	// Lucene分词器
	private static Analyzer analyzer;
	// 最大字段长度
	private static MaxFieldLength maxFieldLength;

	static {
		try {
			// String path = File.separator + "nlzt" + File.separator + "PosDB";
			String path = PropertyUtil.getPropertyByKey("lucene.dir");
			directory = FSDirectory.open(new File(path));
			version = Version.LUCENE_30;
			analyzer = new StandardAnalyzer(version);
			//analyzer = new FrenchAnalyzer(version);
			maxFieldLength = MaxFieldLength.LIMITED;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public static Directory getDirectory() {
		return directory;
	}

	public static Version getVersion() {
		return version;
	}

	public static Analyzer getAnalyzer() {
		return analyzer;
	}

	public static MaxFieldLength getMaxFieldLength() {
		return maxFieldLength;
	}

	// 不让外界new该帮助类
	private LuceneUtil() {
	}

	// 将JavaBean转成Document对象
	public static Document javabean2document(Object obj) throws Exception {
		// 创建Docuemnt对象
		Document document = new Document();
		// 获取obj引用的对象字节码
		@SuppressWarnings("rawtypes")
		Class clazz = obj.getClass();
		// 通过对象字节码获取私有的属性
		java.lang.reflect.Field[] reflectFields = clazz.getDeclaredFields();
		// 迭代
		for (java.lang.reflect.Field reflectField : reflectFields) {
			// 强力反射
			reflectField.setAccessible(true);
			// 获取属性名，id/title/content
			String name = reflectField.getName();
			// 人工拼接方法名
			String methodName = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
			// 获取方法，例如：getId()/getTitle()/getContent()
			Method method = clazz.getMethod(methodName, null);
			// 执行方法
			String value = doNull(method.invoke(obj, null)).toString();
			// 加入到Document对象中去，这时javabean的属性与document对象的属性相同
			String[] analyzedFields = {"pos_bianhao", "louyu_mingcheng", "wei_zhi", "pon_biaoshi", "qu_ju"};
			document.add(new Field(name, value, Store.YES, Index.ANALYZED));
			if(Arrays.asList(analyzedFields).contains(name)){
				document.add(new Field(name, value, Store.YES, Index.ANALYZED));
			}else{
				document.add(new Field(name, value, Store.YES, Index.NOT_ANALYZED));
			}
		}
		// 返回document对象
		return document;
	}
	
	public static String doNull(Object str) {
		return str == null ? "" : str.toString();
	}

	// 将Document对象转成JavaBean对象
	public static Object document2javabean(Document document, @SuppressWarnings("rawtypes") Class clazz)
			throws Exception {
		Object obj = clazz.newInstance();
		java.lang.reflect.Field[] reflectFields = clazz.getDeclaredFields();
		for (java.lang.reflect.Field reflectField : reflectFields) {
			reflectField.setAccessible(true);
			String name = reflectField.getName();// id/title/content
			String value = document.get(name);// 1/培训/传智是一家培训机构
			BeanUtils.setProperty(obj, name, value);// 封装javabean对应的属性中去，通过setXxx()方法
		}
		return obj;
	}

}
