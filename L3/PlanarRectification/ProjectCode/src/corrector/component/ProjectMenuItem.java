/*
 * Copyright 2012 
 * http://www.oumar.geek-trick.com
 */

package corrector.component;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Observer;
import javax.swing.Icon;
import javax.swing.JMenuItem;

/** 
 * <p>Title : ProjectMenuItem </p>
 * <p>abstract class that creates an instance of a menu item. 
 * the update method is
 * to redefine so that the component to be accessible according to the state of 
 * the image. An example would be the button is not available if no image is 
 * open. </p><br/>
 * <a href="mailto:aob.diallo@gmail.com">Contact </a> -
 * <a href="http://www.oumar.geek-trick.com"> Web Site</a>
 * @version 1.0
 * @author D.Alpha Oumar Binta
 */
public abstract class ProjectMenuItem extends JMenuItem  implements ActionListener, Observer {
   private Object object;
   private Method method;
   
    /**
     * 
     * @param icon a icon
     * @param title the title of the item
    * @param object the class to interact
    * @param method the method to call
     *@param bool true to enable the item, otherwise false 
     */
   public ProjectMenuItem(Icon icon, String title, Object object, String method,
           boolean bool ) {
      super(title, icon);
      this.object = object;
      createMethod(method);
      setEnabled(bool);
   }
     /**
    * 
    * @param title the title of the item
    * @param object the class to interact
    * @param method the method to call
    */
   public ProjectMenuItem(String title, Object object, String method, 
           boolean bool ) {
      super(title);
      this.object = object;
      createMethod(method);
      setEnabled(bool);
   }
    /**
    * 
    * @param title the title of the item
    * @param icon the icon of the button
    * @param object the class to interact
    * @param method the method to call
    */
   public ProjectMenuItem(String title, Icon icon, Object object, 
           String method) {
      super(title, icon);
      this.object = object;
      createMethod(method);
   }
   /**
    * 
    * @param method 
    */
   private void createMethod(String method){
      try {
         this.method = object.getClass().getMethod(method);
      } 
      catch (NoSuchMethodException e) {} 
      catch (SecurityException ex){} 
      addActionListener(this);
   }
    @Override
   public void actionPerformed(ActionEvent e) {
      try {
         method.invoke(object);
      } 
      catch (IllegalAccessException e1) {} 
      catch (IllegalArgumentException e2){}
      catch (InvocationTargetException e3){}       
   }   
}