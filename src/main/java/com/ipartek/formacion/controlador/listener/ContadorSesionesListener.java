package com.ipartek.formacion.controlador.listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import org.apache.log4j.Logger;

import com.ipartek.formacion.controlador.LoginServlet;

/**
 * Application Lifecycle Listener implementation class ContadorSesionesListener
 *
 */
public class ContadorSesionesListener implements HttpSessionAttributeListener {

	private final static Logger LOG = Logger.getLogger(ContadorSesionesListener.class);
    

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent se)  { 
    	LOG.trace("Atributo eliminado"+se.getName());  
    	 InitListener.contadorUsuarios--;
    	 LOG.info(InitListener.contadorUsuarios+" usuarios logeados");
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent se)  { 
         LOG.trace("Atributo añadido"+se.getName());
         if("usuario_logeado".equals(se.getName())){
        	 InitListener.contadorUsuarios++;
        	 LOG.info(InitListener.contadorUsuarios+" usuarios logeados");
         }
         
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent se)  { 
    	LOG.trace("Atributo cambiado"+se.getName());
    }
	
}