package com.ipbyhj.dev.common.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Setter
@Getter
public abstract class BaseEntity {

	private LocalDateTime createTime;
	private LocalDateTime updateTime;

	@Column(updatable = false)
	private String createBy;
	private String updateBy;


	public void initDefaultValues() {
		this.createTime = LocalDateTime.now();
		this.updateTime = LocalDateTime.now();
	}

	public void BeforefirstInsert(String createBy) {
		this.createBy = createBy;
		this.updateBy = createBy;
		initDefaultValues();
	}
}
