import java.util.*;


class Solution {
    static ArrayList<appl> apArr;
    static int[][] idxArr;
    public int[] solution(String[] info, String[] query) {
        
        apArr = new ArrayList<>();
        
        
       appl now;
        for(String str:info){
            String[]tmp = str.split(" ");
            now = new appl(toInt(tmp[0]), toInt(tmp[1]), toInt(tmp[2]), toInt(tmp[3]), toInt(tmp[4]));
            apArr.add(now);  
            
        }
        
        //시작점이 0 마지막 점이 1
        idxArr = new int[24][2];
        for(int i=0;i<24;i++){
           Arrays.fill(idxArr[i], -1);
        }
        
        Collections.sort(apArr);
        appl tmp  = apArr.get(0);
        
        //System.out.println(apArr.toString());
        
        int start = 0;
        int end = 0;
        now = apArr.get(0);
        int idx = now.lan*8 + now.job*4 + now.career*2 + now.food;
        idxArr[idx][0] = 0;
        int nxidx = 0;
        for(int i=1;i<apArr.size();i++){
            now = apArr.get(i);
            
            nxidx = now.lan*8 + now.job*4 + now.career*2 + now.food;
            
            if(nxidx != idx){
                idxArr[idx][1] = i-1;
                idxArr[nxidx][0] = i;
                idx = nxidx;
                continue;
            }
        }
        
        idxArr[nxidx][1] = apArr.size()-1;
        // for(int i=0; i<24;i++){
        //     System.out.println(Arrays.toString(idxArr[i]));
        // }
        
        int[] answer = new int[query.length];
        //모든 쿼리를 돌면서
        int[] quArr = new int[5];
        for(int i=0;i<query.length;i++ ){
            String[] tmpQ = query[i].split(" ");
            int j = 0;
            int all = 0;
            int zero = 0;
            for(String s : tmpQ)   {
                if(s.equals("and"))continue;
                quArr[j] = toInt(s);
                if(j==0 && quArr[j]==3){
                    zero = 1;
                }
                else if(quArr[j] == 3){
                    all++;
                }
                j++;
            }
            //System.out.println("----"+i); 
            allsum = 0;
            //System.out.println(Arrays.toString(quArr));
            mkArr(quArr, 0);
            answer[i] = allsum;
           
        }
                
        
        return answer;
    }
    
    static int allsum;
    public void mkArr(int [] arr, int idx){
    
        if(idx == 4){
            //System.out.println(Arrays.toString(arr));
            allsum += cnt(arr);
            return;
        }
        if(idx == 0 && arr[idx] == 3){
            for(int i=0;i<3;i++){
                arr[0] = i;
                mkArr(arr, idx+1);
            }
            arr[0] = 3;
            return;
        }else if(arr[idx] == 3){
            
            for(int i=0;i<2;i++){
                arr[idx] = i;
                mkArr(arr, idx+1);
            }
            arr[idx] = 3;
            return;
        }else{
            mkArr(arr, idx+1);
        }
        
        
    }
    
    public int cnt(int[] arr){
       
        int sum = 0;
        int score = arr[4];
        int idx = arr[0] * 8 + arr[1]*4 + arr[2] * 2 + arr[3] * 1;
        
        int start = idxArr[idx][0];
        int end = idxArr[idx][1];
        if(start == -1 || end == -1){
            sum = 0;
            return sum;
        }
        
        
        //System.out.println("start "+start+" end "+end);
        //System.out.println("score "+score);
//         for(int i=start; i<=end ;i++){
            
//             //System.out.println(" i :"+i+" score "+apArr.get(i).score );
//             if(apArr.get(i).score >= score){
//                 sum = end - i+1;
                
//                 break;
//             }
//         }
        //System.out.println(score);
        sum = search(start, end, score);
        
        return sum;
    }
    

    
    public int search(int s, int e, int score){
        
        boolean chk = false;
        
        int end = e;
        int midIdx = end+1;
        int midVal = 0;
        while(s <= e){
            midIdx = (e+s)/2;
            midVal = apArr.get(midIdx).score;
                        
            if(midVal >= score){
                e = midIdx-1;
            }
            else if(midVal < score){
                s = midIdx + 1;
            }

        }
        
        return end - s+1;
    }
    
    public int toInt(String str){
        switch(str){
            case "cpp":
            case "backend":
            case "junior":
            case "chicken":
                return 0;
            case "java":
            case "frontend":
            case "senior":
            case "pizza":
                return 1;
            case "python":
                return 2;
            case "-":
                return 3;
            default:
                return Integer.parseInt(str);
        }
    }
    
    
    
    
    class appl implements Comparable<appl>{
     int lan;
    int job;
        int career;
        int food;
        int score;
        
        appl(int lan, int job, int career, int food, int score){
            this.lan = lan;
            this.job = job;
            this.career = career;
            this.food = food;
            this.score = score;
        }
        
        public int compareTo(appl o){
            if(this.lan > o.lan){
                return 1;                
            }else if(this.lan < o.lan){
                return -1;
            }
            
          if(this.job > o.job){
                return 1;                
            }else if(this.job < o.job){
                return -1;
            }
        
            if(this.career > o.career){
                return 1;                
            }else if(this.career < o.career){
                return -1;
            }
            
            if(this.food > o.food){
                return 1;                
            }else if(this.food < o.food){
                return -1;
            }
            
            if(this.score > o.score){
                return 1;                
            }else if(this.score < o.score){
                return -1;
            }
            
            return 0;
        }
        
        public String toString(){
            
            return this.lan +" "+this.job+" "+this.career+" "+this.food+" "+this.score;
        }
    }
}

