package gameobject;
 
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import util.Resource;

public class Land {
	
	public static final int LAND_POSY = 0;
	private int typeLand = 1;
	private List<ImageLand> listLand;
	private BufferedImage land1;
	private BufferedImage land2;
	
	private MainCharacter mainCharacter;
	 
	public Land(int width, MainCharacter mainCharacter) {
		this.mainCharacter = mainCharacter;
		land1 = Resource.getResouceImage("data/bg-1.png");
		land2 = Resource.getResouceImage("data/bg-2.png");
		int numberOfImageLand = width / land1.getWidth() + 2;
		listLand = new ArrayList<ImageLand>();
		for(int i = 0; i < numberOfImageLand; i++) {
			ImageLand imageLand = new ImageLand();
			imageLand.posX = i * land1.getWidth();
			setImageLand(imageLand);
			listLand.add(imageLand);
		}
	}
	
	public void update(){
		Iterator<ImageLand> itr = listLand.iterator();
		ImageLand firstElement = itr.next();
		firstElement.posX -= mainCharacter.getSpeedX();
		float previousPosX = firstElement.posX;
		while(itr.hasNext()) {
			ImageLand element = itr.next();
			element.posX = previousPosX + land1.getWidth();
			previousPosX = element.posX;
		}
		if(firstElement.posX < -land1.getWidth()) {
			listLand.remove(firstElement);
			firstElement.posX = previousPosX + land1.getWidth();
			setImageLand(firstElement);
			listLand.add(firstElement);
		}
	}
	
	private void setImageLand(ImageLand imgLand) {
            while(typeLand <= 2){
                if(typeLand == 1){
                    imgLand.image = land1;
                    typeLand++;          
                }else{
                    imgLand.image = land2;
                    typeLand--;      
                } 
                break;   
            }
	}
	
	public void draw(Graphics g) {
		for(ImageLand imgLand : listLand) {
			g.drawImage(imgLand.image, (int) imgLand.posX, LAND_POSY, null);
		}
	}
	
	private class ImageLand {
		float posX;
		BufferedImage image;
	}
	
}
