package gap.client.util;

import gap.common.util.ResultMessage;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class AbstractOperation implements Operation {
	Object classinstance;
	Object args;
	Class<? extends Object> classname;
	Class<? extends Object> argsclass;
	String operation;
	Method method;

	/**
	 * 抽象操作的构造函数
	 * 
	 * @param classinstance 一个对象的实例，需要其来执行之后的方法
	 * @param operation  具体的方法
	 * @param args  方法需要的参数
	 */
	public AbstractOperation(Object classinstance, String operation, Object args) {
		classname = classinstance.getClass();
		this.classinstance = classinstance;
		this.operation = operation;
		this.args = args;
		this.argsclass = args.getClass();
	}

	@Override
	public ResultMessage excute() {
		// TODO 自动生成的方法存根
		try {
			method = classname.getMethod(operation, argsclass);
			return (ResultMessage) method.invoke(classinstance, args);
		} catch (IllegalAccessException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return ResultMessage.FAILED;
	}
}
