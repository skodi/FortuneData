package uk.co.lnssolutions.Fortune.data.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


@Entity

public class HorseSelection {

	@Id 
	private double id;
	//@ManyToOne
	//@JoinColumn(name = "EVENT_ID",nullable=false)
	
	@ManyToOne
	@JoinColumn(name = "eventID")
	private HorseEventFrame horseEventFrame;
	private double event_ID;
	private String event;
	private String country;
	private String full_description;
	private String course;
	private Date scheduled_off;
	private Date actual_off;
	private String selection;
	private Date settled_date;
	private double odds;
	private Date latest_taken;
	private Date first_taken;
	private String in_play;
	private double number_bets;
	private float volume_matched;
	private int sports_id;
	private int selection_id;
	private int win_flag;
	
	// Summary fields we add 
	private double total_number_bets;
	private double total_volume_matched;
	private double min_odds;
	private double max_odds;
	
	public double getEvent_ID() {
		return event_ID;
	}
	public void setEvent_ID(double event_ID) {
		this.event_ID = event_ID;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getFull_description() {
		return full_description;
	}
	public void setFull_description(String full_description) {
		this.full_description = full_description;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public Date getScheduled_off() {
		return scheduled_off;
	}
	public void setScheduled_off(Date scheduled_off) {
		this.scheduled_off = scheduled_off;
	}
	public Date getActual_off() {
		return actual_off;
	}
	public void setActual_off(Date actual_off) {
		this.actual_off = actual_off;
	}
	public String getSelection() {
		return selection;
	}
	public void setSelection(String selection) {
		this.selection = selection;
	}
	public Date getSettled_date() {
		return settled_date;
	}
	public void setSettled_date(Date settled_date) {
		this.settled_date = settled_date;
	}
	public double getOdds() {
		return odds;
	}
	public void setOdds(double odds) {
		this.odds = odds;
	}
	public Date getLatest_taken() {
		return latest_taken;
	}
	public void setLatest_taken(Date latest_taken) {
		this.latest_taken = latest_taken;
	}
	public Date getFirst_taken() {
		return first_taken;
	}
	public void setFirst_taken(Date first_taken) {
		this.first_taken = first_taken;
	}
	public String getIn_play() {
		return in_play;
	}
	public void setIn_play(String in_play) {
		this.in_play = in_play;
	}
	public double getNumber_bets() {
		return number_bets;
	}
	public void setNumber_bets(double number_bets) {
		this.number_bets = number_bets;
	}
	public float getVolume_matched() {
		return volume_matched;
	}
	public void setVolume_matched(float volume_matched) {
		this.volume_matched = volume_matched;
	}
	public int getSports_ID() {
		return sports_id;
	}
	public void setSports_ID(int sports_id) {
		this.sports_id = sports_id;
	}
	public int getSelection_id() {
		return selection_id;
	}
	public void setSelection_id(int selection_id) {
		this.selection_id = selection_id;
	}
	public int getWin_flag() {
		return win_flag;
	}
	public void setWin_flag(int win_flag) {
		this.win_flag = win_flag;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public double getId() {
		return id;
	}
	public void setId(double	 id) {
		this.id = id;
	}
	public int getSports_id() {
		return sports_id;
	}
	public void setSports_id(int sports_id) {
		this.sports_id = sports_id;
	}
	public double getTotal_number_bets() {
		return total_number_bets;
	}
	public void setTotal_number_bets(double total_number_bets) {
		this.total_number_bets = total_number_bets;
	}
	public double getTotal_volume_matched() {
		return total_volume_matched;
	}
	public void setTotal_volume_matched(double total_volume_matched) {
		this.total_volume_matched = total_volume_matched;
	}
	public double getMin_odds() {
		return min_odds;
	}
	public void setMin_odds(double min_odds) {
		this.min_odds = min_odds;
	}
	public double getMax_odds() {
		return max_odds;
	}
	public void setMax_odds(double max_odds) {
		this.max_odds = max_odds;
	}
	
	
	
	
}
/*
"7",						SPORTS_ID
"118723714",			    EVENT_ID ?
"10-05-2015 17:02:15",
"IRE",						COUNTRY
"IRE/Killar 10th May",
"Killar",					
"10-05-2015 16:55",			scheduled_off
"2m1f INHF",
"10-05-2015 16:55:13",		ac
"9471383",
"Cairnshill",
"380",
"2",
".1",
"10-05-2015 16:57:41",
"10-05-2015 16:57:41",
"0",						 WIN_FLAG ?
"IP"                         IN_PLAY
	
"7",						 SPORTS_ID
"118722404",				 EVENT_ID
"10-05-2015 15:48:36",	     SETTLED_DATE
"IRE",						 COUNTRY
"IRE/Leop 10th May",		 EVENT
"Leop",						 COURSE
"10-05-2015 15:40",			 SCHEDULED_OFF
"4 TBP",					 FULL_DESCRIPTION
"10-05-2015 15:41:52",		 ACTUAL_OFF
"7389719",					 SELECTION_ID
"Ballybacka Queen",			 SELECTION	
"5.3",						 ODDS
"5",						 NUMBER_BETS
"14.7",						 VOLUME_MATCHED
"10-05-2015 15:27:50",		 LATEST_TAKEN
"10-05-2015 15:02:00",		 FIRST_TAKEN
"1",					     WIN_FLAG
"PE"						 IN_PLAY

"SPORTS_ID",
"EVENT_ID",
"SETTLED_DATE",
"COUNTRY",
"FULL_DESCRIPTION",
"COURSE",
"SCHEDULED_OFF",
"EVENT",
"ACTUAL_OFF",
"SELECTION_ID",
"SELECTION",
"ODDS",
"NUMBER_BETS",
"VOLUME_MATCHED",
"LATEST_TAKEN",
"FIRST_TAKEN",
"WIN_FLAG",
"IN_PLAY"


*/