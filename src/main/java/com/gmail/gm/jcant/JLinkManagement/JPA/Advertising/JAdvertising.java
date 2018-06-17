package com.gmail.gm.jcant.JLinkManagement.JPA.Advertising;

import javax.persistence.*;
import java.util.Date;

@Entity
public class JAdvertising {
    @Id
    @GeneratedValue
    private long id;

    private String company;

    private Date pubStart;

    private Date pubFinish;

    private String header;

    private String text;

    public JAdvertising() {
    }

    public JAdvertising(String company, Date pubStart, Date pubFinish, String header, String text) {
        this.company = company;
        this.pubStart = pubStart;
        this.pubFinish = pubFinish;
        this.header = header;
        this.text = text;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("JAdvertising{");
        sb.append("id=").append(id);
        sb.append(", company='").append(company).append('\'');
        sb.append(", pubStart=").append(pubStart);
        sb.append(", pubFinish=").append(pubFinish);
        sb.append(", header='").append(header).append('\'');
        sb.append(", text='").append(text).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
