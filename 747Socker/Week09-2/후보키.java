import java.util.*;
class Solution {
 
boolean[] chk; 
int cnt;
Set<String> set = new HashSet<>();
List<String> list= new ArrayList<>();
    public int solution(String[][] relation) {
        int answer = 0;
        cnt = relation.length;
        chk = new boolean[relation[0].length];
       
        
        for(int i=1;i<=relation[0].length;i++){
            dfs(0,i,relation);           
            findUni(relation);
            set.clear();   
        }     
        return list.size();
    }
 
    public void dfs (int start,int r,String[][] relation){     
        if(r==0){
            String temp="";
            for(int i=0;i<relation[0].length;i++){
                if(chk[i]){
                    temp+=i;
                }
            }
            set.add(temp);
        }      
        for(int i=start;i<relation[0].length;i++){
            if(!chk[i]){
                chk[i]=true;
                dfs(start+1,r-1,relation);
                chk[i]=false;
            }
        }
    }
    
    public void findUni(String[][] relation){
        for(String s:set){
            String[] temp= s.split("");
            int[] arr=new int[temp.length];
            for(int i=0;i<temp.length;i++){
                int c =Integer.parseInt(temp[i]);
                arr[i]=c;
             }
            Set<String> set = new HashSet<>();
            for(int i=0;i<cnt;i++){
                String cdd="";
              for(String ss:temp){
                int c =Integer.parseInt(ss);
                cdd+=relation[i][c];
              }
                set.add(cdd);
            }
            if(set.size()==cnt){
                boolean flag=false;
                for(String sss:list){
                    int cnt=0;
                    String[] temp3= sss.split("");         
                    for(String ssss:temp3){
                        if(s.contains(ssss)){
                            cnt++;
                        }
                    }
                    if(cnt==sss.length()){
                        flag=true;
                    }
                }
                if(!flag){
                   list.add(s);                             
                }
            }
        }
    }
}