package com.rpg.utilidades;

import java.util.Scanner;

public class InputManager {
    private static Scanner scanner = new Scanner(System.in);

    // Ler inteiro
    public static int lerInteiro(String mensagem, int min, int max) {
        while (true) {
            try {
                System.out.print(mensagem);
                int valor = Integer.parseInt(scanner.nextLine());

                if (valor < min || valor > max) {
                    throw new IllegalArgumentException(
                            "Valor deve estar entre " + min + " e " + max);
                }
                return valor;

            } catch (NumberFormatException e) {
                System.out.println("Erro: Digite um número inteiro válido!");
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    // 2. Ler string
    public static String lerString(String mensagem) {
        try {
            System.out.print(mensagem);
            return scanner.nextLine().trim();
        } catch (Exception e) {
            System.out.println("Erro ao ler texto: " + e.getMessage());
            return "";
        }
    }

    // 3. Ler Sim ou Não
    public static boolean lerSimNao(String mensagem) {
        while (true) {
            try {
                // garantir a indicação (s/n) na mensagem
                if (!mensagem.endsWith("(s/n)")) {
                    System.out.print(mensagem + " (s/n) ");
                } else {
                    System.out.print(mensagem);
                }

                // ler e transformar a resposta em minusculo
                String resposta = scanner.nextLine().trim().toLowerCase();

                if (resposta.equals("s")) {
                    return true;
                } else if (resposta.equals("n")) {
                    return false;
                } else {
                    throw new IllegalArgumentException("Digite 's' para SIM ou 'n' para NÃO");
                }

            } catch (IllegalArgumentException e) {
                System.out.println("Erro ao ler s/n: " + e.getMessage());
            }
        }
    }

    // 4. Esperar Enter
    public static void esperarEnter(String mensagem) {
        try {
            System.out.print(mensagem);
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Erro ao aguardar entrada: " + e.getMessage());
        }
    }

    // 5. Fechar scanner
    public static void fecharScanner() {
        try {
            if (scanner != null) {
                scanner.close();
            }
        } catch (Exception e) {
            System.out.println("Erro ao fechar scanner: " + e.getMessage());
        }
    }
}