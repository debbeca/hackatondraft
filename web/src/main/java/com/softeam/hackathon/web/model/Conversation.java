package com.softeam.hackathon.web.model;

public class Conversation {

	private Intention intention;
	private Intervalle intervalle;
	private String apporteur;
	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Intervalle getIntervalle() {
		return intervalle;
	}

	public void setIntervalle(Intervalle intervalle) {
		this.intervalle = intervalle;
	}

	public Intention getIntention() {
		return intention;
	}

	public void setIntention(Intention intention) {
		this.intention = intention;
	}

	public String getApporteur() {
		return apporteur;
	}

	public void setApporteur(String apporteur) {
		this.apporteur = apporteur;
	}

}
