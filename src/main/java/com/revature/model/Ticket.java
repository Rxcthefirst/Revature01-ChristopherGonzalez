package com.revature.model;

import com.revature.util.DataTransferObject;

public class Ticket implements DataTransferObject {
	
	private long ticketID;
	private long userID;
	private String userFirstName;
	private String userLastName;
	private Double amount;
	private String description;
	private String status;
	
	public Ticket() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ticket(long ticketID, long userID, String userFirstName, String userLastName, Double amount, String description,
			String status) {
		super();
		this.ticketID = ticketID;
		this.userID = userID;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.amount = amount;
		this.description = description;
		this.status = status;
	}

	public long getTicketID() {
		return ticketID;
	}

	public void setTicketID(long ticketID) {
		this.ticketID = ticketID;
	}

	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}
	
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Ticket [ticketID=" + ticketID + ", userID=" + userID + ", userFirstName=" + userFirstName
				+ ", userLastName=" + userLastName + ", amount=" + amount + ", description=" + description + ", status=" + status + "]";
	}

	@Override
	public long getId() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	

}
