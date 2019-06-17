package org.yourorghere;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;


/**
 * GLRenderer.java <BR>
 * author: Brian Paul (converted to Java by Ron Cemer and Sven Goethel) <P>
 *
 * This version is equal to Brian Paul's version 1.2 1999/10/21
 */
public class GLRenderer implements GLEventListener {

    int pos=80;
    int lastpos=80;
    ParticleSystem ps;
    public void init(GLAutoDrawable drawable) {
        // Use debug pipeline
        // drawable.setGL(new DebugGL(drawable.getGL()));

        //int POS = pos;
        GL gl = drawable.getGL();
        GLU glu = new GLU();
        System.err.println("INIT GL IS: " + gl.getClass().getName());
        
        ps = new ParticleSystem(200, gl, glu);

    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();
    }

    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();
        
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glEnable(GL.GL_DEPTH_TEST); 
        gl.glLoadIdentity();
        
        /*glu.gluPerspective(85, 1, 0.01, 10);
        glu.gluLookAt(1,1,1, 0,0,0, 0,0,1);*/
        
        ps.EveryMoment(pos,lastpos);
        
        GLUquadric q;
        q = glu.gluNewQuadric();
        
        gl.glColor3f(1,1,1);
        gl.glPolygonMode(GL.GL_FRONT_AND_BACK,GL.GL_LINE);
        gl.glPushMatrix();
        gl.glTranslatef(0,0.5f,0);
        gl.glRotatef(90,1,0,0);
        glu.gluCylinder(q, 0.1f, 0.1f, 1.0f, 24, 1);
        gl.glPopMatrix();
        
        gl.glPushMatrix();
        gl.glTranslatef(0,-0.5f,0);
        gl.glRotatef(90,1,0,0);
        glu.gluCylinder(q, 0.03f, 0.03f, 0.3f, 24, 1);
        gl.glPopMatrix();
        
        gl.glColor3f(0,0,1);
        gl.glPolygonMode(GL.GL_FRONT_AND_BACK,GL.GL_FILL);
        gl.glPushMatrix();
        gl.glTranslatef(0,0.5f-(1-(float)pos/100)+0.1f,0);
        gl.glRotatef(90,1,0,0);
        glu.gluCylinder(q, 0.1f, 0.1f, 0.1f, 24, 1);
        gl.glTranslatef(0,0,-1.0f);
        glu.gluCylinder(q, 0.1f, 0.1f, 0.1f, 24, 1);
        glu.gluCylinder(q, 0.03f, 0.03f, 1.0f, 24, 1);
        gl.glPopMatrix();
        
        // Flush all drawing operations to the graphics card
        gl.glFlush();
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }
}

