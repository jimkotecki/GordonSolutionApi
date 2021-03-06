package com.sgg.hibernate;

// Generated Jun 17, 2011 6:49:31 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Message generated by hbm2java
 */
@Entity
@Table(name = "message")
public class Message implements java.io.Serializable {

	private Integer id;
	private String message;
	private int userId;
	private Integer convoId;
	private Date updateDate;
	private Date createDate;
	private Boolean active;
	private Boolean deleted;
	private Date deleteDate;

	public Message() {
	}

	public Message(String message, int userId) {
		this.message = message;
		this.userId = userId;
	}

	public Message(String message, int userId, Integer convoId,
			Date updateDate, Date createDate, Boolean active, Boolean deleted,
			Date deleteDate) {
		this.message = message;
		this.userId = userId;
		this.convoId = convoId;
		this.updateDate = updateDate;
		this.createDate = createDate;
		this.active = active;
		this.deleted = deleted;
		this.deleteDate = deleteDate;
	}

	@SequenceGenerator(name = "generator", sequenceName = "message_id_seq")
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "message", nullable = false, length = 10000)
	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Column(name = "user_id", nullable = false)
	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Column(name = "convo_id")
	public Integer getConvoId() {
		return this.convoId;
	}

	public void setConvoId(Integer convoId) {
		this.convoId = convoId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_date", length = 35)
	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date", length = 35)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "active")
	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Column(name = "deleted")
	public Boolean getDeleted() {
		return this.deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "delete_date", length = 35)
	public Date getDeleteDate() {
		return this.deleteDate;
	}

	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}

}
