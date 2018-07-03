package se.jensen.qa.lessons;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import se.jensen.qa.SeleniumAbstractTest;
import se.jensen.qa.helpers.JsonParser;
import se.jensen.qa.ui.actions.LessonsPageActions;
import se.jensen.qa.ui.models.LessonTemplateData;

public class LessonTemplatesTest extends SeleniumAbstractTest {

	@Override
	protected void initPageActions() {
		lessonsPageActions = new LessonsPageActions(jensenDriver);
	}

	//@Test
	public void createTemplateTest() {
		try {
			List<LessonTemplateData> inputDataList = JsonParser
					.jsonToBeanList("LessonTemplateTest/createTemplate_01.json", LessonTemplateData[].class);
			loginPageActions.loginAs("teacher", "one");
			for (LessonTemplateData iData : inputDataList) {
				homePageActions.changeTab("lessons");
				lessonsPageActions.openCreateLessonsPage();
				lessonsPageActions.openCreateTemplatePage();
				lessonsPageActions.fillTemplateCreateForm(iData);
				Assert.assertEquals(lessonsPageActions.isTemplateSaveButtonDisabled(), false, "template save button is disabled");
				lessonsPageActions.saveTemplatedCreateForm();
				Assert.assertEquals(lessonsPageActions.isTemplateSaved(iData.getTemplateName()), true, "template is not visible");
				lessonsPageActions.openTemplate(iData.getTemplateName());
				LessonTemplateData savedTemplateData = lessonsPageActions.parseTemplateDataFromCreatePage();
				lessonsPageActions.assertTemplateData(savedTemplateData, iData);
				jensenDriver.goBack();
				//lessonsPageActions.deleteTemplate(iData.getTemplateName());
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("failed due to exception " + e.getMessage());
		}
	}

	//@Test
	public void createTemplate_DuplicateName() {
		try {
			LessonTemplateData inputData = JsonParser.jsonToBean("LessonTemplateTest/createTemplate_03.json",
					LessonTemplateData.class);
			System.out.println("Loggen in with Teacher 2 credentials for template duplicate test");
			loginPageActions.loginAs("teacher", "two");
			homePageActions.changeTab("lessons");
			lessonsPageActions.openCreateLessonsPage();
			lessonsPageActions.openCreateTemplatePage();
			lessonsPageActions.fillTemplateCreateForm(inputData);
			Assert.assertEquals(lessonsPageActions.isTemplateSaveButtonDisabled(), false, "template save button is disabled");
			lessonsPageActions.saveTemplatedCreateForm();
			lessonsPageActions.openCreateTemplatePage();
			lessonsPageActions.fillTemplateCreateForm(inputData);
			Assert.assertEquals(lessonsPageActions.isTemplateSaveButtonDisabled(), false, "template save button is disabled");
			lessonsPageActions.saveTemplatedCreateForm();
			//lessonsPageActions.deleteTemplate(inputData.getTemplateName());
			//lessonsPageActions.deleteTemplate(inputData.getTemplateName());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("failed due to exception " + e.getMessage());
		}
	}

	//@Test
	public void createTemplate_blankFields() {
		try {
			LessonTemplateData inputData = JsonParser.jsonToBean("LessonTemplateTest/createTemplate_02.json",
					LessonTemplateData.class);
			loginPageActions.loginAs("teacher", "one");
			homePageActions.changeTab("lessons");
			lessonsPageActions.openCreateLessonsPage();
			lessonsPageActions.openCreateTemplatePage();
			lessonsPageActions.fillTemplateCreateForm(inputData);
			Assert.assertEquals(lessonsPageActions.isTemplateSaveButtonDisabled(), true, "template save button is not disabled");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("failed due to exception " + e.getMessage());
		}
	}

	//@Test
	public void createdTemplateVisibleByOtherTeacher() {
		try {
			LessonTemplateData inputData = JsonParser.jsonToBean("LessonTemplateTest/createTemplate_03.json",
					LessonTemplateData.class);
			//login with user1
			loginPageActions.loginAs("teacher", "one");
			homePageActions.changeTab("lessons");
			lessonsPageActions.openCreateLessonsPage();
			lessonsPageActions.openCreateTemplatePage();
			lessonsPageActions.fillTemplateCreateForm(inputData);
			Assert.assertEquals(lessonsPageActions.isTemplateSaveButtonDisabled(), false, "template save button is disabled");
			lessonsPageActions.saveTemplatedCreateForm();
			// template is visible by user1
			Assert.assertEquals(lessonsPageActions.isTemplateSaved(inputData.getTemplateName()), true, "template is not visible");
			Assert.assertEquals(lessonsPageActions.isTemplateDeletable(inputData.getTemplateName()), true, "template is not deletable");
			homePageActions.logout();
			

			//login with user3 who is not in the same group as user1
			loginPageActions.loginAs("teacher", "three");
			homePageActions.changeTab("lessons");
			lessonsPageActions.openCreateLessonsPage();
			// template is not visible by user3 as he is in the same group
			Assert.assertEquals(lessonsPageActions.isTemplateSaved(inputData.getTemplateName()), false, "template should not be visible");
			homePageActions.logout();

			//login with user2 who is in the same group as user1
			loginPageActions.loginAs("teacher", "two");
			homePageActions.changeTab("lessons");
			lessonsPageActions.openCreateLessonsPage();
			// template is visible by user2 as he is in the same group
			Assert.assertEquals(lessonsPageActions.isTemplateSaved(inputData.getTemplateName()), true, "template is not visible");
			Assert.assertEquals(lessonsPageActions.isTemplateDeletable(inputData.getTemplateName()), false, "template is deletable");
			homePageActions.logout();

			loginPageActions.loginAs("teacher", "one");
			homePageActions.changeTab("lessons");
			lessonsPageActions.openCreateLessonsPage();
			//lessonsPageActions.deleteTemplate(inputData.getTemplateName());

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("failed due to exception " + e.getMessage());
		}
	}

	@Test
	public void createTemplate_student() {
		try {
			loginPageActions.loginAs("student", "one");
			homePageActions.changeTab("lessons");
			Assert.assertEquals(lessonsPageActions.isLessonCreateButtonVisible(), false, "template create button is visible");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("failed due to exception " + e.getMessage());
		}
	}
}
