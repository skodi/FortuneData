package uk.co.lnssolutions.Fortune.data;

import java.util.Date;

import uk.co.lnssolutions.Fortune.data.jdbc.SimpleDataAccessController;

public class UpdateHorseData {

	public static void main(String[] args) {
		//TODO Get logging setup.
		Date start = new java.util.Date();
		
		// TODO Need to mark file as processed somehow.  Rename extension seems right
		LoadStagingFileFromZip fm = new LoadStagingFileFromZip();
		//fm.run();
		
		// Extract EventIDs ready to create rolling Frames
		ProcessStagingTable pst = new ProcessStagingTable();
		pst.run();

		Date end = new java.util.Date();
		
		Long runTime = end.getTime() - start.getTime();
	    System.out.println("Run time "+runTime/1000);
		
	}

}
