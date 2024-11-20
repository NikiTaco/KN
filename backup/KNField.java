import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class KNField {

    public static void saveField(int[] field, String fileName) throws IOException {
        if (field.length != 9) {
            throw new IllegalArgumentException("Field must contain exactly 9 elements.");
        }

        int packedField = 0;
        for (int i = 0; i < field.length; i++) {
            if (field[i] < 0 || field[i] > 3) {
                throw new IllegalArgumentException("Each cell value must be between 0 and 3.");
            }
            packedField |= (field[i] << (i * 2));
        }

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(fileName))) {
            dos.writeByte((packedField >> 16) & 0xFF);
            dos.writeByte((packedField >> 8) & 0xFF);
            dos.writeByte(packedField & 0xFF);
        }

        System.out.println("Tic-tac-toe field saved successfully.");
    }

    public static void main(String[] args) {
        int[] field = {0, 1, 2, 3, 0, 1, 2, 3, 0};

        try {
            saveField(field, "field.dat");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
