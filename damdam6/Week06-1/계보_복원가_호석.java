package DATE1009;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class BAEK24276 {
    static int N, M;
    static List<Integer>[] graph;
    static int[] indegree;
    static String[] names;
    static Map<String, Integer> nameToIdx = new HashMap<>();
    static List<Integer>[] children;

    
    //자식 노드 중복을 없애야될 것 같은데 어떻게 해야할지 고민 중
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        names = new String[N];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            names[i] = st.nextToken();
        }

        Arrays.sort(names);
        for (int i = 0; i < N; i++) {
            nameToIdx.put(names[i], i);
        }

        M = Integer.parseInt(br.readLine());
        graph = new ArrayList[N];
        indegree = new int[N];
        children = new ArrayList[N];
        
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
            children[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int child  = nameToIdx.get(st.nextToken());
            int parent = nameToIdx.get(st.nextToken());
            graph[parent].add(child);
            indegree[child]++;
            children[parent].add(child);
        }

        // Topological sort
        Queue<Integer> q = new ArrayDeque<>();
        List<Integer> roots = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (indegree[i] == 0) {
                q.add(i);
                roots.add(i);
            }
        }

        List<Integer> result = new ArrayList<>();
        
        while (!q.isEmpty()) {
            int current = q.poll();
            result.add(current);
            for (int next : graph[current]) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    q.add(next);
                }
            }
        }

        // Print answer
        StringBuilder sb = new StringBuilder();
        sb.append(roots.size()).append("\n");
        for (int root : roots) {
            sb.append(names[root]).append(" ");
        }
        sb.append("\n");

        for (int i = 0; i < N; i++) {
            Collections.sort(children[i]);
            sb.append(names[i]).append(" ").append(children[i].size());
            for (int child : children[i]) {
                sb.append(" ").append(names[child]);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
