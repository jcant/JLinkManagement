package com.gmail.gm.jcant.JLinkManagement.JPA.Article;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Proxy;

import com.gmail.gm.jcant.JLinkManagement.JPA.User.JUser;

@Entity
@Proxy(lazy = false)
public class JArticle {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private Date created;
	
	private Date pubStart;
	
	private Date pubFinish;
	
	private String header;
	
	@Column(length=10000)
	private String text;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "author")
	private JUser author;

	public JArticle() {
	}

	public JArticle(Date created, Date pubStart, Date pubFinish, String header, String text, JUser author) {
		super();
		this.created = created;
		this.pubStart = pubStart;
		this.pubFinish = pubFinish;
		this.header = header;
		this.text = text;
		this.author = author;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getPubStart() {
		return pubStart;
	}

	public void setPubStart(Date pubStart) {
		this.pubStart = pubStart;
	}

	public Date getPubFinish() {
		return pubFinish;
	}

	public void setPubFinish(Date pubFinish) {
		this.pubFinish = pubFinish;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public JUser getAuthor() {
		return author;
	}

	public void setAuthor(JUser author) {
		this.author = author;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("JArticle [id=");
		builder.append(id);
		builder.append(", created=");
		builder.append(created);
		builder.append(", pubStart=");
		builder.append(pubStart);
		builder.append(", pubFinish=");
		builder.append(pubFinish);
		builder.append(", header=");
		builder.append(header);
		builder.append(", text=");
		builder.append(text);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
}
