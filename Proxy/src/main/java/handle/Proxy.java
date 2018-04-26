package handle;

import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import tank.Moveable;
import tank.Tank;

/**
 * 这个类开始实现方法里的语句动态生成，主要是增加了一个InvocationHandle接口，实现该接口的类就是对方法的具体实现
 * 
 * @author LeePui
 *
 */
public class Proxy {
	
	public static Object getInstance(Class cla, InvocationHandle h) throws Exception{
		//windows下的换行
		String rt = "\r\n";
		//method下的字符串
		String methodString = "";
		
		Method[] methods = cla.getMethods();
//		for (Method m : methods) {
//			methodString += " @Override" + rt + 
//							" public void " + m.getName() + "(){" + rt + 		//这里暂时没有设定返回值，都默认是void
//							"		long start = System.currentTimeMillis();\r\n" + 
//							"		System.out.println(\"开始时间: \" + start);\r\n" + 
//							"		t." +m.getName()+ "()" +";\r\n" + 
//							"		long end = System.currentTimeMillis();\r\n" + 
//							"		System.out.println(\"总花费时间: \" + (end-start));\r\n" + 
//							" }";
//		}
		for (Method m : methods) {
			methodString += " @Override" + rt + 
							" public void " + m.getName() + "(){" + rt + 		//这里暂时没有设定返回值，都默认是void
							" h.invoke(this, " + m + ")" + rt + 
							" }";
		}
		
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
				methodString + 
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
		
		Object o = ctr.newInstance(new Tank());
		return o;
//		return ctr.newInstance(new Tank());
//		return null;
	}
	

}









