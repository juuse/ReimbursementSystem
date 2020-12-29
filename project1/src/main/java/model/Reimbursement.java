package model;

import java.sql.Timestamp;
/**
 * The class representing the reimbursements in Java
 * contains all parts of the reimbursement joined onto the
 * lookup tables and creates string versions
 * of timestamps to access in javascript when sent  
 * @author Justin 
 *
 */
public class Reimbursement {
	
	/**
	 * An enum to represent the 
	 * Reimbursement statuses
	 * PENDING, APPROVED, DENIED
	 */
	public enum ReimbStatus{
		PENDING,
		APPROVED,
		DENIED
	}
	
	/**
	 * A enum to represent the 
	 * Reimbursement types
	 * LODGING, TRAVEL, FOOD, OTHER
	 */
	public enum ReimbType{
		LODGING,
		TRAVEL,
		FOOD,
		OTHER
	}
	
	private int reimbID;
	private double reimbAmount;
	private Timestamp reimbSubmitted;
	private Timestamp reimbResolved;
	private String reimbDescription;
	private int reimbAuthor;
	private int reimbResolver;
	private int reimbStatusID;
	private int reimbTypeID;
	private ReimbStatus rStatus;
	private ReimbType rType;
	private String reimbSubTime;
	private String reimbResolvTime;
	
	
	public Reimbursement() {
	}

	public Reimbursement(int reimbID, double reimbAmount, Timestamp reimbSubmitted, Timestamp reimbResolved,
			String reimbDescription, int reimbAuthor, int reimbResolver, int reimbStatusID, int reimbTypeID,
			ReimbStatus rStatus, ReimbType rType) {
		super();
		this.reimbID = reimbID;
		this.reimbAmount = reimbAmount;
		this.reimbSubmitted = reimbSubmitted;
		this.reimbResolved = reimbResolved;
		this.reimbDescription = reimbDescription;
		this.reimbAuthor = reimbAuthor;
		this.reimbResolver = reimbResolver;
		this.reimbStatusID = reimbStatusID;
		this.reimbTypeID = reimbTypeID;
		this.rStatus = rStatus;
		this.rType = rType;
		this.reimbSubTime = this.reimbSubmitted.toString();
		this.reimbResolvTime = this.reimbResolved != null ? this.reimbResolved.toString() : "";
	}

	public String getReimbSubTime() {
		return reimbSubTime;
	}

	public void setReimbSubTime(String reimbSubTime) {
		this.reimbSubTime = reimbSubTime;
	}

	public String getReimbResolvTime() {
		return reimbResolvTime;
	}

	public void setReimbResolvTime(String reimbResolvTime) {
		this.reimbResolvTime = reimbResolvTime;
	}

	public int getReimbID() {
		return reimbID;
	}

	public void setReimbID(int reimbID) {
		this.reimbID = reimbID;
	}

	public double getReimbAmount() {
		return reimbAmount;
	}

	public void setReimbAmount(double reimbAmount) {
		this.reimbAmount = reimbAmount;
	}

	public Timestamp getReimbSubmitted() {
		return reimbSubmitted;
	}

	public void setReimbSubmitted(Timestamp reimbSubmitted) {
		this.reimbSubmitted = reimbSubmitted;
	}

	public Timestamp getReimbResolved() {
		return reimbResolved;
	}

	public void setReimbResolved(Timestamp reimbResolved) {
		this.reimbResolved = reimbResolved;
	}

	public String getReimbDescription() {
		return reimbDescription;
	}

	public void setReimbDescription(String reimbDescription) {
		this.reimbDescription = reimbDescription;
	}

	public int getReimbAuthor() {
		return reimbAuthor;
	}

	public void setReimbAuthor(int reimbAuthor) {
		this.reimbAuthor = reimbAuthor;
	}

	public int getReimbResolver() {
		return reimbResolver;
	}

	public void setReimbResolver(int reimbResolver) {
		this.reimbResolver = reimbResolver;
	}

	public int getReimbStatusID() {
		return reimbStatusID;
	}

	public void setReimbStatusID(int reimbStatusID) {
		this.reimbStatusID = reimbStatusID;
	}

	public int getReimbTypeID() {
		return reimbTypeID;
	}

	public void setReimbTypeID(int reimbTypeID) {
		this.reimbTypeID = reimbTypeID;
	}

	public ReimbStatus getrStatus() {
		return rStatus;
	}

	public void setrStatus(ReimbStatus rStatus) {
		this.rStatus = rStatus;
	}

	public ReimbType getrType() {
		return rType;
	}

	public void setrType(ReimbType rType) {
		this.rType = rType;
	}

	@Override
	public String toString() {
		return "Reimbursement [reimbID=" + reimbID + ", reimbAmount=" + reimbAmount + ", reimbSubmitted="
				+ reimbSubmitted + ", reimbResolved=" + reimbResolved + ", reimbDescription=" + reimbDescription
				+ ", reimbAuthor=" + reimbAuthor + ", reimbResolver=" + reimbResolver + ", reimbStatusID="
				+ reimbStatusID + ", reimbTypeID=" + reimbTypeID + ", rStatus=" + rStatus + ", rType=" + rType
				+ ", reimbSubTime=" + reimbSubTime + ", reimbResolvTime=" + reimbResolvTime + "]";
	}

	
	
	
}
