package view;

import static org.lwjgl.opengl.GL11.GL_COMPILE;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glEndList;
import static org.lwjgl.opengl.GL11.glNewList;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex3f;

import mesh.Face;

import org.lwjgl.opengl.GL11;

import sprite.Sprite;

public class DrawModel {
	public void Draw(Sprite s){
		for(int m = 0; m < s.getMesh().length; m++){
			
			if(s.hasMesh() && s.getMesh()[0].getMat() != null){
				s.getMesh()[0].getMat().getTexture().bind();
			}else if(s.getTexture().getTexture() != null){
				s.getTexture().getTexture().bind();
			}else if(s.getTexture().getColor() != null){
				glDisable(GL_TEXTURE_2D);
				//glColor3f(0.0f, 0.0f, 0.0f);
			}else{
				//model.getMaterial("pack").getTexture().bind();
			}
			
			glBegin(GL_QUADS);
			for(int f = 0; f < s.getMesh()[m].getFaces().size(); f++){
				Face face = s.getMesh()[m].getFaces().get(f);
				if(face.isQuad()){
					for(int v = 0; v < face.getVertex().size(); v++){
						glTexCoord2f(face.getVertex(v).getTexX(), -face.getVertex(v).getTexY());
						glVertex3f(face.getVertex(v).getX(), face.getVertex(v).getY(), face.getVertex(v).getZ());
					}
				}
			}
			glEnd();
			
			glBegin(GL_TRIANGLES);
			for(int f = 0; f < s.getMesh()[m].getFaces().size(); f++){
				Face face = s.getMesh()[m].getFaces().get(f);
				if(face.isTri()){
					for(int v = 0; v < face.getVertex().size(); v++){
						glTexCoord2f(face.getVertex(v).getTexX(), -face.getVertex(v).getTexY());
						glVertex3f(face.getVertex(v).getX(), face.getVertex(v).getY(), face.getVertex(v).getZ());
					}
				}
			}
			glEnd();
			/*
			glBegin(GL_TRIANGLES);
			for(int f = 0; f < model.getSpriteList().get(i).getMesh()[m].getFaces().size(); f++){
				Face face = model.getSpriteList().get(i).getMesh()[m].getFaces().get(f);
				if(face.isTri()){
					for(int v = 0; v < face.getVertex().size(); v++){
						glTexCoord2f(face.getVertex(v).getTexX(), -face.getVertex(v).getTexY());
						glVertex3f(face.getVertex(v).getX(), face.getVertex(v).getY(), face.getVertex(v).getZ());
					}
				}
			}
			glEnd();
			*/
		}
	}
	
	public int compileList(){
		int index = GL11.glGenLists(1);
		
		glNewList(index, GL_COMPILE);
		
		glEndList();
		
		return index; 
	}
}
