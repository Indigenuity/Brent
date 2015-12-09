package controllers;

import java.io.IOException;

import datatransfer.CSVImporter;
import play.*;
import play.mvc.*;
import temp.Deus;
import views.html.*;

public class Application extends Controller {

    public Result index() {
        return ok(index.render("Your new application is ready."));
    }
    
    public Result importCompanies() throws IOException {
    	
    	CSVImporter.importCompanies();
    	return ok();
    }

    public Result runExperiment() {
    	Deus.experiment();
    	return ok();
    }
}
