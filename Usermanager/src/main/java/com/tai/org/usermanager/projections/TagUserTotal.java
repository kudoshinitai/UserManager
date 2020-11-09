package com.tai.org.usermanager.projections;

import org.springframework.beans.factory.annotation.Value;

public interface TagUserTotal {

	@Value("#{target.hashTagId}")
	public int getHashTagId();
	
	@Value("#{target.totalUser}")
	public Long getTotalUser();
	
	@Value("#{target.maxUser}")
	public Long getMaxUser();
}
