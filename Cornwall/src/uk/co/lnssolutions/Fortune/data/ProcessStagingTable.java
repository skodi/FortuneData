package uk.co.lnssolutions.Fortune.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import uk.co.lnssolutions.Fortune.data.controller.HorseEventFrameController;
import uk.co.lnssolutions.Fortune.data.jdbc.SimpleDataAccessController;
import uk.co.lnssolutions.Fortune.data.model.HorseEventFrame;
import uk.co.lnssolutions.Fortune.data.model.HorseSelection;
import uk.co.lnssolutions.Fortune.data.model.Stg_HorseEvent;

/**
 * Class works on a staging table to update data frames based on events
 * contained in the staging data
 * 
 * Limited to horse events at this time
 * 
 * @author eliotpicken
 *
 */
public class ProcessStagingTable {

	// Get eventIDs

	// For each event ID, create frame

	// For each frame, classify type check if new frame or a merge is needed

	// If new, calculate selections, latest odds, money, race type and persist

	// If not new, grab old frame from db and merge.

	public void run() {

		// --------------------------------------------------
		// Get EventIDs

		// Setup DAO
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Dorset");
		EntityManager em = factory.createEntityManager();

		SimpleDataAccessController DAO = new SimpleDataAccessController();
		List eventIDs = DAO.getEventIDsFromStagingTable();

		if (eventIDs != null) {

			Object o = eventIDs.get(10);
			System.out.println(o.getClass().getName());
			Double eventID = (Double) eventIDs.get(10);
			System.out.println("Sample :" + eventID);

			boolean isUpdateToFrame = false;

			List<HorseSelection> runners = DAO.getSelectionsForEventID(eventID);
			System.out.println("Runners size " + runners.size());

			// eventID = Double.parseDouble("101215230");
			// 118633699 E8
			//
			/*
			 * select
			 * ID,COUNTRY,COURSE,EVENT,FULL_DESCRIPTION,SELECTION,EVENT_ID,
			 * SELECTION_ID,ODDS,VOLUME_MATCHED,WIN_FLAG,IN_PLAY from HORSEEVENT
			 * where event_id = '118633699' and IN_PLAY = 'NI';
			 */

			// TODO Reduce Frame
			// We create a Frame, and our selections to it.
			// TODO Check to see if this eventID exists in DB, and set
			// isUpdateFrame accordingly
			try {
				HorseEventFrame frame = em.find(HorseEventFrame.class, eventID);
				if (frame == null) {
					frame = new HorseEventFrame();
					isUpdateToFrame = false;
				} else
					isUpdateToFrame = true;

				// HorseEventFrame frame = new HorseEventFrame();

				// TODO check if the frame exists (by eventID) in the database
				// already
				/*
				 * This can happen if the market is open across more than one
				 * week's data The plan here is to work out of the event
				 * scheduled date falls inside the
				 */

				Date startDate = DAO.getStartDateOfStagingData();
				Date endData = DAO.getEndtDateOfStagingData();

				// Create a controller for this frame
				HorseEventFrameController frameController = new HorseEventFrameController();
				
				
				// So let's iterate through the list
				for (HorseSelection selection : runners) {
					System.out.println(selection.getCourse() + "\t" + selection.getSelection() + "\t"
							+ selection.getLatest_taken());

					// Check if selection id present in Frame
					HorseSelection previous = frame.getHorseSelection(selection.getSelection_id());
					if (previous == null) {
						frameController.addHorseSelection(frame,selection);
						// This selection has not been stored before
					}
					if (previous != null) {
						// keep the newer one
						frameController.updateHorseSelection(frame,selection);
					}
				}

				// Frame keeps it's own meta data in check, by code in the
				// {add|update} horse selection method

				// So by this point, we should have a basic frame in place.
				// We'll write it our for now, before we try to persist it.
				System.out.println("----------------------------");
				System.out.println(getHorseEventFrameSummaryReport(frame));

				// Persist Frame.

				frameController.doCalcs(frame);
				if (isUpdateToFrame) {
					em.getTransaction().begin();
					em.merge(frame);
					em.getTransaction().commit();
				} else {
					em.getTransaction().begin();
					em.merge(frame);
					em.getTransaction().commit();
				}
			} catch (Exception e) {
				System.out.println("Error Persisting Frame : " + e.toString());
				e.printStackTrace();
			}
		}
	}

	public String getHorseEventFrameSummaryReport(HorseEventFrame event) {
		StringBuffer results = new StringBuffer();
		ArrayList<HorseSelection> selections = (ArrayList<HorseSelection>) event.getSelections();
		for (HorseSelection selection : selections) {
			results.append(selection.getEvent_ID() + "\t");
			results.append(selection.getSelection_id() + "\t");
			results.append(selection.getSelection() + "\t");
			results.append(selection.getLatest_taken() + "\t");
			results.append(selection.getOdds() + "\t");
			results.append(selection.getMin_odds() + "\t");
			results.append(selection.getMax_odds() + "\t");
			results.append(selection.getWin_flag() + "\t");
			results.append(selection.getEvent() + "\t");
			results.append(selection.getVolume_matched() + "\t");
			results.append(selection.getTotal_volume_matched() + "\t");
			results.append("\n");
		}
		return results.toString();
	}

}
