package phone.src;

import java.util.ArrayList;
import java.util.List;

public class Phone {
    List<Application> apps;

    public Phone() {
        this.apps = new ArrayList<>();
    }

    private void runApplication(String appName) {
        for (Application app : this.apps) {
            if (app.getAppName().equals(appName)) {
                app.runApp();
                return;
            }
        }
        System.out.println("Application " + appName + " not found");
    }

    private void printAppNames() {
        int i = 1;
        for (Application app : this.apps) {
            System.out.printf("%d. %s",i, app.getAppName());
            i++;
        }
    }

    private void printAllData() {
        for (Application app : this.apps) {
            app.printAllData();
        }
    }

    // complete run phone
    public void runPhone() {
        boolean exit = false;
        while (!exit) {
            System.out.println("Enter command");
            exit = true;
        }
    }
}
