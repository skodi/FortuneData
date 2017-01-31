package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the HorseEvent database table.
 * 
 */
@Entity
@NamedQuery(name="HorseEvent.findAll", query="SELECT h FROM HorseEvent h")
public class HorseEvent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private double id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="actual_off")
	private Date actualOff;

	private String country;

	private String course;

	private String event;

	private double event_ID;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="first_taken")
	private Date firstTaken;

	@Column(name="full_description")
	private String fullDescription;

	@Column(name="in_play")
	private String inPlay;

	@Temporal(TemporalType.TIMESTAMP)
	private Date latestTaken;

	@Column(name="number_bets")
	private double numberBets;

	private double odds;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="scheduled_off")
	private Date scheduledOff;

	private String selection;

	@Column(name="selection_id")
	private int selectionId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="settled_date")
	private Date settledDate;

	@Column(name="sports_id")
	private int sportsId;

	@Column(name="volume_matched")
	private float volumeMatched;

	@Column(name="win_flag")
	private int winFlag;

	public HorseEvent() {
	}

	public double getId() {
		return this.id;
	}

	public void setId(double id) {
		this.id = id;
	}

	public Date getActualOff() {
		return this.actualOff;
	}

	public void setActualOff(Date actualOff) {
		this.actualOff = actualOff;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCourse() {
		return this.course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getEvent() {
		return this.event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public double getEvent_ID() {
		return this.event_ID;
	}

	public void setEvent_ID(double event_ID) {
		this.event_ID = event_ID;
	}

	public Date getFirstTaken() {
		return this.firstTaken;
	}

	public void setFirstTaken(Date firstTaken) {
		this.firstTaken = firstTaken;
	}

	public String getFullDescription() {
		return this.fullDescription;
	}

	public void setFullDescription(String fullDescription) {
		this.fullDescription = fullDescription;
	}

	public String getInPlay() {
		return this.inPlay;
	}

	public void setInPlay(String inPlay) {
		this.inPlay = inPlay;
	}

	public Date getLatestTaken() {
		return this.latestTaken;
	}

	public void setLatestTaken(Date latestTaken) {
		this.latestTaken = latestTaken;
	}

	public double getNumberBets() {
		return this.numberBets;
	}

	public void setNumberBets(double numberBets) {
		this.numberBets = numberBets;
	}

	public double getOdds() {
		return this.odds;
	}

	public void setOdds(double odds) {
		this.odds = odds;
	}

	public Date getScheduledOff() {
		return this.scheduledOff;
	}

	public void setScheduledOff(Date scheduledOff) {
		this.scheduledOff = scheduledOff;
	}

	public String getSelection() {
		return this.selection;
	}

	public void setSelection(String selection) {
		this.selection = selection;
	}

	public int getSelectionId() {
		return this.selectionId;
	}

	public void setSelectionId(int selectionId) {
		this.selectionId = selectionId;
	}

	public Date getSettledDate() {
		return this.settledDate;
	}

	public void setSettledDate(Date settledDate) {
		this.settledDate = settledDate;
	}

	public int getSportsId() {
		return this.sportsId;
	}

	public void setSportsId(int sportsId) {
		this.sportsId = sportsId;
	}

	public float getVolumeMatched() {
		return this.volumeMatched;
	}

	public void setVolumeMatched(float volumeMatched) {
		this.volumeMatched = volumeMatched;
	}

	public int getWinFlag() {
		return this.winFlag;
	}

	public void setWinFlag(int winFlag) {
		this.winFlag = winFlag;
	}

}