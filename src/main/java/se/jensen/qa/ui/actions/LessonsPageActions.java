package se.jensen.qa.ui.actions;

import java.util.List;

import org.jsoup.Jsoup;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import se.jensen.qa.helpers.JensenDriver;
import se.jensen.qa.ui.models.LessonTemplateData;

public class LessonsPageActions extends BaseActions {

	public LessonsPageActions(JensenDriver jensenDriver) {
		super(jensenDriver);
	}

	public void openCreateLessonsPage() {
    	jensenDriver.waitAndFindElementByCssSelector("a[href='#/lektioner/create']").click();
    }

	public void openCreateTemplatePage() {
    	jensenDriver.waitAndFindElementByCssSelector("a[href='#/lektioner/create/template']").click();
    }

	public void fillTemplateCreateForm(LessonTemplateData formData) {
    	jensenDriver.waitAndFindElementByCssSelector("input[name='name']").sendKeys(formData.getTemplateName());
    	jensenDriver.waitAndFindJensenTextArea("description").sendKeys(formData.getShortDescription());
    	jensenDriver.searchAndSelectFromJensenSelectComponent("form_lesson_course_chosen", formData.getCourse());
    	jensenDriver.waitAndFindJensenTextArea("teacher_instruction").sendKeys(formData.getTeacherInstructions());
    	jensenDriver.waitAndFindJensenTextArea("student_instruction").sendKeys(formData.getStudentInstructions());
	}
	
	public void saveTemplatedCreateForm() {
    	jensenDriver.waitAndFindElementByCssSelector("button[title='Klar']").click();
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
	
	public boolean isLessonCreateButtonVisible() {
		List<WebElement> list = jensenDriver.findElementsByCssSelector("a[href='#/lektioner/create']");
		return list.size() > 0;
	}
	
	public boolean isTemplateDeletable(String templateName) {
		return jensenDriver.isDeletableRowInJensenTable("Namn", templateName);
	}

//	public void deleteTemplate(String templateName) {
//		List<WebElement> list = jensenDriver.waitAndFindElementsByCssSelector("div.table-wrap > ul > li > div[data-title-text='Namn']");
//		Optional<WebElement> ele = list.stream().filter(e -> {
//			System.out.println(e.getText());
//			return templateName.equals(e.getText());
//		}).findFirst();
//		if (ele.isPresent()) {
//			WebElement name = ele.get();
//			WebElement arrow = jensenDriver.findElementByCssSelector(name, " div.icon-entypo-chevron-small-down");
//			arrow.click();
//		}
//	}
	/*public void deleteTemplate(String templateName) throws InterruptedException {
		jensenDriver.deleteRowInJensenTable("Namn", templateName);
		System.out.println("deleted template " + templateName);
	}*/
	
	public void openTemplate(String templateName) throws InterruptedException {
		jensenDriver.clickRowInJensenTable("Namn", templateName);
	}
	
	public LessonTemplateData parseTemplateDataFromCreatePage() {
		LessonTemplateData lessonTemplateData = new LessonTemplateData();
		WebElement form =  jensenDriver.waitAndFindElementByCssSelector("form[name='lessonForm']");
		String templateName = jensenDriver.findElementById("form-lesson-name").getAttribute("value");
		String desc = jensenDriver.findElementByCssSelector(form, "div[ng-bind-html='lesson.lesson_template.description'] > p").getText();
		String teacherInstruction = jensenDriver.findElementByCssSelector(form, "div[ng-model='lesson.lesson_template.teacher_instruction'] > p").getText();
		String studentInstructionHtml = jensenDriver.findElementByCssSelector(form, "input[name='student_instruction']").getAttribute("value");
		String studentInstructionText = Jsoup.parse(studentInstructionHtml).text();
		lessonTemplateData.setShortDescription(desc);
		lessonTemplateData.setTemplateName(templateName);
		lessonTemplateData.setTeacherInstructions(teacherInstruction);
		lessonTemplateData.setStudentInstructions(studentInstructionText);
		return lessonTemplateData;
	}
	
	
	public void assertTemplateData(LessonTemplateData actual, LessonTemplateData expected) {
		Assert.assertEquals(actual.getTemplateName(), expected.getTemplateName());
		Assert.assertEquals(actual.getShortDescription(), expected.getShortDescription());
		Assert.assertEquals(actual.getStudentInstructions(), expected.getStudentInstructions());
		Assert.assertEquals(actual.getTeacherInstructions(), expected.getTeacherInstructions());
	}

}
