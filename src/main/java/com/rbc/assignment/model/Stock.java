package com.rbc.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Stock {

	
	public int quarter;
	public String stockTicker;
	public String date;
	public String open;
	public String high;
	public String low;
	public String close;
	public long volume;
	public float percenChangePrice;
	public float percentChangVolumeOverLastWeek;
	public long previousWeeksVolume;
	public String nextWeeksOpen;
	public String nextWeekClose;
	public float percentChangeNextWeeksPrice;
	public int daysToNextDividend;
	public float percentReturnNextDividend;
	
}
