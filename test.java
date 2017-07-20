//Author:Saul Garza
//tester

import java.util.*;
public class test {
 public static void main(String args[]) {
  Scanner kb = new Scanner(System.in);
  System.out.println("Hello. Thank you for using my program. This program uses \'Stack\' to calculate an expression.");
  System.out.println("Press the corresponding number to execute\n1: Calculate an expression\n2: Exit program.");
  int command = kb.nextInt();
  String infix;
  while (command != 2) {
   switch (command) //checks to see what user wants
   {
    case 1:
     System.out.println("Please type the infix expression you want to calculate: "); //User chose to calculate expression
     infix = kb.next();
     String postfix1 = postfix_eval(infix);
     //System.out.println("The postfix expression looks like: "+postfix1);
     System.out.println("The result is: :" + calculation(postfix1));
     System.out.println("If you would like to compute another expression, please press 1. If you would like to exit please press 2.");
     command = kb.nextInt();
     break;
   }
  }
  if (command == 2) {
   System.out.println("Thank for trying out my Calculator Program! Hope you Enjoyed!!!");
   System.exit(0);
  }
 }
 public static String postfix_eval(Object infix_exp) //converting infix to postfix
  {
   Calculator stack = new Calculator();
   String postfix = "";
   String infix = infix_exp.toString();
   for (int i = 0; i < infix.length(); i++) {
    if (infix.charAt(i) >= '0' && infix.charAt(i) <= '9') {
     postfix = postfix + infix.charAt(i);
     if (i == infix.length() - 1)
      postfix = postfix + ".";
    } else if (infix.charAt(i) == '(') {
     stack.push(infix.charAt(i));
    } else if (infix.charAt(i) == ')') {
     postfix = postfix + ".";
     while ((char) stack.peek() != '(') {
      postfix = postfix + stack.pop();
     }
     stack.pop();
    } else {
     if ((postfix.charAt(postfix.length() - 1) >= '0') && (postfix.charAt(postfix.length() - 1) <= '9')) {
      postfix = postfix + ".";
     }
     while (!stack.IsEmpty() && (char) stack.peek() != '(' && precedence(infix.charAt(i)) >= precedence((char) stack.peek())) {
      postfix = postfix + stack.pop();
     }
     stack.push(infix.charAt(i));
    }
   }
   while (!stack.IsEmpty()) {
    postfix = postfix + stack.pop();

   }
   System.out.println(postfix);
   return postfix;
  }
 public static int precedence(char op) //finding which operator comes first
  {
   int flag = 0;
   if (op == '*' || op == '/') {
    flag = 1;
   } else if (op == '-' || op == '+') {
    flag = 2;
   }
   return flag;
  }
 public static int calculation(String postfix) //calculating postfix expression
  {
   Calculator stack = new Calculator();
   String number = "";
   int result = 0;
   for (int i = 0; i < postfix.length(); i++) {
    if (postfix.charAt(i) >= '0' && postfix.charAt(i) <= '9') {
     number = number + postfix.charAt(i);

    } else if (postfix.charAt(i) == '.') {
     stack.push(Integer.parseInt(number));
     number = "";
    } else {
     int operand2 = (int) stack.pop();
     int operand1 = (int) stack.pop();
     if (postfix.charAt(i) == '*') {
      result = operand1 * operand2;
      stack.push(result);
     } else if (postfix.charAt(i) == '/') {
      result = operand1 / operand2;
      stack.push(result);
     } else if (postfix.charAt(i) == '+') {
      result = operand1 + operand2;
      stack.push(result);
     } else if (postfix.charAt(i) == '-') {
      result = operand1 - operand2;
      stack.push(result);
     }
    }
   }
   return ((int) stack.pop());
  }
}
