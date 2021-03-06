package uk.co.lnssolutions.Fortune.data;

import java.io.BufferedReader;
import java.io.File;
//import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
//import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
//import java.util.zip.ZipInputStream;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
//import javax.swing.filechooser.FileNameExtensionFilter;

public class LoadStagingFileFromZip {

	public void run() {
 
		// Setup DAO
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("Cornwall");
		EntityManager em = factory.createEntityManager();

		int zipCount = 0;
		int fileCount = 0;
		double key = 0;
		Date startDate;
		Date endDate;

		File f = new File(DataProcessorConstants.zipFolder);
		if (f.exists()) {
			System.out.println("We have a folder");
			File[] files = f.listFiles(new FilenameFilter() {
				public boolean accept(File directory, String fileName) {
					return fileName.endsWith(".zip");
				}
			});

			startDate = new Date();
			for (File file : files) {
				try {

					ZipFile zf = new ZipFile(file);
					Enumeration entries = zf.entries();

					DateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
					DateFormat df2 = new SimpleDateFormat("dd-MM-yyyy hh:mm");

					// BufferedReader input = new BufferedReader(new
					// InputStreamReader(System.in));
					em.getTransaction().begin();
					while (entries.hasMoreElements()) {
						ZipEntry ze = (ZipEntry) entries.nextElement();

						long size = ze.getSize();
						if (size > 0) {
							System.out.println("Working " + file.getName());
							System.out.println("Length is " + size);

							System.out.println(new Date() + "\t Processed     0 as we've just started");
							startDate = new Date();
							BufferedReader br = new BufferedReader(new InputStreamReader(zf.getInputStream(ze)));
							String line;

							while ((line = br.readLine()) != null) {
								// System.out.println(line);

								// Split csv
								String[] data = line.split(",");
								// System.out.println(data.length);
								String sportsID = data[0];
								Stg_HorseEvent event = new Stg_HorseEvent();

								// Build the object
								if (sportsID.indexOf('7') > 0) {
									try {
										event.setSports_ID(Integer.parseInt(stripQuotes(data[0]))); // SportsID
										event.setEvent_ID(Double.parseDouble(stripQuotes(data[1]))); // Event_IDl
										event.setSettled_date(df.parse(stripQuotes(data[2]))); // Settled_date
										event.setCountry(stripQuotes(data[3])); // Country
										event.setFull_description(stripQuotes(data[4])); // Full
																							// description
										event.setCourse(stripQuotes(data[5])); // Course
										event.setScheduled_off(df2.parse(stripQuotes(data[6]))); // schedule
																									// off
										event.setEvent(stripQuotes(data[7])); // Event
										// event.setActual_off(df.parse(stripQuotes(data[8])));
										// // actual off
										event.setSelection_id(Integer.parseInt(stripQuotes(data[9]))); // selection_id
										event.setSelection(stripQuotes(data[10])); // selection
										event.setOdds(Double.parseDouble(stripQuotes(data[11]))); // odds
										event.setNumber_bets(Double.parseDouble(stripQuotes(data[12]))); // number_bets
										event.setVolume_matched(Float.parseFloat(stripQuotes(data[13]))); // betting
																											// volume
										event.setLatest_taken(df.parse(stripQuotes(data[14]))); // latest
																								// date
																								// of
																								// odds
										event.setFirst_taken(df.parse(stripQuotes(data[15]))); // first
																								// date
																								// of
																								// odds
										event.setWin_flag(Integer.parseInt(stripQuotes(data[16]))); // win
																									// flag
										event.setIn_play(stripQuotes(data[17]));//

										fileCount++;
										// Then hand this off to the DAO

										key++;
										event.setId(key);

										em.persist(event);

									} catch (ParseException pe) {
										System.out.println(pe.toString());
										System.out.println(line);

									}

									/*
									 * "SPORTS_ID", "EVENT_ID", "SETTLED_DATE",
									 * "COUNTRY", "FULL_DESCRIPTION", "COURSE",
									 * "SCHEDULED_OFF", "EVENT", "ACTUAL_OFF",
									 * "SELECTION_ID", "SELECTION", "ODDS",
									 * "NUMBER_BETS", "VOLUME_MATCHED",
									 * "LATEST_TAKEN", "FIRST_TAKEN",
									 * "WIN_FLAG", "IN_PLAY"
									 */

									// End processing that line
									if ((fileCount % 10000) == 0) {
										endDate = new Date();
										long diff = endDate.getTime() - startDate.getTime();
										System.out.println(endDate + "\t Processed " + fileCount + " from file "
												+ ze.getName() + " in " + TimeUnit.MILLISECONDS.toMinutes(diff));
									}
									
									
									// Also commit a bunch
									if ((fileCount % 500) == 0)
									{
										em.getTransaction().commit();
										em.getTransaction().begin();
									}
									
								}

							}
							System.out.println("File contained " + fileCount);
							fileCount = 0;
							br.close();
						}
					}
					em.getTransaction().commit();

					zf.close();
					renameFileExtension(file,".processed");
					
				} catch (FileNotFoundException fnfe) {
					System.out.println("Unable to find file " + file.getName());
				} catch (IOException ioe) {
					System.out.println("IO Exception " + ioe.toString());
				}
			}

		}
	}

	///////////////////////////////////////////////////////////////////
	//
	//  Utility methods
	
	private String stripQuotes(String in) {
		return in.substring(1, in.length() - 1);
	}

	public  boolean renameFileExtension
	  (File source, String newExtension)
	  {
	    String target;
	    String currentExtension = getFileExtension(source.getName());

	    if (currentExtension.equals("")){
	      target = source + "." + newExtension;
	    }
	    else {
	      target = (source.getAbsolutePath()+'/'+source.getName()).replaceFirst(Pattern.quote("." +
	          currentExtension) + "$", Matcher.quoteReplacement("." + newExtension));

	    }
	    return source.renameTo(new File(target));
	  }

	  public  String getFileExtension(String f) {
	    String ext = "";
	    int i = f.lastIndexOf('.');
	    if (i > 0 &&  i < f.length() - 1) {
	      ext = f.substring(i + 1);
	    }
	    return ext;
	  }
	
	
}
