public class StringListImpl implements StringList {

    private String[] list;
    private int size = 0;

    public StringListImpl(int sizeList) {
        list = new String[sizeList];
    }

    @Override
    public String add(String item) {
        validationString(item);

        if (size < list.length) {
            list[size] = item;
        } else {
            String[] temp = list;
            list = new String[size * 2];
            System.arraycopy(temp, 0, list, 0, temp.length);
            list[size] = item;
        }

        size++;
        return item;
    }

    @Override
    public String add(int index, String item) {
        validationString(item);
        validationIndex(index);

        for (int i = size; i > index; i--) {
            list[i + 1] = list[i];
        }

        size++;
        return list[index] = item;
    }

    @Override
    public String set(int index, String item) {
        validationString(item);
        validationIndex(index);

        return list[index] = item;
    }

    @Override
    public String remove(String item) {
        validationString(item);

        boolean found = false;
        for (int i = 0; i < size; i++) {
            if (list[i].equals(item)) {
                list[i] = null;
                found = true;
                break;
            }
        }

        if (!found) {
            throw new NotFoundItemException("No matches found!");
        }

        for (int i = 0; i < size; i++) {
            if (list[i] == null) {
                list[i] = list[i + 1];
                list[i + 1] = null;
            }
        }
        size--;
        return item;
    }

    @Override
    public String remove(int index) {
        validationIndex(index);

        String result = list[index];
        list[index] = null;

        for (int i = index; i < size; i++) {
            list[i] = list[i + 1];
            list[i + 1] = null;
        }
        size--;
        return result;
    }

    @Override
    public boolean contains(String item) {
        validationString(item);

        for (int i = 0; i < size; i++) {
            if (list[i].equals(item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(String item) {
        validationString(item);

        for (int i = 0; i < size; i++) {
            if (list[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        validationString(item);

        for (int i = size; i > 0; i--) {
            if (list[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        validationIndex(index);
        return list[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        if (otherList == null) {
            return false;
        }
        if (this.size() != otherList.size()) {
            return false;
        }
        for (int i = 0; i < this.size(); i++) {
            if (!this.get(i).equals(otherList.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void clear() {
        this.list = new String[list.length];
        size = 0;
    }

    @Override
    public String[] toArray() {
        String[] result = new String[size];
        System.arraycopy(list, 0, result, 0, size);
        return result;
    }

    private void validationString(String item) {
        if (item == null) {
            throw new NullPointerException("Sorry! Invalid data transmitted. Field cannot be empty!");
        }
    }

    private void validationIndex(int index) {
        if (index > size || index < 0) {
            throw new BadIndexException("Sorry! The passed index exceeds the collection size!");
        }
    }
}
