package com.ipbyhj.dev.main.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface HomeMapper {

	public ArrayList<String> getHomeDetails();
}
