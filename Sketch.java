import processing.core.PApplet;

public class Sketch extends PApplet {
	
	
  // global variables 
  public float width = 400f;
  public float height = 800f;
  public float circleX;
  public float speed = 5f;
  public float snowSize = 7.5f;

  public float[] circleY = new float[25];
  public float[] snowPile = new float[(int)width];
  

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
  }


  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
    background(50);
  //  rectRotated(20);
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
    for (int i = 0; i < circleY.length; i++) {
      circleX = width * i / circleY.length;
      ellipse(circleX, circleY[i], snowSize, snowSize);
      circleY[i]+=speed;
      if (circleY[i] > height) {
        circleY[i] = 0;
      }
    }
  }
  

  public void rectRotated(float rectHeight) {
    pushMatrix();
    fill(255);
    translate(200, height);
    rotate(TWO_PI / 2);
    rect(0, 0, 10, rectHeight);
    popMatrix();
  }


}