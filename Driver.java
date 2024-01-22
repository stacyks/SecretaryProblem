package main;
import java.util.*;

public class Driver {
	protected static final double [] WEIGHTS = {.3, .25, .2, .15, .1};
	private static final String [] QUALITIES = 
		{"Work Experience", "Communication Skills", "Time Management", "Organization", "Punctuality"};
	//	private Applicant bestApplicant = new Applicant (0);
	private Set <Applicant> applicantPool = new HashSet <Applicant> ();
	//	private String [] qualityRanking = new String [5];
	protected static String [] qualityHierarchy = QUALITIES;


	public Driver () {
		//		Scanner keyboard = new Scanner (System.in);
		//
		//		//set up: create rank for each quality of a secretary
		//		System.out.println("SECRETARY QUALIFICATIONS");
		//		for (int i = 0; i < QUALITIES.length; i++) {
		//			System.out.println(QUALITIES[i]);
		//		}
		//
		//		System.out.println("----");
		//		System.out.println("Rank each quality, 1-5 (1 being most important): ");
		//
		//
		//		for (int i = 0; i < QUALITIES.length; i++) {
		//			System.out.print(QUALITIES[i] + ":");
		//
		//			int rank = keyboard.nextInt();
		//			while (rank < 1 || rank > 5 || qualityRanking[rank-1] != null) {
		//				System.out.println("Rank not within range, or ranking already filled.");
		//				System.out.print(QUALITIES[i] + ":");
		//				rank = keyboard.nextInt();
		//			}
		//
		//			qualityRanking[rank-1] = QUALITIES[i];
		//		}

		//determine how many applicants are in the pool. 
		//make sure this number isn't 0
//				System.out.println("Applicants to generate: ");
//				int numApplicants = keyboard.nextInt();

		//determine how many trials of this should be conducted. 
//						System.out.println("Number of trials: ");
//						int trials = keyboard.nextInt();


		int numWorks = 0;
		
		for (int i = 0; i < 1000000; i++) {
			boolean result = runTrial((int) (Math.random()*100000)+1);
			if (result) {
				numWorks++;
			}
		}

		System.out.println((double) (numWorks)/1000000);


		//		keyboard.close();
	}


	private class Trial {
		private Iterator <Applicant> iter = applicantPool.iterator();

		public Applicant findBestSoFarApplicant (int numApplicants) {

			//determine the the stopping applicant index (stop looking after 37%)
			int stoppingApplicantIndex = (int) (Math.round(((double) (1)/Math.E)*numApplicants));
			//find the stopping applicant via rule.

			//does take up a bit of space.
			Applicant bestSoFar = new Applicant (0);

			while(iter.hasNext()) {
				Applicant curr = iter.next();

				if (curr.getFinalScore() > bestSoFar.getFinalScore()) {

					//looking: determining the best so far
					if (curr.getApplicantNumber() <= stoppingApplicantIndex) {
						bestSoFar = curr;
						//leaping: next applicant that is better than the ones in the look phase is selected.
					} else {
						return curr;
					}

				}

			}

			return bestSoFar;

		}

	}

	public boolean runTrial (int numApplicants) {

		Applicant bestApplicant = findBestApplicant(numApplicants);
		Trial trial = new Trial ();
		Applicant bestSoFarApplicant = trial.findBestSoFarApplicant(numApplicants);	

		//reset trial for next one.
		applicantPool.clear();
		Applicant.applicantCount = 0;

//		System.out.println("bestApp: " + bestApplicant.getApplicantNumber() + " guessApp: " + bestSoFarApplicant.getApplicantNumber());
		return bestSoFarApplicant.getApplicantNumber() == bestApplicant.getApplicantNumber();

	}

	private Applicant findBestApplicant (int numApplicants) {
		Applicant bestApplicant = new Applicant (0);

		//finds the best applicant BF style		
		while (applicantPool.size() != numApplicants) {
			Applicant toAdd = new Applicant (1);
			applicantPool.add(toAdd);

			if (toAdd.getFinalScore() > bestApplicant.getFinalScore()) {
				bestApplicant = toAdd;
			}
		}

//		System.out.println("pool size: " + applicantPool.size());
		return bestApplicant;
	}


	public static void main (String [] args) {
		Driver x = new Driver ();
	}
}