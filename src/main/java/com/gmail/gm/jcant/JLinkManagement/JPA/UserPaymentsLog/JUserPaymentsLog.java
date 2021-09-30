package com.gmail.gm.jcant.JLinkManagement.JPA.UserPaymentsLog;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.gmail.gm.jcant.JLinkManagement.JPA.Link.JLink;
import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUser;

@Entity
public class JUserPaymentsLog {
	@Id
	@GeneratedValue
	private long id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private JUser user;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "link_id")
	private JLink link;

	private Date date;

	private double amount;

	private String paySystem;

	public JUserPaymentsLog() {
	}

	public JUserPaymentsLog(JUser user, JLink link, Date date, double amount, String paySystem) {
		super();
		this.user = user;
		this.link = link;
		this.date = date;
		this.amount = amount;
		this.paySystem = paySystem;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public JUser getUser() {
		return user;
	}

	public void setUser(JUser user) {
		this.user = user;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getPaySystem() {
		return paySystem;
	}

	public void setPaySystem(String paySystem) {
		this.paySystem = paySystem;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("JUserPaymentsLog [id=");
		builder.append(id);
		builder.append(", user=");
		builder.append(user);
		builder.append(", date=");
		builder.append(date);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", paySystem=");
		builder.append(paySystem);
		builder.append("]");
		return builder.toString();
	}

}
