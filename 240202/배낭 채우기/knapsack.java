import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n, k; // 물품의 수, 버틸 수 있는 무게
	static int result;
	static class Thing {
		int w, v;
		
		public Thing(int w, int v){
			this.w = w;
			this.v = v;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		Thing[] things = new Thing[n];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			things[i] = new Thing(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		for(int i=1; i<(1<<n); i++) {
			int tempW = 0;
			int tempV = 0;
			for(int j=0; j<n; j++) {
				if((i & 1<<j)!=0) { // 비트마스킹으로 부분집합 만들기
					if (things[j].w>k) break;
					tempW += things[j].w;
					tempV += things[j].v;
					if (tempW>k) break;
				}
			}
			if(tempW<=k) result = Math.max(result, tempV);
		}
		System.out.println(result);
	}
}