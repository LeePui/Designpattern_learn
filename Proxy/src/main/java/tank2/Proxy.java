package tank2;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import tank.Moveable;
import tank.Tank;

public class Proxy {
	
	public static Object getInstance(Class cla) throws Exception{
		String s = "package tank2;\r\n" + 
				"\r\n" + 
				"import tank.Moveable;\r\n" + 
				"\r\n" + 
				"/**\r\n" + 
				" * 该类想实现以下计算Tank的move方法使用的时间\r\n" + 
				" * 这种方式叫做聚合\r\n" + 
				" * @author LeePui\r\n" + 
				" *\r\n" + 
				" */\r\n" + 
				"public class TankTimeProxy implements "+cla.getName()+" {\r\n" + 
				"\r\n" + 
				"	public Moveable t;\r\n" + 
				"	\r\n" + 
				"	public TankTimeProxy(Moveable t) {\r\n" + 
				"		super();\r\n" + 
				"		this.t= t;\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"	@Override\r\n" + 
				"	public void move() {\r\n" + 
				"		long start = System.currentTimeMillis();\r\n" + 
				"		System.out.println(\"开始时间: \" + start);\r\n" + 
				"		t.move();\r\n" + 
				"		long end = System.currentTimeMillis();\r\n" + 
				"		System.out.println(\"总花费时间: \" + (end-start));\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"}";
		
		/**
		 * 下面将上述的字符串写入文件
		 */
		String fileName = System.getProperty("user.dir") + "/src/main/java/tank2/TankTimeProxy.java";
		FileWriter fw = new FileWriter(fileName);
		fw.write(s);
		fw.flush();fw.close();
		
		/**
		 * 编译上面的java文件
		 */
		//拿到java默认的编译器(javac)
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		//拿到一个文件管理器，初始化
		StandardJavaFileManager fileMgr = compiler.getStandardFileManager(null, null, null);
		//简单理解为一个数组，用来迭代
		Iterable<? extends JavaFileObject> units = fileMgr.getJavaFileObjects(fileName);
		//编译任务
		CompilationTask t = compiler.getTask(null, fileMgr, null, null, null, units);
		//编译
		t.call();
		fileMgr.close();
		
		/**
		 * 加载到内存并且生成实例
		 */
		String urlName = "file:\\" + System.getProperty("user.dir") + "/src/main/java";
		URL[] urls = new URL[] {new URL(urlName)};
		//classloader类加载不了，使用下面这个类
		URLClassLoader ul = new URLClassLoader(urls);
		Class c = ul.loadClass("tank2.TankTimeProxy");
		Constructor ctr = c.getConstructor(Moveable.class);
		
		return ctr.newInstance(new Tank());
	}
	
//	public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
//		String s = "package tank2;\r\n" + 
//				"\r\n" + 
//				"import tank.Moveable;\r\n" + 
//				"\r\n" + 
//				"/**\r\n" + 
//				" * 该类想实现以下计算Tank的move方法使用的时间\r\n" + 
//				" * 这种方式叫做聚合\r\n" + 
//				" * @author LeePui\r\n" + 
//				" *\r\n" + 
//				" */\r\n" + 
//				"public class TankTimeProxy implements Moveable{\r\n" + 
//				"\r\n" + 
//				"	public Moveable t;\r\n" + 
//				"	\r\n" + 
//				"	public TankTimeProxy(Moveable t) {\r\n" + 
//				"		super();\r\n" + 
//				"		this.t= t;\r\n" + 
//				"	}\r\n" + 
//				"\r\n" + 
//				"	@Override\r\n" + 
//				"	public void move() {\r\n" + 
//				"		long start = System.currentTimeMillis();\r\n" + 
//				"		System.out.println(\"开始时间: \" + start);\r\n" + 
//				"		t.move();\r\n" + 
//				"		long end = System.currentTimeMillis();\r\n" + 
//				"		System.out.println(\"总花费时间: \" + (end-start));\r\n" + 
//				"	}\r\n" + 
//				"\r\n" + 
//				"}";
//		
//		/**
//		 * 下面将上述的字符串写入文件
//		 */
//		String fileName = System.getProperty("user.dir") + "/src/main/java/tank2/TankTimeProxy.java";
//		FileWriter fw = new FileWriter(fileName);
//		fw.write(s);
//		fw.flush();fw.close();
//		
//		/**
//		 * 编译上面的java文件
//		 */
//		//拿到java默认的编译器(javac)
//		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
//		//拿到一个文件管理器，初始化
//		StandardJavaFileManager fileMgr = compiler.getStandardFileManager(null, null, null);
//		//简单理解为一个数组，用来迭代
//		Iterable<? extends JavaFileObject> units = fileMgr.getJavaFileObjects(fileName);
//		//编译任务
//		CompilationTask t = compiler.getTask(null, fileMgr, null, null, null, units);
//		//编译
//		t.call();
//		fileMgr.close();
//		
//		/**
//		 * 加载到内存并且生成实例
//		 */
//		String urlName = "file:\\" + System.getProperty("user.dir") + "/src/main/java";
//		URL[] urls = new URL[] {new URL(urlName)};
//		//classloader类加载不了，使用下面这个类
//		URLClassLoader ul = new URLClassLoader(urls);
//		Class c = ul.loadClass("tank2.TankTimeProxy");
//		Constructor ctr = c.getConstructor(Moveable.class);
//		Moveable m = (Moveable) ctr.newInstance(new Tank());
//		m.move();
//	}
}









