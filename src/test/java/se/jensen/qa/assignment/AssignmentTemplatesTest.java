package se.jensen.qa.assignment;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import se.jensen.qa.SeleniumAbstractTest;
import se.jensen.qa.helpers.JsonParser;
import se.jensen.qa.ui.actions.AssignmentPageActions;
import se.jensen.qa.ui.models.AssignmentTemplateData;
import se.jensen.qa.ui.models.LessonTemplateData;

public class AssignmentTemplatesTest extends SeleniumAbstractTest {

	@Override
	protected void initPageActions() {
		assignmentPageActions = new AssignmentPageActions(jensenDriver);
	}

@Test
	//CREATE AND DISTRIBUTE REGULAR ASSIGNMENT
	public void createAssignmentTemplatesTest() {
		try {
			List<AssignmentTemplateData> inputDataList = JsonParser.jsonToBeanList(
					"AssignmentTemplateTest/createAssignmentTemplate_01.json", AssignmentTemplateData[].class);
			loginPageActions.loginAs("teacher", "one");
			System.out.println("Logged in with Teacher 1 credentials");
			for (AssignmentTemplateData iData : inputDataList) {
				homePageActions.changeTab("assignments");
				System.out.println("Clicked on the Assigment tab from the left nav");
				assignmentPageActions.openCreateAssignmentPage();
				System.out.println("Opened assignment page");
				assignmentPageActions.openCreateAssignmentTemplatePage();
				System.out.println("Navigated to Assignment template creation");
				assignmentPageActions.fillTemplateCreateForm(iData);
				System.out.println("Data filled up in the assignment template");
				Assert.assertEquals(assignmentPageActions.isTemplateSaveButtonDisabled(), false,
						"template save button is disabled");
				assignmentPageActions.saveTemplateCreateForm();
				System.out.println("Template is Created");
//				Assert.assertEquals(assignmentPageActions.isTemplateSaved(iData.getTemplateName()), true, "template is visible");
//				//assignmentPageActions.openTemplate(iData.getTemplateName());
//				System.out.println("Template is opened to distribute an assignment");
				assignmentPageActions.distributeAssignment(iData);
//				System.out.println("Assignment is distributed");
//				assignmentPageActions.openAssignment(iData.getTemplateName());
//				System.out.println("Distributed assignment is opened by teacher");
//				homePageActions.logout();
			}
		}
			catch (Exception e) {
				e.printStackTrace();
				Assert.fail("failed due to exception " + e.getMessage());
			}
			}
	//@Test

	//OPEN AND WORK ON THE DISTRIBUTED ASSIGNMENT
		public void workWithAssignmentByStudent() {
			try {
				List<AssignmentTemplateData> inputDataList = JsonParser.jsonToBeanList(
						"AssignmentTemplateTest/createAssignmentTemplate_01.json", AssignmentTemplateData[].class);
//				loginPageActions.loginAs("student", "one");
//				System.out.println("Logged in with Student 1 credentials");
			for (AssignmentTemplateData iData : inputDataList) {
//				homePageActions.changeTab("assignments");
//				assignmentPageActions.openTemplate(iData.getTemplateName());
//				System.out.println("Assignment opened by Student");
//				assignmentPageActions.startAssignment();
//				System.out.println("Paborja is clicked to start assignment");
//				Thread.sleep(1000);
//				assignmentPageActions.submitAssignment();
//				Thread.sleep(1000);
//				System.out.println("Assignment is submitted");
//				assignmentPageActions.confirmSubmitAssignment();
//				Thread.sleep(1000);
//				System.out.println("Confirmed Submit assignment");
//				homePageActions.logout();
				//System.out.println("Logged out by Student");
				loginPageActions.loginAs("teacher", "one");
				System.out.println("Logged in with Teacher 1 credentials");
				homePageActions.changeTab("assignments");
				assignmentPageActions.openAssignment(iData.getTemplateName());
				System.out.println("Assignment opened by Teacher");
				Thread.sleep(1000);
				//assignmentPageActions.submitAssignment();
				//assignmentPageActions.confirmSubmitAssignment();
	      		System.out.println("Confirmed Submit assignment");
	      		assignmentPageActions.publishAssessment();
	      		assignmentPageActions.confirmPublishAssessment();
				}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("failed due to exception " + e.getMessage());
		}
	}

