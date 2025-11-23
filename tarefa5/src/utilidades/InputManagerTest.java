package utilidades;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class InputManagerTest {
    private final InputStream originalSystemIn = System.in;
    private final PrintStream originalSystemOut = System.out;
    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    public void setUp() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    public void restoreStreams() {
        System.setIn(originalSystemIn);
        System.setOut(originalSystemOut);
        InputManager.fecharScanner(); // Fecha scanner após cada teste
    }

    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    public void testLerString_ValidInput() {
        provideInput("Teste Entrada\n");
        String result = InputManager.lerString("Digite algo: ");
        System.out.println("Teste: Ler string válida - Resultado: '" + result + "'");
    }

    public void testLerString_EmptyInput() {
        provideInput("\n");
        String result = InputManager.lerString("Digite algo: ");
        System.out.println("Teste: Ler string vazia - Resultado: '" + result + "'");
    }

    public void testLerSimNao_Sim() {
        provideInput("s\n");
        boolean result = InputManager.lerSimNao("Deseja continuar?");
        System.out.println("Teste: Ler sim/não (sim) - Resultado: " + result);
    }

    public void testLerSimNao_Nao() {
        provideInput("n\n");
        boolean result = InputManager.lerSimNao("Deseja continuar?");
        System.out.println("Teste: Ler sim/não (não) - Resultado: " + result);
    }

    public void testLerSimNao_InvalidInputThenValid() {
        provideInput("x\ns\n");
        boolean result = InputManager.lerSimNao("Deseja continuar?");
        System.out.println("Teste: Ler sim/não (inválido depois válido) - Resultado: " + result);
    }

    public static void main(String[] args) {
        InputManagerTest test = new InputManagerTest();
        test.setUp();
        test.testLerString_ValidInput();
        test.testLerString_EmptyInput();
        test.testLerSimNao_Sim();
        test.testLerSimNao_Nao();
        test.testLerSimNao_InvalidInputThenValid();
        test.restoreStreams();
    }
}