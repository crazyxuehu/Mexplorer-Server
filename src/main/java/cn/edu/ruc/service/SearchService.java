package cn.edu.ruc.service;

import cn.edu.ruc.model.Dropdown;
import cn.edu.ruc.model.Profile;
import cn.edu.ruc.model.Query;

import java.util.List;

public interface SearchService {
	//get auto completion list
	Dropdown getDropdown(String keywords);

	//get query
	Query getQuery(List<String> entityStringList, List<String> featureStringList);

	//get profile of entity
	Profile getProfile(String queryEntityString);
}
