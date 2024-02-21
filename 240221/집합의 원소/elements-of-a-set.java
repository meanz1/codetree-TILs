import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] uf;
	static int find(int x) {
		// x가 루트 노드라면 x값 반환
		if(uf[x]==x) return x;
		 
		// 루트 노드가 아니라면, x의 부모인 uf[x]에서 탐색을 더 진행한 뒤, 
		// 찾아낸 루트 노드를 uf[x]에 넣어주고, 해당 노드값을 반환
		return uf[x] = find(uf[x]);
	}
	
	// x, y가 같은 집합이 될 수 있도록 함
	static void union(int x, int y) {
		int X = find(x);
		int Y = find(y);
		uf[X] = Y;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		uf = new int[n+1];
		
		for(int i=1; i<=n; i++)
			uf[i] = i; // 자기 자신으로 집합 만들어줌(연결해줌)
		
		for(int t=0; t<m; t++) {
			st = new StringTokenizer(br.readLine());
			int order = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(order == 0) union(a, b);
			else {
				if(find(a)==find(b)) System.out.println(1);
				else System.out.println(0);
			}
		}
	}
}