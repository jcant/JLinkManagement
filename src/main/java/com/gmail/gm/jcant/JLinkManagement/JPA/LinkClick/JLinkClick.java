package com.gmail.gm.jcant.JLinkManagement.JPA.LinkClick;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Proxy;

import com.gmail.gm.jcant.JLinkManagement.JPA.Link.JLink;

@Entity
@Proxy(lazy = false)
public class JLinkClick {
	@Id
	@GeneratedValue
	private long id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "link_id")
	private JLink link;

	private Date date;

	private String country;

	public JLinkClick() {
	}

	public JLinkClick(JLink link, Date date, String country) {
		this.link = link;
		this.date = date;
		this.country = country;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public JLink getLink() {
		return link;
	}

	public void setLink(JLink link) {
		this.link = link;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("JLinkClick [id=");
		builder.append(id);
		//builder.append(", link=");
		//builder.append(link);
		builder.append(", date=");
		builder.append(date);
		builder.append(", country=");
		builder.append(country);
		builder.append("]");
		return builder.toString();
	}

}
