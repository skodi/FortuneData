package uk.co.lnssolutions.Fortune.data.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class HorseEventFrame {

	@Id
	private double eventID; // Unique eventID of this event

   @OneToMany(mappedBy = "horseEventFrame", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
   // @OneToMany(mappedBy = "company", fetch = FetchType.EAGER)
	private ArrayList<HorseSelection> selections; // Array of horses in this event
	private boolean inPlay;
	private double totalVolume;
	private double numberBets;
	private Date settled;

	// Classification fields
	private double numRunners;
	private double numWinners; // Each way check
	private double winnerID; // Hold the reference to the winner
	private boolean favWasWinner;
	private boolean favWasPlaced;
	private double favOddsAtOff;

	/**
	 * This method accepts a HorseSelection record and updates the Frame.
	 * 
	 * Based on the selectionID : - if not in frame {Add selection, increment
	 * runners, if winner set winnerID - if in frame, get existing selection if
	 * incoming is newer,
	 * 
	 * @param incoming
	 * @return
	 */


	/**
	 * Get existing selection ID -
	 * 
	 * @param selectionID
	 * @return
	 */
	public HorseSelection getHorseSelection(int ID) {
		if (selections != null) {
			for (HorseSelection s : selections) {
				if (s.getSelection_id() == ID)
					return s;
			}
		}

		return null;
	}

	/*
	 * Return collection of selections in this event
	 * 
	 */
	public ArrayList<HorseSelection> getSelections() {
		return selections;
	}

	public void addSelection(HorseSelection in){
		selections.add(in);
	}
	
	public double getEventID() {
		return eventID;
	}

	public void setEventID(double eventID) {
		this.eventID = eventID;
	}

	public boolean isInPlay() {
		return inPlay;
	}

	public void setInPlay(boolean inPlay) {
		this.inPlay = inPlay;
	}

	public double getTotalVolume() {
		return totalVolume;
	}

	public void setTotalVolume(double totalVolume) {
		this.totalVolume = totalVolume;
	}

	public double getNumberBets() {
		return numberBets;
	}

	public void setNumberBets(double numberBets) {
		this.numberBets = numberBets;
	}

	public Date getSettled() {
		return settled;
	}

	public void setSettled(Date settled) {
		this.settled = settled;
	}

	public double getNumRunners() {
		return numRunners;
	}

	public void setNumRunners(double numRunners) {
		this.numRunners = numRunners;
	}

	public double getNumWinners() {
		return numWinners;
	}

	public void setNumWinners(double numWinners) {
		this.numWinners = numWinners;
	}

	public double getWinnerID() {
		return winnerID;
	}

	public void setWinnerID(double winnerID) {
		this.winnerID = winnerID;
	}

	public boolean isFavWasWinner() {
		return favWasWinner;
	}

	public void setFavWasWinner(boolean favWasWinner) {
		this.favWasWinner = favWasWinner;
	}

	public boolean isFavWasPlaced() {
		return favWasPlaced;
	}

	public void setFavWasPlaced(boolean favWasPlaced) {
		this.favWasPlaced = favWasPlaced;
	}

	public double getFavOddsAtOff() {
		return favOddsAtOff;
	}

	public void setFavOddsAtOff(double favOddsAtOff) {
		this.favOddsAtOff = favOddsAtOff;
	}

	public void setSelections(ArrayList<HorseSelection> selections) {
		this.selections = selections;
	}

	
}
