package com.codevalue.exercise.domain;

import java.util.Date;

/*
 *  Tank is the data holder/domain 
 *  In real application we can persist it to db 
 */
public class Tank {
	
	
	private final int id;
	private int currentCapacity;
	private final int maxCapacity; // immutable
	private Date lastUpdate;
	private final int leakPerMinute;
 	
	public Tank(TankBuilder builder) {
		this.id=builder.id;
		this.currentCapacity=builder.currentCapacity;
		this.maxCapacity=builder.maxCapacity;
		this.lastUpdate=builder.lastUpdate;
		this.leakPerMinute=builder.leakPerMinute;
		 
	}
	
  

	public static class TankBuilder{
		private int id;
		private int currentCapacity;
		private int maxCapacity;
		private Date lastUpdate;
		private int leakPerMinute;

		public TankBuilder(int id) {
			this.id=id;
			
		}
		
		public TankBuilder currentCapacity( int currentCapacity) {
			this.currentCapacity=currentCapacity;
			return this;
		}
		public TankBuilder leakPerMinute(int leakPerMinute) {
			
			this.leakPerMinute=leakPerMinute;
			return this;
		}
		
		public TankBuilder maxCapacity(int maxCapacity) {
			this.maxCapacity=maxCapacity;
			return this;
			
		}
		public TankBuilder lastUpdate(Date lastUpdate) {
			this.lastUpdate=lastUpdate;
			return this;
		}
		
		public Tank build() {
			return new Tank(this);
		}
	}


	public synchronized int getCurrentCapacity() {
		return currentCapacity;
	}


	public synchronized  void setCurrentCapacity(int currentCapacity) {
		this.currentCapacity = currentCapacity;
	}

    // don't need to synchronize
	public    int getMaxCapacity() {
		return maxCapacity;
	}

 

	public synchronized Date getLastUpdate() {
		return lastUpdate;
	}


	public synchronized void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}


	public int getId() {
		return id;
	}


	public int getLeakPerMinute() {
		return leakPerMinute;
	}


	 

}
