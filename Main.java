import java.util.Scanner;

public class Main{

    public static void main(String args[]){
        
        InputReader inputReader = new ScannerInputReader();
        
        OutputDisplayer outputDisplayer = new ConsoleOutputDisplayer();
        CapitalizeLettersOutputPort presenter = new ConsolePrinterPresenter(outputDisplayer);
        CapitalizeLettersInputPort capitalizeLettersUseCase = new CapitalizeLettersUseCase(presenter);

        Controller controller =  new Controller(
          capitalizeLettersUseCase,
          inputReader
        );

        controller.capitalizeLetters();
    }
}


// Input
interface InputReader{
    String getInput();
}

class ScannerInputReader implements InputReader{

    private Scanner scanner;

    public ScannerInputReader(){
        scanner = new Scanner(System.in);
    }

    @Override
    public String getInput(){
        return scanner.next();
    }
}

// Output
interface OutputDisplayer{
    void display(String output);
}

class ConsoleOutputDisplayer implements OutputDisplayer{

    @Override
    public void display(String output){
        System.out.println(output);
    }
}

// Controller
class Controller{

    private CapitalizeLettersInputPort useCaseInputPort;
    private InputReader inputReader;

    public Controller(CapitalizeLettersInputPort useCaseInputPort, InputReader inputReader){
        this.useCaseInputPort = useCaseInputPort;
        this.inputReader = inputReader;
    }

    public void capitalizeLetters(){
        String word = inputReader.getInput();
        useCaseInputPort.capitalizeLetters(word);
    }
}

// Ports
interface CapitalizeLettersInputPort{
    void capitalizeLetters(String input);
}

interface CapitalizeLettersOutputPort{
    void display(String output);
}

class CapitalizeLettersUseCase implements CapitalizeLettersInputPort{

    private CapitalizeLettersOutputPort useCaseOutputPort;

    public CapitalizeLettersUseCase(CapitalizeLettersOutputPort useCaseOutputPort){
        this.useCaseOutputPort = useCaseOutputPort;
    }

    @Override
    public void capitalizeLetters(String input){
        String output = input.toUpperCase();
        useCaseOutputPort.display(output);
    }
}

class ConsolePrinterPresenter implements CapitalizeLettersOutputPort{

    private OutputDisplayer displayer;

    public ConsolePrinterPresenter(OutputDisplayer displayer){
        this.displayer = displayer;
    }

    @Override
    public void display(String output){
        displayer.display(output);
    }
}
