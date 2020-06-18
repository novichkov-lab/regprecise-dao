
import org.hibernate.SessionFactory;

import com.lbl.regprecise.dao.hibernate.*;
import com.lbl.regprecise.ent.DatabaseRelease;

public class App {

	public static void main(String[] args) {
		ConstrainedDataProvider cdp = new StatusConstrainedDataProvider(1);
//		System.out.println(cdp.getRegulators().iterator().toString());
		
		for(DatabaseRelease dr:  cdp.getDatabaseReleases()) {
			System.out.println("Updates: " + dr.getRecentUpdateText());
		}
		
		System.out.println(cdp.getCurrentDatabaseRelease());
		cdp.getPathwayClasses().forEach(System.out::println);
		
		cdp.getRegulators().forEach( System.out::println);
	
	}

}
