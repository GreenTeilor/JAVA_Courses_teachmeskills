package by.teachmeskills.homeworks.hw_03032023.part3;

public abstract class Animal {
    protected String picture;
    protected String food;
    protected int hunger;

    protected Boundaries boundaries;

    protected Location location;

    public static class Boundaries {
        private int width;
        private int height;

        public Boundaries(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        @Override
        public String toString() {
            return "Boundaries{ width = " + width + ", height = " + height + "}";
        }
    }

    public static class Location {
        private int x;
        private int y;

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        @Override
        public String toString() {
            return "Location{ x = " + x + ", y = " + y + "}";
        }
    }

    public static class AnimalsArray {
        private Animal[] animals;

        public AnimalsArray(int size) {
            animals = new Animal[size];
        }

        public void add(Animal animal) {
            Animal[] tempArray = new Animal[animals.length + 1];
            System.arraycopy(animals, 0, tempArray, 0, animals.length);
            tempArray[tempArray.length - 1] = animal;
            animals = new Animal[animals.length + 1];
            System.arraycopy(tempArray, 0, animals, 0, animals.length);
        }

        public Animal find(String picture) {
            for (Animal animal : animals)
                if (animal.picture.contains(picture))
                    return animal;
            return null;
        }

        public void delete(String picture) {
            Animal[] result = new Animal[animals.length - 1];
            for (int i = 0; i < animals.length; ++i)
                if (animals[i].picture.contains(picture)) {
                    System.arraycopy(animals, 0, result, 0, i);
                    System.arraycopy(animals, i + 1, result, i, animals.length - i - 1);
                    animals = result;
                    break;
                }
        }

        static public void editAnimal(Animal animal, String newPicture, String newFood, int newHunger, Boundaries newBoundaries, Location newLocation) {
            animal.picture = newPicture;
            animal.food = newFood;
            animal.hunger = newHunger;
            animal.boundaries = newBoundaries;
            animal.location = newLocation;
        }

    }

    public Animal(String picture, String food, int hunger, Boundaries boundaries, Location location) {
        this.picture = picture;
        this.food = food;
        this.hunger = hunger;
        this.boundaries = boundaries;
        this.location = location;
    }

    protected abstract void makeNoise();
    protected abstract void eat();
    protected void sleep() {
        System.out.println("Sleeping...");
    }
    protected void roam() {
        System.out.println("Roaming...");
    }

    public void printInfo() {
        System.out.println("Picture: " + picture);
        System.out.println("Food: " + food);
        System.out.println("Hunger: " + hunger);
        System.out.println("Boundaries: {" + boundaries.width + ", " + boundaries.height + "}");
        System.out.println("Location: {" + location.x + ", " + location.y + "}");
    }

}
