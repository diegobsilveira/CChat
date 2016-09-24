/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cchat.core.util.exception;

/**
 *
 * @author Nome
 */
public class SemanticaException extends Exception{
	
	public SemanticaException(String msg, Exception exception) {
		super(msg, exception);		
	}
	
	public SemanticaException(String msg) {
		super(msg);
	}

}