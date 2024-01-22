package main;
import java.util.*;

public class Applicant {
	
	protected static int applicantCount = 0;
	private int applicantNumber;
	private double finalScore = 0;
	
	public Applicant (int type) {
		
		applicantNumber = applicantCount;
		applicantCount++;
		
		if (type == 0) {
			finalScore = 0;
			return;
		}
		
		
//		for (int i = 0; i < Driver.qualityHierarchy.length; i++) {
		for (int i = 0; i < 5; i++) {
			double score = Math.random()*5;
			finalScore += score*Driver.WEIGHTS[i];
		}
	}
	

	
	public double getFinalScore () {
		return finalScore;
	}
	
	public int getApplicantNumber () {
		return applicantNumber;
	}
	
	public String toString () {
		return "#" + applicantNumber + " " + finalScore;
	}
	
	
}
