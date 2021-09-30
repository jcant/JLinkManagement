package com.gmail.gm.jcant.JLinkManagement.JPA.RootLink;

import org.hibernate.annotations.Proxy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Proxy(lazy = false)
public class JRootLink {
	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false, unique = true)
	private String url;

	@Column(nullable = false)
	private boolean enabled;

	public JRootLink() {
	}

	public JRootLink(String url) {
		this.url = url;
		this.enabled = true;
	}

	public JRootLink(String url, boolean enabled) {
		this.url = url;
		this.enabled = enabled;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "JRootLink{" + "id=" + id + ", url='" + url + '\'' + ", enabled=" + enabled + '}';
	}
}
