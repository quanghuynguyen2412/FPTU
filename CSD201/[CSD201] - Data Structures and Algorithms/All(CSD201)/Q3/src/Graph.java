/* This program contains 2 parts: (1) and (2)
   YOUR TASK IS TO COMPLETE THE PART  (2)  ONLY
 */
//(1)============================================
import java.io.*;
import java.util.*;
//-------------------------------------------------------------------------------

public class Graph {

    int[][] a;
    int n;
    char v[];
    int deg[];

    Graph() {
        v = "ABCDEFGHIJKLMNOP".toCharArray();
        deg = new int[20];
        a = new int[20][20];
        n = 0;
    }

    void loadData(int k) //do not edit this function
    {
        RandomAccessFile f;
        int i, j, x;
        String s;
        StringTokenizer t;
        a = new int[20][20];
        try {
            f = new RandomAccessFile("data.txt", "r");
            for (i = 0; i < k; i++) {
                f.readLine();
            }
            s = f.readLine();
            s = s.trim();
            n = Integer.parseInt(s);
            for (i = 0; i < n; i++) {
                s = f.readLine();
                s = s.trim();
                t = new StringTokenizer(s);
                for (j = 0; j < n; j++) {
                    x = Integer.parseInt(t.nextToken().trim());
                    a[i][j] = x;
                }
            }
            f.close();
        } catch (Exception e) {
        }

    }

    void dispAdj() {
        int i, j;
        for (i = 0; i < n; i++) {
            System.out.println();
            for (j = 0; j < n; j++) {
                System.out.printf("%4d", a[i][j]);
            }
        }
    }

    void fvisit(int i, RandomAccessFile f) throws Exception {
        f.writeBytes(" " + v[i]);
    }

    void fdispAdj(RandomAccessFile f) throws Exception {
        int i, j;
        f.writeBytes("n = " + n + "\r\n");
        for (i = 0; i < n; i++) {
            f.writeBytes("\r\n");
            for (j = 0; j < n; j++) {
                f.writeBytes("  " + a[i][j]);
            }
        }
        f.writeBytes("\r\n");
    }

    void breadth(boolean[] en, int i, RandomAccessFile f) throws Exception {
        Queue q = new Queue();
        int r, j;
        q.enqueue(i);
        en[i] = true;
        while (!q.isEmpty()) {
            r = q.dequeue();
            fvisit(r, f);
            for (j = 0; j < n; j++) {
                if (!en[j] && a[r][j] > 0) {
                    q.enqueue(j);
                    en[j] = true;
                }
            }
        }
    }

    void breadth(int k, RandomAccessFile f) throws Exception {
        boolean[] en = new boolean[20];
        int i;
        for (i = 0; i < n; i++) {
            en[i] = false;
        }
        breadth(en, k, f);
        for (i = 0; i < n; i++) {
            if (!en[i]) {
                breadth(en, i, f);
            }
        }
    }

    void depth(boolean[] visited, int k, RandomAccessFile f) throws Exception {
        fvisit(k, f);
        visited[k] = true;
        for (int i = 0; i < n; i++) {
            if (!visited[i] && a[k][i] > 0) {
                depth(visited, i, f);
            }
        }
    }

    void depth(int k, RandomAccessFile f) throws Exception {
        boolean[] visited = new boolean[20];
        int i;
        for (i = 0; i < n; i++) {
            visited[i] = false;
        }
        depth(visited, k, f);
        for (i = 0; i < n; i++) {
            if (!visited[i]) {
                depth(visited, i, f);
            }
        }
    }

//===========================================================================
//(2)===YOU CAN EDIT OR EVEN ADD NEW FUNCTIONS IN THE FOLLOWING PART========
//===========================================================================
    void calDegrees() {
        int i, j, t;
        for (i = 0; i < n; i++) {
            t = 0;
            for (j = 0; j < n; j++) {
                t += a[i][j];
            }
            if (a[i][i] > 0) {
                t++;
            }
            deg[i] = t;
        }
    }

    void fdepthDeg(int k, RandomAccessFile f) throws Exception {
        calDegrees();
        boolean[] visited = new boolean[20];
        int i;
        for (i = 0; i < n; i++) {
            visited[i] = false;
        }
        fdepthDeg(visited, k, f);
        for (i = 0; i < n; i++) {
            if (!visited[i]) {
                fdepthDeg(visited, i, f);
            }
        }

    }

    void fdepthDeg(boolean[] visited, int k, RandomAccessFile f) throws Exception {
        fvisit(k, f);
        visited[k] = true;
        for (int i = 0; i < n; i++) {
            if (!visited[i] && a[k][i] > 1) {
                fdepthDeg(visited, i, f);
            }
        }
    }

