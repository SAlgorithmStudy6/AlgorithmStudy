import java.util.*;

class Solution {
    static String s[];
    
    int is_right(int point)
    {
       
        Stack<String> stack = new Stack<>();
        String prev;
        
        //스택에 괄호 전부 순서대로 넣기
        for(int i=0;i<s.length;i++)
        {
           point = ( point + s.length ) % s.length;  
            //System.out.println("s[point] = "+ s[point]);
            
            if(s[point].equals("[") || s[point].equals("{") || s[point].equals("("))
            {
               // System.out.println("stack push : "+ s[point]);
                stack.push(s[point]);
            }
            else if(s[point].equals("]") || s[point].equals(")") || s[point].equals("}"))
            {
                if(stack.empty())
                    return 0;
                prev = stack.pop();
                
                //System.out.println("prev = "+prev);
                if(( s[point].equals(")")  && !prev.equals("(") ||
                   (s[point].equals("}")  && !prev.equals("{")) ||
                   (s[point].equals("]")  && !prev.equals("["))))
                    return 0;
            }
            point++;
        }
        if(stack.empty())
            return 1;
        else 
            return 0;
            
    }
        
    public int solution(String st) {
        s = st.split("");
        int count = 0;
        
       
        if (s.length > 1)
        {
            for(int point = 0; point < s.length; point++)
            {
            //System.out.println("point = "+point);
            
            if(is_right(point) == 1)
                count++;
            //System.out.println("==>>  \ncnt = " + count);
            //System.out.println();
            }
        }
        
        return (count);
      
    }
}
