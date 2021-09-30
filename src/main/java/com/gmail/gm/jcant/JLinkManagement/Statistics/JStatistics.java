package com.gmail.gm.jcant.JLinkManagement.Statistics;

public class JStatistics {

	private int allCnt;
	private int dayCnt;
	private int weekCnt;
	private int monthCnt;
	private int yearCnt;

	private long daysLeft;

	private String allPerDay;
	private String dayPerHour;
	private String weekPerDay;
	private String monthPerDay;
	private String yearPerDay;

	public JStatistics() {
	}

	public JStatistics(int allCnt, int dayCnt, int weekCnt, int monthCnt, int yearCnt, long daysLeft) {
		this.allCnt = allCnt;
		this.dayCnt = dayCnt;
		this.weekCnt = weekCnt;
		this.monthCnt = monthCnt;
		this.yearCnt = yearCnt;
		this.daysLeft = daysLeft;
		setAllPerDay(allCnt);
		setDayPerHour(dayCnt);
		setWeekPerDay(weekCnt);
		setMonthPerDay(monthCnt);
		setYearPerDay(yearCnt);
	}

	public int getAllCnt() {
		return allCnt;
	}

	public int getDayCnt() {
		return dayCnt;
	}

	public int getWeekCnt() {
		return weekCnt;
	}

	public int getMonthCnt() {
		return monthCnt;
	}

	public int getYearCnt() {
		return yearCnt;
	}

	public void setAllCnt(int allCnt) {
		this.allCnt = allCnt;
	}

	public void setDayCnt(int dayCnt) {
		this.dayCnt = dayCnt;
	}

	public void setWeekCnt(int weekCnt) {
		this.weekCnt = weekCnt;
	}

	public void setMonthCnt(int monthCnt) {
		this.monthCnt = monthCnt;
	}

	public void setYearCnt(int yearCnt) {
		this.yearCnt = yearCnt;
	}

	public String getAllPerDay() {
		return allPerDay;
	}

	public String getDayPerHour() {
		return dayPerHour;
	}

	public String getWeekPerDay() {
		return weekPerDay;
	}

	public String getMonthPerDay() {
		return monthPerDay;
	}

	public String getYearPerDay() {
		return yearPerDay;
	}

	public void setAllPerDay(int allCnt) {
		this.allPerDay = String.format("%.2f", ((double) allCnt / 365));
	}

	public void setDayPerHour(int dayCnt) {
		this.dayPerHour = String.format("%.2f", ((double) dayCnt / 24));
	}

	public void setWeekPerDay(int weekCnt) {
		this.weekPerDay = String.format("%.2f", ((double) weekCnt / 7));
	}

	public void setMonthPerDay(int monthCnt) {
		this.monthPerDay = String.format("%.2f", ((double) monthCnt / 30));
	}

	public void setYearPerDay(int yearCnt) {
		this.yearPerDay = String.format("%.2f", ((double) yearCnt / 365));
	}

	public long getDaysLeft() {
		return daysLeft;
	}

	public void setDaysLeft(long daysLeft) {
		this.daysLeft = daysLeft;
	}

	@Override
	public String toString() {
		return "JStatistics{" + "allCnt=" + allCnt + ", dayCnt=" + dayCnt + ", weekCnt=" + weekCnt + ", monthCnt="
				+ monthCnt + ", yearCnt=" + yearCnt + ", daysLeft=" + daysLeft + ", allPerDay='" + allPerDay + '\''
				+ ", dayPerHour='" + dayPerHour + '\'' + ", weekPerDay='" + weekPerDay + '\'' + ", monthPerDay='"
				+ monthPerDay + '\'' + ", yearPerDay='" + yearPerDay + '\'' + '}';
	}
}
