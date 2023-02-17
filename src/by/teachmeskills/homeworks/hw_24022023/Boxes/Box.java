package by.teachmeskills.homeworks.hw_24022023.Boxes;

public class Box {
    private Box[] boxes;
    private boolean isKeyInside;

    public Box(int boxesInside) {
        boxes = boxesInside == 0 ? null : new Box[boxesInside];
        this.isKeyInside = false;
    }

    public static void hideKey(Box box) {
        if (box.boxes != null) {
            box.isKeyInside = true;
            for (int i = 0; i < box.boxes.length; ++i) {
                box.boxes[i] = new Box(0);
            }
            int indexOfBoxWithKey = (int) (Math.random() * box.boxes.length);
            box.boxes[indexOfBoxWithKey] = new Box((int) (Math.random() * 10) + 1);
            box.boxes[indexOfBoxWithKey].isKeyInside = true;
            for (int i = 0; i < box.boxes[indexOfBoxWithKey].boxes.length; ++i)
                box.boxes[indexOfBoxWithKey].boxes[i] = new Box(0);
            int indexOfSmallBoxWithKey = (int) (Math.random() * box.boxes[indexOfBoxWithKey].boxes.length);
            box.boxes[indexOfBoxWithKey].boxes[indexOfSmallBoxWithKey] = new Box(0);
            box.boxes[indexOfBoxWithKey].boxes[indexOfSmallBoxWithKey].isKeyInside = true;
        }
    }

    public static Box findKeyIterative(Box box) {
        if (box.boxes != null && box.boxes[0] != null && box.isKeyInside) {
            for (Box smallBox : box.boxes) {
                if (smallBox.isKeyInside)
                    for (Box smallestBox : smallBox.boxes)
                        if (smallestBox.isKeyInside)
                            return smallestBox;
            }
        }
        return null;
    }

    public static Box findKeyRecursive(Box box) {
        Box result = null;
        if (box != null) {
            Box temp = null;
            if (box.isKeyInside && box.boxes == null)
                return box;
            if (box.boxes != null)
                for (int i = 0; i < box.boxes.length; ++i) {
                    temp = findKeyRecursive(box.boxes[i]);
                    if (temp != null) {
                        result = temp;
                        break;
                    }
                }
        }
        return result;
    }

    public static String compareResults(Box boxWithKey1, Box boxWithKey2) {
        if  (boxWithKey1 != null && boxWithKey2 == null)
            return "Первая функция нашла ключ, а вторая нет";
        else if  (boxWithKey1 == null && boxWithKey2 != null)
            return "Вторая функция нашла ключ, а первая нет";
        else if (boxWithKey1 == null)
            return "Ни одна из функций не нашла ключ";
        else if (boxWithKey1 == boxWithKey2)
            return "Обе функции нашли ключ";
        else {
            return "Функции нашли ключ в разных коробках";
        }
    }

    public static void main(String[] args) {
        Box box = new Box(6);
        Box emptyBox = new Box(0);

        System.out.println(compareResults(findKeyIterative(box), findKeyRecursive(box))); //Ключ не был спрятан в коробки, поэтому не будет найден
        hideKey(box); //Прячем ключ
        System.out.println(compareResults(findKeyIterative(box), findKeyRecursive(box))); //Обе функции найдут спрятанный ключ

        System.out.println(compareResults(findKeyIterative(emptyBox), findKeyRecursive(emptyBox))); //В пустой коробке ключ не будет найден
        hideKey(box);
        System.out.println(compareResults(findKeyIterative(emptyBox), findKeyRecursive(emptyBox))); //Спрятать ключ в пустой коробке некуда, поэтому функции не найдут его
    }
}
