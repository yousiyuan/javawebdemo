package com.zxb.Initialize;

import java.util.Date;

public class GlobalTaskInitialize implements Runnable {

	private Integer intervals;
	private Boolean confirmStop = true;

	public GlobalTaskInitialize(int intervals) {
		this.intervals = intervals;
	}

	public void stopWork() {
		confirmStop = false;
	}

	public Boolean hasStop() {
		return !confirmStop;
	}

	@Override
	public void run() {
		while (confirmStop) {
			try {
				// put your initialize code
				System.out.println(new Date() + "：全局任务执行中...");

				Thread.sleep(intervals);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
