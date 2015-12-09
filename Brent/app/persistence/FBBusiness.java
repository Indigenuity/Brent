package persistence;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FBBusiness {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long fbBusinessId;
	
	private String name;
	private String timezoneId;
	private String primaryPageId;
	private String verticalId;
	private Date updateTime;
	private Date creationTime;
	private String createdByName;
	private String createdById;
	public long getFbBusinessId() {
		return fbBusinessId;
	}
	public void setFbBusinessId(long fbBusinessId) {
		this.fbBusinessId = fbBusinessId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTimezoneId() {
		return timezoneId;
	}
	public void setTimezoneId(String timezoneId) {
		this.timezoneId = timezoneId;
	}
	public String getPrimaryPageId() {
		return primaryPageId;
	}
	public void setPrimaryPageId(String primaryPageId) {
		this.primaryPageId = primaryPageId;
	}
	public String getVerticalId() {
		return verticalId;
	}
	public void setVerticalId(String verticalId) {
		this.verticalId = verticalId;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Date getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	public String getCreatedByName() {
		return createdByName;
	}
	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}
	public String getCreatedById() {
		return createdById;
	}
	public void setCreatedById(String createdById) {
		this.createdById = createdById;
	}
	
	
	

}
