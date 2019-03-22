package se.jensen.qa.ui.actions;

import java.util.List;

import org.jsoup.Jsoup;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.gargoylesoftware.htmlunit.javascript.host.xml.FormData;

import se.jensen.qa.helpers.JensenDriver;
import se.jensen.qa.ui.models.AssignmentTemplateData;


public class AssignmentPageActions extends BaseActions {

	public AssignmentPageActions(JensenDriver jensenDriver) {
		super(jensenDriver);
	}
	
	public void openCreateAssignmentPage() {
		jensenDriver.waitAndFindElementByCssSelector("a[href='#/assignments/create']").click();
	}

	public void openCreateAssignmentTemplatePage() {
		jensenDriver.waitAndFindElementByCssSelector("a[href='#/assignments/create/template']").click();
	}
	
	public void openCopyCourse() {
		jensenDriver.waitAndFindElementByCssSelector("a[ng-click='openCopyCourse()']").click();
	}
	public void selectSourceGroup(AssignmentTemplateData formData) {
		jensenDriver.searchAndSelectFromJensenSelectComponent("form_source_group_chosen", formData.getSelectSourceGroup() );
	}
	public void selectDestinationGroup(AssignmentTemplateData formData) {
		jensenDriver.searchAndSelectFromJensenSelectComponent("form_destination_group_chosen", formData.getSelectDestinationGroup() );
	}
	public void gotoStep2() {
		jensenDriver.waitAndFindElementByCssSelector("button[type='submit']").click();
	}
	public void copy() {
		jensenDriver.waitAndFindElementByCssSelector("button[type='submit']").click();
	}
	public void fillTemplateCreateForm(AssignmentTemplateData formData) {
		jensenDriver.waitAndFindElementByCssSelector("input[name='name']").sendKeys(formData.getTemplateName());
		jensenDriver.searchAndSelectFromJensenSelectComponent("form_assignment_type_chosen", formData.getTypeDropdown());
		jensenDriver.waitAndFindJensenTextArea("description").sendKeys(formData.getShortDescription());
		jensenDriver.searchAndSelectFromJensenSelectComponent("form_assignment_course_chosen", formData.getCourse());
		jensenDriver.waitAndFindJensenTextArea("teacher_instruction").sendKeys(formData.getTeacherInstructions());
		jensenDriver.waitAndFindJensenTextArea("student_temp_instruction").sendKeys(formData.getStudentInstructions());
		jensenDriver.waitAndFindJensenTextArea("temp_purpose").sendKeys(formData.getAssessmentAspect());
		WebElement assignmentCheckbox =jensenDriver.findElementByCssSelector("input[ng-model='assignmentTemplate.google_doc']");
		if (formData.isAssignmentGoogleDocCheckbox() != assignmentCheckbox.isSelected()) {
			assignmentCheckbox.click();
		}
		jensenDriver.waitAndFindElementByCssSelector("input[ng-model='assignmentTemplate.enable_attachments']").click();
		//jensenDriver.waitAndFindElementByCssSelector("input[ng-model='assignmentTemplate.urkund_check']").click();
		
	}

	public void saveTemplateCreateForm() {
		jensenDriver.waitAndFindElementByCssSelector("button[title='Dela ut direkt']").click();
	}
	public void saveTemplateCreateFormWithKlar() {
		jensenDriver.waitAndFindElementByCssSelector("button[title='Klar']").click();
	}
	public void startAssignment() {
		jensenDriver.waitAndFindElementByCssSelector("button[ng-click='pupilStartAssignment()']").click();
	}
	//public void workWithTheTask() {
	//	jensenDriver.waitAndFindElementByCssSelector("button[ng-click='pupilStartAssignment()']").click();
	//}
	public void submitAssignment() {
		jensenDriver.waitAndFindElementByCssSelector("button[ng-click='pupilSubmitAssignment()']").click();
	}
	public void confirmSubmitAssignment() {
		jensenDriver.waitAndFindElementByCssSelector("button[ng-click='confirm()']").click();
	}
	
	public void publishAssessment() {
		jensenDriver.waitAndFindElementByCssSelector("button[ng-click='assesPupil()']").click();
	}
	public void confirmPublishAssessment() {
		jensenDriver.waitAndFindElementByCssSelector("button[ng-click='confirmAndGenerate()']").click();
	}
	public void cancelPublishAssessment() {
		jensenDriver.waitAndFindElementByCssSelector("button[ng-click='closeThisDialog()']").click();
	}
	
