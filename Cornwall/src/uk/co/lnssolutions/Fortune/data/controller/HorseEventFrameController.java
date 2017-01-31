package uk.co.lnssolutions.Fortune.data.controller;

import java.util.ArrayList;

import uk.co.lnssolutions.Fortune.data.model.HorseEventFrame;
import uk.co.lnssolutions.Fortune.data.model.HorseSelection;

/**
 * Analysis class for HorseEventFrame
 * 
 * Contains utility methods to update HorseEventFrame
 * 
 * Contains classifier methods to add analytics to an Event Frame
 * 
 * @author eliotpicken
 *
 */
public class HorseEventFrameController {

	public int updateHorseSelection(HorseEventFrame frame,HorseSelection incoming) {
		// Assume if this is called, we have a selections object
		if (frame.getSelections() == null)
			frame.setSelections(new ArrayList<HorseSelection>());

		double eventID = incoming.getEvent_ID();
		frame.setEventID(eventID);

		// Get existing Selection
		incoming.setMin_odds(incoming.getOdds());
		incoming.setMax_odds(incoming.getOdds());
		for (HorseSelection existing : frame.getSelections()) {
			if (existing.getSelection_id() == incoming.getSelection_id()) {
				// We have an update
				// figure out which is newer
				if (existing.getLatest_taken().before(incoming.getLatest_taken())) {
					// We have a later record
					// So we need to update existing record in the frame with
					// latest_taken datetime, odds and anything we want to do
					// with volumes and totals
					existing.setLatest_taken(incoming.getLatest_taken());
					existing.setOdds(incoming.getOdds());
					existing.setTotal_volume_matched(existing.getTotal_volume_matched() + incoming.getVolume_matched());
					existing.setTotal_number_bets(existing.getTotal_number_bets() + incoming.getTotal_number_bets());
				}

				// regardless of timestamp, update min/max odds
				if (existing.getMax_odds() < incoming.getMax_odds())
					existing.setMax_odds(incoming.getMax_odds());
				if (existing.getMin_odds() > incoming.getMin_odds())
				{
					//System.out.println("Incoming min odds : "+incoming.getMin_odds());
					//System.out.println("Existing min odds : "+existing.getMin_odds());
					existing.setMin_odds(incoming.getMin_odds());
							
				}
				System.out.println("Updating odds for " + existing.getSelection()+ "\t odds : " + existing.getOdds()+"\t min : "+existing.getMin_odds()+"\t max : "+existing.getMax_odds());
			}		
		}
		return 0;
	}

	public void addHorseSelection(HorseEventFrame frame,HorseSelection data) {
		if (frame.getSelections() == null)
			frame.setSelections(new ArrayList<HorseSelection>());
		
		//System.out.format("Adding new selection " + data.getSelection() + "\t" + data.getOdds());
		//System.out.println(" For selection id " + data.getSelection_id());
		data.setTotal_number_bets(data.getNumber_bets());
		data.setTotal_volume_matched(data.getVolume_matched());
		double eventID = data.getEvent_ID();
		frame.setSettled(data.getSettled_date());
		frame.setEventID(eventID);
		data.setMin_odds(data.getOdds());
		data.setMax_odds(data.getOdds());
		frame.setNumRunners(frame.getSelections().size());
		frame.addSelection(data);
		System.out.println("Adding Selection with odds of : " + data.getOdds()+"\t min : "+data.getMin_odds()+"\t  max : "+data.getMax_odds());
		
	}

	/////////////////////////////////////////////////////////
	//
	// Analysis methods below here
	//

	/**
	 * Utility method to call the various calc methods - expectd to be called
	 * prior to a persist;
	 */
	public void doCalcs(HorseEventFrame frame) {
		HorseSelection fav = getFovourite(frame);
		getWinners(frame);

	}

	/**
	 * Method to find Favourite at off
	 * 
	 * Also updats Frame with Fav's odds and win flag.
	 * 
	 * 
	 */
	private HorseSelection getFovourite(HorseEventFrame frame) {
		double odds = 999999999;
		HorseSelection result = new HorseSelection();
		for (HorseSelection selection : frame.getSelections()) {
			if (selection.getOdds() < odds) {
				result = selection;
				odds = result.getOdds();
			}
		}

		frame.setFavOddsAtOff(result.getOdds());
		if (result.getWin_flag() == 1)
		   frame.setFavWasWinner(true);
		return result;
	}

	/**
	 * Get the winner of an event (assumed to be only one - ie. no each way
	 * here)
	 * 
	 * @return
	 */
	private ArrayList getWinners(HorseEventFrame frame) {

		int winflag = 0;
		ArrayList<Integer> winnerIDs = new ArrayList<Integer>();
		ArrayList<HorseSelection> winners = new ArrayList<HorseSelection>();
		for (HorseSelection selection : frame.getSelections()) {
			if (selection.getWin_flag() > winflag) {
				winners.add(selection);
				winnerIDs.add(selection.getSelection_id());
			}
		}
		frame.setNumWinners(winnerIDs.size());

		return winners;
	}

}
