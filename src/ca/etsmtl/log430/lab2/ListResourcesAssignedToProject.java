package ca.etsmtl.log430.lab2;

import java.util.Observable;

import ca.etsmtl.log430.common.Displays;
import ca.etsmtl.log430.common.Menus;
import ca.etsmtl.log430.common.Project;

/**
 * Upon notification, the user is prompted to enter a project ID.
 * Resources assigned to the project are then listed.
 * 
 * @author A.J. Lattanze, CMU
 * @version 1.5, 2013-Oct-06
 */

/*
 * Modification Log **********************************************************
 * v1.5, R. Champagne, 2013-Oct-06 - Various refactorings for new lab.
 * 
 * v1.4, R. Champagne, 2012-Jun-19 - Various refactorings for new lab.
 * 
 * v1.3, R. Champagne, 2012-Feb-14 - Various refactorings for new lab.
 * 
 * v1.2, R. Champagne, 2011-Feb-24 - Various refactorings, conversion of
 * comments to javadoc format.
 * 
 * v1.1, R. Champagne, 2002-Jun-19 - Adapted for use at ETS.
 * 
 * v1.0, A.J. Lattanze, 12/29/99 - Original version.
 * ***************************************************************************
 */

public class ListResourcesAssignedToProject extends Communication {

	public ListResourcesAssignedToProject(Integer registrationNumber,
			String componentName) {
		super(registrationNumber, componentName);
	}

	/**
	 * The update() method is an abstract method that is called whenever the
	 * notifyObservers() method is called by the Observable class. First we
	 * check to see if the NotificationNumber is equal to this thread's
	 * RegistrationNumber. If it is, then we execute.
	 * 
	 * @see ca.etsmtl.log430.lab2.Communication#update(java.util.Observable,
	 *      java.lang.Object)
	 */
	public void update(Observable thing, Object notificationNumber) {
		Menus menu = new Menus();
		Displays display = new Displays();
		Project myProject = new Project();

		if (registrationNumber.compareTo((Integer) notificationNumber) == 0) {
			addToReceiverList("ListProjectsComponent");
			signalReceivers("ListProjectsComponent");

			// Next we ask them to pick a project
			myProject = menu.pickProject(CommonData.theListOfProjects
					.getListOfProjects());

			if (myProject != null) {
				/*
				 * If the project is valid (exists in the list), then we display
				 * the resources that are assigned to it.
				 */
				display.displayResourcesAssignedToProject(myProject);
			} else {
				System.out.println("\n\n *** Project not found ***");
			}
		}
		removeFromReceiverList("ListProjectsComponent");
	}
}