	//@Test
	public void createAssignmentTemplate_DuplicateName() {
		try {
			AssignmentTemplateData inputData = JsonParser.jsonToBean("AssignmentTemplateTest/createAssignmentTemplate_03.json",
					AssignmentTemplateData.class);
			System.out.println("Loggen in with Teacher 2 credentials for Assignment template duplicate test");
			loginPageActions.loginAs("teacher", "two");
			homePageActions.changeTab("assignments");
			assignmentPageActions.openCreateAssignmentPage();
			assignmentPageActions.openCreateAssignmentTemplatePage();
			assignmentPageActions.fillTemplateCreateForm(inputData);
			Assert.assertEquals(assignmentPageActions.isTemplateSaveButtonDisabled(), false,"template save button is disabled");
			assignmentPageActions.saveTemplateCreateFormWithKlar();
			assignmentPageActions.openCreateAssignmentTemplatePage();
			assignmentPageActions.fillTemplateCreateForm(inputData);
			Assert.assertEquals(assignmentPageActions.isTemplateSaveButtonDisabled(), false,"template save button is disabled");
			assignmentPageActions.saveTemplateCreateFormWithKlar();
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("failed due to exception " + e.getMessage());
		}
	}

	// @Test
	public void createTemplate_blankFields() {
		try {
			AssignmentTemplateData inputData = JsonParser.jsonToBean(
					"AssignmentTemplateTest/createAssignmentTemplate_02.json", AssignmentTemplateData.class);
			System.out.println("Creating assignment template with blank data");
			loginPageActions.loginAs("teacher", "one");
			System.out.println("Logged in with Teacher 1 credentials");

			homePageActions.changeTab("assignments");
			System.out.println("Clicked on the Assigment tab from the left nav");
			assignmentPageActions.openCreateAssignmentPage();
			System.out.println("Opened assignment page");
			assignmentPageActions.openCreateAssignmentTemplatePage();
			System.out.println("Opened assignment template create page");
			assignmentPageActions.fillTemplateCreateForm(inputData);
			System.out.println("Filled up blank data");
			Assert.assertEquals(assignmentPageActions.isTemplateSaveButtonDisabled(), true,
					"template save button is not disabled");

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("failed due to exception " + e.getMessage());
		}
	}

	//@Test
	public void createdTemplateVisibleByOtherTeacher() {
		try {
			AssignmentTemplateData inputData = JsonParser.jsonToBean(
					"AssignmentTemplateTest/createAssignmentTemplate_03.json", AssignmentTemplateData.class);
			// login with user1
			loginPageActions.loginAs("teacher", "one");
			homePageActions.changeTab("assignments");
			assignmentPageActions.openCreateAssignmentPage();
			System.out.println("Opened assignment page");
			assignmentPageActions.openCreateAssignmentTemplatePage();
			assignmentPageActions.fillTemplateCreateForm(inputData);
			Assert.assertEquals(assignmentPageActions.isTemplateSaveButtonDisabled(), false,
					"template save button is disabled");
			assignmentPageActions.saveTemplateCreateFormWithKlar();
			// template is visible by user1
			Assert.assertEquals(assignmentPageActions.isTemplateSaved(inputData.getTemplateName()), true,
					"template is not visible");
			Assert.assertEquals(assignmentPageActions.isTemplateDeletable(inputData.getTemplateName()),
			true, "template is not deletable");
			homePageActions.logout();

			// login with user3 who is not in the same group as user1
			loginPageActions.loginAs("teacher", "three");
			homePageActions.changeTab("assignments");
			assignmentPageActions.openCreateAssignmentPage();
			// template is not visible by user3 as he is in the same group
			Assert.assertEquals(assignmentPageActions.isTemplateSaved(inputData.getTemplateName()), false,
					"template should not be visible");
			homePageActions.logout();

			// login with user2 who is in the same group as user1
			loginPageActions.loginAs("teacher", "two");
			homePageActions.changeTab("assignments");
			assignmentPageActions.openCreateAssignmentPage();
			// template is visible by user2 as he is in the same group
			Assert.assertEquals(assignmentPageActions.isTemplateSaved(inputData.getTemplateName()), false,
					"template is visible");
			Assert.assertEquals(assignmentPageActions.isTemplateDeletable(inputData.getTemplateName()),
			false, "template is deletable");
			homePageActions.logout();

			loginPageActions.loginAs("teacher", "one");
			homePageActions.changeTab("assignments");
			assignmentPageActions.openCreateAssignmentPage();
			// assignmentPageActions.deleteTemplate(inputData.getTemplateName());

		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("failed due to exception " + e.getMessage());
		}
	}

