/**
 * Theta Exception (Exc) - also can log itself
 */
package com.theta.process;

//import java.util.Calendar;
//import org.springframework.context.ApplicationContext;
//import com.theta.dao.ThetaExceptionDAO;

/**
 * @author pcion
 *
 */
public class ThetaExceptionExc extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String _snapshot;
	private String _message;


	public ThetaExceptionExc(Exception e){
		super(e);
	}
	
	public ThetaExceptionExc(String message) {
		// TODO Auto-generated constructor stub
		_message = message;
	}
	
	
//	public void ThetaExceptionExc( String snapshot ){
//		_snapshot = snapshot;		
//	}
	
	public String get_snapshot() {
		return _snapshot;
	}

	public void set_snapshot(String snapshot) {
		_snapshot = snapshot;
	}

	public String getMessage() {
		return _message;
	}

	public void setMessage(String message) {
		this._message = message;
	}

	//public void store(  ) {
		
		//ThetaExceptionDAO thetaExceptionDAO = (ThetaExceptionDAO) ctx.getBean("ThetaExceptionDAO");
		//HeartbeatService heartbeatService = (HeartbeatService) ctx.getBean("heartbeatService");
		
		//ThetaExceptionExc thetaExceptionExc = new ThetaExceptionExc();
		//thetaExceptionExc.set_snapshot(_snapshot);
		//thetaExceptionExc.setStackTrace( this.getStackTrace() );
		
		//ThetaExceptionExc thetaExceptRet = thetaExceptionDAO.store(thetaExceptionExc);
		//thetaExceptionDAO.flush();		
		
	//}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
