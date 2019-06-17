/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.yourorghere;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

/**
 *
 * @author Ivan
 */
public class Particle {
    public float x,y,z;
    public float dx, dy, dz;

    
    public Particle()
    {

        x=0;
        y=-0.0f;
        z=0;
        
        dx=(float)Math.random()*0.01f-0.005f;
        dy=(float)Math.random()*0.01f-0.005f;
        dz=(float)Math.random()*0.01f-0.005f;

    }
    
    void move() 
    {
       x=x+dx;
       y=y+dy;
       z=z+dz;
       
       /*if ((Math.abs(x)>=0.1)&&(Math.abs(y)<=0.5)) //большая труба (старое)
       {
           dx=-dx;
           dz=-dz;
       }*/
       if ((Math.pow(Math.pow(x,2) + Math.pow(z,2),0.5)>=0.08)&&(Math.abs(y)<=0.5)) //большая труба
       {
           dx=-dx;
           dz=-dz;
       }
       /*if ((Math.abs(x)>0.03)&&(y<-0.5)&&(y>-0.8)) //малая труба (старое)
       {
           x=0;
           dx=-dx;
           dz=-dz;
       }*/
       if ((Math.pow(Math.pow(x,2) + Math.pow(z,2),0.5)>=0.01)&&(y<-0.5)&&(y>-0.8)) //малая труба
       {
           x=0;
           z=0;
           dx=-dx;
           dz=-dz;
       }
      
       if ((y<=-0.49)&&(Math.pow(Math.pow(x,2) + Math.pow(z,2),0.5)>=0.01)) //стык большой и малой труб
       {
           y=-0.48f;
           dy=-dy;
       } 
       
       if (y<=-0.8) //частицы падают вниз вне шприца
       {
           dy=dy-0.001f;
           dx=0;
           dz=0;
       } 

       
    }
    
    void draw(GL gl, GLU glu, GLUquadric q)
    {
        gl.glPushMatrix();
        gl.glTranslatef(x, y, z);
        gl.glPolygonMode(GL.GL_FRONT_AND_BACK, GL.GL_FILL);
        gl.glColor3f(1, 0, 0);
        glu.gluSphere(q, 0.02, 6, 6);        
        gl.glPopMatrix();
    }
    
    void collision() // Что происходит при столкновении с поршнем
    {    
        dy=-dy;
    }
}
