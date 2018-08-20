package com.zxb.listener;

import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.lnson.util.C3P0Manager;

import com.zxb.Initialize.GlobalTaskInitialize;

public class GlobalListener implements ServletContextListener {

	private final static Integer DEFAULT_INTERVALS = 2000;
	private GlobalTaskInitialize _globalTask = null;
	private Thread _thread = null;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		_globalTask = new GlobalTaskInitialize(DEFAULT_INTERVALS);
		_thread = new Thread(_globalTask);
		_thread.start();
		System.out.println("任务调度服务已经启动");
	}

	@SuppressWarnings("deprecation")
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		try {
			//应用程序结束时，把数据库连接池对象释放掉以及
			C3P0Manager.destroy();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		while (_thread.isAlive()) {
			try {
				if (!_globalTask.hasStop()) {
					_globalTask.stopWork();
				}
				// 等待最后一次任务执行完毕之后，该线程自然结束，而不必直接暴力结束该线程
				Thread.sleep(DEFAULT_INTERVALS);
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			} finally {
				// 直接暴力结束该线程
				_thread.stop();
			}
		}
		System.out.println("任务调度服务已经停止");
	}

}
