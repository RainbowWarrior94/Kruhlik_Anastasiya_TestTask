import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

//Interfejs Structure
interface Structure {
    //Deklaracja metod findBlockByColor, findBlocksByMaterial, count

    // zwraca dowolny element o podanym kolorze
    Optional<Block> findBlockByColor(String color);

    // zwraca wszystkie elementy z danego materiału
    List<Block> findBlocksByMaterial(String material);

    //zwraca liczbę wszystkich elementów tworzących strukturę
    int count();
}

//Klasa Wall która implementuje interfejs Structure
public class Wall implements Structure {
    private List<Block> blocks;         //Deklaracja zmiennej "blocks"

    //Konstruktor clasy "Wall"
    public Wall(List<Block> blocks) {
        this.blocks = blocks;           //Przypisanie do pola "blocks" wartości "blocks" przekazanej do konstruktora
    }

    //Implementacja metody "findBlockByColor"
    @Override
    public Optional<Block> findBlockByColor(String color) {
        Stack<Block> stack = new Stack<>();         //Tworzenie stosu, który będzie przechowywać obiekty typu "Block"
        stack.addAll(blocks);           //Dodawanie wszystkich elementów listy "blocks" do stosu

        //Pętla, która będzie działać, dopóki stos nie będzie pusty
        while(!stack.isEmpty()) {

            Block block = stack.pop();          //Tworzenie zmiennej "block" typu "Block" i przypisywanie jej wartości elementu, który został pobrany ze stosu

            /*Warunek, który porównuje kolor "block"  z poszukiwanym kolorem.
            Po spełnieniu warunku tworzony i zwracany jest obiekt klasy Optional,
            który zawiera "block" z poszukiwanym kolorem.*/
            if(block.getColor().equals(color)) {
                return Optional.of(block);
            }

            /*Warunek, który sprawdza, czy "blok" jest instancją klasy "CompositeBlock".
            Po spełnieniu warunku wszystkie elementy znajdujące się w "compositeBlock" zostaną dodane do stosu. */
            if (block instanceof CompositeBlock compositeBlock) {
                stack.addAll(compositeBlock.getBlocks());
            }
        }

        return Optional.empty();            //Zwracanie pustego obiektu klasy Optional
    }

    //Implementacja metody "findBlocksByMaterial"
    @Override
    public List<Block> findBlocksByMaterial(String material) {
        List<Block> foundBlocks = new ArrayList<>();            //Tworzenie listy "foundBlocks", która będzie zawierać elementy typu "Block"
        Stack<Block> stack = new Stack<>();         //Tworzenie stosu, który będzie przechowywać obiekty typu "Block"
        stack.addAll(blocks);           //Dodawanie wszystkich elementów listy "blocks" do stosu

        //Pętla, która będzie działać, dopóki stos nie będzie pusty
        while(!stack.isEmpty()) {
            Block block = stack.pop();          //Tworzenie zmiennej "block" typu "Block" i przypisywanie jej wartości elementu, który został pobrany ze stosu


            /*Warunek, który porównuje kolor "block"  z poszukiwanym materiałem. Po spełnieniu
            warunku "block" z poszukiwanym materiałem jest dodawany do listy "foundBlocks".*/
            if(block.getMaterial().equals(material)) {
                foundBlocks.add(block);
            }

            /*Warunek, który sprawdza, czy "blok" jest instancją klasy "CompositeBlock".
            Po spełnieniu warunku wszystkie elementy znajdujące się w "compositeBlock" zostaną dodane do stosu. */
            if (block instanceof CompositeBlock compositeBlock) {
                stack.addAll(compositeBlock.getBlocks());
            }
        }

        return foundBlocks;         //Zwracanie listy "foundBlocks"
    }

    //Implementacja metody "count"
    @Override
    public int count() {

        int allElementsNumber = 0;          //Deklaracja i inicjalizacja zmiennej "allElementsNumber"

        Stack<Block> stack = new Stack<>();         //Tworzenie stosu, który będzie przechowywać obiekty typu "Block"
        stack.addAll(blocks);           //Dodawanie wszystkich elementów listy "blocks" do stosu

        //Pętla, która będzie działać, dopóki stos nie będzie pusty
        while(!stack.isEmpty()) {

            Block block = stack.pop();          //Tworzenie zmiennej "block" typu "Block" i przypisywanie jej wartości elementu, który został pobrany ze stosu

            /*Warunek, który sprawdza, czy "blok" jest instancją klasy "CompositeBlock".
            Po spełnieniu warunku wszystkie elementy znajdujące się w "compositeBlock" zostaną dodane do stosu. */
            if (block instanceof CompositeBlock compositeBlock) {
                stack.addAll(compositeBlock.getBlocks());
            } else {            //Jeśli warunek nie został spełniony ("block" nie jest instancją klasy "CompositeBlock"), liczba elementów jest inkrementowana
                allElementsNumber++;
            }
        }

        return allElementsNumber;           //Zwracanie policzonej liczby elementów
    }
}

//Interfejs Block
interface Block {
    String getColor();          //Deklaracja metody getColor()
    String getMaterial();           //Deklaracja metody getMaterial()
}

//Interfejs CompositeBlock który dziedziczy interfejs Block.
interface CompositeBlock extends Block {
    List<Block> getBlocks();            //Deklaracja metody getBlocks();
}


