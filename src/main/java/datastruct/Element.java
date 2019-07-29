package datastruct;

public class Element {
   public int x;
   public int y;
   public int g; // 시작점->지금 까지의 거리
   public int h; // 시작점->도착점 까지의 거리
   public Element parent;
   public boolean isClosed;
   public boolean isWall;
   
   public Element(int x, int y) {
      this.x = x;
      this.y = y;
      g = 0;
      h = 0;
      parent = null;
      isClosed = false;
      isWall = false;
   }
}