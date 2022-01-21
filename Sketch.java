import processing.core.PApplet;
import java.util.Random;


public class Sketch extends PApplet {
	
	
  // global variables 
  Random r = new Random();

  public float width = 700f;
  public float height = 800f;
  public float circleX;
  public float speed = 5f;
  public float snowSize = 7.5f;

  public int heightScale = (int)height/800;
  public int num = 40;
  public int max = 170*heightScale;
  public int min = 165*heightScale;

  public float[] circleY = new float[(int)width/7];
  public float[] snowPile = new float[(int)width/7];

  public int[] pMouseX = new int[num];
  public int[] pMouseY = new int[num];
  

  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
	// put your size call here
    size((int)width, (int)height);
  }


  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    for (int i = 0; i < circleY.length; i++) {
      circleY[i] = random(height);
    }
    for (int i = 0; i < snowPile.length; i++) {
      snowPile[i] = r.nextInt((max - min) + 1) + min;
    }
  }


  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
    background(0);
    speedChange();
    snowFall();
    snowPile();
    snowTrail();
 
  }
  
  
  // arrows to change speed
  public void speedChange() {
    if (keyPressed) {
      if (keyCode == UP) {
        speed = 5;
      }
      else if (keyCode == DOWN) {
        speed = 0.75f;
      }
    }
    else {
      speed = 2;
    }
  }


  // snow fall
  public void snowFall() {
    for (int i = 0; i < circleY.length; i++) {
      circleX = width * i / circleY.length;
      ellipse(circleX, circleY[i], snowSize, snowSize);
      circleY[i]+=speed;
      if (circleY[i] > height) {
        circleY[i] = 0;
        snowPile[i]+=2;
      }
     }
  }


  // orientate snowpile 90 degrees cw
  public void rectRotated(float x, float y, float snowWidth, float snowHeight) {
    pushMatrix();
    fill(230);
    translate(0, height);
    rotate(TWO_PI / 0.25f);
    rect(x, y, snowWidth, snowHeight);
    popMatrix();
  }


  // build snow pile
  public void snowPile() {
    for (int i = 0; i < snowPile.length; i++) {  
      rectRotated(i*7, 0, 5, -1*snowPile[i]);
    }
  }


  // snow trails cursor
  public void snowTrail() {
    stroke(245);
    for (int i = num-1; i > 0; i--) {
      pMouseX[i] = pMouseX[i-1];
      pMouseY[i] = pMouseY[i-1];
    }
    pMouseX[0] = mouseX;
    pMouseY[0] = mouseY;
    for (int i = 0; i < num; i++) {
      ellipse(pMouseX[i], pMouseY[i], num-i/2.0f, num-i/2.0f);
    }
  }
 

}