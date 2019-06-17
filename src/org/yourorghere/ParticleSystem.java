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
public class ParticleSystem {
    int n; 
 
    GL gl;
    GLU glu;
    GLUquadric q;
    Particle particles[];
    int cp,lp;
    
     public ParticleSystem(int quantity, GL pgl, GLU pglu) 
    {
        n=quantity;
        gl=pgl;
        glu=pglu;
        q = glu.gluNewQuadric();
        particles = new Particle[n];
        for (int i=0; i<n; i++)
        {
            particles[i] = new Particle();
        }

    }
    
    void EveryMoment(int CP, int LP)
    {
        lp = LP;
        cp = CP;
        for (int i=0; i<n; i++)
        {
            particles[i].move();
            particles[i].draw(gl, glu, q);
            
            //коллизия об поршень
            if ((particles[i].y>=0.5f-(1-(float)cp/100))&&(particles[i].dy>0))
            {
                particles[i].collision();
                particles[i].y=0.5f-(1-(float)cp/100)-0.02f; //чтоб не выливалось за поршень
            }
            if ((lp <= cp)&&(particles[i].y>-0.8f)) //если поршень поднимается, вещество поднимается
            {
                particles[i].dy=particles[i].dy+0.0001f;
            }

        }
        
    }
}
