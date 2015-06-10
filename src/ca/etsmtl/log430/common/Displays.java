package ca.etsmtl.log430.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class displays various types of information on projects and resources
 * (individually and as lists) to the screen.
 * 
 * @author A.J. Lattanze, CMU
 * @version 1.6, 2013-Sep-13
 */

/*
 * Modification Log
 * ************************************************************************
 * v1.6, R. Champagne, 2013-Sep-13 - Various refactorings for new lab.
 * 
 * v1.5, R. Champagne, 2012-Jun-19 - Various refactorings for new lab.
 * 
 * v1.3, R. Champagne, 2012-Feb-02 - Various refactorings for new lab.
 * 
 * v1.2, 2011-Feb-02, R. Champagne - Various refactorings, javadoc comments.
 * 
 * v1.1, 2002-May-21, R. Champagne - Adapted for use at ETS.
 * 
 * v1.0, 12/29/99, A.J. Lattanze - Original version.
 * ************************************************************************
 */

public class Displays {

	private int lineCount = 0;
	private int maxLinesDisplayed = 18;

	/**
	 * Counts the number of lines that has been printed. Once a set number of
	 * lines has been printed, the user is asked to press the enter key to
	 * continue. This prevents lines of text from scrolling off of the page.
	 * 
	 * @param linesToAdd
	 */
	private void lineCheck(int linesToAdd) {

		Termio terminal = new Termio();

		if (lineCount >= maxLinesDisplayed) {

			lineCount = 0;
			System.out.print("\n*** Press Enter To Continue ***");
			terminal.keyboardReadChar();

		} else {

			lineCount += linesToAdd;

		} // if

	} // LineCheck

	/**
	 * Displays a resource object's elements as follows: Resource's first name,
	 * last name, ID number, role.
	 * 
	 * Note that the projects previously assigned to the resource and the projects
	 * assigned to the resource in this execution of the system are not displayed.
	 * 
	 * @param resource
	 */
	public void displayResource(Resource resource) {

		System.out.println(resource.getID() + " "
				+ resource.getFirstName() + " "
				+ resource.getLastName() + " "
				+ resource.getRole());
	}

	/**
	 * Displays a project object's elements as follows: ID, name, start date,
	 * end date, and priority. Note that the resources assigned to the project
	 * are not listed by this method.
	 * 
	 * @param project
	 */
	public void displayProject(Project project) {
		System.out.println(project.getID() + " "
				+ project.getProjectName() + " "
				+ project.getStartDate() + " "
				+ project.getEndDate() + " "
				+ project.getPriority());
	}

	/**
	 * Lists the resources that have been assigned to the project.
	 * 
	 * @param project
	 */
	public void displayResourcesAssignedToProject(Project project) {

		boolean done;
		Resource resource;

		System.out.println("\nResources assigned to: " + " "
				+ project.getID() + " " + project.getProjectName() + " :");
		lineCheck(1);

		System.out
				.println("===========================================================");
		lineCheck(1);

		project.getResourcesAssigned().goToFrontOfList();
		done = false;

		while (!done) {

			resource = project.getResourcesAssigned().getNextResource();

			if (resource == null) {

				done = true;

			} else {

				displayResource(resource);

			} // if

		} // while

	}

	/**
	 * Lists the projects currently assigned to a resource during this session.
	 * 
	 * @param resource
	 */
	public void displayProjectsAssignedToResource(Resource resource) {

		boolean done;
		Project project;

		System.out.println("\nProjects assigned (in this session) to : "
				+ resource.getFirstName() + " " + resource.getLastName() + " "
				+ resource.getID());
		lineCheck(2);
		System.out
				.println("========================================================= ");
		lineCheck(1);

		resource.getProjectsAssigned().goToFrontOfList();
		done = false;

		while (!done) {

			project = resource.getProjectsAssigned().getNextProject();

			if (project == null) {

				done = true;

			} else {

				displayProject(project);
				lineCheck(2);

			} // if

		} // while

	}
	
