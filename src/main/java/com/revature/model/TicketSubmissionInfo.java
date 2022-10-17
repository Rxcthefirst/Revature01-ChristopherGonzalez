package com.revature.model;

public class TicketSubmissionInfo {

	private Double amount;
	private String description;

	public TicketSubmissionInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TicketSubmissionInfo(Double amount, String description) {
		super();
		this.amount = amount;
		this.description = description;
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

	@Override
	public String toString() {
		return "TicketSubmissionInfo [amount=" + amount + ", description=" + description + "]";
	}

}
