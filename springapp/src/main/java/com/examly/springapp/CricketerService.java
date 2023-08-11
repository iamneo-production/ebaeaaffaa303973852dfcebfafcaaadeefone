package com.examly.springapp;

import java.util.List;

public class CricketerService {

	private CricketDAO cricketDAO;

	public CricketerService() {
	 this.cricketDAO = new CricketDAO();
	 }

	public List<Cricketer> getAllCricketer() {
		return cricketDAO.getAllCricketer();
	}

	public void addCricketer(Cricketer cricketer) {
		cricketDAO.addCricketer(cricketer);
	}
}
