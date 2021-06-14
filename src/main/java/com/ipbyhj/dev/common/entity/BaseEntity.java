package com.ipbyhj.dev.common.entity;

import java.time.LocalDateTime;

import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Setter
@Getter
public abstract class BaseEntity {

	private LocalDateTime createTime;
	private LocalDateTime updateTime;
	private String createBy;
	private String updateBy;

}
