package com.algaworks.algalog.common;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //componente spring com objetivo de configuração de beans spring
public class ModelMapperConfig {
	
	@Bean 
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
