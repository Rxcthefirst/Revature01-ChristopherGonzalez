package com.revature.model;

import com.revature.util.DataTransferObject;

public class Ticket implements DataTransferObject {

	private long ticketID;
	private long userID;
	private Double amount;
	private String description;
	private String status;

	public Ticket() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ticket(long ticketID, long userID, Double amount, String description, String status) {
		super();
		this.ticketID = ticketID;
		this.userID = userID;
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
		return "Ticket [ticketID=" + ticketID + ", userID=" + userID + ", amount=" + amount + ", description=" + description + ", ticket_status="
				+ status + "]";
	}

	@Override
	public long getId() {
		// TODO Auto-generated method stub
		return 0;
	}

}
