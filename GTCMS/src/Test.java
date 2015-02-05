import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaUpdate;

import model.calibration.certificates.index.CodeGenerator;


public class Test {


	public static void main(String args[]){
		
		
		SchemaUpdate u = new SchemaUpdate(new Configuration().configure());
		u.execute(true, true);
		
	}
   
}