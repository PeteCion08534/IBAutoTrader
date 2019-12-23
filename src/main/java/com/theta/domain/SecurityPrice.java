package com.theta.domain;

import java.io.Serializable;

import java.lang.StringBuilder;
import java.util.Calendar;

import javax.persistence.EntityResult;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import javax.xml.bind.annotation.*;

import javax.persistence.*;

/**
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findAllSecurityPrices", query = "select mySecurityPrice from SecurityPrice mySecurityPrice"),
		@NamedQuery(name = "findSecurityPriceByPrice", query = "select mySecurityPrice from SecurityPrice mySecurityPrice where mySecurityPrice.price = ?1"),
		@NamedQuery(name = "findSecurityPriceByPrimaryKey", query = "select mySecurityPrice from SecurityPrice mySecurityPrice where mySecurityPrice.securityPriceId = ?1"),
		@NamedQuery(name = "findSecurityPriceBySecurityPriceId", query = "select mySecurityPrice from SecurityPrice mySecurityPrice where mySecurityPrice.securityPriceId = ?1"),
		@NamedQuery(name = "findSecurityPriceByTicker", query = "select mySecurityPrice from SecurityPrice mySecurityPrice where mySecurityPrice.ticker = ?1"),
		@NamedQuery(name = "findSecurityPricesByTickerAndSourceSinceTime", query = "select mySecurityPrice from SecurityPrice mySecurityPrice where mySecurityPrice.ticker = ?1 and mySecurityPrice.source = ?2 and mySecurityPrice.createdDate > ?3 and mySecurityPrice.createdDate < ?4"),
        @NamedQuery(name = "findSecurityPricesByTickerSinceTime",          query = "select mySecurityPrice from SecurityPrice mySecurityPrice where mySecurityPrice.ticker = ?1 and mySecurityPrice.createdDate > ?2 and mySecurityPrice.createdDate < ?3"),
		@NamedQuery(name = "findSecurityPriceByTickerContaining", query = "select mySecurityPrice from SecurityPrice mySecurityPrice where mySecurityPrice.ticker like ?1") })

		
@SqlResultSetMapping(name="implicit",
           entities=@EntityResult(entityClass=SecurityPrice.class))
@NamedNativeQueries( {
@NamedNativeQuery(name="findDayOpenPriceByDateAndTicker", 
				query="select * from security_price " + 
				"where security_price_id = (select min(security_price_id) from security_price where " + 
				"TO_CHAR(created_date, 'YYYYMMDD')=?1  and ticker=?2 and used='Y')",
				resultSetMapping="implicit"),
@NamedNativeQuery(name="findEarliestRecordSince", 
				query="select * from security_price " + 
				"where created_date = (select min(created_date) from security_price where " + 
				"TO_CHAR(created_date, 'YYYYMMDD-HH24MI')>=?1  and ticker=?2 and used='Y') and ticker=?3",
				resultSetMapping="implicit"),
@NamedNativeQuery(name="findLatestRecordBefore", 
				query="select * from security_price " + 
				"where created_date = (select max(created_date) from security_price where " + 
				"TO_CHAR(created_date, 'YYYYMMDD-HH24MI')<=?1  and ticker=?2 and used='Y') and ticker=?3",
				resultSetMapping="implicit"),
@NamedNativeQuery(name="findLastBusinessDay", 
				query="select * from security_price " + 
				"where security_price_id = (select max(security_price_id) from security_price where " + 
				"created_date < sysdate - 1)",
				resultSetMapping="implicit"),
@NamedNativeQuery(name="findMovingAverage", 
                  query=
                  "select trunc(avg(price)) from " +
                  "(" +
                  "select max(ticker), max(price) as price, to_char(created_date, 'YYYYMMDD') " +
                  "from security_price " +
                  "where " +
                  "to_char(created_date,'YYYYMMDD') > ?1 " +
                  "and " +
                  "created_date < sysdate " +
                  "and ticker = ?2 " +
                  "and price > 0 " +
                  "group by to_char(created_date,'YYYYMMDD') " +
                  "union " +
                  "select min(ticker), min(price) as price, to_char(created_date, 'YYYYMMDD') " +
                  "from security_price " +
                  "where " +
                  "to_char(created_date,'YYYYMMDD') > ?1 " +
                  "and " +
                  "created_date <  sysdate " +
                  "and ticker = ?2 " +
                  "and price > 0 " +
                  "group by to_char(created_date,'YYYYMMDD') " +
                  ")",
                  resultSetMapping="implicit")
})

					
@Table(schema = "THETA", name = "SECURITY_PRICE")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(namespace = "theta11/com/theta/domain", name = "SecurityPrice")
@XmlRootElement(namespace = "theta11/com/theta/domain")
public class SecurityPrice implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 */

	@Column(name = "SECURITY_PRICE_ID", nullable = false)
	@Basic(fetch = FetchType.EAGER)
	@Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SECURITY_PRICE_SEQ")
    @SequenceGenerator(name="SECURITY_PRICE_SEQ", sequenceName = "SECURITY_PRICE_SEQ")
	@XmlElement
	Integer securityPriceId;
	/**
	 */
	@Column(name = "TICKER", length = 10)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String ticker;
	/**
	 */
	@Column(name = "PRICE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer price;
	/**
	 */
	@Column(name = "CREATED_DATE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Calendar createdDate;
	/**
	 */
	@Column(name = "SOURCE", length = 20)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String source;
	/**
	 */
	@Column(name = "USED", length = 20)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String used;
	/**
	 */
	@Column(name = "MOV_AVG_1")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer movAvg1;
	/**
	 */
	@Column(name = "MOV_AVG_2")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer movAvg2;

	@Column(name = "MOV_AVG_RANGE")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer movAvgRange;
	
	@Column(name = "SLOPE_1")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer slope1;

	@Column(name = "SLOPE_2")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer slope2;

	@Column(name = "SLOPE_3")
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	Integer slope3;
	/**
	 */
	@Column(name = "LONG_OR_SHORT_FLAG", length = 5)
	@Basic(fetch = FetchType.EAGER)
	@XmlElement
	String longOrShortFlag;
	
	/**
	 */
	public void setSecurityPriceId(Integer securityPriceId) {
		this.securityPriceId = securityPriceId;
	}

	/**
	 */
	public Integer getSecurityPriceId() {
		return this.securityPriceId;
	}

	/**
	 */
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	/**
	 */
	public String getTicker() {
		return this.ticker;
	}

	/**
	 */
	public void setPrice(Integer price) {
		this.price = price;
	}
	/**
	 */
	public Integer getPrice() {
		return this.price;
	}

	/**
	 */
	public Calendar getCreatedDate() {
		return createdDate;
	}

	/**
	 */
	public void setCreatedDate(Calendar createdDate) {
		this.createdDate = createdDate;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getUsed() {
		return used;
	}

	public void setUsed(String used) {
		this.used = used;
	}

	public Integer getMovAvg1() {
		return movAvg1;
	}

	public void setMovAvg1(Integer movAvg1) {
		this.movAvg1 = movAvg1;
	}

	public Integer getMovAvg2() {
		return movAvg2;
	}

	public void setMovAvg2(Integer movAvg2) {
		this.movAvg2 = movAvg2;
	}

	public Integer getMovAvgRange() {
		return movAvgRange;
	}

	public void setMovAvgRange(Integer movAvgRange) {
		this.movAvgRange = movAvgRange;
	}
	
	public Integer getSlope1() {
		return slope1;
	}

	public void setSlope1(Integer slope1) {
		this.slope1 = slope1;
	}

	public Integer getSlope2() {
		return slope2;
	}
	public void setSlope2(Integer slope2) {
		this.slope2 = slope2;
	}
	public void setSlope3(Integer slope3) {
		this.slope3 = slope3;
	}
	public String getLongOrShortFlag() {
		return longOrShortFlag;
	}
	public void setLongOrShortFlag(String longOrShortFlag) {
		this.longOrShortFlag = longOrShortFlag;
	}

	/**
	 */
	public SecurityPrice() {
	}

	/**
	 * Copies the contents of the specified bean into this bean.
	 *
	 */
	public void copy(SecurityPrice that) {
		setSecurityPriceId(that.getSecurityPriceId());
		setTicker(that.getTicker());
		setPrice(that.getPrice());
		setCreatedDate(that.getCreatedDate());
	}

	/**
	 * Returns a textual representation of a bean.
	 *
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SecurityPrice [securityPriceId=");
		builder.append(securityPriceId);
		builder.append(", ticker=");
		builder.append(ticker);
		builder.append(", price=");
		builder.append(price);
		builder.append(", createdDate=");
		builder.append(createdDate.getTime());
		builder.append(", source=");
		builder.append(source);
		builder.append(", used=");
		builder.append(used);
		builder.append(", movAvg1=");
		builder.append(movAvg1);
		builder.append(", movAvg2=");
		builder.append(movAvg2);
		builder.append("]");
		return builder.toString();
	}
	
	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (int) (prime * result + ((securityPriceId == null) ? 0 : securityPriceId.hashCode()));
		return result;
	}
	/**
	 */
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof SecurityPrice))
			return false;
		SecurityPrice equalCheck = (SecurityPrice) obj;
		if ((securityPriceId == null && equalCheck.securityPriceId != null) || (securityPriceId != null && equalCheck.securityPriceId == null))
			return false;
		if (securityPriceId != null && !securityPriceId.equals(equalCheck.securityPriceId))
			return false;
		return true;
	}
}
