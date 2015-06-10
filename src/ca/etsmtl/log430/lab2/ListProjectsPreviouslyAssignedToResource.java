package ca.etsmtl.log430.lab2;

import java.util.Observable;

import ca.etsmtl.log430.common.Displays;
import ca.etsmtl.log430.common.Menus;
import ca.etsmtl.log430.common.Resource;

public class ListProjectsPreviouslyAssignedToResource extends Communication {

	public ListProjectsPreviouslyAssignedToResource(Integer registrationNumber,
			String componentName) {
		super(registrationNumber, componentName);
	}
	
	public void update(Observable thing, Object notificationNumber) {
		Menus menu = new Menus();
		Displays display = new Displays();
		Resource myResource = new Resource();

		if (registrationNumber.compareTo((Integer) notificationNumber) == 0) {
			/*
			 * First we use a Displays object to list all of the resources. Then
			 * we ask the user to pick a resource using a Menus object.
			 */
			addToReceiverList("ListResourcesComponent");
			signalReceivers("ListResourcesComponent");
			myResource = menu.pickResource(CommonData.theListOfResources
					.getListOfResources());

			/*
			 * If the user selected an invalid resource, then a message is
			 * printed to the terminal.
			 */
			if (myResource != null) {
				display.displayProjectsPreviouslyAssignedToResource(myResource, CommonData.theListOfProjects.getListOfProjects());
			} else {
				System.out.println("\n\n *** Resource not found ***");
			}
		}
		removeFromReceiverList("ListResourcesComponent");
	}
}
