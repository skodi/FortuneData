/** So this is a cheat.
 * 
 * This class will use straight JBBC to return a 5K long list of every ecent ID 
 * in the staging table.  JBP doesn't like it.
 */
package uk.co.lnssolutions.Fortune.data.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import uk.co.lnssolutions.Fortune.data.model.HorseSelection;
import uk.co.lnssolutions.Fortune.data.model.Stg_HorseEvent;

public class SimpleDataAccessController {

	private boolean connected;
	private String url = "jdbc:mysql://localhost:3306/fortune_data";
	private String user = "epicken";
	private String password = "peY2tm";

	public SimpleDataAccessController() {
		connected = false;
	}

	public List getEventIDsFromStagingTable() {

		List<Double> eventIDs = new ArrayList<Double>();
		try {

			// Establish connection to MySQL
			Connection conn = DriverManager.getConnection(url, user, password);

			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery("SELECT distinct EVENT_ID FROM Stg_HorseEvent stg;");
			// So do we get this ?

			while (results.next()) {
				Double i = results.getDouble(1);
				eventIDs.add(i);
			}
			results.close();
			stmt.close();
			System.out.println("Results size " + eventIDs.size());
			stmt.close();
			conn.close();

		} catch (Exception e) {
			System.out.println("Direct JDBC Driver error " + e.toString());
		}
		return eventIDs;
	}

	/**
	 * DAO method to pull all Selections for a given Event ID
	 * 
	 * At this stage, we'll just use regular JDBC please.
	 * 
	 * @param eventID
	 * @return
	 */
	public List getSelectionsForEventID(Double eventID) {
        
		/** In theory, this might work.  I'll never find out as it's too slow
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Dorset");
		EntityManager em = factory.createEntityManager();
		
		TypedQuery<HorseSelection> query = em.createQuery("Select s from Stg_HorseEvent s where s.event_ID = :eventID",HorseSelection.class); //.getResultList();
		List<HorseSelection> selections = query.setParameter("eventID",eventID).getResultList();
		*/
		
		List<HorseSelection> selections = new ArrayList<HorseSelection>();
		try {

			// Establish connection to MySQL
			Connection conn = DriverManager.getConnection(url, user, password);

			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery("SELECT * FROM Stg_HorseEvent stg where event_ID = '"+eventID+"';");
			// So do we get this ?

			while (results.next()) {
				HorseSelection selection = new HorseSelection();
				selection.setId(results.getDouble(1));
				selection.setActual_off(results.getDate(2));
				selection.setCountry(results.getString(3));
				selection.setCourse(results.getString(4));
				selection.setEvent(results.getString(5));
				selection.setEvent_ID(results.getDouble(6));
				selection.setFirst_taken(results.getDate(7));
				selection.setFull_description(results.getString(8));
                selection.setIn_play(results.getString(9));
                selection.setLatest_taken(results.getDate(10));
                selection.setNumber_bets(results.getDouble(11));
                selection.setOdds(results.getDouble(12));
                selection.setScheduled_off(results.getDate(13));
                selection.setSelection(results.getString(14));
                selection.setSelection_id(results.getInt(15));
                selection.setSettled_date(results.getDate(16));
                selection.setSports_ID(results.getInt(17));
                selection.setVolume_matched(results.getFloat(18));
                selection.setWin_flag(results.getInt(19));
                
                //TODO handle nulls
				selections.add(selection);
			}
			results.close();
			stmt.close();
			System.out.println("Results List size " + selections.size());

			conn.close();

		} catch (Exception e) {
			System.out.println("Direct JDBC Driver error " + e.toString());
		}
		return selections;
	}
	
	/** To detect bets placed on selections outside for events not completed during the staging data, we need 
	 * to track the start and end date of the staging data.
	 * @return
	 */
	public Date getStartDateOfStagingData(){
		try {

			// Establish connection to MySQL
			Connection conn = DriverManager.getConnection(url, user, password);

			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery("select min(scheduled_off) from STG_HORSEEVENT;");

			Date startDate = null;
			while (results.next()) {
				 startDate = results.getDate(1);
			}
			results.close();
			stmt.close();
            return startDate;

		} catch (Exception e) {
			System.out.println("Direct JDBC Driver error " + e.toString());
		}
		return null;
	}
	
	
	/** To detect bets placed on selections outside for events not completed during the staging data, we need 
	 * to track the start and end date of the staging data.
	 * @return
	 */
	public Date getEndtDateOfStagingData(){
		try {

			// Establish connection to MySQL
			Connection conn = DriverManager.getConnection(url, user, password);

			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery("select min(scheduled_off) from STG_HORSEEVENT;");

			Date endDate = null; 
			while (results.next()) {
				 endDate = results.getDate(1);
			}
			results.close();
			stmt.close();
            return endDate;

		} catch (Exception e) {
			System.out.println("Direct JDBC Driver error " + e.toString());
		}
		return null;
	}
	
	
}