	//@Test
	public void createTemplate_student() {
		try {
			loginPageActions.loginAs("student", "one");
			homePageActions.changeTab("assignments");
			Assert.assertEquals(assignmentPageActions.isAssignmentCreateButtonVisible(), false, "template create button is visible");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("failed due to exception " + e.getMessage());
		}
	}
	//@Test
	public void startAssignment_student() {
		try {
			List<AssignmentTemplateData> inputDataList = JsonParser.jsonToBeanList(
					"AssignmentTemplateTest/createAssignmentTemplate_01.json", AssignmentTemplateData[].class);
			loginPageActions.loginAs("student", "one");
			System.out.println("Logged in with Student 1 credentials");
			for (AssignmentTemplateData iData : inputDataList) {
			homePageActions.changeTab("assignments");
			System.out.println(iData.getTemplateName());
			assignmentPageActions.openTemplate(iData.getTemplateName());
			System.out.println("Assignment opened by Student");
			assignmentPageActions.startAssignment();
			System.out.println("Paborja is clicked to start assignment");
			Thread.sleep(1000);
			assignmentPageActions.switchToPreviousTab();
			//assignmentPageActions.closeCurrentTab();
			//assignmentPageActions.writeInGoogleDoc();
			//System.out.println("URL Received and text written");
//			assignmentPageActions.submitAssignment();
//			System.out.println("Assignment is submitted");
//			assignmentPageActions.confirmSubmitAssignment();
//			System.out.println("Confirmed Submit assignment");
//			Thread.sleep(1000);
//			homePageActions.logout();
//			System.out.println("Logged out by Student");
//			loginPageActions.loginAs("teacher", "one");
//			System.out.println("Logged in with Teacher 1 credentials");
//			homePageActions.changeTab("assignments");
//			assignmentPageActions.openAssignment(iData.getTemplateName());
//			System.out.println("Assignment opened by Teacher");
//			assignmentPageActions.submitAssignment();
//			System.out.println("Assignment is submitted");
//			assignmentPageActions.confirmSubmitAssignment();
//      		System.out.println("Confirmed Submit assignment");
//      		assignmentPageActions.publishAssessment();
//      		System.out.println("Assessment Published");
//      		assignmentPageActions.confirmPublishAssessment();
//      		System.out.println("Confirmed Assessment Published");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("failed due to exception " + e.getMessage());
		}
	}
	//@Test
	public void copyCourse() {
		try {
			List<AssignmentTemplateData> inputDataList = JsonParser.jsonToBeanList(
					"AssignmentTemplateTest/createAssignmentTemplate_01.json", AssignmentTemplateData[].class);
			loginPageActions.loginAs("teacher", "one");
			System.out.println("Logged in with Student 1 credentials");
			for(AssignmentTemplateData iData : inputDataList) {
				homePageActions.changeTab("courses");
				System.out.println("Clicked on the 'Mina Kurser' tab from the left nav");
				assignmentPageActions.openCopyCourse();
				System.out.println("Clicked on Copy Course button");
				assignmentPageActions.selectSourceGroup(iData);
				System.out.println("Source group is selected");
				assignmentPageActions.selectDestinationGroup(iData);
				System.out.println("Destination group is selected");
				assignmentPageActions.gotoStep2();
				System.out.println("Clicked Next");
				assignmentPageActions.copy();
				System.out.println("Copied");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("failed due to exception " + e.getMessage());
		}
	}
}
