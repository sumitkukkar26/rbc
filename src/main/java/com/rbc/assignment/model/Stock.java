package com.rbc.assignment.model;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class Stock {

	public int quarter;
	public String stockTicker;
	public LocalDateTime date;
	public float open;
	public float high;
	public float low;
	public float close;
	public long volume;
	public float percenChangePrice;
	public float percentChangVolumeOverLastWeek;
	public long previousWeeksVolume;
	public float nextWeeksOpen;
	public float nextWeekClose;
	public float percentChangeNextWeeksPrice;
	public float percentReturnNextDividend;
	
}
