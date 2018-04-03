import java.util.Collections;

class Stack {
    int top;
    int stackSize;
    String[] S;
    Stack(int stackSize){
        top = -1;
        this.stackSize = stackSize;
        S = new String[stackSize];
    }

    boolean isEmpty(){
        if (top==-1) return true;
        else return false;
    }

    void push(String item){
        top++;
        S[top] = item;
    }

    String pop(){
        return S[top--];
    }

    String peek(){
        if (top>-1) return S[top];
        else return "";
    }

    void printStack(){
        for (int i=0; i<=top; i++) {
            System.out.println(S[i]);
        }
    }
}

class eqCalc{
    String eq, postEq;
    Stack S;

    eqCalc(String eq) {
        String[] eqArr = eq.split(" ");
        String temp;
        this.eq = eq;
        int n = eqArr.length;

        S = new Stack(n);
        postEq = "";

        for(int i=0; i<n; i++){
            if(eqArr[i].equals("(")==true) {
                S.push(eqArr[i]);
            }
            else if(eqArr[i].equals(")")==true) {
                while(true){
                    temp = S.pop();
                    if(temp.equals("(")==false) postEq += temp +" ";
                    else break;
                }
            }
            else if(eqArr[i].equals("+")==true || eqArr[i].equals("-")==true){
                while(true){
                    if(operYN(S.peek())==true) postEq += S.pop()+" ";
                    else break;
                }
                S.push(eqArr[i]);
            }
            else if(eqArr[i].equals("*")==true || eqArr[i].equals("/")==true){
                while(true){
                    if(S.peek().equals("*")==true || S.peek().equals("/")==true) postEq += S.pop()+" ";
                    else break;
                }
                S.push(eqArr[i]);
            }
            else {
                postEq += eqArr[i] + " ";
            }
        }
        while (S.isEmpty()==false) postEq += S.pop()+" ";
    }

    boolean operYN(String item) {
        if(item.equals("*")) return true;
        else if (item.equals("/")) return true;
        else if (item.equals("+")) return true;
        else if (item.equals("-")) return true;
        else return false;
    }

    String getPostEq() {
        return postEq;
    }

    String getPostCalc() {
        String[] eqArr=postEq.split(" ");
        int n = eqArr.length;
        S = new Stack(n);
        double a;
        double b;
        String result="";

        for(int i=0; i<n; i++) {
            if(operYN(eqArr[i])==false) S.push(eqArr[i]);
            else{
                a = (double)Double.parseDouble(S.pop());
                b = (double)Double.parseDouble(S.pop());

                if(eqArr[i].equals("*")) S.push(String.valueOf(a*b));
                else if(eqArr[i].equals("/")) S.push(String.valueOf(a/b));
                else if(eqArr[i].equals("+")) S.push(String.valueOf(a+b));
                else if(eqArr[i].equals("-")) S.push(String.valueOf(a-b));
            }
        }
        while(S.isEmpty()==false) result += S.pop();
        return result;
    }
}

public class Postfix {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        String A = "( 3 + 2 ) / 5";
        eqCalc calc1 = new eqCalc(A);
        System.out.println(calc1.getPostEq());
        System.out.println(calc1.getPostCalc());

    }
}
// ( 12.3 + 6 ) * 3 / 6
// “12.3 6 + 3 * 6 /”