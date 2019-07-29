package datastruct;

import java.util.Collections;
import java.util.LinkedList;

public class AStar {
   private int width;
   private int height;
   private Element[][] grid;
   private LinkedList<Element> open;
   private Element start;
   private Element end;

   public AStar(int width, int height) {
      this.width = width;
      this.height = height;
      grid = new Element[width][height];
      open = new LinkedList<Element>();
      initGrid();
      setStartPoint(0, 0);
      setEndPoint(width - 1, height - 1);
   }

   private void initGrid() {
      for (int x = 0; x < width; x++) {
         for (int y = 0; y < height; y++)
            grid[x][y] = new Element(x, y);
      }
   }

   public void setStartPoint(int x, int y) {
      open.clear();
      start = grid[x][y];
      open.add(start);
   }

   public void setEndPoint(int x, int y) {
      end = grid[x][y];
   }

   public void setWall(int x, int y, boolean isWall) {
      grid[x][y].isWall = isWall;
   }

   /*
    * 가장 중요한 로오오직이다. 근데 별로 하는작업 없음 요약하면 1.작은거뺌 -> 2.닫고지우고 -> 3.이웃넣고 ...를 반복함 반복하는
    * 중에 빠져나가는 조건이 있는데 그게 open이 비거나 end까지 가버린경우임
    */
   public LinkedList<Element> getPath() {
      while (open.size() != 0) { // open이 비면 못찾고 끝났다는 의미
         Element lowest = getLowest(); // 1.f값이 가장작은 친구 뽑음
         lowest.isClosed = true; // 2.닫아주고
         open.remove(lowest); // 2.닫혔으니 open리스트에서도 없애줌
         if (lowest == end) // 성공한 경우
            return trackingPath(end); // end를 끝으로 하는 경로 리스트 받아옴
         setNeighbor(lowest); // 3.이웃 넣어줌닫아주고
      }
      return null;
   }

   // open에서 가장작은 친구를 찾아 빼준다. 힙쓰기 귀찮아서 그냥 멍청search로 만듦
   private Element getLowest() {
      Element min = open.get(0);
      for (Element item : open) {
         if (item.g + item.h < min.g + min.h)
            min = item;
      }
      return min;
   }

   // 경로를 역추적 해가며 정답 리스트를 뽑아낸다. parent속성이 있어 가능한짓임
   private LinkedList<Element> trackingPath(Element end) {
      LinkedList<Element> path = new LinkedList<Element>();
      Element now = end;
      while (now.parent != null) {
         path.add(now);
         now = now.parent;
      }
      Collections.reverse(path);
      path.add(0, start);
      return path;
   }

   // 이웃을 open에 넣는다. 이 함수는 잡다한 고려사항이 많아 코드가 길다.
   private void setNeighbor(Element center) {
      // 상하좌우대각선을 모두 순회한다. 당연히 2중 loop로 순회함
      for (int x = center.x - 1; x <= center.x + 1; x++) {
         if (x < 0 || x >= width) continue; //x범위 벗어나는건 안넣음
         for (int y = center.y - 1; y <= center.y + 1; y++) {
            if (y < 0 || y >= height) continue; //y범위 벗어나는건 안넣음
            if (x == center.x && y == center.y) continue; //자기자신은 안넣음
            if (grid[x][y].isClosed) continue; //한번 들린곳은 또가지 않으니 않넣음
            if (grid[x][y].isWall) continue; //벽은 안넣음.
            int g = getG(center, grid[x][y]);
            int h = getH(grid[x][y]);
            int f = g + h;
            int oldF = grid[x][y].g + grid[x][y].h;
            if (f > oldF && oldF != 0) continue; //기존의 거리보다 새로 산정된 거리가 큰경우 안넣음
            grid[x][y].g = g; //넣기전에 g 갱신함
            grid[x][y].h = h; //넣기전에 h 갱신함
            grid[x][y].parent = center; //넣기전 부모 갱신함
            if (open.indexOf(grid[x][y]) >= 0) continue; //이미 들어있다면 또 넣지 않음
            open.add(grid[x][y]); //조건에 다 통과하면 넣음
         }
      }
   }

   // g거리를 구한다. g가뭔지 모르겠으면 구글검색 ㄱㄱ
   private int getG(Element parent, Element child) {
      int g = parent.g;
      g += (parent.x - child.x == 0 || parent.y - child.y == 0) ? 10 : 14;
      return g;
   }

   // h거리를 구한다. h가뭔지 모르겠으면 구글검색 ㄱㄱ
   private int getH(Element child) {
      return (Math.abs(end.x - child.x) + Math.abs(end.y - child.y)) * 10;
   }
}