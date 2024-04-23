/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.balancedparentheses;

/**
 *
 * @author pabloispache
 */
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class BalancedParentheses {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option = 0;

        while (true) {
            System.out.println("\nMenú:");
            System.out.println("1. Ingresar expresión para verificar");
            System.out.println("2. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
                continue;
            }

            switch (option) {
                case 1:
                    System.out.print("Ingrese la expresión: ");
                    String expression = scanner.nextLine();
                    boolean result = areParenthesesBalanced(expression);
                    System.out.println("La expresión es válida: " + result);
                    break;
                case 2:
                    System.out.println("Programa finalizado.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        }
    }

    private static boolean areParenthesesBalanced(String expression) {
        StackLinkedList stack = new StackLinkedList();
        HashMap<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');

        Set<Character> validChars = Set.of('(', ')', '{', '}', '[', ']', '+', '-', '*', '/', ' ');

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            if (Character.isDigit(ch) || validChars.contains(ch)) {
                if (map.containsValue(ch)) {
                    stack.push(ch);
                } else if (map.containsKey(ch)) {
                    if (stack.isEmpty() || stack.pop() != map.get(ch)) {
                        return false;
                    }
                }
            } else {
                return false; // Si encuentra un carácter no válido, retorna false
            }
        }

        return stack.isEmpty();
    }
}

class StackLinkedList {
    private Node top;

    public StackLinkedList() {
        this.top = null;
    }

    public void push(char data) {
        Node node = new Node(data);
        if (top != null) {
            node.next = top;
        }
        top = node;
    }

    public char pop() {
        if (top == null) {
            throw new RuntimeException("Stack underflow");
        }
        char data = top.data;
        top = top.next;
        return data;
    }

    public boolean isEmpty() {
        return top == null;
    }
}

class Node {
    char data;
    Node next;

    public Node(char data) {
        this.data = data;
        this.next = null;
    }
}