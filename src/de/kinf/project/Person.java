package de.kinf.project;

import java.util.Map;

public class Person {

	private int id;
	private int pageOrigin;
	private int hessNr;
	private String appellation;
	private String aristocrat;
	private String jesuit;
	private int day;
	private int month;
	private int year;
	private String yearN;
	private int academicYearN;
	private String facultyN;
	private String graduate;
	private String title;
	private String titleN;
	private String location;
	private String comments;

	private Map<Source, FirstName> prenames;
	private Map<Source, LastName> surnames;
	private Map<Source, StudyField> studyFields;
	private Map<Source, Place> places;
	private Map<Source, Course> courses;
	private Map<Source, EconomicState> economicStates;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPageOrigin() {
		return pageOrigin;
	}

	public void setPageOrigin(int pageOrigin) {
		this.pageOrigin = pageOrigin;
	}

	public int getHessNr() {
		return hessNr;
	}

	public void setHessNr(int hessNr) {
		this.hessNr = hessNr;
	}

	public String getAppellation() {
		return appellation;
	}

	public void setAppellation(String appellation) {
		this.appellation = appellation;
	}

	public String getAristocrat() {
		return aristocrat;
	}

	public void setAristocrat(String aristocrat) {
		this.aristocrat = aristocrat;
	}

	public String getJesuit() {
		return jesuit;
	}

	public void setJesuit(String jesuit) {
		this.jesuit = jesuit;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getYearN() {
		return yearN;
	}

	public void setYearN(String yearN) {
		this.yearN = yearN;
	}

	public int getAcademicYearN() {
		return academicYearN;
	}

	public void setAcademicYearN(int academicYearN) {
		this.academicYearN = academicYearN;
	}

	public String getFacultyN() {
		return facultyN;
	}

	public void setFacultyN(String facultyN) {
		this.facultyN = facultyN;
	}

	public String getGraduate() {
		return graduate;
	}

	public void setGraduate(String graduate) {
		this.graduate = graduate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitleN() {
		return titleN;
	}

	public void setTitleN(String titleN) {
		this.titleN = titleN;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Map<Source, FirstName> getPrenames() {
		return prenames;
	}

	public void setPrenames(Map<Source, FirstName> prenames) {
		this.prenames = prenames;
	}

	public Map<Source, LastName> getSurnames() {
		return surnames;
	}

	public void setSurnames(Map<Source, LastName> surnames) {
		this.surnames = surnames;
	}

	public Map<Source, StudyField> getStudyFields() {
		return studyFields;
	}

	public void setStudyFields(Map<Source, StudyField> studyFields) {
		this.studyFields = studyFields;
	}

	public Map<Source, Place> getPlaces() {
		return places;
	}

	public void setPlaces(Map<Source, Place> places) {
		this.places = places;
	}

	public Map<Source, Course> getCourses() {
		return courses;
	}

	public void setCourses(Map<Source, Course> courses) {
		this.courses = courses;
	}

	public Map<Source, EconomicState> getEconomicStates() {
		return economicStates;
	}

	public void setEconomicStates(Map<Source, EconomicState> economicStates) {
		this.economicStates = economicStates;
	}

}