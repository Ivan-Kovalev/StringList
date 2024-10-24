public class IntegerList implements List {

    private Integer[] list;
    private int size = 0;

    public IntegerList(int sizeList) {
        list = new Integer[sizeList];
    }

    @Override
    public Integer add(Integer item) {
        validationInteger(item);

        if (size < list.length) {
            list[size] = item;
        } else {
            Integer[] temp = list;
            list = new Integer[size * 2];
            System.arraycopy(temp, 0, list, 0, temp.length);
            list[size] = item;
        }

        size++;
        return list[size];
    }

    @Override
    public Integer add(int index, Integer item) {
        validationInteger(item);
        validationIndex(index);

        for (int i = size; i > index; i--) {
            list[i + 1] = list[i];
        }

        size++;
        return list[index] = item;
    }

    @Override
    public Integer set(int index, Integer item) {
        validationInteger(item);
        validationIndex(index);

        return list[index] = item;
    }

    @Override
    public Integer remove(Integer item) {
        validationInteger(item);

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
    public Integer remove(int index) {
        validationIndex(index);

        Integer result = list[index];
        list[index] = null;

        for (int i = index; i < size; i++) {
            list[i] = list[i + 1];
            list[i + 1] = null;
        }
        size--;
        return result;
    }

    @Override
    public boolean contains(Integer item) {
        validationInteger(item);
        sort(list, 0, size);

        int min = 0;
        int max = size;

        while (min <= max) {
            int mid = (min + max) / 2;
            if (item.equals(list[mid])) {
                return true;
            }
            if (item < list[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    @Override
    public int indexOf(Integer item) {
        validationInteger(item);

        for (int i = 0; i < size; i++) {
            if (list[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        validationInteger(item);

        for (int i = size; i > 0; i--) {
            if (list[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        validationIndex(index);
        return list[index];
    }

    @Override
    public boolean equals(List otherList) {
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
        this.list = new Integer[list.length];
        size = 0;
    }

    @Override
    public Integer[] toArray() {
        Integer[] result = new Integer[size];
        System.arraycopy(list, 0, result, 0, size);
        return result;
    }

    public void sort(Integer[] array, int low, int high) {
        if (low < high) {
            int partitionIndex = partition(array, low, high);
            sort(array, low, partitionIndex - 1);
            sort(array, partitionIndex + 1, high);
        }
    }

    private int partition(Integer[] array, int low, int high) {
        int pivot = array[high];
        int i = low;

        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1;
    }


    private void validationInteger(Integer item) {
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
