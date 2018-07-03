package se.jensen.qa.ui.models;

public class AssignmentTemplateData {

	private String shortDescription;
	private String templateName;
	private String typeDropdown;
	private String course;
	private String teacherInstructions;
	private String studentInstructions;
	private String assessmentAspect;
	private boolean assignmentGoogleDocCheckbox;
	private boolean assignmentAttachmentsCheckbox;
	private boolean assignmentUrkundCheckbox;
	private String distributeCoursegroupdropdown;
	private String flexAssignmentStartPercent;
	private String flexAssignmentEndPercent;

	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	
	public String getTypeDropdown() {
		return typeDropdown;
	}
	public void setTypeDropdown(String typeDropdown) {
		this.typeDropdown = typeDropdown;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getTeacherInstructions() {
		return teacherInstructions;
	}
	public void setTeacherInstructions(String teacherInstructions) {
		this.teacherInstructions = teacherInstructions;
	}
	public String getStudentInstructions() {
		return studentInstructions;
	}
	public void setStudentInstructions(String studentInstructions) {
		this.studentInstructions = studentInstructions;
	}
	public String getAssessmentAspect() {
		return assessmentAspect;
	}
	public void setAssessmentAspect(String assessmentAspect) {
		this.assessmentAspect = assessmentAspect;
	}
	public boolean isAssignmentGoogleDocCheckbox() {
		return assignmentGoogleDocCheckbox;
	}
	public void setAssignmentGoogleDocCheckbox(boolean assignmentGoogleDocCheckbox) {
		this.assignmentGoogleDocCheckbox = assignmentGoogleDocCheckbox;
	}
	public boolean isAssignmentAttachmentsCheckbox() {
		return assignmentAttachmentsCheckbox;
	}
	public void setAssignmentAttachmentsCheckbox(boolean assignmentAttachmentsCheckbox) {
		this.assignmentAttachmentsCheckbox = assignmentAttachmentsCheckbox;
	}
	public boolean isAssignmentUrkundCheckbox() {
		return assignmentUrkundCheckbox;
	}
	public void setAssignmentUrkundCheckbox(boolean assignmentUrkundCheckbox) {
		this.assignmentUrkundCheckbox = assignmentUrkundCheckbox;
	}
	public String getDistributeCoursegroupdropdown() {
		return distributeCoursegroupdropdown;
	}
	public void setDistributeCoursegroupdropdown(String distributeCoursegroupdropdown) {
		this.distributeCoursegroupdropdown = distributeCoursegroupdropdown;
	}
	public String getFlexAssignmentStartPercent() {
		return flexAssignmentStartPercent;
	}
	public void setFlexAssignmentStartPercent(String flexAssignmentStartPercent) {
		this.flexAssignmentStartPercent = flexAssignmentStartPercent;
	}
	public String getFlexAssignmentEndPercent() {
		return flexAssignmentEndPercent;
	}
	public void setFlexAssignmentEndPercent(String flexAssignmentEndPercent) {
		this.flexAssignmentEndPercent = flexAssignmentEndPercent;
	}
}
