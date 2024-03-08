import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static int num;
	static int moveDistance; // 움직인 거리
	static int[][] grid;
	static int time;
	static int endX, endY;
	static int[] dxs = {-1, 1, 0, 0}; // 상,하,우,좌
	static int[] dys = {0, 0, 1, -1};
	static List<Person> list = new ArrayList<>();
	static class Person{
		int x, y, d;
		
		Person(int x, int y, int d){
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}
	
	static void setDistance() {
		for(Person p: list) {
			p.d = Math.abs(p.x-endX)+Math.abs(p.y-endY); // 사람과 목적지까지의 거리
		}
	}
	
	static void move() { // 움직여 
		for(int s=0; s<list.size(); s++) {
			Person p = list.get(s);
			for(int i=0; i<4; i++) {
				int nx = p.x + dxs[i]; 
				int ny = p.y + dys[i]; 
				
				if(inRange(nx, ny) && grid[nx][ny] <=0) { // 범위 안에 있고, 갈 수 있는 곳이면
					// 거리가 가까워야함.
					int tempDistance = Math.abs(nx-endX)+Math.abs(ny-endY);
					if(p.d>tempDistance) {
						grid[p.x][p.y] = 0; // 기존에 사람 있다고 표시한 데 0으로 변경
						p.x = nx; 
						p.y = ny;
						moveDistance++; // 움직인 거리 1증가
						if(p.x == endX && p.y == endY) {
							p.d = -10;
							break;
						}
						grid[nx][ny] = -1;
						break;
					}
				}
			}
		}
		for(Iterator<Person> iterator = list.iterator() ; iterator.hasNext();) {
			Person current = iterator.next();
			if (current.d == -10) {
				iterator.remove();
			}
		}
	}
	
	static void rotate() {
		// 회전을 시키는데 0,0부터 돌거야. 2~사이즈가 N만큼 !
		int size = 2;
		int rx = 0; 
		int ry = 0;
		boolean personFlag = false;
		boolean exitFlag = false;
		loop: while(size <= N) {
			for(int i=0; i<N-size+1; i++) {
				for(int j=0; j<N-size+1; j++) {
					for(int tx = i; tx<i+size; tx++) {
						for(int ty = j; ty<j+size; ty++) {
							if(grid[tx][ty] == -1) personFlag = true;
							if(grid[tx][ty] == -2) exitFlag = true;
							if(personFlag && exitFlag) {
								rx = i;
								ry = j;
								break loop;
							}
						}
					}
					personFlag = false;
					exitFlag = false;
				}
			}
			size++;
		}
		
		int[][] rotateGrid = new int[size][size]; // 그 범위만큼의 . 
		int[][] afterRotateGrid = new int[size][size]; // 그 범위만큼의 . 
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				rotateGrid[i][j] = grid[rx+i][ry+j];
			}
		}
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				afterRotateGrid[i][j] = rotateGrid[size - j-1][i];
			}
		}
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				int setData = 0;
				if(afterRotateGrid[i][j] == 0) setData = 0;
				else if(afterRotateGrid[i][j] > 0) setData = afterRotateGrid[i][j]-1;// 내구도 1씩 줄여주는건데....
				else if(afterRotateGrid[i][j] == -1|| afterRotateGrid[i][j]== -2) setData = afterRotateGrid[i][j];
				grid[rx+i][ry+j] = setData; 
			}
		}
		list.clear();
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(grid[i][j] == -2) {
					endX = i;
					endY = j;
				} else if(grid[i][j] == -1) {
					list.add(new Person(i, j, 0));
				}
			}
		}
	}
	
	static boolean inRange(int x, int y) { // 그리드의 범위 안에 있는지.
		return 0<=x && x<N && 0<=y && y<N;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		num = M; // 사람수
		grid = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int px = Integer.parseInt(st.nextToken());
			int py = Integer.parseInt(st.nextToken());
			px -= 1;
			py -= 1;
			grid[px][py] = -1; // 사람
			list.add(new Person(px, py, 0)); // 목적지와 사람까지의 거리를 빼고 리스트 만들어줌.
		}
		st = new StringTokenizer(br.readLine());
		endX = Integer.parseInt(st.nextToken())-1;
		endY = Integer.parseInt(st.nextToken())-1;
		grid[endX][endY] = -2; // 목적지
		// 입력 끝
		
		while(K-->0) {
			setDistance(); // 각 참가자들마다 출구까지의 거리 생성
			move(); // 이동시킴 , 출구와 가까운 거리로 갈 수 있을 때만 이동.
			if(list.isEmpty()) break; // 참가자들 없으면 탈출,
			setDistance();
			rotate(); // 출구+사람 포함한 가장 작은 정사각형 회전, 내구도 -1
		}
		sb.append(moveDistance+"\n"+(endX+1)+" "+(endY+1));
		System.out.println(sb);
	}
}