    void f1() throws Exception {
        loadData(1);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        depth(0, f);
        f.writeBytes("\r\n");
        //-------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
        fdepthDeg(0, f);
        //-------------------------------------------------------------------------------------
        f.writeBytes("\r\n");
        f.close();
    }

//=================================================================
    boolean isUndirected() {
        int i, j;
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                if (a[i][j] != a[j][i]) {
                    return (false);
                }
            }
        }
        return (true);
    }

    boolean isEvenDegree() {
        int i, j, deg;
        for (i = 0; i < n; i++) {
            deg = 0;
            for (j = 0; j < n; j++) {
                deg += a[i][j];
            }
            if (deg % 2 == 1) {
                return (false);
            }
        }
        return (true);
    }

    boolean isConnected() {
        boolean[] pushed = new boolean[20];
        boolean cont = false;
        int i, j, k, r;
        for (i = 0; i < n; i++) {
            pushed[i] = false;
        }
        Stack s = new Stack();
        s.push(0);
        pushed[0] = true;
        while (!s.isEmpty()) {
            r = s.pop();
            for (i = 0; i < n; i++) {
                if (i == r) {
                    continue;
                }
                if (!pushed[i] && a[r][i] > 0) {
                    s.push(i);
                    pushed[i] = true;
                }
            }
        }
        for (i = 0; i < n; i++) {
            if (!pushed[i]) {
                return (false);
            }
        }
        return (true);
    }

    void checkEulerCycle(RandomAccessFile f) throws Exception {
        if (isUndirected()) {
            f.writeBytes("The graph is undirected.\r\n");
        } else {
            f.writeBytes("The graph is directed.\r\n");
        }
        if (isConnected()) {
            f.writeBytes("The graph is connected.\r\n");
        } else {
            f.writeBytes("The graph is not connected.\r\n");
        }

        if (isEvenDegree()) {
            f.writeBytes("All vertices have even degree.\r\n");
        } else {
            f.writeBytes("The graph has a vertex with odd degree\r\n");
        }
        if (isUndirected() && isConnected() && isEvenDegree()) {
            f.writeBytes("Conditions for Euler's cycle are satisfied.\r\n");
        } else {
            f.writeBytes("Conditions for Euler's cycle are not satisfied.\r\n");
        }

    }

    boolean hasEulerCycle() {
        boolean ok = true;
        if (!isUndirected()) {
            System.out.println("The graph is directed.\r\n");
            ok = false;
        }
        if (!isConnected()) {
            System.out.println("The graph is not connected.\r\n");
            ok = false;
        }

        if (!isEvenDegree()) {
            System.out.println("The graph has a vertex with odd degree\r\n");
            ok = false;
        }
        if (!ok) {
            System.out.println("Conditions for Euler's cycle are not satisfied.\r\n");
            return (false);
        }
        return (true);
    }

    void EulerCycle(int k, RandomAccessFile f) throws Exception {
        if (!hasEulerCycle()) {
            return;
        }
        Stack s = new Stack();
        int[][] b = new int[20][20];
        int[] eu = new int[20];
        int m;
        int i, j, r, t;
        int[] x = new int[50];
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                b[i][j] = a[i][j];
            }
        }
        s.push(k);
        m = 0;
        t = 0;
        x[0] = k;
        while (!s.isEmpty()) {
            r = s.top();
            i = 0;
            while (i < n && b[r][i] == 0) {
                i++;
            }
            if (i == n) {
                eu[m++] = r;
                s.pop();
            } else {
                x[++t] = i;
                s.push(i);
                b[r][i]--;
                b[i][r]--;
            }
        }
        for (i = 0; i < m; i++) {
            f.writeBytes(v[eu[i]] + "  ");
        }
        f.writeBytes("\r\n");
    }

    /*
Algorithm for finding an Euler cycle from the vertex X using stack 
//Input: Connected graph G with all vertices having even degrees
//Output: Euler cycle
declare a stack S of characters
declare empty array E (which will contain Euler cycle)
push the vertex X to S
while(S is not empty)
 {r = top element of the stack S 
  if r is isolated then remove it from the stack and put it to E
   else
   select the first vertex Y (by alphabet order), which is adjacent
   to r, push  Y  to  S and remove the edge (r,Y) from the graph   
 }
 the last array E obtained is an Euler cycle of the graph
     */
    void f2() throws Exception {
        loadData(12);
        String fname = "f2.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        f.writeBytes("\r\n");
        //-------------------------------------------------------------------------------------
        /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
        // You can use the statement fvisit(i,f); i = 0, 1, 2,...,n-1 to display the vertex i to file f6.txt 
        //  and statement f.writeBytes(" " + k); to write  variable k to the file f6.txt  
        EulerCycle(2, f);
        //-------------------------------------------------------------------------------------
        f.writeBytes("\r\n");
        f.close();
    }

}
//=================================================================