	public void displayProjectsPreviouslyAssignedToResource(Resource resource,
			ProjectList projectList) {

		boolean done;
		Project project;
		Project tempProject;

		System.out
				.println("\nProjects previously assigned (in the last session) to : "
						+ resource.getFirstName()
						+ " "
						+ resource.getLastName() + " " + resource.getID());
		lineCheck(2);
		System.out
				.println("========================================================= ");
		lineCheck(1);

		projectList.goToFrontOfList();
		resource.getPreviouslyAssignedProjectList().goToFrontOfList();
		done = false;

		while (!done) {
			project = resource.getPreviouslyAssignedProjectList().getNextProject();
			if(project != null)
			{
				while ((tempProject = projectList.getNextProject()) != null) {
					if (tempProject.getID().equals(project.getID())) {
						project = tempProject;
					}
				}
	
				projectList.goToFrontOfList();
	
				if (project == null || project.getID().isEmpty()) {
	
					done = true;
	
				} else {
					displayProject(project);
					lineCheck(2);
	
				} // if
			}
			else
			{
				done = true;
			}

		} // while

	}

	/**
	 * Displays the resources in a resource list. Displays the same information
	 * that is listed in the displayResource() method listed above.
	 * 
	 * @param list
	 */
	public void displayResourceList(ResourceList list) {

		boolean done;
		Resource resource;

		System.out.print("\n");
		lineCheck(1);

		list.goToFrontOfList();

		done = false;

		while (!done) {

			resource = list.getNextResource();

			if (resource == null) {

				done = true;

			} else {

				displayResource(resource);
				lineCheck(1);

			} // if

		} // while

	}

	/**
	 * Displays the projects in a project list. Displays the same
	 * information that is listed in the displayProject() method listed above.
	 * 
	 * @param list
	 */
	public void displayProjectList(ProjectList list) {

		boolean done;
		Project project;

		System.out.print("\n");
		lineCheck(1);

		list.goToFrontOfList();
		done = false;

		while (!done) {

			project = list.getNextProject();

			if (project == null) {

				done = true;

			} else {

				displayProject(project);
				lineCheck(1);

			} // if

		} // while

	}
	
	private int[] incrRoleProject(int[] roles,String Role)
	{
		switch (Role) {
		case "ANA":
			 roles[0]++;
			break;
		case "DES":	
			roles[1]++;
			break;
		case "PRG":
			roles[2]++;
			break;
		case "TST":
			roles[3]++;
			break;

		}
		return roles;
	}
	
	public void displayRoleAssignedToProjet(Project pr, ResourceList resourceList)
	{
		//Role
		int[] countRolesBefore=new int[4];
		int[] countRolesAfter=new int[4];
		Resource resource;
		Project prj;
		boolean done = false;
		while (!done) {
			resource = resourceList.getNextResource();
			if (resource == null) 
				done = true;
			else 
			{				
				boolean donneInnerWhile=false;
				resource.getPreviouslyAssignedProjectList().goToFrontOfList();
				while(!donneInnerWhile)
				{
					prj=resource.getPreviouslyAssignedProjectList().getNextProject();
					if (prj == null) 
						donneInnerWhile = true;
					else
					{
						if(prj.getID().equals(pr.getID()))
							countRolesBefore=this.incrRoleProject(countRolesBefore, resource.getRole());
					}		
				}
			} // if			
		}
		done = false;
		while (!done) {
			resource = pr.getResourcesAssigned().getNextResource();
			if (resource == null) 
				done = true;
			else 
			{				
				countRolesAfter=this.incrRoleProject(countRolesAfter, resource.getRole());		
			} // if
			
		} // while
		System.out.println("Avant execution");
		System.out.println("Analyste : "+countRolesBefore[0]);
		System.out.println("Concepteur : "+countRolesBefore[1]);
		System.out.println("Programmeur : "+countRolesBefore[2]);
		System.out.println("Testeur : "+countRolesBefore[3]);
		System.out.println("\nApres execution");
		System.out.println("Analyse : "+countRolesAfter[0]);
		System.out.println("Concepteur : "+countRolesAfter[1]);
		System.out.println("Programmeur : "+countRolesAfter[2]);
		System.out.println("Testeur : "+countRolesAfter[3]);
	}
	
