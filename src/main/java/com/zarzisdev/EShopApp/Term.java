package com.zarzisdev.EShopApp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "term", uniqueConstraints = @UniqueConstraint(columnNames = { "fr", "en", "de" }))
public class Term implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String fr;
	private String en;
	private String de;
	@Temporal(TemporalType.DATE)
	private Date createdAt;

	public Term(String fr, String en, String de, Date createdAt) {
		super();
		this.fr = fr;
		this.en = en;
		this.de = de;
		this.createdAt = createdAt;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Term() {
		super();
	}

	public String getFr() {
		return fr;
	}

	public void setFr(String fr) {
		this.fr = fr;
	}

	public String getEn() {
		return en;
	}

	public void setEn(String en) {
		this.en = en;
	}

	public String getDe() {
		return de;
	}

	public void setDe(String de) {
		this.de = de;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
