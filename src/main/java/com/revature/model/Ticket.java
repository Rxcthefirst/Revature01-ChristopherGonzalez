package com.revature.model;

import com.revature.util.DataTransferObject;

public class Ticket implements DataTransferObject {
	
	private int ticketID;
	private int userID;
	private String userFirstName;
	private String userLastName;
	private String description;
	private String status;
	
	public Ticket() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ticket(int ticketID, int userID, String userFirstName, String userLastName, String description,
			String status) {
		super();
		this.ticketID = ticketID;
		this.userID = userID;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.description = description;
		this.status = status;
	}

	public int getTicketID() {
		return ticketID;
	}

	public void setTicketID(int ticketID) {
		this.ticketID = ticketID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
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
				+ ", userLastName=" + userLastName + ", description=" + description + ", status=" + status + "]";
	}

	@Override
	public long getId() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	

}
