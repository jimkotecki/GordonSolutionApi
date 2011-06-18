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
 * Ranking generated by hbm2java
 */
@Entity
@Table(name = "ranking")
public class Ranking implements java.io.Serializable {

	private Integer id;
	private String rankingType;
	private Integer ranking;
	private Integer score;
	private Date updateDate;
	private Date createDate;
	private Boolean active;
	private Boolean deleted;
	private Date deleteDate;

	public Ranking() {
	}

	public Ranking(String rankingType) {
		this.rankingType = rankingType;
	}

	public Ranking(String rankingType, Integer ranking, Integer score,
			Date updateDate, Date createDate, Boolean active, Boolean deleted,
			Date deleteDate) {
		this.rankingType = rankingType;
		this.ranking = ranking;
		this.score = score;
		this.updateDate = updateDate;
		this.createDate = createDate;
		this.active = active;
		this.deleted = deleted;
		this.deleteDate = deleteDate;
	}

	@SequenceGenerator(name = "generator", sequenceName = "ranking_id_seq")
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "ranking_type", nullable = false, length = 15)
	public String getRankingType() {
		return this.rankingType;
	}

	public void setRankingType(String rankingType) {
		this.rankingType = rankingType;
	}

	@Column(name = "ranking")
	public Integer getRanking() {
		return this.ranking;
	}

	public void setRanking(Integer ranking) {
		this.ranking = ranking;
	}

	@Column(name = "score")
	public Integer getScore() {
		return this.score;
	}

	public void setScore(Integer score) {
		this.score = score;
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