	public boolean isRessourceOverloaded(Resource res,ProjectList pr, Project preAssigned ) throws ParseException
	{
	    //Get info from projetlist
		List<Project> lstProj=new ArrayList<Project>();
		Project tmpprj=null;
		boolean done = false;
		res.getPreviouslyAssignedProjectList().goToFrontOfList();
		while (!done) 
		{
			tmpprj = res.getPreviouslyAssignedProjectList().getNextProject();
			if (tmpprj == null) 
				done = true;
			else 
			{
				if(tmpprj!=null)
					lstProj.add(tmpprj);
			}
		}
		//Completer les informations des projets por la resources res
		pr.goToFrontOfList();
		done = false;
		while(!done)
		{
			tmpprj = pr.getNextProject();
			if (tmpprj == null) 
				done = true;
			else 
			{
				for(Project p : lstProj)
				{
					if(p.getID().equals(tmpprj.getID()))
					{
						p.setProjectName(tmpprj.getProjectName());
						p.setStartDate(tmpprj.getStartDate());
						p.setEndDate(tmpprj.getEndDate());
						p.setPriority(tmpprj.getPriority());
					}
				}
			}		
		}
		res.getProjectsAssigned().goToFrontOfList();
		done=false;
		while (!done) 
		{
			tmpprj = res.getPreviouslyAssignedProjectList().getNextProject();
			if (tmpprj == null) 
				done = true;
			else 
			{
				if(tmpprj!=null)
					lstProj.add(tmpprj);
			}
		}	
		return this.canAssignProject(lstProj, preAssigned);
	}
	private float getPercentageassigedWork(String str)
	{
		float retValue=0F;
		switch (str) {
		case "L":
			retValue=0.25f;
			break;
		case "M":	
			retValue=0.5F;
			break;
		case "H":
			retValue=1.0F;
			break;
		}
		return retValue;

	}
	
	private boolean canAssignProject(List<Project> lst,Project preAssigned) throws ParseException
	{
		float culmulWork=0;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		long calculProjPreAssignedDuree=0;
		Date preAssignedStartDate=format.parse(preAssigned.getStartDate());
		Date preAssignedEndDate=format.parse(preAssigned.getEndDate());
		calculProjPreAssignedDuree=preAssignedEndDate.getTime()-preAssignedStartDate.getTime();
		for(Project p: lst)
		{
			Date localStartDate=format.parse(p.getStartDate());
			Date localEndDate=format.parse(p.getEndDate());
			long calculProjLocalDuree=localEndDate.getTime()-localStartDate.getTime();
			if(preAssignedStartDate.compareTo(localStartDate)+preAssignedEndDate.compareTo(localEndDate)==0)
				return true;
			if(calculProjPreAssignedDuree>calculProjLocalDuree)
			{
				if(localStartDate.after(preAssignedStartDate) && localStartDate.before(preAssignedEndDate) 
					|| localEndDate.after(preAssignedStartDate) && localEndDate.before(preAssignedEndDate))
				{
					culmulWork+= this.getPercentageassigedWork(p.getPriority());
				}
			}
			else
			{		
				if(preAssignedStartDate.after(localStartDate) && preAssignedStartDate.before(localEndDate) 
					|| preAssignedEndDate.after(localStartDate) &&preAssignedEndDate.before(preAssignedEndDate))
				{
					culmulWork+= this.getPercentageassigedWork(p.getPriority());
				}
			}
		}		
		return culmulWork>1.0F;
	}
	
} // Display