	public void writeInGoogleDoc() {
		jensenDriver.writeGoogleDoc();
	}
	
	public void closeCurrentTab() {
		jensenDriver.waitAndFindElementByCssSelector("body").sendKeys(Keys.COMMAND+"w");
	}
	public void switchToPreviousTab() {
		jensenDriver.switchTabs(); 
	}
	
	public void distributeAssignment(AssignmentTemplateData formData) {
		jensenDriver.searchAndSelectFromJensenSelectComponent("form_assignment_course_group_chosen", formData.getDistributeCoursegroupdropdown());
		System.out.println("Course Group is selected from the dropdown");
		//jensenDriver.waitAndFindElementByCssSelector("input[name='start_percent']").sendKeys(formData.getFlexAssignmentStartPercent());
		//jensenDriver.waitAndFindElementByCssSelector("input[name='end_percent']").sendKeys(formData.getFlexAssignmentEndPercent());
		jensenDriver.waitAndFindElementByCssSelector("button[type='submit']").click();
		System.out.println("Submit button is clicked to distribute an assignment");
	}
	
	public boolean isTemplateSaveButtonDisabled() {
		WebElement button = jensenDriver.waitAndFindElementByCssSelector("button[title='Klar']");
		String disabled = button.getAttribute("disabled");
		return "true".equals(disabled);
	}

	public boolean isTemplateSaved(String templateName) {
		WebElement row = jensenDriver.findRowInJensenTable("Namn", templateName);
		return row != null;
	}

	public boolean isAssignmentCreateButtonVisible() {
		List<WebElement> list = jensenDriver.findElementsByCssSelector("a[href='#/assignments/create']");
		return list.size() > 0;
	}

public boolean isTemplateDeletable(String templateName) {
	return jensenDriver.isDeletableRowInJensenTable("Namn", templateName);
}

//	public void deleteTemplate(String templateName) throws InterruptedException {
//		jensenDriver.deleteRowInJensenTable("Namn", templateName);
//		System.out.println("deleted template " + templateName);
//	}

	public void openTemplate(String templateName) throws InterruptedException {
		jensenDriver.clickRowInJensenTable("Namn", templateName);
	}
	public void openAssignment(String templateName) throws InterruptedException {
		jensenDriver.clickRowInJensenTable("Namn", templateName);
	}
	public void openAssignmentFromDashboard(String assignmentName) throws InterruptedException {
		jensenDriver.clickRowInJensenTable("Namn", assignmentName);
	}
	public AssignmentTemplateData parseTemplateDataFromCreatePage() {
		AssignmentTemplateData assignmentTemplateData = new AssignmentTemplateData();
		WebElement form =  jensenDriver.waitAndFindElementByCssSelector("form[name='assignmentForm']");
		String templateName = jensenDriver.findElementById("form-assignment-name").getAttribute("value");
		String desc = jensenDriver.findElementByCssSelector(form, "div[ng-bind-html='assignment.assignment_template.description'] > p").getText();
		String teacherInstruction = jensenDriver.findElementByCssSelector(form, "div[ng-model='assignment.assignment_template.teacher_instruction'] > p").getText();
		String studentInstructionHtml = jensenDriver.findElementByCssSelector(form, "input[name='student_temp_instruction']").getAttribute("value");
		String studentInstructionText = Jsoup.parse(studentInstructionHtml).text();
		assignmentTemplateData.setShortDescription(desc);
		assignmentTemplateData.setTemplateName(templateName);
		assignmentTemplateData.setTeacherInstructions(teacherInstruction);
		assignmentTemplateData.setStudentInstructions(studentInstructionText);
		return assignmentTemplateData;
	}


	public void assertTemplateData(AssignmentTemplateData actual, AssignmentTemplateData expected) {
		Assert.assertEquals(actual.getTemplateName(), expected.getTemplateName());
		Assert.assertEquals(actual.getShortDescription(), expected.getShortDescription());
		Assert.assertEquals(actual.getStudentInstructions(), expected.getStudentInstructions());
		Assert.assertEquals(actual.getTeacherInstructions(), expected.getTeacherInstructions());
	}

}
