package com.arca.taskproject.entity;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="data")
public class DataInfo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String time;
	
	private byte valeur;
	
	private String origine;
	
	
	public DataInfo(String time, byte valeur, String origine) {
		super();
		this.time = time;
		this.valeur = valeur;
		this.origine = origine;
	}

	public DataInfo() {
		super();
	}

	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public byte getValeur() {
		return valeur;
	}
	public void setValeur(byte valer) {
		this.valeur = valer;
	}
	public String getOrigine() {
		return origine;
	}
	public void setOrigine(String origine) {
		this.origine = origine;
	}

	@Override
	public String toString() {
		return "DataInfo [id=" + id + ", time=" + time + ", valeur=" + valeur + ", origine=" + origine + "]";
	}
	
	
	
}
